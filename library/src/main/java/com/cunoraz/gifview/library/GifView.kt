package com.cunoraz.gifview.library

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Movie
import android.os.Build
import android.util.AttributeSet
import android.view.View
import java.io.FileInputStream
import java.io.FileNotFoundException

/**
 * Created by Cuneyt on 4.10.2015.
 * Gifview
 */
class GifView(var contxt: Context, attrs: AttributeSet, defStyle: Int) : View(contxt, attrs, defStyle) {

    private var mMovieResourceId: Int = 0
    private var movie: Movie? = null

    private var mMovieStart: Long = 0
    private var mCurrentAnimationTime: Int = 0


    /**
     * Position for drawing animation frames in the center of the view.
     */
    private var mLeft: Float = 0.toFloat()
    private var mTop: Float = 0.toFloat()

    /**
     * Scaling factor to fit the animation within view bounds.
     */
    private var mScale: Float = 0.toFloat()

    /**
     * Scaled movie frames width and height.
     */
    private var mMeasuredMovieWidth: Int = 0
    private var mMeasuredMovieHeight: Int = 0

    @Volatile var isPaused: Boolean = false
    private var mVisible = true

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs!!, R.styleable.CustomTheme_gifViewStyle)

    init {
        setViewAttributes(context, attrs, defStyle)
    }

    @SuppressLint("NewApi")
    private fun setViewAttributes(context: Context, attrs: AttributeSet, defStyle: Int) {

        /**
         * Starting from HONEYCOMB(Api Level:11) have to turn off HW acceleration to draw
         * Movie on Canvas.
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        }

        val array = context.obtainStyledAttributes(attrs,
                R.styleable.GifView, defStyle, R.style.Widget_GifView)

        //-1 is default value
        mMovieResourceId = array.getResourceId(R.styleable.GifView_gif, -1)
        isPaused = array.getBoolean(R.styleable.GifView_paused, false)

        array.recycle()

        if (mMovieResourceId != -1) {
            movie = Movie.decodeStream(resources.openRawResource(mMovieResourceId))
        }
    }

    @Throws(FileNotFoundException::class)
    fun setGifPath(path: String) {
        movie = Movie.decodeStream(FileInputStream(path))
        requestLayout()
    }

    var gifResource: Int
        get() = this.mMovieResourceId
        set(movieResourceId) {
            this.mMovieResourceId = movieResourceId
            movie = Movie.decodeStream(resources.openRawResource(mMovieResourceId))
            requestLayout()
        }


    fun play() {
        if (this.isPaused) {
            this.isPaused = false

            /**
             * Calculate new movie start time, so that it resumes from the same
             * frame.
             */
            mMovieStart = android.os.SystemClock.uptimeMillis() - mCurrentAnimationTime

            invalidate()
        }
    }

    fun pause() {
        if (!this.isPaused) {
            this.isPaused = true

            invalidate()
        }

    }

    val isPlaying: Boolean
        get() = !this.isPaused

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        if (movie != null) {
            val movieWidth = movie!!.width()
            val movieHeight = movie!!.height()

            /*
             * Calculate horizontal scaling
			 */
            var scaleH = 1f
            val measureModeWidth = View.MeasureSpec.getMode(widthMeasureSpec)

            if (measureModeWidth != View.MeasureSpec.UNSPECIFIED) {
                val maximumWidth = View.MeasureSpec.getSize(widthMeasureSpec)
                if (movieWidth > maximumWidth) {
                    scaleH = movieWidth.toFloat() / maximumWidth.toFloat()
                }
            }

            /*
             * calculate vertical scaling
			 */
            var scaleW = 1f
            val measureModeHeight = View.MeasureSpec.getMode(heightMeasureSpec)

            if (measureModeHeight != View.MeasureSpec.UNSPECIFIED) {
                val maximumHeight = View.MeasureSpec.getSize(heightMeasureSpec)
                if (movieHeight > maximumHeight) {
                    scaleW = movieHeight.toFloat() / maximumHeight.toFloat()
                }
            }

            /*
             * calculate overall scale
			 */
            mScale = 1f / Math.max(scaleH, scaleW)

            mMeasuredMovieWidth = (movieWidth * mScale).toInt()
            mMeasuredMovieHeight = (movieHeight * mScale).toInt()

            setMeasuredDimension(mMeasuredMovieWidth, mMeasuredMovieHeight)

        } else {
            /*
             * No movie set, just set minimum available size.
			 */
            setMeasuredDimension(suggestedMinimumWidth, suggestedMinimumHeight)
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        /*
		 * Calculate mLeft / mTop for drawing in center
		 */
        mLeft = (width - mMeasuredMovieWidth) / 2f
        mTop = (height - mMeasuredMovieHeight) / 2f

        mVisible = visibility == View.VISIBLE
    }

    override fun onDraw(canvas: Canvas) {
        if (movie != null) {
            if (!isPaused) {
                updateAnimationTime()
                drawMovieFrame(canvas)
                invalidateView()
            } else {
                drawMovieFrame(canvas)
            }
        }
    }

    /**
     * Invalidates view only if it is mVisible.
     * <br></br>
     * [.postInvalidateOnAnimation] is used for Jelly Bean and higher.
     */
    @SuppressLint("NewApi")
    private fun invalidateView() {
        if (mVisible) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                postInvalidateOnAnimation()
            } else {
                invalidate()
            }
        }
    }

    /**
     * Calculate current animation time
     */
    private fun updateAnimationTime() {
        val now = android.os.SystemClock.uptimeMillis()

        if (mMovieStart == 0L) {
            mMovieStart = now
        }

        var dur = movie!!.duration()

        if (dur == 0) {
            dur = DEFAULT_MOVIE_VIEW_DURATION
        }

        mCurrentAnimationTime = ((now - mMovieStart) % dur).toInt()
    }

    /**
     * Draw current GIF frame
     */
    private fun drawMovieFrame(canvas: Canvas) {

        movie!!.setTime(mCurrentAnimationTime)

        canvas.save(Canvas.MATRIX_SAVE_FLAG)
        canvas.scale(mScale, mScale)
        movie!!.draw(canvas, mLeft / mScale, mTop / mScale)
        canvas.restore()
    }

    @SuppressLint("NewApi")
    override fun onScreenStateChanged(screenState: Int) {
        super.onScreenStateChanged(screenState)
        mVisible = screenState == View.SCREEN_STATE_ON
        invalidateView()
    }

    @SuppressLint("NewApi")
    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
        mVisible = visibility == View.VISIBLE
        invalidateView()
    }

    override fun onWindowVisibilityChanged(visibility: Int) {
        super.onWindowVisibilityChanged(visibility)
        mVisible = visibility == View.VISIBLE
        invalidateView()
    }

    companion object {

        private val DEFAULT_MOVIE_VIEW_DURATION = 1000
    }
}