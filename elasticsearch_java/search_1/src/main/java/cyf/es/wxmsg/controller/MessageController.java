package cyf.es.wxmsg.controller;

import cyf.es.wxmsg.domain.TemplateData;
import cyf.es.wxmsg.domain.WxTemplate;
import cyf.es.wxmsg.util.*;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/message")
@SuppressWarnings("rawtypes")
public class MessageController {


    private static Logger logger = LoggerFactory.getLogger(MessageController.class);

    /**
     * 旧版消息发送
     * @param appId
     * @param appSecret
     * @param openId
     */
    @RequestMapping(value = "/sendMessage", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void message(String appId, String appSecret, String openId) {

        String access_token = "enoYwAbA1wp-tOSLf7Co-Zgn9EwXSqIRFXjNZucoUSH9PK__w_MCx9q3XSDFlTEKEGQIvODItGNigFHJtQIaK0ndu9ttOTg96U96IFtLUrGkl50ZWt_sP68A8oK2jWRVBNDfAIAIUK";
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + access_token;
        WxTemplate temp = new WxTemplate();
        temp.setUrl("http://weixin.qq.com/download");

        openId = "oCn8ExDrI5um_rFwyl-ue2iKYiBk";
        temp.setTouser(openId);
        temp.setTopcolor("#000000");
//        temp.setTemplate_id("ngqIpbwh8bUfcSsECmogfXcV14J0tQlEpBO27izEYtY");
        temp.setTemplate_id("HNb_GttgvjDTtAeXiQkNGtXyJcqHNbAj_rhTATOSDPc");
        Map<String, TemplateData> m = new HashMap<String, TemplateData>();
        TemplateData first = new TemplateData();
        first.setColor("#000000");
        first.setValue("这里填写您要发送的模板信息");
        m.put("first", first);
        TemplateData name = new TemplateData();
        name.setColor("#000000");
        name.setValue("另一行内人");
        m.put("name", name);
        TemplateData wuliu = new TemplateData();
        wuliu.setColor("#000000");
        wuliu.setValue("N行");
        m.put("wuliu", wuliu);
        TemplateData orderNo = new TemplateData();
        orderNo.setColor("#000000");
        orderNo.setValue("**666666");
        m.put("orderNo", orderNo);
        TemplateData receiveAddr = new TemplateData();
        receiveAddr.setColor("#000000");
        receiveAddr.setValue("*测试模板");
        m.put("receiveAddr", receiveAddr);
        TemplateData remark = new TemplateData();
        remark.setColor("#000000");
        remark.setValue("***备注说明***");
        m.put("Remark", remark);
        temp.setData(m);
        String jsonString = JSONObject.fromObject(temp).toString();
        JSONObject jsonObject = SendMessageHelper.httpRequest(url, "POST", jsonString);
        System.out.println(jsonObject);
        int result = 0;
        if (null != jsonObject) {
            if (0 != jsonObject.getInt("errcode")) {
                result = jsonObject.getInt("errcode");
                logger.error("错误 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        logger.info("模板消息发送结果：" + result);
    }

    /**
     * 新版发送消息，参考知助
     * @param shNum
     * @param openId
     */

    @RequestMapping(value = "/sendMessageNew", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void sendMessageNew(String shNum, String openId) {
        JSONObject wxMsg = getESMsgOnWechat(openId, shNum,String.format("您好！您有一个新的ES数据更新信息（%s），请及时处理", shNum));
        WXInterfaceReturn wxInterfaceReturn = WXMsgUtil.sendMsg(wxMsg.toString());
        System.out.println(wxInterfaceReturn.toString());
    }


    @RequestMapping(value = "/getOpenid", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String getUserOpenId() {
        /*String appId = "wx99c2381b15bbf68a";
        String appSecret = "46f26a9b99d2c39a6a4ca383be9951ac";*/
        String accessToken = AccessTokenUtil.getAccessToken(WXConst.getAppID(), WXConst.getAppSecret());
        String result = null;
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
        System.out.println(accessToken);
        JSONObject jsonObject = SendMessageHelper.httpRequest(requestUrl, "GET", null);
        if (null != jsonObject) {
            System.out.println(jsonObject);
            System.out.println(jsonObject.get("total"));
            System.out.println(jsonObject.get("data"));
            result = jsonObject.get("data") + "";
        }
        return result;
    }


    public static JSONObject getESMsgOnWechat(String openid,String shNum, String remark) {
        if (openid == null || openid.trim().length() == 0) {
            return null;
        }

        JSONObject json = new JSONObject();

        json.put("touser", openid);
        json.put("template_id", WXConst.MsgTemplate.OPENTM400180867.getTemplateId());
        json.put("topcolor", "#FF0000");

        JSONObject data = new JSONObject();

        JSONObject first = new JSONObject();
        first.put("value", "ES数据更新");
        first.put("color", "#173177");

        JSONObject OrderSn = new JSONObject();
        OrderSn.put("value", shNum);
        OrderSn.put("color", "#173177");

        JSONObject OrderStatus = new JSONObject();
        OrderStatus.put("value", "待更新");
        OrderStatus.put("color", "#173177");

        JSONObject _remark = new JSONObject();
        _remark.put("value", remark);
        _remark.put("color", "#173177");

        data.put("first", first);
        data.put("keyword1", OrderSn);
        data.put("keyword2", OrderStatus);
        data.put("remark", _remark);

        json.put("data", data);
        return json;
    }


}
