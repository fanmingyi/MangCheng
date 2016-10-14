package fmy.qf.com.mancheng.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2016/7/18 0018.
 */
public class Url {

    //资讯-头条页面的接口
    public static final String game_headline_path = "http://a121.baopiqi.com/api/mh/getConsultation.php?&appname=%E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7&page=0&limit=20";

    //资讯-头条页面分页加载的接口
    public static String getHeadPagingPath(int index) {
        String path = "http://a121.baopiqi.com/api/mh/getConsultation.php?&appname=%E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7&page=" + index + "&limit=20";
        return path;
    }

    //资讯-头条页面跳转页面的接口
    public static String getHeadDetailsPath(String id) {
        String path = "http://manhua007.com/index.php/Index/zxdetail1/id/" + id + ".html";
        return path;
    }

    //资讯-动漫页面的接口
    public static final String game_cartoon_path = "http://a121.baopiqi.com/api/mh/getVideoClassification.php?&appname=%E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7&page=0&limit=12";

    //资讯-动漫页面分页加载的接口
    public static String getCarttonPagingPath(int index) {
        String path = "http://a121.baopiqi.com/api/mh/getVideoClassification.php?&appname=%E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7&page=" + index + "&limit=12";
        return path;
    }

    //资讯-动漫页面跳转页面的接口
    public static String getCarttonDetailsPath(String id) {
        String path = "http://a121.baopiqi.com/api/mh/getVideo.php?&appname=%E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7&id=" + id + "&page=0&limit=20";
        return path;
    }

    //资讯-图片页面的接口
    public static final String game_picture_path = "http://a121.baopiqi.com/api/mh/getCartoonWallpaper.php?id=1&appname=%E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7&page=0&limit=12";

    //资讯-图片页面分页加载的接口
    public static String getPicturePagingPath(int index) {
        String path = "http://a121.baopiqi.com/api/mh/getCartoonWallpaper.php?id=1&appname=%E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7&page=" + index + "&limit=12";
        return path;
    }

    //资讯-段子页面的接口
    public static final String game_joke_path = "http://a121.baopiqi.com/api/mh/getJokesAll.php?&appname=%E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7&page=0&limit=20";

    //资讯-段子页面分页加载的接口
    public static String getJokePadingPaht(int index) {
        String path = "http://a121.baopiqi.com/api/mh/getJokesAll.php?&appname=%E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7&page=" + index + "&limit=20";
        return path;
    }


//第一页的分类

//第二页的搜索
//http://a121.baopiqi.com/api/mh/getSearchCartoon.php?&appname=%E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7&limit=20&page=1&key=%E4%BC%A0%E8%AF%B4


    //http://a121.baopiqi.com/api/mh/getSearchCartoon.php&appname=E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7&limit=20&page=1&key=爱情
    //**当成搜索都在看
    public static String getSeekSearch(int page, int limit) {

        return "http://a121.baopiqi.com/api/mh/getSearchCartoon.php?&appname=%E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7&limit=" + limit + "&page=" + page + "&key=%E4%BC%A0%E8%AF%B4";
    }

    //当初搜索搜索结果
    public static String getSearchResult(int page, int limit, String key) {
        try {
            key = URLEncoder.encode(key, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "http://a121.baopiqi.com/api/mh/getSearchCartoon.php?&appname=%E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7&limit=" + limit + "&page=" + page + "&key=" + key;
    }
//详情页  作者的连接

    //http://a121.baopiqi.com/api/mh/getSearchAuthor.php?&appname=%E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7&limit=20&page=1&key=%E5%87%89%E8%97%A4
    //第一个界面的精品界面
    public static final String BOUTIQUE_URL = "http://a121.baopiqi.com/api/mh/getCartoonHomePageAll.php?&appname=%E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7";
   //第二个界面热门搜索关键字
    public static final String SOFT_GRIDVIEW = "http://a121.baopiqi.com/api/mh/getSearch.php?&appname=%E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7";
    //第一个界面的排行界面
    public static final String SOFT_CHANGEVIEW = "http://a121.baopiqi.com/api/mh/getBoutique.php?&appname=%E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7&page=0&limit=100000000";

    //第一个界面的排行界面

    /**
     * @param page
     * @param limit
     * @return 网址
     */
    public static String getSoftChangeview(int page, int limit) {
        String mypath = "http://a121.baopiqi.com/api/mh/getBoutique.php?&appname=%E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7&page=" + page + "&limit=" + limit;
        return mypath;
    }

    public static final int PAGE_SIZE = 20;

    public static String getRecommendMore(int pageNum, int type) {
        String url = null;
        switch (type) {
            case 1:
                //推荐
                url = "http://a121.baopiqi.com/api/mh/getBoutique.php";
//                url = "http://a121.baopiqi.com/api/mh/getBoutique.php?&appname=%E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7&page=" + pageNum + "&limit=20";
                break;
            case 2:
                //热门
                url = "http://a121.baopiqi.com/api/mh/getCartoonRankingMore.php";
//                url = "http://a121.baopiqi.com/api/mh/getCartoonRankingMore.php?type=1&appname=%E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7&page=" + pageNum + "&limit=20";
                break;
            case 3:
                //最新
                url = "http://a121.baopiqi.com/api/mh/getCartoonNew.php";
//                url = "http://a121.baopiqi.com/api/mh/getCartoonNew.php?&appname=%E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7&page=" + pageNum + "&limit=20";
                break;
            case 4:
                url = "http://a121.baopiqi.com/api/mh/getCartoonRankingMore.php?type=4&appname=%E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7&page=" + pageNum + "&limit=20";
                break;
        }
        return url;
    }

    //分类和搜索
    public static String getTypeData(int pageNum, String type) {
        return "http://a121.baopiqi.com/api/mh/getSearchCartoon.php";
    }

    public static String getAuthorData(int pageNum, String type) {
        return "http://a121.baopiqi.com/api/mh/getSearchAuthor.php?";
    }

    public static String getChapterData(String id, String number) {
        return "http://a121.baopiqi.com/api/mh/getCartoonChapter.php?number=" + number + "&id=" + id + "&page=0&limit=1000000&appname=%E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7";
    }

    public static final String BOOK_DAIL_URL = "http://a121.baopiqi.com/api/mh/getCartoonInfo.php?&appname=%E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7&id=";
    public static final String CHAPTER_DAIL_URL = "http://a121.baopiqi.com/api/mh/getCartoonChapter.php?";

    static public String getChapter(int id){

        return "http://a121.baopiqi.com/api/mh/getCartoonInfo.php?&appname=%E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7&id="+id;
    }

}
