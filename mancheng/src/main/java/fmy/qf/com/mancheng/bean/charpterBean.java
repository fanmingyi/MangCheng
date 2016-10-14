package fmy.qf.com.mancheng.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/14.
 */

public class CharpterBean {

    /**
     * id : 400
     * name : 常青茶馆
     * icon : http://biggame1.b0.upaiyun.com/imgv/bed2a9d6d48f851fc896a92e93c72810.jpg
     * author : 高炳俊
     * theme : 爱情
     * region : 日本
     * ranking : 3857
     * state : 连载中
     * introduction : 常青茶馆漫画 ，茶馆里飘逸着一种春天的气息…雪熙身陷三角恋，自己却全然不知…青春的交响曲溶于从中，会擦出怎样的火花…
     * chapter : [{"title":"常青茶馆-第01话","number":"35171","order":"1"},{"title":"常青茶馆-第02话","number":"35814","order":"2"},{"title":"常青茶馆-第03话","number":"37803","order":"3"},{"title":"常青茶馆-第04话","number":"37804","order":"4"}]
     */

    private String id;
    private String name;
    private String icon;
    private String author;
    private String theme;
    private String region;
    private String ranking;
    private String state;
    private String introduction;
    /**
     * title : 常青茶馆-第01话
     * number : 35171
     * order : 1
     */

    private List<ChapterBean> chapter;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public List<ChapterBean> getChapter() {
        return chapter;
    }

    public void setChapter(List<ChapterBean> chapter) {
        this.chapter = chapter;
    }

    public static class ChapterBean {
        private String title;
        private String number;
        private String order;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }
    }
}
