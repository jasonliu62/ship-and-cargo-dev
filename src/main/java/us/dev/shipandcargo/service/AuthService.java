package us.dev.shipandcargo.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public String hashPassword(String originPassword) {
        return BCrypt.hashpw(originPassword, BCrypt.gensalt());
    }

    public boolean checkPassword(String originPassword, String hashPassword) {
        return BCrypt.checkpw(originPassword, hashPassword);
    }
}
