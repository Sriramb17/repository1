package com.project.Task.Model;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class PasswordEncode {
    @Autowired
    private Gson json;

        public static Map<String,Object> map=new HashMap<>();

    private KeyPairGenerator keyPairGenerator;
    private KeyPair keyPair;

    public PasswordEncode() throws NoSuchAlgorithmException {
        keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        keyPair = keyPairGenerator.generateKeyPair();
    }
    public String generateJwt(UserTable payload) throws Exception {
        ObjectMapper m = new ObjectMapper();
        Map<String, Object> props = m.convertValue(payload, Map.class);
        JWTCreator.Builder tokenBuilder = JWT.create()
                .withPayload(props)
                .withExpiresAt(Date.from(Instant.now().plusSeconds(300)))
                .withIssuedAt(Date.from(Instant.now()));
        System.out.println(keyPair.getPublic());
        System.out.println(keyPair.getPrivate());
        return tokenBuilder.sign(Algorithm.RSA256(((RSAPublicKey) keyPair.getPublic()), ((RSAPrivateKey) keyPair.getPrivate())));
    }
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}
