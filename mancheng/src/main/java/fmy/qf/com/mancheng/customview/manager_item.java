package fmy.qf.com.mancheng.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import fmy.qf.com.mancheng.R;

/**
 * Created by Administrator on 2016/10/12.
 */
public class manager_item extends LinearLayout {

    private View view;
    private TextView myText;
    private View line;

    public manager_item(Context context) {
        super(context);
        init();
    }

    public manager_item(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.manager_item);
        String string = typedArray.getString(R.styleable.manager_item_myText);
        myText.setText(string);
        boolean flag = typedArray.getBoolean(R.styleable.manager_item_drawUnderLine,true);
        if (!flag){
            line.setVisibility(View.GONE);
        }
    }

    private void init() {
        view = LayoutInflater.from(getContext()).inflate(R.layout.manager_eachitem,this,true);
        myText = ((TextView) view.findViewById(R.id.tv));
        line = ((View) view.findViewById(R.id.line));
    }





}
