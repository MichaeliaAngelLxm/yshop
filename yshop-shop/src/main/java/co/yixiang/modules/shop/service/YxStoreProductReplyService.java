/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.shop.service;
import co.yixiang.common.service.BaseService;
import co.yixiang.modules.shop.domain.YxStoreProductReply;
import co.yixiang.modules.shop.service.dto.YxStoreProductReplyDTO;
import co.yixiang.modules.shop.service.dto.YxStoreProductReplyQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author hupeng
* @date 2020-05-12
*/
public interface YxStoreProductReplyService  extends BaseService<YxStoreProductReply>{

/**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(YxStoreProductReplyQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<YxStoreProductReplyDto>
    */
    List<YxStoreProductReply> queryAll(YxStoreProductReplyQueryCriteria criteria);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<YxStoreProductReplyDTO> all, HttpServletResponse response) throws IOException;
}
