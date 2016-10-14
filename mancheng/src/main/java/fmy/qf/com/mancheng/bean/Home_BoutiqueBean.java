package fmy.qf.com.mancheng.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by Administrator on 2016/10/9.
 */
public class Home_BoutiqueBean {
    /**
     * name : 今日推荐
     * boutique : [{"id":"510","name":"狂野少女","icon":"http://biggame1.b0.upaiyun.com/imgv/deb1841d4cb50a4bc713b503394b9340.jpg","author":"Hun/zhena","theme":"格斗/爱情/校园/热血","region":"","ranking":"15982071","introduction":"女生是那么一点点暴力，我不断地洗澡，油腻的师姐在哪里","state":"连载中"},{"id":"514","name":"UQHOLDER!","icon":"http://biggame1.b0.upaiyun.com/imgv/d88a4257ac51f07a8a067dac9a5f9ba6.jpg","author":"赤松健","theme":"魔法/爱情/欢乐向","region":"","ranking":"17382292","introduction":"赤松健的新作名为「UQHOLDER!」，故事以近未来世界为舞台，讲述了魔法出现在世界上的十年后，独自一人过着平稳生活的少年刀太与同伴们一起在世界里冒险的故事","state":"连载中"},{"id":"527","name":"只有神知道的世界","icon":"http://biggame1.b0.upaiyun.com/imgv/5379084ff2fe246bc546aee7679c5653.jpg","author":"若木民喜","theme":"爱情/校园/后宫","region":"","ranking":"29104356","introduction":"《只有神知道的世界》是若木民喜创作的爱情喜剧作品。《周刊少年Sunday》2008年19号开始连载。有一天，一位少女从天而降，那位少女的名字叫作艾鲁西，从遥远的地狱到来，拜托桂马帮忙捕获「驱魂」。而方法就是把心的空隙填上，驱魂没地方躲就会跑出来，而把心的空隙填满的最佳方法就是「恋爱」。就这样，桂马开始了三次元女性的攻略。","state":"已完结"},{"id":"530","name":"堀与宫村","icon":"http://biggame1.b0.upaiyun.com/imgv/1a3a832ae2998a79f33a0f7a8f9d8f52.jpg","author":"萩原ダイスケ/HERO","theme":"爱情","region":"","ranking":"9562696","introduction":"打扮前卫的超优等生其实是超朴素的居家型女生\u2014\u2014堀京子。戴着超土眼镜夏天还穿长袖的宅男其实是扎满耳洞纹遍全身的美型潮男\u2014\u2014宫村伊澄。看似完全相反，却又相似的两人偶然间相遇了\u2026！？甜蜜到让你胸口一紧的超微碳酸系校园生活！","state":"连载中"},{"id":"532","name":"山田和七个魔女","icon":"http://biggame1.b0.upaiyun.com/imgv/68afec1d32cbcfbee5ff1c885a369939.jpg","author":"吉河美希","theme":"欢乐向/爱情/校园","region":"","ranking":"20775616","introduction":"漫画讲述私立高校的问题少年山田龙在某一天摔下楼梯，与同班女生白石うらら交换身体的故事。在优等生身体里的过程中，山田明白了白石的辛苦。","state":"连载中"},{"id":"639","name":"因为会长大人是未婚夫","icon":"http://biggame1.b0.upaiyun.com/imgv/06ae9d50b23fe86c34ad1aa111137b81.jpg","author":"华夜","theme":"爱情","region":"","ranking":"4490722","introduction":"最喜欢的人结婚,是非常幸福的事\u2026然而没想到实际上,却是如此的危险\u2026与学生会长的危险恋爱物语!","state":"连载中"}]
     */

