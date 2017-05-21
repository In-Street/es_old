package cyf.es.search.controller;

import cyf.es.search.service.SearchEngineService;
import cyf.es.search.service.TMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yufei on 2017/5/21.
 */
@Controller
public class SearchController {
    @Autowired
    TMService tmService;

    @Autowired
    SearchEngineService searchEngineService;

    @RequestMapping("dataUpdate")
    @ResponseBody
    public String dataUpdate() {
        boolean result = searchEngineService.allTmApplicantNameIntoEngine();
        return "";
    }
}
