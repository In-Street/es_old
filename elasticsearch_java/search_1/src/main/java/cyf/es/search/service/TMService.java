package cyf.es.search.service;

import cyf.es.search.dao.TMInfoMapper;
import cyf.es.search.domain.TMInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yufei on 2017/5/21.
 */
@Service
public class TMService {
    @Autowired
    TMInfoMapper tmInfoMapper;

    public Integer selectMaxId() {
        return tmInfoMapper.selectMaxId();
    }
    public List<TMInfo> selectByIDRange(TMInfo tmInfo){
        return tmInfoMapper.selectByIDRange(tmInfo);
    }
}
