package us.dev.shipandcargo.config;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class AuthToken {
    public static SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
}
