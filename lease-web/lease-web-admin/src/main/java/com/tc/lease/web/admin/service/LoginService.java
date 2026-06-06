package com.tc.lease.web.admin.service;

import com.tc.lease.web.admin.vo.login.CaptchaVo;
import com.tc.lease.web.admin.vo.login.LoginVo;
import com.tc.lease.web.admin.vo.system.user.SystemUserInfoVo;

public interface LoginService {

    CaptchaVo getCaptcha();

    String login(LoginVo loginVo);

    SystemUserInfoVo getSystemUserInfoById(Long userId);
}
