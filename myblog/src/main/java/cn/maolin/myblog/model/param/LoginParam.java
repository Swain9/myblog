package cn.maolin.myblog.model.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 登录参数
 *
 * @author biezhi
 * @date 2017/9/17
 */
@Data
public class LoginParam {

    @NotEmpty(message = "用户名不能为空")
    private String username;
    @NotEmpty(message = "密码不能为空")
    private String password;
    private String remeberMe;

}
