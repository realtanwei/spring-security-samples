package com.tanwei.spring.security.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanwei.spring.security.entity.model.SysMenu;
import com.tanwei.spring.security.mapper.SysMenuMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tanwei
 * @date 2023-07-11 10:30
 **/
@Service
public class SysMenuService extends ServiceImpl<SysMenuMapper, SysMenu> {

    public List<String> selectPermsByUserId(Long id) {
        return this.baseMapper.selectPermsByUserId(id);
    }
}
