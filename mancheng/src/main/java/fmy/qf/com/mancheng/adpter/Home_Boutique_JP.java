package fmy.qf.com.mancheng.adpter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import fmy.qf.com.mancheng.R;
import fmy.qf.com.mancheng.activity.CharpterActivity;
import fmy.qf.com.mancheng.bean.Home_BoutiqueBean;

/**
 * Created by Administrator on 2016/10/10.
 */
public class Home_Boutique_JP extends BaseAdapter {

    Context context;
    List<?extends Home_BoutiqueBean.BaseBean> beanSet;


    public Home_Boutique_JP() {
    }

    public Home_Boutique_JP(Context context, List<? extends Home_BoutiqueBean.BaseBean> beanSet) {
        this.context = context;
        this.beanSet = beanSet;
    }

    public void setData(List<?extends Home_BoutiqueBean.BaseBean> beanSet){
        this.beanSet =beanSet;
    }

    @Override
    public int getCount() {
        return beanSet==null?0:beanSet.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolod hold ;
        final Home_BoutiqueBean.BaseBean baseBean = beanSet.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.home_jp_gridview_item,parent,false);
            hold = new ViewHolod();
            hold.ivShow=((ImageView) convertView.findViewById(R.id.iv_show));
            hold.tv_js = ((TextView) convertView.findViewById(R.id.tv_js));
            convertView.setTag(hold);
            hold.ivShow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id = baseBean.getId();
                    if (!TextUtils.isEmpty(id)){
                        Intent intent = new Intent(context, CharpterActivity.class);
                        intent.putExtra("id",Integer.parseInt(id));
                        context.startActivity(intent);
                    }
                }
            });
        }else{
            hold = (ViewHolod) convertView.getTag();
        }

        String icon = baseBean.getIcon();
   
        Picasso.with(context).load(icon).into(hold.ivShow);

        hold.tv_js.setText(baseBean.getName());
        return convertView;
    }

    class ViewHolod {
        ImageView ivShow;
        TextView tv_js;
    }
}
