package com.xld.core.service.impl.user;

import com.xld.core.dao.user.IUserMapper;
import com.xld.core.entity.DO.UserDO;
import com.xld.core.entity.VO.UserVO;
import com.xld.core.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 用户service
 * @Author xld
 * @Date 2019-09-29
 */
@Service("userService")
public class UserServiceImpl implements IUserService {

    /** 用户信息mapper */
    @Autowired
    private IUserMapper userMapper;

    /**
     * 通过账号密码返回用户信息
     * @return 用户信息
     */
    @Override
    public List<UserVO> listUser() {
        return userMapper.listUser();
    }

    /**
     * 新增实体
     * @param userDO 待新增实体
     * @return 成功：返回 true 失败：返回 false
     */
    @Override
    public UserDO insert(UserDO userDO){
        int addNumber = userMapper.insert(userDO);
        if (addNumber == 1){
            return userDO;
        }else {
            return null;
        }
    }

    /**
     * 根据主键获取用户信息
     * @param userId 用户Id
     * @return 返回 获取的实体
     */
    @Override
    public UserDO selectByPrimaryKey(Integer userId){
        return userMapper.selectByPrimaryKey(userId);
    }

    /**
     * 根据主键更新用户信息
     * @param updateUser 修改的实体
     * @return 成功：返回 true 失败：返回 false
     */
    @Override
    public boolean updateByPrimaryKeySelective(UserDO updateUser){
        return userMapper.updateByPrimaryKeySelective(updateUser) == 1;
    }

    /**
     * 根据主键删除用户信息
     * @param userId 用户Id
     * @return 成功：返回 true 失败：返回 false
     */
    @Override
    public boolean deleteByPrimaryKeySelective(Integer userId){
        UserDO deleteUser = new UserDO();
        deleteUser.setUserId(userId);
        deleteUser.setUserStatus(-1);
        return userMapper.updateByPrimaryKeySelective(deleteUser) == 1;
    }
}
