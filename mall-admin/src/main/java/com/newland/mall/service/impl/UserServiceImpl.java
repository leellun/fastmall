package com.newland.mall.service.impl;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.User;
import com.newland.mall.enums.UserGenderEnum;
import com.newland.mall.exception.BusinessException;
import com.newland.mall.mapper.UserMapper;
import com.newland.mall.model.dto.wx.UserProfileDTO;
import com.newland.mall.model.vo.UserVO;
import com.newland.mall.service.UserService;
import com.newland.mall.utils.AssertUtil;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.page.PageWrapper;
import com.newland.mybatis.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 用户表 服务实现类
 *
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public UserVO getUserVoByUserId(Long userId) {
        User user = baseMapper.selectByPrimaryKey(userId);
        UserVO userVo = new UserVO();
        userVo.setNickname(user.getNickname());
        userVo.setAvatar(user.getAvatar());
        return userVo;
    }

    @Override
    public PageInfo<User> listUserPage(String username, String mobile, PageEntity pageEntity) {
        return PageWrapper.page(pageEntity, () -> baseMapper.listByUsernameAndMobile(username, mobile));
    }

    @Override
    public User get(Long id) {
        User dbUser = baseMapper.selectByPrimaryKey(id);
        AssertUtil.notNull(dbUser, "数据不存在");
        return dbUser;
    }

    @Override
    public void add(User user) {
        baseMapper.insertSelective(user);
    }

    @Override
    public void update(User user) {
        User dbUser = baseMapper.selectByPrimaryKey(user.getId());
        AssertUtil.notNull(dbUser, "数据不存在");
        baseMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public Long count() {
        return baseMapper.count();
    }

    @Override
    public User getByOid(String openId) {
        return baseMapper.getByOpenId(openId);
    }

    @Override
    public void updateProfile(Long userId, UserProfileDTO userProfileDTO) {
        String avatar = userProfileDTO.getAvatar();
        Integer gender = userProfileDTO.getGender();
        String nickname = userProfileDTO.getNickname();

        User user = baseMapper.selectByPrimaryKey(userId);
        if (StringUtils.hasText(avatar)) {
            user.setAvatar(avatar);
        }
        if (gender != null) {
            if (UserGenderEnum.valueOf(gender) != null) {
                user.setGender(gender);
            }
        }
        if (StringUtils.hasText(nickname)) {
            user.setNickname(nickname);
        }

        if (baseMapper.updateByPrimaryKeySelective(user) == 0) {
            throw new BusinessException("更新失败");
        }
    }
    @Override
    public User getInfo(Long userId) {
        User dbUser = baseMapper.selectByPrimaryKey(userId);
        User user=new User();
        user.setNickname(dbUser.getNickname());
        user.setAvatar(dbUser.getAvatar());
        user.setGender(dbUser.getGender());
        user.setMobile(dbUser.getMobile());
        return user;
    }
}