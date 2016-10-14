package fmy.qf.com.mancheng.adpter;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import fmy.qf.com.mancheng.R;
import fmy.qf.com.mancheng.bean.CharpterBean;

/**
 * Created by Administrator on 2016/10/14.
 */

public class Charpter extends BaseAdapter {
    Activity activity;
    //数据bean集合
    CharpterBean bean;
    private List<CharpterBean.ChapterBean> chapter;

    public Charpter(Activity activity, CharpterBean bean) {
        this.activity = activity;
        this.bean = bean;
        if (bean != null) {
            chapter = bean.getChapter();
        }
    }
    public void setdata(CharpterBean bean) {
        this.bean = bean;
        if (bean != null) {
            chapter = bean.getChapter();
        }
    }
    @Override
    public int getCount() {
        return chapter == null ? 0 : chapter.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHold {
        TextView tv;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHold hold;
        if (convertView == null) {
            convertView = LayoutInflater.from(activity).inflate(R.layout.chapter_item, parent, false);
            hold = new ViewHold();
            hold.tv = (TextView) convertView.findViewById(R.id.tv);
            convertView.setTag(hold);
        } else {
            hold = (ViewHold) convertView.getTag();
        }
        hold.tv.setText(chapter.get(position).getTitle());
        //如果是奇数
        if ( (position & 1)== 0) {
            hold.tv.setBackgroundColor(Color.argb(0x66,0x00,0x00,0x00));
        } else {
            hold.tv.setBackgroundColor(Color.WHITE);
        }
        return convertView;
    }
}
