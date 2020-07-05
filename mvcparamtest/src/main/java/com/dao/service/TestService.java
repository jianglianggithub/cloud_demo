package com.dao.service;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dao.entity.SysPermission;
import com.dao.mapper.SysPermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TestService {


    @Autowired
    SysPermissionMapper sysPermissionMapper;


    public List<SysPermission> sle(){
        List<SysPermission> oneLevelNode = sysPermissionMapper.selectROOT();
        for (SysPermission sysPermission : oneLevelNode) {
            sysPermission.setChins(new ArrayList<>());
            re(sysPermission);
        }
        return oneLevelNode;
    }

    public void re(SysPermission sysPermission) {
        List<SysPermission> chinlds = sysPermissionMapper.selectByPid(sysPermission);
        if (chinlds.size() < 1) {
            sysPermission.setChins(null);
            return;
        }
        for (SysPermission chinld : chinlds) {
            chinld.setChins(new ArrayList<>());
            sysPermission.getChins().add(chinld);
            this.re(chinld);
        }
    }


}
