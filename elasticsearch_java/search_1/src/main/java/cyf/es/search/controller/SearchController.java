package cyf.es.search.controller;

import cyf.es.search.domain.TMInfo;
import cyf.es.search.service.SearchEngineService;
import cyf.es.search.service.TMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
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
    @RequestMapping("/dataUpdate")
    @ResponseBody
    public String dataUpdate() {
        boolean result = searchEngineService.allTmApplicantNameIntoEngine();
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

    @RequestMapping(value="/query",method = RequestMethod.POST)
    @ResponseBody
    public String query(String keyword) throws IOException {
        List<TMInfo> list = searchEngineService.query(keyword);
        return "";
    }

}
