package com.xld.core.service;

import com.xld.core.entity.DO.UserDO;
import com.xld.core.entity.VO.UserVO;
import java.util.List;

/**
 * 用户
 * @Author xld
 * @Date 2019-06-16
 */
public interface IUserService {

    /**
     * 获取用户列表信息
     * @return 用户信息
     */
    List<UserVO> listUser ();

    /**
     * 新增实体
     * @param userDO 待新增实体
     * @return 成功：返回 true 失败：返回 false
     */
    UserDO insert(UserDO userDO);

    /**
     * 根据主键获取用户信息
     * @param userId 用户Id
     * @return 返回 获取的实体
     */
    UserDO selectByPrimaryKey(Integer userId);

    /**
     * 根据主键更新用户信息
     * @param updateUser 修改的实体
     * @return 成功：返回 true 失败：返回 false
     */
    boolean updateByPrimaryKeySelective(UserDO updateUser);

    /**
     * 根据主键删除用户信息
     * @param userId 用户Id
     * @return 成功：返回 true 失败：返回 false
     */
    boolean deleteByPrimaryKeySelective(Integer userId);

}
