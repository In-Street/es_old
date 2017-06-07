package cyf.es.wxmsg.util;

import cyf.es.util.PropertiesUtil;
import cyf.es.util.SysConstans;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 微信公众号开发常量定义
 */
public class WXConst {
	private static final Logger logger = LoggerFactory.getLogger(WXConst.class);
	/**
	 * APPID
	 * @return
	 */
	public static String getAppID(){
		return PropertiesUtil.getPropertyValueByKeyAndLocation(SysConstans.WX_CONFIG_PROPERTIES_LOCATION, "APPID");
	}
	/**
	 * APPSECRET
	 * @return
	 */
	public static String getAppSecret(){
		return PropertiesUtil.getPropertyValueByKeyAndLocation(SysConstans.WX_CONFIG_PROPERTIES_LOCATION, "APPSECRET");
	}
	/**
	 * TOKEN
	 * @return
	 */
	public static String getToken(){
		return PropertiesUtil.getPropertyValueByKeyAndLocation(SysConstans.WX_CONFIG_PROPERTIES_LOCATION, "TOKEN");
	}
	
	/**
	 * 获取微信菜单配置
	 * @return
	 */
	public static String getMenu(){
		return PropertiesUtil.getPropertyValueByKeyAndLocation(SysConstans.WX_CONFIG_PROPERTIES_LOCATION, "MENU");
	}
	/**
	 * 获取创建菜单url
	 * @return
	 */
	public static String getCreateMenuUrl(){

		return "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+AccessTokenUtil.getAccessToken(getAppID(),getAppSecret());
	}
	
	/**
	 * 获取发送消息至对应模板url
	 * @return
	 */
	public static String getSendMsgToUserUrl(){
		return "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+AccessTokenUtil.getAccessToken(getAppID(),getAppSecret());
	}
	
	
	
	
	
	/*******************************************网页授权获取用户基本信息****************************************************/
	
	/**
	 * 拉取用户信息url	
	 * @param access_token 网页授权accesstoken，区别于普通accesstoken(即AccessTokenUtil中的ACCESS_TOKEN)
	 * @param openid
	 * @return
	 */
	public static String getUserInfoUrl(String access_token,String openid){
		return "https://api.weixin.qq.com/sns/userinfo?access_token="+access_token+"&openid="+openid+"&lang=zh_CN";
	}
	
	/**
	 * 获取微信服务器网页授权页面 URL
	 * @param redirect_uri  
	 * @param scope:snsapi_userinfo或者
	 * @return 
	 */
	public static String getAuth_UserInfoUrl(String redirect_uri,String scope){
		String url = null;
		try {
			url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+WXConst.getAppID()+
					"&redirect_uri="+URLEncoder.encode(redirect_uri,"UTF-8")+"&response_type=code&scope="+scope+"&state=3434234234#wechat_redirect";
		} catch (UnsupportedEncodingException e) {
			logger.debug("获取微信服务器网页授权页面 URL ERROR",e);
		}
		return url;
	}
	
	/**
	 * 获取刷新用户授权access_token URL	
	 * @param appid
	 * @param refresh_token
	 * @return
	 */
	public static String getAuth_RefreshUserInfoUrl(String appid,String refresh_token){
		return "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid="+appid+"&grant_type=refresh_token&refresh_token="+refresh_token;
	}
	
	/**
	 * 验证网页授权access_token是否有效url
	 * @param access_token
	 * @param openid
	 * @return
	 */
	public static String isAuth_AccessTokenValidUrl(String access_token,String openid){
		return "https://api.weixin.qq.com/sns/auth?access_token="+access_token+"&openid="+openid;
	}
	
	/**
	 * 改连接通过code换取网页授权access_token
	 * @param code 
	 * @return
	 */
	public static String getAccess_tokenByCodeUrl(String code){
		return "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+WXConst.getAppID()+"&secret="+WXConst.getAppSecret()+"&code="+code+"&grant_type=authorization_code";
	}
	
	/**
	 * 获取微信消息模板id接口地址
	 * @author 	  Su Jishen
	 * @DateTime 2015年9月21日 下午4:28:57
	 */
	public static String getMsgTemplateUrl() {
		return "https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=" + getToken();
	}
	
