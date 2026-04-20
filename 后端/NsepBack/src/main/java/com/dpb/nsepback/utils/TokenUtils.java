package com.dpb.nsepback.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.dpb.nsepback.entity.User;
import com.dpb.nsepback.service.IUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class TokenUtils {

    private static IUserService staticUserService;
    private static String jwtSecret;
    private static Integer tokenExpireHours;

    @Resource
    private IUserService userService;

    @Value("${nsep.jwt.secret}")
    private String appJwtSecret;

    @Value("${nsep.jwt.expire-hours:2}")
    private Integer appTokenExpireHours;

    @PostConstruct
    public void setUserService() {
        staticUserService = userService;
        if (StrUtil.isBlank(appJwtSecret)
                || "change-this-jwt-secret-in-production".equals(appJwtSecret)
                || appJwtSecret.length() < 32) {
            throw new IllegalStateException("NSEP_JWT_SECRET must be set to a strong secret (length >= 32)");
        }
        jwtSecret = appJwtSecret;
        tokenExpireHours = appTokenExpireHours;
    }

    /**
     * 生成token
     *
     * @return
     */
    public static String genToken(String userId, Integer role) {
        if (role == null) {
            throw new IllegalArgumentException("role must not be null when generating token");
        }
        return JWT.create().withAudience(userId) // 将 user id 保存到 token 里面,作为载荷
                .withClaim("role", role)
                .withExpiresAt(DateUtil.offsetHour(new Date(), tokenExpireHours))
                .sign(Algorithm.HMAC256(jwtSecret));
    }

    public static String getJwtSecret() {
        return jwtSecret;
    }

    /**
     * 获取当前登录的用户信息
     *
     * @return user对象
     */
    public static User getCurrentUser() {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader("token");
            if (StrUtil.isNotBlank(token)) {
                String userId = JWT.decode(token).getAudience().get(0);
                return staticUserService.getById(Integer.valueOf(userId));
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}
