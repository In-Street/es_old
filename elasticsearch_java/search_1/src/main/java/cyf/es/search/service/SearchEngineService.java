package cyf.es.search.service;

import cyf.es.search.domain.TMInfo;
import cyf.es.util.ESHelper;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;



@Service
public class SearchEngineService {

    private static Logger LOGGER = LoggerFactory.getLogger(SearchEngineService.class);

    @Autowired
    TMService tmService;

    TransportClient transportClient = null;


    @PostConstruct
    void initLocalResource() {
        Settings settings = Settings.builder()
                .put("client.transport.sniff", true).build();
        try {
            transportClient = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(ESHelper.getESIP()), ESHelper.getESPort()));
        } catch (UnknownHostException e) {
            LOGGER.error("ES服务器连接异常", e);
        }
    }

    public TransportClient getTransportClient() {
        return transportClient;
    }

    public boolean allTmApplicantNameIntoEngine() {
        int batchSize = 5000;
        TMInfo tmInfo = new TMInfo();
        tmInfo.setPageCnt(batchSize);
        int recordcount = tmService.selectMaxId();
        int pageNum = recordcount / batchSize;
        pageNum = recordcount % batchSize == 0 ? pageNum : pageNum + 1;

        BulkRequestBuilder bulkRequest = null;
        for (int i = 0; i < pageNum; i++) {
            LOGGER.info("全量更新开始处理：ID[{}]", i);
            tmInfo.setId(i * batchSize + 1);
            try {
                List<TMInfo> tmInfos = tmService.selectByIDRange(tmInfo);
                if (tmInfos.size() == 0) {
                    continue;
                }
                bulkRequest = transportClient.prepareBulk();
                JsonConfig jsonConfig = getJsonConfig();
                TMInfo _tminfo = null;
                for (int j = 0; j < tmInfos.size(); j++) {
                    _tminfo = tmInfos.get(j);
                    try {
                        bulkRequest.add(transportClient.prepareIndex(ESHelper.getTheNameOfIndexForTmdetail(), ESHelper.getTheTypeForTmdetail()).setSource(JSONObject.fromObject(_tminfo, jsonConfig).toString()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            BulkResponse bulkResponse = bulkRequest.get();
            if (bulkResponse.hasFailures()) {
                LOGGER.error("申请人名称全量更新处理出现错误，执行下一批次：ID[{}]", i);
            }
            LOGGER.info("申请人名称全量更新处理结束：ID[{}]", i);
        }
        return true;
    }


    public boolean singleTMIntoEngine(Object object) {
        BulkRequestBuilder bulkRequest = transportClient.prepareBulk();
        JsonConfig jsonConfig = getJsonConfig();
        try {
            if (object.getClass().equals(TMInfo.class)) {
                bulkRequest.add(transportClient.prepareIndex(ESHelper.getTheNameOfIndexForTmdetail(), ESHelper.getTheTypeForTmdetail()).setSource(JSONObject.fromObject(object, jsonConfig).toString()));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        BulkResponse bulkResponse = bulkRequest.get();
        if (bulkResponse.hasFailures()) {
            LOGGER.error("ES插入失败");
            return false;
        }
        return true;
    }

    private JsonConfig getJsonConfig() {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(java.util.Date.class, new JsonValueProcessor() {
            private final String format = "yyyy-MM-dd";

            public Object processObjectValue(String key, Object value, JsonConfig arg2) {
                if (value == null)
                    return "1970-01-01";
                if (value instanceof java.util.Date) {
                    String str = new SimpleDateFormat(format).format((java.util.Date) value);
                    return str;
                }
                return value.toString();
            }

            public Object processArrayValue(Object value, JsonConfig arg1) {
                return null;
            }

        });
        jsonConfig.registerJsonValueProcessor(String.class, new JsonValueProcessor() {
            private final String format = "yyyy-MM-dd";

            public Object processObjectValue(String key, Object value, JsonConfig arg2) {

                if (key.equals("appDate") || key.equals("interRegDate") || key.equals("trialDate")) {
                    if (value == null || StringUtils.trimToEmpty((String) value).length() == 0)
                        return "1970-01-01";
                    try {
                        return DateFormatUtils.format(DateUtils.parseDate(StringUtils.trimToEmpty((String) value), new String[]{"yyyy-M-d", "yyyyMM-dd", "yyyyMMdd", "yyyy-MMdd", "yyyy-MM-dd","MM d yyyy"}), format);
                    } catch (ParseException e) {
                        LOGGER.error(e.getLocalizedMessage());
                        return "1970-01-01";
                    }
                }

                if (value == null)
                    return "";
                return StringUtils.trimToEmpty((String) value);
            }

            public Object processArrayValue(Object value, JsonConfig arg1) {
                return null;
            }

        });
        return jsonConfig;
    }

}

