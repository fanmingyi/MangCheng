package fmy.qf.com.mancheng.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fmy.qf.com.mancheng.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class News_tp_Fragment extends Fragment {


    public News_tp_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_tp_, container, false);
    }

}
