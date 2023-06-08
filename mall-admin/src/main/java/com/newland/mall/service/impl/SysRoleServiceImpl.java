package com.newland.mall.service.impl;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.SysMenu;
import com.newland.mall.entity.SysRole;
import com.newland.mall.entity.SysRoleMenu;
import com.newland.mall.enumeration.BasicEnum;
import com.newland.mall.enums.UserServiceErrorEnum;
import com.newland.mall.exception.BusinessException;
import com.newland.mall.mapper.SysMenuMapper;
import com.newland.mall.mapper.SysRoleMapper;
import com.newland.mall.mapper.SysRoleMenuMapper;
import com.newland.mall.mapper.SysUserRoleMapper;
import com.newland.mall.model.dto.RoleQueryDTO;
import com.newland.mall.service.SysRoleService;
import com.newland.mall.utils.AssertUtil;
import com.newland.mybatis.page.PageWrapper;
import com.newland.mybatis.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author leellun
 * @since 2022-12-06
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysRole> getAllRoles() {
        return baseMapper.getRoleByNameAndEnabled(null, BasicEnum.YES.getKey());
    }

    @Override
    public PageInfo<SysRole> getRolePage(RoleQueryDTO roleQueryDTO) {
        PageInfo<SysRole> page = PageWrapper.page(roleQueryDTO, () -> baseMapper.getRoleByNameAndEnabled(roleQueryDTO.getName(), roleQueryDTO.getEnabled()));
        return page;
    }

    @Override
    public SysRole getRole(Long id) {
        SysRole sysRole = baseMapper.selectByPrimaryKey(id);
        AssertUtil.notNull(sysRole, UserServiceErrorEnum.ROLE_NOT_EXIST.getDesc());
        return sysRole;
    }

    @Override
    public void addRole(SysRole sysRole) {
        SysRole dbRole = baseMapper.getRoleByName(sysRole.getName());
        AssertUtil.isNull(dbRole, UserServiceErrorEnum.ROLE_NAME_EXIST.getDesc());
        dbRole = baseMapper.getRoleByCode(sysRole.getCode());
        AssertUtil.isNull(dbRole, UserServiceErrorEnum.ROLE_CODE_EXIST.getDesc());
        sysRole.setEnabled(1);
        sysRole.setLevel(1);
        baseMapper.insert(sysRole);
    }

    @Override
    public void updateRole(SysRole sysRole) {
        SysRole role = baseMapper.selectByPrimaryKey(sysRole.getId());
        AssertUtil.notNull(role, UserServiceErrorEnum.ROLE_NOT_EXIST.getDesc());
        if (!sysRole.getName().equals(role.getName())) {
            SysRole dbRole = baseMapper.getRoleByName(sysRole.getName());
            AssertUtil.isNull(dbRole, UserServiceErrorEnum.ROLE_NAME_EXIST.getDesc());
        }
        if (!sysRole.getCode().equals(role.getCode())) {
            SysRole dbRole = baseMapper.getRoleByCode(sysRole.getCode());
            AssertUtil.isNull(dbRole, UserServiceErrorEnum.ROLE_CODE_EXIST.getDesc());
        }
        baseMapper.updateByPrimaryKeySelective(sysRole);
    }

    @Override
    public void enableRole(Long id, Integer enable) {
        SysRole role = baseMapper.selectByPrimaryKey(id);
        AssertUtil.notNull(role, UserServiceErrorEnum.ROLE_NOT_EXIST.getDesc());
        SysRole sysRole = new SysRole();
        sysRole.setId(id);
        sysRole.setEnabled(enable);
        baseMapper.updateByPrimaryKeySelective(sysRole);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, noRollbackFor = BusinessException.class)
    public void deleteRoles(Set<Long> ids) {
        int count = baseMapper.deleteBatchIds(ids);
        AssertUtil.isTrue(count > 0, UserServiceErrorEnum.ROLE_DELETE_FAIL.getDesc());
        ids.forEach(id -> {
            sysUserRoleMapper.deleteByRoleId(id);
            sysRoleMenuMapper.deleteByRoleId(id);
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class, noRollbackFor = BusinessException.class)
    public void addMenuPermission(Long id, Set<Long> permissions) {
        List<Long> permissionIds = getAllExpandMenuIds(permissions);
        List<Long> menuIds = sysRoleMenuMapper.getMenuIdsByRoleId(id);
        List<Long> extraIds = menuIds.stream().filter(item -> !permissionIds.contains(item)).collect(Collectors.toList());
        permissionIds.removeAll(menuIds);
        if (extraIds.size() > 0) {
            sysRoleMenuMapper.deleteByRoleAndMenus(id, extraIds);
        }
        List<SysRoleMenu> roleMenus = new ArrayList<>();
        for (Long menuId : permissionIds) {
            roleMenus.add(new SysRoleMenu(id, menuId));
            if (roleMenus.size() >= 1000) {
                sysRoleMenuMapper.insertBatch(roleMenus);
                roleMenus.clear();
            }
        }
        if (roleMenus.size() > 0) {
            sysRoleMenuMapper.insertBatch(roleMenus);
        }
    }

    @Override
    public List<Long> getMenuPermission(Long id) {
        List<Long> menuIds = sysRoleMenuMapper.getMenuIdsByRoleId(id);
        return menuIds;
    }

    /**
     * 获取所有菜单id，包括未展开的
     *
     * @param ids
     * @return
     */
    private List<Long> getAllExpandMenuIds(Collection<Long> ids) {
        Set<Long> list = new HashSet<>();
        List<Long> checkPids = new ArrayList<>(ids);
        while (ids.size() > 0) {
            Set<Long> set = new HashSet<>(ids);
            list.addAll(set);
            List<SysMenu> menuObjs = sysMenuMapper.selectBatchByPids(ids);
            ids = menuObjs.stream().map(SysMenu::getId).collect(Collectors.toList());
        }
        return new ArrayList<>(list);
    }
}
