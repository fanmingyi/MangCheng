package fmy.qf.com.mancheng.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import fmy.qf.com.mancheng.R;
import fmy.qf.com.mancheng.animation.Constant;
import fmy.qf.com.mancheng.animation.SwitchAnimationUtil;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolod hold ;
        Home_BoutiqueBean.BaseBean baseBean = beanSet.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.home_jp_gridview_item,parent,false);
            hold = new ViewHolod();
            hold.ivShow=((ImageView) convertView.findViewById(R.id.iv_show));
            hold.tv_js = ((TextView) convertView.findViewById(R.id.tv_js));
            convertView.setTag(hold);

        }else{
            hold = (ViewHolod) convertView.getTag();
        }

        String icon = baseBean.getIcon();
        hold.ivShow.setTag(icon);
        Picasso.with(context).load(icon).into(hold.ivShow);
        hold.tv_js.setText(baseBean.getName());
        return convertView;
    }

    class ViewHolod {
        ImageView ivShow;
        TextView tv_js;
    }
}
