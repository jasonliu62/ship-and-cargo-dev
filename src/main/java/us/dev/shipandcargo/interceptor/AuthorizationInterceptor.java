package us.dev.shipandcargo.interceptor;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import us.dev.shipandcargo.config.AuthToken;
import us.dev.shipandcargo.domain.User;
import us.dev.shipandcargo.service.UserService;

import java.util.Date;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            if (validateToken(token)) {
                return true; // token 验证成功，请求继续
            }
        }

        // response.sendRedirect("/");


        return false; // 继续处理请求
    }

    private boolean validateToken(String token) {
        // 在这里添加您的token验证逻辑
        // 例如，检查token是否存在于数据库，并且没有过期
        User user = userService.findUserByToken(token);
        if (user == null) {
            return false;
        }


        try {
            // 解析 token
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(AuthToken.key)
                    .build()
                    .parseClaimsJws(token);

            // 获取 token 的过期时间
            Date expiration = claims.getBody().getExpiration();
            // 检查是否已过期
            return !expiration.before(new Date());

        } catch (ExpiredJwtException e) {
            // token 已经过期
            return false;
        } catch (Exception e) {
            // token 无效或解析失败
            return false;
        }
    }
}

