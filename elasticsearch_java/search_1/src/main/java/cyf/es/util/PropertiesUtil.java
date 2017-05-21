package cyf.es.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * The property tool bean
 * @author 	  Su Jishen
 * @DateTime 2015年8月26日 下午12:00:24
 */
public class PropertiesUtil {
	
	/**
	 * 获取指定位置指定key的系统属性值
	 * @author 	  Su Jishen
	 * @DateTime 2015年8月26日 下午12:02:58
	 */
	public static String getPropertyValueByKeyAndLocation (String location, String key) {
		try {
			Properties properties = new Properties();
			if (StringUtil.isBlank(location) || StringUtil.isBlank(key)) {
				return "";
			}
			
			InputStream in = PropertiesUtil.class.getResourceAsStream(location);
			
			try {
				if (in == null ) {
					return "";
				} else {
					properties.load(new InputStreamReader(in,"UTF-8"));
					if (in != null) {
						in.close();
					}
					
					return StringUtil.objToString(properties.get(key));
				}
			} catch (IOException e) {
				new Exception("No properties file in location " + location + "!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
