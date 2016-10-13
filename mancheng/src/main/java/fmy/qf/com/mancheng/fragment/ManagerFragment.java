package fmy.qf.com.mancheng.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import fmy.qf.com.mancheng.R;
import fmy.qf.com.mancheng.activity.LocationActivity;
import fmy.qf.com.mancheng.animation.Constant;
import fmy.qf.com.mancheng.animation.SwitchAnimationUtil;


public class ManagerFragment extends Fragment {


    private View rootView;
    private View viewShow3;
    private View viewShow3Location;

    public ManagerFragment() {
        // Required empty public constructor
    }

    Activity myActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myActivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_manager, container, false);
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Constant.mType = SwitchAnimationUtil.AnimationType.FLIP_VERTICAL;
                new SwitchAnimationUtil().startAnimation(rootView, Constant.mType );
            }
        });
        viewShow3 = rootView.findViewById(R.id.include_sho3);
        viewShow3Location = viewShow3.findViewById(R.id.location);
        initLocation();
        return rootView;
    }
    public  void initLocation(){

        viewShow3Location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myActivity.startActivity(new Intent(myActivity, LocationActivity.class));
            }
        });
    }

}
