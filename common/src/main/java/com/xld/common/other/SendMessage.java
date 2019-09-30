package com.xld.common.other;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import java.util.Random;

/**
 * 短信验证码工具类
 * @author xld
 */
public class SendMessage {

    /**
     * 创建短信验证码
     * @return 生成的验证码字符串
     */
    public static String createMessageCode() {
        // 生成随机类
        Random random = new Random();
        StringBuffer sRand = new StringBuffer();
        for (int i = 0; i < 6; i++) {
            sRand = sRand.append(random.nextInt(10));
        }
        return String.valueOf(sRand);
    }

    /**
     * 短信验证码
     * @param phoneNumber 手机号
     * @param code        验证码
     * @return 成功返回true，失败返回false
     */
    public static boolean sendMessageCode(String phoneNumber, String code, int type) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAIkwca9eEd68Xb", "jnF48UT5BSdgwX61BJnsxaUBLABqML");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phoneNumber);
        request.putQueryParameter("SignName", "稚子系统");
        request.putQueryParameter("TemplateParam", "{\"code\":\"" + code + "\"}");

        if (type == 1) {
            //注册模板
            request.putQueryParameter("TemplateCode", "SMS_166190003");
        } else if
        (type == 2) {
            //修改密码模板
            request.putQueryParameter("TemplateCode", "SMS_166190002");
        }else if(type == 3){
            //变更信息模板
            request.putQueryParameter("TemplateCode", "SMS_166190001");
        }else if(type == 4) {
            //身份验证模板
            request.putQueryParameter("TemplateCode", "SMS_166190006");
        }else {
            return false;
        }

        try {
            CommonResponse response = client.getCommonResponse(request);
            JSONObject jsonObject = JSON.parseObject(response.getData());
            if (jsonObject == null) {
                return false;
            }
            String responseCode = jsonObject.getString("Code");
            if ("OK".equals(responseCode)) {
                LogUtil.printInfoLog("\n【发送验证码】\n手机号："+phoneNumber+"\n验证码："+code);
                return true;
            } else {
                LogUtil.printInfoLog(response.getData());
                return false;
            }
        } catch (ServerException e) {
            LogUtil.printErrorLog(e);
            return false;
        } catch (ClientException e) {
            LogUtil.printErrorLog(e);
            return false;
        }
    }
}
