package com.zhenhui.demo.falcon.service.dao;

import com.zhenhui.demo.falcon.core.domain.Model;
import com.zhenhui.demo.falcon.service.mybatis.mapper.ModelMapper;
import com.zhenhui.demo.falcon.service.utils.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Component
@CacheConfig(cacheNames = "models")
public class ModelDAO {

    @Autowired
    private ModelMapper modelMapper;

    @Cacheable(key = "#name")
    public Model queryModel(String name) {
        return modelMapper.selectByName(name);
    }

    @CacheEvict(key = "#model.name")
    public void createModel(Model model) {
        try {
            modelMapper.insert(model);
        } catch (Exception e) {
            if (!ExceptionUtils.hasDuplicateEntryException(e)) {
                throw e;
            }
        }
    }

}
