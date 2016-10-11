package fmy.qf.com.mancheng.customview;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2016/10/10.
 */
public class MygifView extends pl.droidsonroids.gif.GifImageView {


    private boolean keepPosition;

    public MygifView(Context context) {
        super(context);
    }

    public MygifView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MygifView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void layout(int l, int t, int r, int b) {
        if (!keepPosition) {
            super.layout(l, t, r, b);
            keepPosition=true;
        }
    }

    public void moveTiger(int l, int t, int r, int b) {
        super.layout(l, t, r, b);
    }

    public void startKeepPosition() {
        keepPosition = true;
    }
}