    private BoutiquesBean boutiques;
    /**
     * name : 今日热门
     * ranking : [{"id":"715","name":"伪恋","icon":"http://biggame1.b0.upaiyun.com/imgv/7967c6fc32530e8f06f0f23083c571d7.jpg","author":"古味直志","theme":"欢乐向/爱情/校园/颜艺","region":"","ranking":"29995860","state":"已完结","introduction":"自称有着大众脸的高中生一条乐，其实是这一代有名的混混组首领的独子。想过平凡人生的他，在升入高中以后与曾经有过约定的女孩再度相遇，不得不展开了不平凡的高中生活\u2026\u2026"},{"id":"510","name":"狂野少女","icon":"http://biggame1.b0.upaiyun.com/imgv/deb1841d4cb50a4bc713b503394b9340.jpg","author":"Hun,zhena","theme":"格斗/爱情/校园/热血","region":"","ranking":"15982071","state":"连载中","introduction":"女生是那么一点点暴力，我不断地洗澡，油腻的师姐在哪里"},{"id":"639","name":"因为会长大人是未婚夫","icon":"http://biggame1.b0.upaiyun.com/imgv/06ae9d50b23fe86c34ad1aa111137b81.jpg","author":"华夜","theme":"爱情","region":"","ranking":"4490722","state":"已完结","introduction":"最喜欢的人结婚,是非常幸福的事\u2026然而没想到实际上,却是如此的危险\u2026与学生会长的危险恋爱物语!"},{"id":"671","name":"Iam\u2026","icon":"http://biggame1.b0.upaiyun.com/imgv/c389b93a8ba45556e110196ac7800238.jpg","author":"增田英二","theme":"欢乐向/爱情/奇幻/颜艺","region":"","ranking":"3581376","state":"连载中","introduction":"那个可爱的女孩！什么都被人看到了.....\r\n故事的主人公不是人类，而是吸血鬼、宇宙人、恶魔等...\r\n多个主人公一起交织出一个恋爱喜剧故事..."},{"id":"3025","name":"GrandBlue","icon":"http://biggame1.b0.upaiyun.com/imgv/fcf5bc6b50e84192125ae0ba740bfbf7.jpg","author":"井上坚二,吉冈公威","theme":"欢乐向/爱情/校园/颜艺","region":"","ranking":"2783210","state":"连载中","introduction":"以上大学为契机寄宿在海边爷爷家的主人公·北原伊织，遇到了绝世美女与散发着青春的学长们。为了寻求无可替代的瞬间，北原伊织挥洒青春。最棒的大学生活，等着你！！"},{"id":"1825","name":"恋爱暴君","icon":"http://biggame1.b0.upaiyun.com/imgv/2b2e6010136e7508636b5961b4369447.jpg","author":"三星めがね","theme":"欢乐向/爱情","region":"","ranking":"2311151","state":"连载中","introduction":"从亲吻❤开始的危险物语"}]
     */

    private RankingsBean rankings;
    /**
     * name : 今日最新
     * new : [{"id":"2428","name":"你们这些家伙真麻烦！","icon":"http://biggame1.b0.upaiyun.com/imgv/521341311a1248747fbbcbe00adbd742.jpg","author":"TOBI","theme":"爱情/校园","region":"","ranking":"937388","state":"连载中","introduction":"tobi老师新作，一个发生学校的师生恋的温馨故事，姑娘你真机智"},{"id":"885","name":"你我之间一墙之隔","icon":"http://biggame1.b0.upaiyun.com/imgv/ad94a35684ec766bbd820a97cbcaf477.jpg","author":"筑岛治","theme":"爱情","region":"","ranking":"283385","state":"连载中","introduction":"突然被青梅竹马的怜太提出交往，因为他高傲的态度而拒绝的真琴赌气去参加联谊，却被大学生\u2026\u2026？把人按在墙上【扶墙杀】是那么流行的吗？明明是青梅竹马却从今天开始成为我的男朋友？？40P新连载备受瞩目！"},{"id":"3315","name":"猫之寺的知恩姐","icon":"http://biggame1.b0.upaiyun.com/imgv/74542e548379eace13f34c937c122edf.jpg","author":"オジロマコト","theme":"欢乐向/爱情","region":"","ranking":"659","state":"连载中","introduction":"离开老家，到娴静的乡下高中就读的源。寄宿的远亲的寺庙，同居的大自己3岁的知恩姐，虽然看上去很可靠但是却非常无防备\u2026？与有点老土、大了3岁、工作者，大而化之的知恩姐共同呈现的，廊檐恋爱喜剧！"},{"id":"660","name":"尽管如此世界依然美丽","icon":"http://biggame1.b0.upaiyun.com/imgv/9da060f5f17e22fa2ee09dac62fde448.jpg","author":"椎名橙","theme":"爱情/魔法","region":"","ranking":"1381401","state":"连载中","introduction":"拥有降雨能力的雨之公国的第四王女妮可。虽然不情愿但为了国家而嫁给了晴之公国的太阳王理贝斯一世！继位三年便征服了世界的他，竟然是个孩子？？甚至用无聊的理由，要求妮可降雨。。。！！"},{"id":"673","name":"植物图鉴","icon":"http://biggame1.b0.upaiyun.com/imgv/faf57baa30ae1e75138c545be0b7ee36.jpg","author":"堤翔,有川浩","theme":"爱情","region":"","ranking":"34858","state":"连载中","introduction":"某天，路旁出现了一位忠犬系美男对沙弥香说，「能把我捡回家吗？」，沙弥香一下子没把持住便将他捡回了家，而后发现，这男人不仅上得厅堂，还下得了厨房！两人达成协议后开始了同居生活\u2014\u2014藉由生长在路边的野花野草，娓娓道来最浓稠最甜腻的恋爱！"},{"id":"1194","name":"辉夜大小姐想让我告白~天才们的恋爱头脑战~","icon":"http://biggame1.b0.upaiyun.com/imgv/fe1e6e4f36f6b77904b9e10eb37ece64.jpg","author":"赤坂アカ","theme":"欢乐向/爱情","region":"","ranking":"468547","state":"连载中","introduction":"恋爱正是适合天才的游戏！？秀知院学园的学生会长（天才）和副会长（天才）每天都为了让对方向自己告白而绞尽脑汁\u2026\u2026"}]
     */

