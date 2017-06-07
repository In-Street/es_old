package cyf.es.search.controller;

import cyf.es.search.domain.TMInfo;
import cyf.es.search.service.SearchEngineService;
import cyf.es.search.service.TMService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by yufei on 2017/5/21.
 */
@Controller
public class SearchController {
    @Autowired
    TMService tmService;

    @Autowired
    SearchEngineService searchEngineService;

    /**
     * 批量导入数据
     *
     * @return
     */
    @RequestMapping(value="/dataUpdate", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public String dataUpdate() throws InterruptedException, IOException {
//        boolean result = searchEngineService.allTmApplicantNameIntoEngine();
      //  boolean result = searchEngineService.IntoEngineByThreadPool();


        searchEngineService.getDate();
        //searchEngineService.getSH("D:/Cheng/es_cyf_data");
        return "";
    }

    @RequestMapping("/del")
    @ResponseBody
    public String del() {
        searchEngineService.del();
        return "";
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update() throws IOException {
        searchEngineService.update();
        return "";
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public String query(String keyword) throws IOException {
        List<TMInfo> list = searchEngineService.query(keyword);
        return JSONArray.fromObject(list).toString();
    }

    @RequestMapping(value = "/delByTMId", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public String delByTMId(String keyword) throws IOException {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        List<QueryBuilder> list = boolQueryBuilder.should();
        list.add(QueryBuilders.wildcardQuery("tmRegNbr", "*"));
        searchEngineService.delByTMId(boolQueryBuilder);
        return "";
    }


}
