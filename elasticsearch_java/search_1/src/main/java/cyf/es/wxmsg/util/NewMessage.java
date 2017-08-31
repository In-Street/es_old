package cyf.es.wxmsg.util;


import java.util.List;

/**
 * @author Cheng Yufei
 * @create 2017-08-31 11:44
 **/
public class NewMessage {
    private String Content;
    private int ArticleCount;
    private String ToUserName;
    private String FromUserName;
    private String CreateTime;
    private String MsgType;
    private List<Articles> Articles;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public int getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(int articleCount) {
        ArticleCount = articleCount;
    }

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public List<cyf.es.wxmsg.util.Articles> getArticles() {
        return Articles;
    }

    public void setArticles(List<cyf.es.wxmsg.util.Articles> articles) {
        Articles = articles;
    }
}
