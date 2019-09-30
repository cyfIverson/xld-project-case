package com.xld.core.entity.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
* 用户DTO
* @author xld
* @date 2019-09-29
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ApiModel(description="用户实体")
public class UserDTO {

    /**
     * 用户Id
     */
    @ApiModelProperty("用户Id")
    private Integer userId;

    /**
     * 用户帐号
     */
    @ApiModelProperty("用户帐号")
    private String userAccount;

    /**
     * 登录密码
     */
    @ApiModelProperty("登录密码")
    private String password;

    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
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