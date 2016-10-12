package fmy.qf.com.mancheng.adpter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import fmy.qf.com.mancheng.R;
import fmy.qf.com.mancheng.bean.News_TT_Bean;

/**
 * Created by Administrator on 2016/10/12.
 */
public class News_TT extends BaseAdapter {

    //存放所有的数据
    private List<News_TT_Bean> allData;
    Context myContext;

    public News_TT(Context myContext, List<News_TT_Bean> allData) {
        this.myContext = myContext;
        this.allData = allData;
    }

    public void setData(List<News_TT_Bean> allData){
        this.allData=allData;
        notifyDataSetChanged();
    }

    public void addData(List<News_TT_Bean> allData){
        if (this.allData == null) {
            this.allData = new ArrayList<>();
        }
        if (allData != null) {
            this.allData.addAll(allData);
        }
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return allData==null?0:allData.size();
    }

    class ViewHold {
        TextView title;
        TextView type;
        TextView newsAuthor;
        ImageView ivShow;
    }
    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHold hold ;
        if (convertView == null) {
            convertView =LayoutInflater.from(myContext).inflate(R.layout.news_tt_item,parent,false);
            hold = new ViewHold();
            hold.title= ((TextView) convertView.findViewById(R.id.title));
            hold.ivShow = ((ImageView) convertView.findViewById(R.id.iv_show));
            hold.newsAuthor = ((TextView) convertView.findViewById(R.id.newsauthor));
            hold.type = ((TextView) convertView.findViewById(R.id.type));
            convertView.setTag(hold);
        }else {
            hold = (ViewHold) convertView.getTag();
        }
        News_TT_Bean bean = allData.get(position);

        hold.title.setText(bean.getTitle());
        hold.type.setText(bean.getNewstype_content());
        hold.newsAuthor.setText(bean.getNewsauthor_content());
        if (!TextUtils.isEmpty(bean.getL_picture())){
            Picasso.with(myContext).load(bean.getL_picture()).into(hold.ivShow);
        }
        return convertView;
    }
}
