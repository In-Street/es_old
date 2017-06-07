package cyf.es.wxmsg.util;

/**
 * 微信接口调用返回信息
 *
 * Created by tianxuan on 2016/12/19.
 */

public class WXInterfaceReturn {
    /**
     * 返回码,为0表示调用成功,其余返回码参见https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1445241432&token=&lang=zh_CN
     *
     */
    private int errcode;

    /**
     * 错误内容，成功调用返回ok
     */
    private String errmsg;

    /**
     * 原始返回内容
     */
    private String content;

    public WXInterfaceReturn(){

    }

    public WXInterfaceReturn(int errcode, String errmsg, String content) {
        this.errcode = errcode;
        this.errmsg = errmsg;
        this.content = content;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "WXInterfaceReturn{" +
                "errcode=" + errcode +
                ", errmsg='" + errmsg + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