    private NewsBean news;
    /**
     * id : 400
     * name : 常青茶馆
     * icon : http://biggame1.b0.upaiyun.com/imgv/bed2a9d6d48f851fc896a92e93c72810.jpg
     * author : 高炳俊
     * theme : 爱情
     * region : 日本
     * ranking : 3857
     * state : 连载中
     * cover : http://biggame.b0.upaiyun.com/filedevelopment/cartoonh/Public/upload/2016-07-27/57988477edd22.jpg
     * style : 1
     * introduction : 常青茶馆漫画，茶馆里飘逸着一种春天的气息…雪熙身陷三角恋，自己却全然不知…青春的交响曲溶于从中，会擦出怎样的火花…
     * type : 0
     * advert :
     */

    private List<CarouselFigureBean> carousel_figure;
    /**
     * id : 5
     * name : 爱情
     * icon : http://biggame1.b0.upaiyun.com/imgv/aiqing.jpg
     */

    private List<ClassificationBean> classification;

    public BoutiquesBean getBoutiques() {
        return boutiques;
    }

    public void setBoutiques(BoutiquesBean boutiques) {
        this.boutiques = boutiques;
    }

    public RankingsBean getRankings() {
        return rankings;
    }

    public void setRankings(RankingsBean rankings) {
        this.rankings = rankings;
    }

    public NewsBean getNews() {
        return news;
    }

    public void setNews(NewsBean news) {
        this.news = news;
    }

    public List<CarouselFigureBean> getCarousel_figure() {
        return carousel_figure;
    }

    public void setCarousel_figure(List<CarouselFigureBean> carousel_figure) {
        this.carousel_figure = carousel_figure;
    }

    public List<ClassificationBean> getClassification() {
        return classification;
    }

    public void setClassification(List<ClassificationBean> classification) {
        this.classification = classification;
    }


    public  static  class  BaseBean{
        private String id;
        private String name;
        private String icon;
        private String author;
        private String theme;
        private String region;
        private String ranking;
        private String introduction;
        private String state;

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

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }


    }

    public static class BoutiquesBean {
        private String name;
        /**
         * id : 510
         * name : 狂野少女
         * icon : http://biggame1.b0.upaiyun.com/imgv/deb1841d4cb50a4bc713b503394b9340.jpg
         * author : Hun/zhena
         * theme : 格斗/爱情/校园/热血
         * region :
         * ranking : 15982071
         * introduction : 女生是那么一点点暴力，我不断地洗澡，油腻的师姐在哪里
         * state : 连载中
         */

        private List<BoutiqueBean> boutique;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<BoutiqueBean> getBoutique() {
            return boutique;
        }

        public void setBoutique(List<BoutiqueBean> boutique) {
            this.boutique = boutique;
        }

        public static class BoutiqueBean extends  BaseBean{

        }
    }

    public static class RankingsBean {
        private String name;
        /**
         * id : 715
         * name : 伪恋
         * icon : http://biggame1.b0.upaiyun.com/imgv/7967c6fc32530e8f06f0f23083c571d7.jpg
         * author : 古味直志
         * theme : 欢乐向/爱情/校园/颜艺
         * region :
         * ranking : 29995860
         * state : 已完结
         * introduction : 自称有着大众脸的高中生一条乐，其实是这一代有名的混混组首领的独子。想过平凡人生的他，在升入高中以后与曾经有过约定的女孩再度相遇，不得不展开了不平凡的高中生活……
         */

        private List<RankingBean> ranking;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<RankingBean> getRanking() {
            return ranking;
        }

        public void setRanking(List<RankingBean> ranking) {
            this.ranking = ranking;
        }

        public static class RankingBean extends BaseBean{
        }
    }

    public static class NewsBean {
        private String name;
        /**
         * id : 2428
         * name : 你们这些家伙真麻烦！
         * icon : http://biggame1.b0.upaiyun.com/imgv/521341311a1248747fbbcbe00adbd742.jpg
         * author : TOBI
         * theme : 爱情/校园
         * region :
         * ranking : 937388
         * state : 连载中
         * introduction : tobi老师新作，一个发生学校的师生恋的温馨故事，姑娘你真机智
         */

        @JSONField(name = "new")
        private List<NewBean> newX;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<NewBean> getNewX() {
            return newX;
        }

        public void setNewX(List<NewBean> newX) {
            this.newX = newX;
        }

        public static class NewBean extends BaseBean{

        }
    }

    public static class CarouselFigureBean {
        private String id;
        private String name;
        private String icon;
        private String author;
        private String theme;
        private String region;
        private String ranking;
        private String state;
        private String cover;
        private String style;
        private String introduction;
        private String type;
        private String advert;

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

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAdvert() {
            return advert;
        }

        public void setAdvert(String advert) {
            this.advert = advert;
        }
    }

    public static class ClassificationBean {
        private String id;
        private String name;
        private String icon;

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
    }
}
