/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.shop.service;
import co.yixiang.common.service.BaseService;
import co.yixiang.modules.shop.domain.YxStoreOrder;
import co.yixiang.modules.shop.service.dto.OrderCountDto;
import co.yixiang.modules.shop.service.dto.OrderTimeDataDTO;
import co.yixiang.modules.shop.service.dto.YxStoreOrderDTO;
import co.yixiang.modules.shop.service.dto.YxStoreOrderQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author hupeng
* @date 2020-05-12
*/
public interface YxStoreOrderService  extends BaseService<YxStoreOrder>{

/**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(YxStoreOrderQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<YxStoreOrderDto>
    */
    List<YxStoreOrder> queryAll(YxStoreOrderQueryCriteria criteria);


    YxStoreOrderDTO create(YxStoreOrder resources);

    void update(YxStoreOrder resources);
    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<YxStoreOrderDTO> all, HttpServletResponse response) throws IOException;


    Map<String,Object> queryAll(List<String> ids);


    String orderType(int id,int pinkId, int combinationId,int seckillId,
                     int bargainId,int shippingType);

    void refund(YxStoreOrder resources);

    OrderCountDto getOrderCount();

    OrderTimeDataDTO getOrderTimeData();

    Map<String,Object> chartCount();
}
