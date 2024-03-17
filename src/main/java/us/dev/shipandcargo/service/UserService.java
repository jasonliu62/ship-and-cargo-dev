package us.dev.shipandcargo.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.dev.shipandcargo.Vo.UserVo;
import us.dev.shipandcargo.config.AuthToken;
import us.dev.shipandcargo.dao.UserDao;
import us.dev.shipandcargo.domain.User;
import us.dev.shipandcargo.enums.ApiMessage;
import us.dev.shipandcargo.exception.ApiException;

import java.util.Date;
import java.util.regex.Pattern;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private AuthService authService;

    public User selectUserById(Long id) {
        User user = userDao.selectUserById(id);
        return user;
    }

    public User selectUserByEmail(String email) {
        User user = userDao.selectUserByEmail(email);
        return user;
    }

    private Boolean checkEmailValid(String email) {
        String regex = "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\."
                + "[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        return Pattern.matches(regex, email);
    }

    public int registerUser(String email, String password, String name) {
        if (!checkEmailValid(email)) {
            throw new ApiException(ApiMessage.EMAIL_FORMAT_INVALID);
        }
        User existUser = userDao.selectUserByEmail(email);
        if (null != existUser) {
            throw new ApiException(ApiMessage.EMAIL_ALREADY_REG);
        }

        // 后期对于密码的验证可能要修改，demo不需要
        User user = new User();
        // userId为自增，未来可能改为snowflake
        user.setName(name);
        user.setEmail(email);
        user.setPassword(authService.hashPassword(password));
        return userDao.insertUser(user);
    }


    public UserVo login(String email, String password) {
        User user = userDao.selectUserByEmail(email);
        if (user == null) {
            throw new ApiException(ApiMessage.LOGIN_ERROR);
        }
        if (!authService.checkPassword(password, user.getPassword())) {
            throw new ApiException(ApiMessage.LOGIN_ERROR);
        }

        long expirationTime = 36000000; // 10hrs

        String token = Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(AuthToken.key, SignatureAlgorithm.HS256)
                .compact();

        user.setToken(token);
        userDao.updateUser(user);

        UserVo userVo = new UserVo();
        userVo.setId(user.getId());
        userVo.setName(user.getName());
        userVo.setEmail(user.getEmail());
        userVo.setToken(user.getToken());
        return userVo;
    }

    public int deleteUserByEmail (String email) {
        User user = userDao.selectUserByEmail(email);
        if (user == null) {
            throw new ApiException(ApiMessage.DELETE_FAIL_USER_NA);
        }
        return userDao.deleteUserByEmail(email);
    }

    public User findUserByToken(String token) {
        return userDao.selectUserByToken(token);
    }

}
