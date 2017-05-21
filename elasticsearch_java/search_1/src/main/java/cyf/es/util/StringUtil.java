package cyf.es.util;

import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * String工具类
 * 
 * @author sujishen
 * @date 2015/26/06
 */
public class StringUtil {
	private static final Pattern emoji=Pattern.compile("(?:[\\uD83C\\uDF00-\\uD83D\\uDDFF]|[\\uD83E\\uDD00-\\uD83E\\uDDFF]|[\\uD83D\\uDE00-\\uD83D\\uDE4F]|[\\uD83D\\uDE80-\\uD83D\\uDEFF]|[\\u2600-\\u26FF]\\uFE0F?|[\\u2700-\\u27BF]\\uFE0F?|\\u24C2\\uFE0F?|[\\uD83C\\uDDE6-\\uD83C\\uDDFF]{1,2}|[\\uD83C\\uDD70\\uD83C\\uDD71\\uD83C\\uDD7E\\uD83C\\uDD7F\\uD83C\\uDD8E\\uD83C\\uDD91-\\uD83C\\uDD9A]\\uFE0F?|[\\u0023\\u002A\\u0030-\\u0039]\\uFE0F?\\u20E3|[\\u2194-\\u2199\\u21A9-\\u21AA]\\uFE0F?|[\\u2B05-\\u2B07\\u2B1B\\u2B1C\\u2B50\\u2B55]\\uFE0F?|[\\u2934\\u2935]\\uFE0F?|[\\u3030\\u303D]\\uFE0F?|[\\u3297\\u3299]\\uFE0F?|[\\uD83C\\uDE01\\uD83C\\uDE02\\uD83C\\uDE1A\\uD83C\\uDE2F\\uD83C\\uDE32-\\uD83C\\uDE3A\\uD83C\\uDE50\\uD83C\\uDE51]\\uFE0F?|[\\u203C\\u2049]\\uFE0F?|[\\u25AA\\u25AB\\u25B6\\u25C0\\u25FB-\\u25FE]\\uFE0F?|[\\u00A9\\u00AE]\\uFE0F?|[\\u2122\\u2139]\\uFE0F?|\\uD83C\\uDC04\\uFE0F?|\\uD83C\\uDCCF\\uFE0F?|[\\u231A\\u231B\\u2328\\u23CF\\u23E9-\\u23F3\\u23F8-\\u23FA]\\uFE0F?)");

	/**
	 * convert the object to be string
	 * 
	 * @param obj
	 * @return
	 */
	public static String objToString(Object obj) {
		if (obj == null || "NULL".equalsIgnoreCase(String.valueOf(obj).trim())) {
			return "";
		}
		return String.valueOf(obj).trim();
	}

	/**
	 * Returns if this object is empty.
	 * 
	 * @param Obj
	 * @return
	 */
	public static boolean isBlank(Object obj) {
		return obj == null || "".equals(String.valueOf(obj).trim())
				|| "NULL".equalsIgnoreCase(String.valueOf(obj).trim());
	}

	/**
	 * Returns if this object array contains empty elements.
	 * 
	 * @param Obj
	 * @return
	 */
	public static boolean isBlank(Object... objs) {
		boolean yes = false;
		for (Object obj : objs) {
			if (isBlank(obj)) {
				yes = true;
				break;
			}
		}
		return yes;
	}

	/**
	 * Returns if each element of the object array is empty.
	 * 
	 * @param Obj
	 * @return
	 */
	public static boolean ifAllBlank(Object... objs) {
		boolean yes = true;
		for (Object obj : objs) {
			if (!isBlank(obj)) {
				yes = false;
			}
		}
		return yes;
	}

	/**
	 * Returns the Integer value of the String type
	 * 
	 * @author sujishen
	 * @date 2015-6-25 下午2:42:42
	 * @param str
	 * @return
	 */
	public static Integer parseInt(String str) {
		if (!isBlank(str)) {
			return new Integer(str);
		}
		return null;
	}
	
	/**
	 * Returns the Integer value of the String type
	 * 
	 * @author sujishen
	 * @date 2015-6-25 下午2:42:42
	 * @param str
	 * @return
	 */
	public static Float parseFloat(String str) {
		if (!isBlank(str)) {
			return new Float(str);
		}
		return null;
	}

	/**
	 * Returns if the string arrays contains the specified string.
	 * 
	 * @author Su Jishen
	 * @DateTime 2015年8月10日 下午4:15:27
	 */
	public static boolean isInScope(String original, String... scopes) {
		if (!isBlank(original) && !isBlank((Object[]) scopes)) {
			if (Arrays.asList(scopes).contains(original)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns if the para is a number obj
	 * 
	 * @author Su Jishen
	 * @DateTime 2015年9月1日 下午2:15:33
	 */
	public static boolean isNumber(String paramVal) {
		boolean flag = true;
		try {
			Integer.parseInt(paramVal);
		} catch (Exception e) {
			flag = false;
		}
		if (flag) {
			flag = paramVal.matches("[\\d]+");
		}

		return flag;
	}
	
	/**
	 * 
	 * @author Su Jishen
	 * @DateTime 2015年9月1日 下午2:15:33
	 */
	public static boolean isNumber(String... paramVals) {
		boolean flag = true;
		String s = "";
		try {
			for (String paramVal : paramVals) {
				s = paramVal;
				Integer.parseInt(paramVal);
			}
		} catch (Exception e) {
			flag = false;
		}
		if (flag) {
			flag = s.matches("[\\d]+");
		}

		return flag;
	}
	
	/**
	 * Returns if the 2nd and 3rd pargs contain the 1st arg
	 * @author 	  Su Jishen
	 * @DateTime 2015年9月1日 下午2:23:11
	 */
	public static boolean isInBetween(String param, String start, String end) {
		if(isBlank(param, start, end)) {
			return false;
		}
		Integer paramVal = parseInt(param);
		Integer startNum = parseInt(start);
		Integer endNum = parseInt(end);
		if (paramVal >= startNum) {
			if (endNum > 0) {
				return (paramVal <= endNum);
			}
			return true;
		}
		return false;
	}
	
	/**
	 * 是否为金融值
	 * @author 	  Su Jishen
	 * @DateTime 2015年9月7日 下午3:52:27
	 */
	public static boolean isNumberExt(String paramVal, boolean dot, boolean negative) {
		boolean flag = true;
		try {
			Double.parseDouble(paramVal);
		} catch (Exception e) {
			flag = false;
		}
		if (flag) {
			String regex = "[\\d]+";
			if (dot)
				regex = regex + "+\\.?\\d*";

			if (negative) {
				regex = "^(-)?" + regex;
			}
			regex = regex + "$";

			flag = paramVal.matches(regex);
		}

		return flag;
	}

	/**
	 * 替换全角括号为半角括号
	 * @param str 需要替换的字符串
	 * @return
	 */
	public static String replaceFullWidthBrackets(String str){
		String[] searchList = new String[]{"（","）"};
		String[] replacementList = new String[]{"(",")"};
		return StringUtils.replaceEach(str,searchList,replacementList);
	}

	/**
	 * 清空emoji符号
	 * @param replaceStr
	 * @return
	 */
	public static String repalceEmojiWithBlank(String replaceStr){
		return  StringUtils.isEmpty(replaceStr)?"":emoji.matcher(replaceStr).replaceAll("");
	}

}
