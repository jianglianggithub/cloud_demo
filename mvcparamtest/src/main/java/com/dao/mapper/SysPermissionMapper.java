package com.dao.mapper;

import com.dao.entity.SysPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 后台用户权限表 Mapper 接口
 * </p>
 *
 * @author Mht
 * @since 2020-06-09
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {


    List<SysPermission> selectROOT();


    List<SysPermission> selectByPid(SysPermission sysPermission);
}
