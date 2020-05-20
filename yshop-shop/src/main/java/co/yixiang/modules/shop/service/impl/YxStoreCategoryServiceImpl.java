/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.shop.service.impl;

import co.yixiang.modules.shop.domain.YxStoreCategory;
import co.yixiang.common.service.impl.BaseServiceImpl;
import lombok.AllArgsConstructor;
import co.yixiang.dozer.service.IGenerator;
import com.github.pagehelper.PageInfo;
import co.yixiang.common.utils.QueryHelpPlus;
import co.yixiang.utils.FileUtil;
import co.yixiang.modules.shop.service.YxStoreCategoryService;
import co.yixiang.modules.shop.service.dto.YxStoreCategoryDTO;
import co.yixiang.modules.shop.service.dto.YxStoreCategoryQueryCriteria;
import co.yixiang.modules.shop.service.mapper.StoreCategoryMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.io.IOException;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

/**
* @author hupeng
* @date 2020-05-12
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "yxStoreCategory")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxStoreCategoryServiceImpl extends BaseServiceImpl<StoreCategoryMapper, YxStoreCategory> implements YxStoreCategoryService {

    private final IGenerator generator;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(YxStoreCategoryQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<YxStoreCategoryDTO> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", page.getList());
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<YxStoreCategoryDTO> queryAll(YxStoreCategoryQueryCriteria criteria){
        return generator.convert(baseMapper.selectList(QueryHelpPlus.getPredicate(YxStoreCategory.class, criteria)),YxStoreCategoryDTO.class);
    }


    @Override
    public void download(List<YxStoreCategoryDTO> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxStoreCategoryDTO yxStoreCategory : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("父id", yxStoreCategory.getPid());
            map.put("分类名称", yxStoreCategory.getCateName());
            map.put("排序", yxStoreCategory.getSort());
            map.put("图标", yxStoreCategory.getPic());
            map.put("是否推荐", yxStoreCategory.getIsShow());
            map.put("添加时间", yxStoreCategory.getAddTime());
            map.put("删除状态", yxStoreCategory.getIsDel());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public Object buildTree(List<YxStoreCategoryDTO> categoryDTOS) {
        Set<YxStoreCategoryDTO> trees = new LinkedHashSet<>();
        Set<YxStoreCategoryDTO> cates= new LinkedHashSet<>();
        List<String> deptNames = categoryDTOS.stream().map(YxStoreCategoryDTO::getCateName)
                .collect(Collectors.toList());

        YxStoreCategoryDTO categoryDTO = new YxStoreCategoryDTO();
        Boolean isChild;
        List<YxStoreCategory> categories = this.list();
        for (YxStoreCategoryDTO deptDTO : categoryDTOS) {
            isChild = false;
            if ("0".equals(deptDTO.getPid().toString())) {
                trees.add(deptDTO);
            }
            for (YxStoreCategoryDTO it : categoryDTOS) {
                if (it.getPid().equals(deptDTO.getId())) {
                    isChild = true;
                    if (deptDTO.getChildren() == null) {
                        deptDTO.setChildren(new ArrayList<YxStoreCategoryDTO>());
                    }
                    deptDTO.getChildren().add(it);
                }
            }
            if(isChild)
                cates.add(deptDTO);
            for (YxStoreCategory category : categories) {
                if(category.getId()==deptDTO.getPid()&&!deptNames.contains(category.getCateName())){
                    cates.add(deptDTO);
                }
            }
        }



        if (CollectionUtils.isEmpty(trees)) {
            trees = cates;
        }



        Integer totalElements = categoryDTOS!=null?categoryDTOS.size():0;

        Map map = new HashMap();
        map.put("totalElements",totalElements);
        map.put("content",CollectionUtils.isEmpty(trees)?categoryDTOS:trees);
        return map;
    }
}
