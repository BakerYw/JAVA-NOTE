package com.example.managersystem.service.impl;

import com.example.managersystem.entity.Icon;
import com.example.managersystem.mapper.IconMapper;
import com.example.managersystem.service.IconService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cheese
 * @since 2021-01-06
 */
@Service
public class IconServiceImpl implements IconService {


    @Autowired
    private IconMapper iconMapper;

    @Override
    public List<Icon> selectAll() {
        return iconMapper.selectList(null);
    }
}
