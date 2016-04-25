package cuneyt.example.com.gifview.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.cunoraz.gifview.library.GifView;

import cuneyt.example.com.gifview.Fragments.GifFragment;
import cuneyt.example.com.gifview.R;

public class MainActivity extends AppCompatActivity {
    private Button loadingBtn1;
    private Button loadingBtn2;
    private Button loadingBtn3;
    private Button loadingBtn4;
    private Button deletingBtn;
    private Button toast;
    private Button fragment;
    private Button activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        activity = (Button) findViewById(R.id.activity);
        activity.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();
            }
        });
        fragment = (Button) findViewById(R.id.fragment);
        fragment.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment();
            }
        });
        toast = (Button) findViewById(R.id.toast);
        toast.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast();

            }
        });

        loadingBtn1 = (Button) findViewById(R.id.loading_btn1);
        loadingBtn1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                openLoadingDialog(1);
            }
        });
        loadingBtn2 = (Button) findViewById(R.id.loading_btn2);
        loadingBtn2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                openLoadingDialog(2);
            }
        });
        loadingBtn3 = (Button) findViewById(R.id.loading_btn3);
        loadingBtn3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                openLoadingDialog(3);
            }
        });
        loadingBtn4 = (Button) findViewById(R.id.loading_btn4);
        loadingBtn4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                openLoadingDialog(4);
            }
        });

        deletingBtn = (Button) findViewById(R.id.deleting_btn);
        deletingBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                openDeletingDialog();
            }
        });


    }

    public void openActivity() {
        Intent i = new Intent(MainActivity.this, ListViewActivity.class);
        startActivity(i);
    }

    public void openFragment() {
        FragmentTransaction trans = getSupportFragmentManager()
                .beginTransaction();
        GifFragment fragmentLocal = new GifFragment().newInstance();
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

    public void openLoadingDialog(int index) {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_loading, null);
        GifView gifView1 = (GifView) view.findViewById(R.id.gif1);
        GifView gifView2 = (GifView) view.findViewById(R.id.gif2);
        GifView gifView3 = (GifView) view.findViewById(R.id.gif3);
        GifView gifView4 = (GifView) view.findViewById(R.id.gif4);
        switch (index) {
            case 1:
                gifView1.setVisibility(View.VISIBLE);
            /*    gifView1.setPaused(true);
                gifView1.setMovie();
                gifView1.setMovieResource();
                gifView1.setMovieTime();
                gifView1.getMovie()*/
                break;
            case 2:
                gifView2.setVisibility(View.VISIBLE);
                break;
            case 3:
                gifView3.setVisibility(View.VISIBLE);
                break;
            case 4:
                gifView4.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }

        MaterialDialog dialog = new MaterialDialog.Builder(MainActivity.this).customView(view, true).build();
        dialog.show();


    }

    public void openDeletingDialog() {
        MaterialDialog dialog = new MaterialDialog.Builder(MainActivity.this).customView(R.layout.dialog_deleting, true).build();
        dialog.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
