/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.mp.service.mapper;

import co.yixiang.common.mapper.CoreMapper;
import co.yixiang.mp.domain.YxWechatReply;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author hupeng
* @date 2020-05-12
*/
@Repository
@Mapper
public interface WechatReplyMapper extends CoreMapper<YxWechatReply> {

}
