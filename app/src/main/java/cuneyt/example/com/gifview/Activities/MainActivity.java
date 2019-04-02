package cuneyt.example.com.gifview.Activities;

import android.os.Bundle;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.cunoraz.gifview.library.GifView;

import cuneyt.example.com.gifview.Fragments.GifFragment;
import cuneyt.example.com.gifview.R;

public class MainActivity extends AppCompatActivity {
    private Button pauseButton;
    private Button playButton;
    private Button nextButton;
    private Button dialogButton;
    private Button toastButton;
    private Button fragmentButton;
    private GifView gifView;
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        gifView = findViewById(R.id.gif1);
        pauseButton = findViewById(R.id.button_gif_pause);
        playButton = findViewById(R.id.button_gif_play);
        nextButton = findViewById(R.id.button_gif_next);
        dialogButton = findViewById(R.id.button_dialog);
        fragmentButton = findViewById(R.id.fragment);
        toastButton = findViewById(R.id.toast);


        fragmentButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment();
            }
        });

        toastButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast();

            }
        });

        pauseButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (gifView.isPlaying())
                    gifView.pause();
            }
        });
        playButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (gifView.isPaused())
                    gifView.play();
            }
        });
        nextButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               switch (index % 10){
                   case 0:
                       gifView.setGifResource(R.mipmap.wine);
                       break;
                   case 1:
                       gifView.setGifResource(R.mipmap.gif1);
                       break;
                   case 2:
                       gifView.setGifResource(R.mipmap.gif2);
                       break;
                   case 3:
                       gifView.setGifResource(R.mipmap.gif3);
                       break;
                   case 4:
                       gifView.setGifResource(R.mipmap.gif4);
                       break;
                   case 5:
                       gifView.setGifResource(R.mipmap.gif5);
                       break;
                   case 6:
                       gifView.setGifResource(R.mipmap.gif6);
                       break;
                   case 7:
                       gifView.setGifResource(R.mipmap.gif7);
                       break;
                   case 8:
                       gifView.setGifResource(R.mipmap.gif8);
                       break;
                   case 9:
                       gifView.setGifResource(R.mipmap.gif9);
                       break;
               }
                index++;
            }
        });

        dialogButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }


    public void openFragment() {
        FragmentTransaction trans = getSupportFragmentManager()
                .beginTransaction();
        GifFragment fragmentLocal =  GifFragment.newInstance();
        fragmentLocal.setHasOptionsMenu(true);
        trans.replace(R.id.frame, fragmentLocal, fragmentLocal.getTAG());
        trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        trans.addToBackStack(fragmentLocal.getTAG());
        trans.commitAllowingStateLoss();
    }

    public void showToast() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, null);
        Toast toastLocal = new Toast(getApplicationContext());
        toastLocal.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toastLocal.setDuration(Toast.LENGTH_LONG);
        toastLocal.setView(layout);
        toastLocal.show();
    }

    public void openDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
        builder.setView(getLayoutInflater().inflate(R.layout.dialog_deleting,null));
        builder.show();

    }

}
