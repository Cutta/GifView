package cuneyt.example.com.gifview.Activities;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import cuneyt.example.com.gifview.R;
import cuneyt.example.com.gifview.Utils.GifView;

/**
 * Created by Cuneyt on 14.10.2015.
 */
public class ListViewActivity extends ListActivity {
    SwipeRefreshLayout mSwipeRefreshLayout;
    ListView listView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = (ListView)findViewById(android.R.id.list);
        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.city_names));
        listView.setAdapter(itemsAdapter);

        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();
            }
        });

        refreshContent();
        Toast.makeText(ListViewActivity.this, "Swipe To Refresh", Toast.LENGTH_LONG).show();
    }
    private void refreshContent() {//fake
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_loading, null);
        GifView gifView1 = (GifView) view.findViewById(R.id.gif1);
        gifView1.setVisibility(View.VISIBLE);
        final MaterialDialog dialog = new MaterialDialog.Builder(ListViewActivity.this).customView(view, true).build();
        dialog.show();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
                dialog.dismiss();
            }
        }, 1000);
    }

}