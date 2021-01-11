package com.example.managersystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.managersystem.common.result.PageBean;
import com.example.managersystem.entity.Log;
import com.example.managersystem.mapper.LogMapper;
import com.example.managersystem.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cheese
 * @since 2021-01-06
 */
@Service
public class LogServiceImpl implements LogService{

    @Autowired
    private LogMapper logMapper;

    @Override
    public void insertByLog(Log log) {
        logMapper.insert(log);
    }

    @Override
    public PageBean<Log> queryPage(Map<String, Object> paramMap) {
        PageBean<Log> pageBean = new PageBean<>((Integer) paramMap.get("pageno"),(Integer) paramMap.get("pagesize"));

        Integer startIndex = pageBean.getStartIndex();
        paramMap.put("startIndex",startIndex);
        List<Log> datas = logMapper.queryList(paramMap);
        pageBean.setDatas(datas);

        Integer totalsize = logMapper.queryCount(paramMap);
        pageBean.setTotalsize(totalsize);
        return pageBean;
    }



    @Override
    public int delByLogIds(List<Integer> ids) {
        return logMapper.deleteBatchIds(ids);
    }
}
