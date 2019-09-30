package com.xld.core.dao.user;

import com.xld.core.entity.DO.UserDO;
import com.xld.core.entity.VO.UserVO;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * 企业用户数据层
 * @author xld
 */
@Repository
public interface IUserMapper {

    /**
     * 获取用户信息
     * @return 用户信息
     */
    List<UserVO> listUser();

    /**
     * 新增实体
     * @param userDO 待新增实体
     * @return 成功：返回1 失败：返回 0
     */
    int insert(UserDO userDO);

    /**
     * 根据主键获取用户信息
     * @param userId 用户Id
     * @return 返回 获取的实体
     */
    UserDO selectByPrimaryKey(Integer userId);

    /**
     * 根据主键更新用户信息
     * @param updateUser 修改的实体
     * @return 成功：返回1 失败：返回 0
     */
    int updateByPrimaryKeySelective(UserDO updateUser);
}
