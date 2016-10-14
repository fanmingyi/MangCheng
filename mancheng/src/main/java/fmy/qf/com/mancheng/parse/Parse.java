package fmy.qf.com.mancheng.parse;

import com.alibaba.fastjson.JSON;

import java.util.List;

import fmy.qf.com.mancheng.bean.Home_BoutiqueBean;
import fmy.qf.com.mancheng.bean.Home_RandingBean;
import fmy.qf.com.mancheng.bean.News_TT_Bean;
import fmy.qf.com.mancheng.bean.Search_AllSeekBean;
import fmy.qf.com.mancheng.bean.Search_TagBean;
import fmy.qf.com.mancheng.bean.CharpterBean;

/**
 * Created by Administrator on 2016/10/9.
 */
public class Parse {
    /**
     * 解析第一个界面的精品界面bean
     * @param json
     * @return Home_BoutiqueBean对象
     */
    public static Home_BoutiqueBean parseHomeBoutique(String json) throws Exception{
        Home_BoutiqueBean home_boutiqueBean = JSON.parseObject(json, Home_BoutiqueBean.class);
        return home_boutiqueBean;
    }


    /**
     * 解析第二个界面的精品界面bean
     * @param json
     * @return Home_RandingBean对象集合
     */
    public static  List<Home_RandingBean> parseHomeRanking(String json) throws Exception{
        List<Home_RandingBean> home_randingBeen = JSON.parseArray(json, Home_RandingBean.class);
        return home_randingBeen;
    }

    /**
     * 解析搜索界面的大家都在看
     */
    public static  List<Search_AllSeekBean> parseSearchAllSeek(String json) throws Exception{
        List<Search_AllSeekBean> home_randingBeen = JSON.parseArray(json, Search_AllSeekBean.class);
        return home_randingBeen;
    }

    /**
     * 解析搜索界面的热门标签
     */
    public static  List<Search_TagBean> parseSearchHotTag(String json) throws Exception{
        List<Search_TagBean> home_randingBeen = JSON.parseArray(json, Search_TagBean.class);
        return home_randingBeen;
    }

    /**
     * 解析资讯界面的头条
     */
    public static  List<News_TT_Bean> parseNewsTT(String json) throws Exception{
        List<News_TT_Bean> home_randingBeen = JSON.parseArray(json, News_TT_Bean.class);
        return home_randingBeen;
    }


    /**
     * 解析漫画章节
     */
    public static CharpterBean parseChapterBean(String json) throws Exception{
       return JSON.parseObject(json,CharpterBean.class);
    }
}