	/**
	 * 消息模板
	 *
	 */
	public enum MsgTemplate {
		/**
		 * 详细内容
		 *		    {{first.DATA}}
		 *		    
		 *		    订单编号: {{OrderSn.DATA}}
		 *		    订单状态: {{OrderStatus.DATA}}
		 *		    {{remark.DATA}}
		 *	内容示例
		 *		       尊敬的kant:
		 *		       
		 *		       订单编号: 1130927196009757
		 *		       订单状态: 已收货
		 *		       
		 *		       其他订单信息
		 *		       物流信息: 圆通速递(上海)
		 *		       快递单号: 8031971890
		 *		       点击“详情”查看完整物流信息
		 */
		TM100017("TM100017", "aI0fnZIqDefbEspW8bXbhvfgC_C-17NrEw_dalswPf0"),
		/**
		 * 
		 * 账户变更提醒 
		 * 
		 * 详细内容
         *		       {{first.DATA}} 
         *        
         *            账户：{{account.DATA}}
         *            时间：{{time.DATA}}
         *            类型：{{type.DATA}}
         *            {{remark.DATA}}  
         * 
         *内容示例
         *           恭喜你成功升级为钻石会员。
         *        
         *           账户：张先生
         *           时间：2013年9月30日 17:58
         *           类型：升级
         *           恭喜你升级为钻石会员，点击查看会员特权。
		 */
		TM00370("TM00370", "JcNHkmlxco0Qc91Es2SBSC4WqpERfYFFjU1jM6zGkcg"),
		/**
		 * 账户状态更新
		 * 详细内容
		 * 		{{first.DATA}}
		 * 		账户：{{keyword1.DATA}}
		 * 		时间：{{keyword2.DATA}}
		 * 		类型：{{keyword3.DATA}}
		 * 		{{remark.DATA}}
		 * 内容示例
		 * 		账户信息审核通过
		 * 		账户：13854077157
		 * 		时间：2014年7月21日
		 * 		类型：代理人
		 * 		账户信息已审核通过，请在电脑上登录e商标平台开始使用各项功能，网址www.eshangbiao.cn
		 *
		 */
		OPENTM400180854("OPENTM400180854",PropertiesUtil.getPropertyValueByKeyAndLocation(SysConstans.WX_CONFIG_PROPERTIES_LOCATION,"OPENTM400180854")),
		/**
		 * ES数据更新
		 * 详细内容
		 * 		{{first.DATA}}
		 * 		.sh编号：{{keyword1.DATA}}
		 * 		状态：{{keyword2.DATA}}
		 * 		{{remark.DATA}}
		 */
		OPENTM400180867("OPENTM400180867",PropertiesUtil.getPropertyValueByKeyAndLocation(SysConstans.WX_CONFIG_PROPERTIES_LOCATION,"OPENTM400180867")),
		/**
		 * 发票申请通知
		 * 详细内容
		 * 		 {{first.DATA}}
		 *	     发票类型：{{keyword1.DATA}}
		 *		 申请时间：{{keyword2.DATA}}
		 *		 {{remark.DATA}}
		 * 内容示例
		 * 		 尊敬的用户您好，本月您有订单采购，可以申请开具发票。超过申请时间后，将不再接受发票申请，请您及时到平台上操作
		 *		 发票类型：增值税发票
		 *		 申请时间：1月2 至1月15
		 *		 如有任何疑问，可直接回复公众账号，联系客服
		 */
		OPENTM401531182("OPENTM401531182",PropertiesUtil.getPropertyValueByKeyAndLocation(SysConstans.WX_CONFIG_PROPERTIES_LOCATION,"OPENTM401531182"));

		private String templateShort;
		private String templateId;
		
		public String getTemplateShort() {
			return templateShort;
		}

		public String getTemplateId() {
			return templateId;
		}

		private MsgTemplate(String templateShort, String templateId) {
			this.templateShort = templateShort;
			this.templateId = templateId;
		}
	}
}
