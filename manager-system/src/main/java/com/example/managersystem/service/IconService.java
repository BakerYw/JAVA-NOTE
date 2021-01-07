package com.example.managersystem.service;

import com.example.managersystem.entity.Icon;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cheese
 * @since 2021-01-06
 */
public interface IconService {
    List<Icon> selectAll();
}
