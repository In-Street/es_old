package cyf.es.util;



public class ESHelper {

    public static String getESIP(){
        return PropertiesUtil.getPropertyValueByKeyAndLocation(SysConstans.SYS_CONFIG_PROPERTIES_LOCATION,"sys.elasticsearch.ip");
    }

    public static Integer getESPort(){
        return Integer.valueOf(PropertiesUtil.getPropertyValueByKeyAndLocation(SysConstans.SYS_CONFIG_PROPERTIES_LOCATION,"sys.elasticsearch.port"));
    }

    public static String getTheNameOfIndexForTmdetail(){
        return PropertiesUtil.getPropertyValueByKeyAndLocation(SysConstans.SYS_CONFIG_PROPERTIES_LOCATION,"sys.elasticsearch.index.name.cyf");
    }

    public static String getTheTypeForTmdetail(){
        return PropertiesUtil.getPropertyValueByKeyAndLocation(SysConstans.SYS_CONFIG_PROPERTIES_LOCATION,"sys.elasticsearch.index.type.search_1");
    }

    public static String getTheTmCnFieldNameForTmdetail(){
        return PropertiesUtil.getPropertyValueByKeyAndLocation(SysConstans.SYS_CONFIG_PROPERTIES_LOCATION,"sys.elasticsearch.index.type.field.tmCn");
    }




}
