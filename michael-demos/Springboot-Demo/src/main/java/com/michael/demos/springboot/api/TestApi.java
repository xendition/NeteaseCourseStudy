package com.michael.demos.springboot.api;

import com.michael.demos.springboot.tt.User;
import com.michael.demos.springboot.tt.UserDTO;
import com.scales.common.wx.config.WechatConfig;
import com.scales.common.wx.dto.WxJsConfigDTO;
import com.scales.common.wx.service.WechatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 类功能描述:
 * <pre>
 *   xxxx
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/11/2 15:13
 */
@RestController
public class TestApi {

    @Autowired
    private WechatService wechatService;

    @PostMapping("/t")
    public Object get(@RequestBody User user) {
        final BeanCopier beanCopier = BeanCopier.create(User.class, UserDTO.class, false);
        UserDTO userDto = new UserDTO();
        beanCopier.copy(user, userDto, null);
        return userDto;
    }

    @RequestMapping("/test")
    public WxJsConfigDTO tt(@RequestParam String url) {
        return wechatService.getWxJsConfig(url);
    }
}
