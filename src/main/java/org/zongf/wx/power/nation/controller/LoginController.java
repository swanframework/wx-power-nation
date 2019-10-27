package org.zongf.wx.power.nation.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.EscapedErrors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zongf.wx.power.nation.constant.SessionConstant;
import org.zongf.wx.power.nation.util.BaiduOcrUtil;
import org.zongf.wx.power.nation.vo.ocr.AccessTokenResponse;

import javax.servlet.http.HttpSession;

/**
 * @author: zongf
 * @created: 2019-10-27
 * @since 1.0
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String login(String ak, String sk, HttpSession session) {
        AccessTokenResponse accessTokenResponse = BaiduOcrUtil.requestToken(ak, sk);
        if (StringUtils.isNoneEmpty(accessTokenResponse.getAccess_token())) {
            session.setAttribute(SessionConstant.BAI_DU_AK, ak);
            session.setAttribute(SessionConstant.BAI_DU_SK, sk);
            return "success";
        }else {
            return "fail";
        }
    }

}
