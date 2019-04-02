package com.cunoraz.gifview.library

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Movie
import android.os.Build
import android.util.AttributeSet
import android.view.View

/**
 * Created by Cuneyt on 4.10.2015.
 * Updated by Cuneyt on 02.04.2019.
 * Gifview
 */
class GifView @JvmOverloads
constructor(context: Context,
            attrs: AttributeSet? = null,
            defStyle: Int = R.styleable.CustomTheme_gifViewStyle)
    : View(context, attrs, defStyle) {

    private var movieMovieResourceId: Int = 0
    private var movie: Movie? = null
    private var movieStart: Long = 0
    private var currentAnimationTime: Int = 0

    private var movieLeft: Float = 0F
    private var movieTop: Float = 0F
    private var movieScale: Float = 0F

    private var movieMeasuredMovieWidth: Int = 0
    private var movieMeasuredMovieHeight: Int = 0

    @Volatile
    var isPaused: Boolean = false

    private var isVisible = true

    var gifResource: Int
        get() = this.movieMovieResourceId
        set(movieResourceId) {
            this.movieMovieResourceId = movieResourceId
            movie = Movie.decodeStream(resources.openRawResource(movieMovieResourceId))
            requestLayout()
        }

    val isPlaying: Boolean
        get() = !this.isPaused

    init {
        setViewAttributes(context, attrs, defStyle)
    }

    @SuppressLint("NewApi")
    private fun setViewAttributes(context: Context, attrs: AttributeSet?, defStyle: Int) {
        setLayerType(View.LAYER_TYPE_SOFTWARE, null)

        val array = context.obtainStyledAttributes(attrs,
                R.styleable.GifView, defStyle, R.style.Widget_GifView)

        //-1 is default value
        movieMovieResourceId = array.getResourceId(R.styleable.GifView_gif, -1)
        isPaused = array.getBoolean(R.styleable.GifView_paused, false)

        array.recycle()

        if (movieMovieResourceId != -1) {
            movie = Movie.decodeStream(resources.openRawResource(movieMovieResourceId))
        }
    }


    fun play() {
        if (this.isPaused) {
            this.isPaused = false
            /**
             * Calculate new movie start time, so that it resumes from the same
             * frame.
             */
            movieStart = android.os.SystemClock.uptimeMillis() - currentAnimationTime
            invalidate()
        }
    }

    fun pause() {
        if (!this.isPaused) {
            this.isPaused = true
            invalidate()
        }
    }

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
            movieScale = 1f / Math.max(scaleH, scaleW)
            movieMeasuredMovieWidth = (movieWidth * movieScale).toInt()
            movieMeasuredMovieHeight = (movieHeight * movieScale).toInt()
            setMeasuredDimension(movieMeasuredMovieWidth, movieMeasuredMovieHeight)
        } else {
            /*
			 * No movie set, just set minimum available size.
			 */
            setMeasuredDimension(suggestedMinimumWidth, suggestedMinimumHeight)
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        /*
         * Calculate movieLeft / movieTop for drawing in center
         */
        movieLeft = (width - movieMeasuredMovieWidth) / 2f
        movieTop = (height - movieMeasuredMovieHeight) / 2f
        isVisible = visibility == View.VISIBLE
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
     * Invalidates view only if it is isVisible.
     * <br></br>
     * [.postInvalidateOnAnimation] is used for Jelly Bean and higher.
     */
    @SuppressLint("NewApi")
    private fun invalidateView() {
        if (isVisible) {
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

        if (movieStart == 0L) {
            movieStart = now
        }

        var duration = movie!!.duration()

        if (duration == 0) {
            duration = DEFAULT_MOVIE_VIEW_DURATION
        }

        currentAnimationTime = ((now - movieStart) % duration).toInt()
    }

    /**
     * Draw current GIF frame
     */
    private fun drawMovieFrame(canvas: Canvas) {
        movie!!.setTime(currentAnimationTime)
        canvas.save()
        canvas.scale(movieScale, movieScale)
        movie!!.draw(canvas, movieLeft / movieScale, movieTop / movieScale)
        canvas.restore()
    }

    @SuppressLint("NewApi")
    override fun onScreenStateChanged(screenState: Int) {
        super.onScreenStateChanged(screenState)
        isVisible = screenState == View.SCREEN_STATE_ON
        invalidateView()
    }

    @SuppressLint("NewApi")
    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
        isVisible = visibility == View.VISIBLE
        invalidateView()
    }

    override fun onWindowVisibilityChanged(visibility: Int) {
        super.onWindowVisibilityChanged(visibility)
        isVisible = visibility == View.VISIBLE
        invalidateView()
    }

    companion object {

        private const val DEFAULT_MOVIE_VIEW_DURATION = 1000
    }
}