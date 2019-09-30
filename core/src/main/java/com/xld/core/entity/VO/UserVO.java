package com.xld.core.entity.VO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
* 用户VO
* @author xld
* @date 2019-09-29
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class UserVO {

    /**
     * 用户Id
     */
    private Integer userId;

    /**
     * 用户帐号
     */
    private String userAccount;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 1:启用 2：禁用 -1：删除
     */
    private Integer userStatus;

    /**
     * 添加时间
     */
    private Timestamp addTime;

    /** 上次登录的企业Id*/
    private Integer lastLoginCompanyId;
}