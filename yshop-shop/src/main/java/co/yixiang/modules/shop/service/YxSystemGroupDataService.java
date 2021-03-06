/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.shop.service;
import co.yixiang.common.service.BaseService;
import co.yixiang.modules.shop.domain.YxSystemGroupData;
import co.yixiang.modules.shop.service.dto.YxSystemGroupDataDTO;
import co.yixiang.modules.shop.service.dto.YxSystemGroupDataQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author hupeng
* @date 2020-05-12
*/
public interface YxSystemGroupDataService  extends BaseService<YxSystemGroupData>{

/**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(YxSystemGroupDataQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<YxSystemGroupDataDto>
    */
    List<YxSystemGroupData> queryAll(YxSystemGroupDataQueryCriteria criteria);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<YxSystemGroupDataDTO> all, HttpServletResponse response) throws IOException;
}
