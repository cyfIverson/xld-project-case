package com.xld.core.entity.BO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * 用户登录Session
 * @author xld
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class UserSessionBO implements Serializable {

	private static final long serialVersionUID = -5152044654113146889L;

	/** 用户Id */
	private int userId;
	/** 企业用户帐号 */
	private String userAccount;
	/** 手机号 */
	private String phoneNumber;
	/**  过期时间 */
	private long outTime;
	/**  登录的企业Id */
	private int loginCompanyId;
}
