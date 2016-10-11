package fmy.qf.com.mancheng.adpter;

import android.content.Context;
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
import fmy.qf.com.mancheng.bean.Home_RandingBean;

/**
 * Created by Administrator on 2016/10/10.
 */
public class Home_Ranking extends BaseAdapter {

    Context context;
    private List<Home_RandingBean> data;

    public Home_Ranking(Context context, List<Home_RandingBean> data) {
        this.context = context;
        this.data = data;
    }

    public void setData(List<Home_RandingBean> data) {
        this.data = new ArrayList<Home_RandingBean>(data);
    }



    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
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
        ImageView iv_show;
        TextView tv_title;
        TextView tv_ranking;
        TextView tv_state;
        TextView tv_categry;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHold hold;
        Home_RandingBean bean = data.get(position);
        if (convertView == null) {
            hold = new ViewHold();
            convertView = LayoutInflater.from(context).inflate(R.layout.home_ph_item, parent, false);
            convertView.setTag(hold);
            hold.iv_show = ((ImageView) convertView.findViewById(R.id.iv_show));
            hold.tv_categry = (TextView) convertView.findViewById(R.id.tv_categry);
            hold.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            hold.tv_state = (TextView) convertView.findViewById(R.id.tv_state);
            hold.tv_ranking = (TextView) convertView.findViewById(R.id.tv_ranking);
        } else {
            hold = (ViewHold) convertView.getTag();
        }


        hold.tv_title.setText(bean.getName());
        hold.tv_ranking.setText("人气:"+bean.getRanking());
        hold.tv_state.setText("状态:"+bean.getState());
        hold.tv_categry.setText("类别"+bean.getTheme());
        Picasso.with(context).load(bean.getIcon()).into(hold.iv_show);

        return convertView;
    }
}
