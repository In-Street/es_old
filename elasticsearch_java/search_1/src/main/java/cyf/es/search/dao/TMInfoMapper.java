package cyf.es.search.dao;

import cyf.es.search.domain.TMInfo;

import java.util.List;

/**
 * Created by yufei on 2017/5/21.
 */
public interface TMInfoMapper {
    Integer selectMaxId();
    List<TMInfo> selectByIDRange(TMInfo tmInfo);
}
