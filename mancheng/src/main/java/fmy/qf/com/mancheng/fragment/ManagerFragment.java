package fmy.qf.com.mancheng.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import fmy.qf.com.mancheng.R;
import fmy.qf.com.mancheng.animation.Constant;
import fmy.qf.com.mancheng.animation.SwitchAnimationUtil;


public class ManagerFragment extends Fragment {


    private View rootView;

    public ManagerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_manager, container, false);
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Constant.mType = SwitchAnimationUtil.AnimationType.FLIP_VERTICAL;
                new SwitchAnimationUtil().startAnimation(rootView, Constant.mType );
            }
        });

        return rootView;
    }

}
