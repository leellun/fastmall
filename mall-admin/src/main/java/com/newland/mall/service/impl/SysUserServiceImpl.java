package com.newland.mall.service.impl;

import com.github.pagehelper.PageInfo;
import com.newland.mall.common.UserConstant;
import com.newland.mall.entity.SysRole;
import com.newland.mall.entity.SysUser;
import com.newland.mall.entity.SysUserRole;
import com.newland.mall.enumeration.BasicEnum;
import com.newland.mall.enums.UserServiceErrorEnum;
import com.newland.mall.exception.BusinessException;
import com.newland.mall.mapper.SysRoleMapper;
import com.newland.mall.mapper.SysUserMapper;
import com.newland.mall.mapper.SysUserRoleMapper;
import com.newland.mall.model.LoginUser;
import com.newland.mall.model.dto.LoginDTO;
import com.newland.mall.model.dto.SysUserDto;
import com.newland.mall.model.dto.UserPassVO;
import com.newland.mall.model.dto.UserQueryDTO;
import com.newland.mall.model.vo.SysUserItemVo;
import com.newland.mall.model.vo.SysUserVo;
import com.newland.mall.service.SysMenuService;
import com.newland.mall.service.SysUserService;
import com.newland.mall.utils.AesUtils;
import com.newland.mall.utils.AssertUtil;
import com.newland.mall.utils.Md5Util;
import com.newland.mybatis.page.PageWrapper;
import com.newland.mybatis.service.impl.ServiceImpl;
import com.newland.security.utils.SecurityUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author leellun
 * @since 2022-12-06
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public LoginUser login(LoginDTO loginDTO) {
        String password = AesUtils.decrypt(loginDTO.getPassword());
        String md5Password = Md5Util.encrypt(password);
        SysUser sysUser = baseMapper.selectByUsername(loginDTO.getUsername());
        AssertUtil.notNull(sysUser, UserServiceErrorEnum.USER_NOT_EXIST.getDesc());
        AssertUtil.isTrue(md5Password.endsWith(sysUser.getPassword()), UserServiceErrorEnum.USER_PASSWORD_ERROR.getDesc());
        SysUser loginTimeUser = new SysUser();
        loginTimeUser.setLastLoginTime(LocalDateTime.now());
        baseMapper.updateByPrimaryKeySelective(loginTimeUser);
        LoginUser loginUser = new LoginUser();
        loginUser.setUsername(sysUser.getUsername());
        loginUser.setMobile(sysUser.getPhone());
        loginUser.setUserId(sysUser.getId());
        loginUser.setAuthorities(sysMenuService.getPermissions(sysUser.getId()));
        return loginUser;
    }

    @Override
    public PageInfo<SysUserVo> getUsers(UserQueryDTO userQueryDTO) {
        PageInfo<SysUserVo> result = PageWrapper.page(userQueryDTO, () -> baseMapper.selectUsersPage(userQueryDTO));
        result.getList().forEach(item -> {
            List<SysRole> roles = sysRoleMapper.getRoleWithIdByUserId(item.getId(), null);
            item.setRoleNames(roles.stream().map(SysRole::getName).collect(Collectors.toList()));
        });
        return result;
    }

    @Override
    public SysUserItemVo getUser(Long userId) {
        SysUser sysUser = baseMapper.selectByPrimaryKey(userId);
        AssertUtil.notNull(sysUser, UserServiceErrorEnum.USER_NOT_EXIST.getDesc());
        SysUserItemVo sysUserVo = new SysUserItemVo();
        BeanUtils.copyProperties(sysUser, sysUserVo);
        sysUserVo.setRoleIds(sysUserRoleMapper.selectRoleIdsByUserId(userId));

        return sysUserVo;
    }

    @Override
    @Transactional(noRollbackFor = {BusinessException.class}, rollbackFor = {Exception.class})
    public void addUser(SysUserDto sysUserDto) {
        AssertUtil.isTrue(sysUserDto.getRoleIds() != null && sysUserDto.getRoleIds().size() > 0, UserServiceErrorEnum.ROLE_NOT_SELECT.getDesc());
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserDto, sysUser);
        SysUser dbUser = baseMapper.selectByUsername(sysUser.getUsername());
        AssertUtil.isNull(dbUser, UserServiceErrorEnum.USER_EXIST.getDesc());
        dbUser = baseMapper.selectByPhone(sysUser.getPhone());
        AssertUtil.isNull(dbUser, UserServiceErrorEnum.USER_PHONE_EXIST.getDesc());
        dbUser = baseMapper.selectByEmail(sysUser.getEmail());
        AssertUtil.isNull(dbUser, UserServiceErrorEnum.USER_EMAIL_EXIST.getDesc());
        sysUser.setPassword(Md5Util.encrypt(UserConstant.DEFAULT_PASSWORD));
        sysUser.setEnabled(BasicEnum.YES.getKey());
        sysUser.setMustResetPwd(BasicEnum.YES.getKey());
        sysUser.setPwdFailsCount(0);
        sysUser.setCanDeleted(BasicEnum.YES.getKey());
        List<SysRole> roleList = sysRoleMapper.selectByIds(sysUserDto.getRoleIds());
        AssertUtil.isTrue(roleList.size() > 0, UserServiceErrorEnum.ROLE_NOT_SELECT.getDesc());

        baseMapper.insert(sysUser);

        roleList.forEach(item -> sysUserRoleMapper.insert(new SysUserRole(sysUser.getId(), item.getId())));
    }

    @Override
    @Transactional(noRollbackFor = {BusinessException.class}, rollbackFor = {Exception.class})
    public void updateUser(SysUserDto sysUserDto) {
        AssertUtil.isTrue(sysUserDto.getRoleIds() != null && sysUserDto.getRoleIds().size() > 0, UserServiceErrorEnum.ROLE_NOT_SELECT.getDesc());
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserDto, sysUser);
        SysUser dbUser = baseMapper.selectByPrimaryKey(sysUser.getId());
        AssertUtil.notNull(dbUser, UserServiceErrorEnum.USER_NOT_EXIST.getDesc());
        if (!StringUtils.equals(dbUser.getUsername(), sysUser.getUsername())) {
            SysUser dbData = baseMapper.selectByUsername(sysUser.getUsername());
            AssertUtil.isNull(dbData, UserServiceErrorEnum.USER_EXIST.getDesc());
        }
        if (!StringUtils.equals(dbUser.getPhone(), sysUser.getPhone())) {
            if (StringUtils.isNotEmpty(sysUser.getPhone())) {
                SysUser dbData = baseMapper.selectByPhone(sysUser.getPhone());
                AssertUtil.isNull(dbData, UserServiceErrorEnum.USER_PHONE_EXIST.getDesc());
            }
        }
        if (!StringUtils.equals(dbUser.getEmail(), sysUser.getEmail())) {
            if (StringUtils.isNotEmpty(sysUser.getEmail())) {
                SysUser dbData = baseMapper.selectByEmail(sysUser.getEmail());
                AssertUtil.isNull(dbData, UserServiceErrorEnum.USER_EMAIL_EXIST.getDesc());
            }
        }
        sysUserRoleMapper.deleteByUserId(sysUser.getId());

        List<SysRole> roleList = sysRoleMapper.selectByIds(sysUserDto.getRoleIds());
        AssertUtil.isTrue(roleList.size() > 0, UserServiceErrorEnum.ROLE_NOT_SELECT.getDesc());
        baseMapper.updateByPrimaryKeySelective(sysUser);

        roleList.forEach(item -> sysUserRoleMapper.insert(new SysUserRole(sysUser.getId(), item.getId())));
    }

    @Override
    public void enableUser(Long id, Integer enable) {
        SysUser dbUser = baseMapper.selectByPrimaryKey(id);
        AssertUtil.notNull(dbUser, UserServiceErrorEnum.USER_NOT_EXIST.getDesc());
        SysUser updateUser = new SysUser();
        updateUser.setEnabled(enable);
        updateUser.setId(dbUser.getId());
        baseMapper.updateByPrimaryKeySelective(updateUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, noRollbackFor = BusinessException.class)
    public void deleteUser(Set<Long> ids) {
        int count = baseMapper.deleteByUserIds(ids);
        AssertUtil.isTrue(count > 0, UserServiceErrorEnum.USER_DELETE_FAIL.getDesc());
        //删除用户关联角色
        sysUserRoleMapper.deleteByUserIds(ids);
    }

    @Override
    public void updatePass(UserPassVO userPassVO) {
        LoginUser loginUser = SecurityUtil.getLoginUser();
        SysUser dbUser = baseMapper.selectByPrimaryKey(loginUser.getUserId());
        String oldMd5Password = Md5Util.encrypt(AesUtils.decrypt(userPassVO.getOldPass()));
        AssertUtil.isNull(dbUser.getPassword().equals(oldMd5Password), UserServiceErrorEnum.USER_OLD_PASSWORD_ERROR.getDesc());
        String md5Password = Md5Util.encrypt(AesUtils.decrypt(userPassVO.getNewPass()));
        SysUser updateUser = new SysUser();
        updateUser.setPassword(md5Password);
        updateUser.setPwdResetTime(LocalDateTime.now());
        updateUser.setId(dbUser.getId());
        baseMapper.updateByPrimaryKeySelective(updateUser);
    }

    @Override
    public void resetPass(Long id) {
        SysUser dbUser = baseMapper.selectByPrimaryKey(id);
        AssertUtil.notNull(dbUser, UserServiceErrorEnum.USER_NOT_EXIST.getDesc());
        String md5Password = Md5Util.encrypt(UserConstant.DEFAULT_PASSWORD);
        SysUser updateUser = new SysUser();
        updateUser.setPassword(md5Password);
        updateUser.setPwdResetTime(LocalDateTime.now());
        updateUser.setId(dbUser.getId());
        baseMapper.updateByPrimaryKeySelective(updateUser);
    }

    @Override
    public void updateAvatar(String avatar) {
        LoginUser loginUser = SecurityUtil.getLoginUser();
        SysUser dbUser = baseMapper.selectByPrimaryKey(loginUser.getUserId());
        AssertUtil.notNull(dbUser, UserServiceErrorEnum.USER_NOT_EXIST.getDesc());
        SysUser updateUser = new SysUser();
        updateUser.setAvatar(avatar);
        updateUser.setId(dbUser.getId());
        baseMapper.updateByPrimaryKeySelective(updateUser);
    }

    @Override
    public List<SysUser> all() {
        return baseMapper.listAll();
    }
}
