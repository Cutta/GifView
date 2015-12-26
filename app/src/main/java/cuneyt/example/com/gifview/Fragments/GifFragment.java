package cuneyt.example.com.gifview.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cuneyt.example.com.gifview.R;

/**
 * Created by Cuneyt on 14.10.2015.
 */
public class GifFragment extends Fragment {
    private String TAG;
    LayoutInflater inflater;
    private static int InstanceCount = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        this.inflater = inflater;
        View view = inflater.inflate(R.layout.fragment_layout, container, false);
        return view;
    }

    public static GifFragment newInstance() {
        GifFragment fragment = new GifFragment();
        fragment.setTAG((GifFragment.InstanceCount++) + "");
        return fragment;
    }

    public String getTAG() {
        return TAG;
    }

    public void setTAG(String TAG) {
        this.TAG = TAG;
    }
}
