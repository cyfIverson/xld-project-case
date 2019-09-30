package com.xld.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.xld.common.bean.ENUM.HttpStatusEnum;
import com.xld.common.bean.BO.ResultBO;
import com.xld.common.result.Results;
import com.xld.common.time.DateUtil;
import com.xld.core.entity.DO.UserDO;
import com.xld.core.entity.DTO.UserDTO;
import com.xld.core.entity.VO.UserVO;
import com.xld.core.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @descript 用户controller
 * @author xld
 * @date 2019-09-29 11:45 AM
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/web/user")
public class UserController extends BaseController {

    /** 用户service */
    @Autowired
    private IUserService userService;

    /**
     * 获取用户列表
     */
    @ApiOperation(value="获取用户列表")
    @GetMapping(value = "")
    public ResultBO listUser() {
        List<UserVO> userList = userService.listUser();
        JSONObject data = new JSONObject();
        data.put("userList", userList);
        return Results.result(HttpStatusEnum.SUCCESS, data);
    }

    /**
     * 新增用户
     */
    @PostMapping(value = "")
    @ApiOperation(value="新增用户", notes="根据用户对象创建用户")
    public ResultBO addUser(@RequestBody UserDTO userDTO) {
        UserDO addUser = new UserDO();
        BeanUtils.copyProperties(userDTO, addUser);
        addUser.setAddTime(DateUtil.getCurrentTime());
        addUser.setUserStatus(1);
        addUser = userService.insert(addUser);
        if (addUser != null) {
            JSONObject data = new JSONObject();
            data.put("userId", addUser.getUserId());
            return Results.result(HttpStatusEnum.SUCCESS, data);
        } else {
            return Results.fail(HttpStatusEnum.FAIL);
        }
    }

    /**
     * 根据用户Id获取用户信息
     */
    @ApiOperation(value="根据用户Id获取用户信息", notes="根据url的userId来获取用户详细信息")
    @ApiImplicitParam(paramType = "path", dataType = "Integer", name = "userId", value = "用户编号", required = true, example = "1")
    @GetMapping(value = "{userId}")
    public ResultBO addUser(@PathVariable("userId") Integer userId) {
        UserDO userDO = userService.selectByPrimaryKey(userId);
        JSONObject data = new JSONObject();
        data.put("userId", userDO);
        return Results.result(HttpStatusEnum.SUCCESS, data);
    }

    /**
     * 修改用户
     */
    @ApiOperation(value="修改用户", notes="根据用户对象修改用户")
    @PutMapping(value = "")
    public ResultBO modifyUser(@RequestBody UserDTO userDTO) {
        UserDO addUser = new UserDO();
        BeanUtils.copyProperties(userDTO, addUser);
        boolean updateFlag = userService.updateByPrimaryKeySelective(addUser);
        if (updateFlag) {
            return Results.result(HttpStatusEnum.SUCCESS);
        } else {
            return Results.fail(HttpStatusEnum.FAIL);
        }
    }

    /**
     * 删除用户
     */
    @ApiOperation(value="删除用户", notes="根据url的useId来指定删除对象")
    @ApiImplicitParam(paramType = "path", dataType = "Integer", name = "userId", value = "用户编号", required = true, example = "1")
    @DeleteMapping(value = "{userId}")
    public ResultBO deleteUser(@PathVariable("userId")Integer userId) {
        boolean deleteFlag = userService.deleteByPrimaryKeySelective(userId);
        if (deleteFlag) {
            return Results.result(HttpStatusEnum.SUCCESS);
        } else {
            return Results.fail(HttpStatusEnum.FAIL);
        }
    }
}
