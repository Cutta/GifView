package cuneyt.example.com.gifview.Activities;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.cunoraz.gifview.library.GifView;

import cuneyt.example.com.gifview.R;

/**
 * Created by Cuneyt on 14.10.2015.
 */
public class ListViewActivity extends ListActivity {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = (ListView) findViewById(android.R.id.list);
        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.city_names));
        listView.setAdapter(itemsAdapter);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
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
        }, 3000);//3 seconds
    }

}