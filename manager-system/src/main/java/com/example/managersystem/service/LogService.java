package com.example.managersystem.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.managersystem.common.result.PageBean;
import com.example.managersystem.entity.Log;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cheese
 * @since 2021-01-06
 */
public interface LogService {
    void insertByLog(Log log);

    PageBean<Log> queryPage(Map<String, Object> paramMap);


    int delByLogIds(List<Integer> ids);
}
