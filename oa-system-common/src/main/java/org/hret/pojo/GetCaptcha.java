package org.hret.pojo;

import com.aliyun.tea.*;
import com.aliyun.captcha20230305.models.*;
import com.aliyun.teaopenapi.models.*;
import lombok.SneakyThrows;

/**
 * @author HRET Milky Way
 * @version 1.0
 * Date:2024/4/10
 * description TODO
 */
public class GetCaptcha {

    @SneakyThrows
    public static JsonResult getCaptchaValidate(String captchaVerifyParam) {
        // ====================== 1. 初始化配置 ======================
        Config config = new Config();
        // 设置您的AccessKey ID 和 AccessKey Secret。
        // getEnvProperty只是个示例方法，需要您自己实现AccessKey ID 和 AccessKey Secret安全的获取方式。
        config.accessKeyId = System.getenv("ACCESS_KEY_ID");
        config.accessKeySecret = System.getenv("ACCESS_KEY_SECRET");
        //设置请求地址 国内调用地址 captcha.cn-shanghai.aliyuncs.com   新加坡调用地址 xxxxx.captcha-open-southeast.aliyuncs.com （xxx为用户身份标）
        config.endpoint = "captcha.cn-shanghai.aliyuncs.com";
        // 设置连接超时为5000毫秒
        config.connectTimeout = 5000;
        // 设置读超时为5000毫秒
        config.readTimeout = 5000;
        // ====================== 2. 初始化客户端（实际生产代码中建议复用client） ======================
        com.aliyun.captcha20230305.Client client = new com.aliyun.captcha20230305.Client(config);
        // 创建APi请求
        VerifyIntelligentCaptchaRequest request = new VerifyIntelligentCaptchaRequest();
        // 本次验证的场景ID，建议传入，防止前端被篡改场景
        request.sceneId = "w438806i";
        // 前端传来的验证参数 CaptchaVerifyParam
        request.captchaVerifyParam = captchaVerifyParam;
        // ====================== 3. 发起请求） ======================
        try {
            VerifyIntelligentCaptchaResponse resp = client.verifyIntelligentCaptcha(request);
            // 建议使用您系统中的日志组件，打印返回
            // 获取验证码验证结果（请注意判空），将结果返回给前端。出现异常建议认为验证通过，优先保证业务可用，然后尽快排查异常原因。
            Boolean captchaVerifyResult = resp.body.result.verifyResult;
            // 原因code
            String captchaVerifyCode = resp.body.result.verifyCode;

            return JsonResult.ok("验证成功", captchaVerifyResult);
        } catch (TeaException error) {
            // 建议使用您系统中的日志组件，打印异常
            // 出现异常建议认为验证通过，优先保证业务可用，然后尽快排查异常原因。
            Boolean captchaVerifyResult = true;
            return JsonResult.error("验证失败", captchaVerifyResult);
        } catch (Exception _error) {
            TeaException error = new TeaException(_error.getMessage(), _error);
            // 建议使用您系统中的日志组件，打印异常
            // 出现异常建议认为验证通过，优先保证业务可用，然后尽快排查异常原因。
            Boolean captchaVerifyResult = true;
            return JsonResult.error("验证失败", captchaVerifyResult);
        }
    }

}
