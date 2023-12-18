package com.example.case_study.model.utils;

import com.example.case_study.model.utils.generator.CodeGenerator;
import com.example.case_study.model.utils.login.Validator;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TokenManager  {
    private static TokenManager instance;
    private final Map<String, Token> tokenManagement;

    private TokenManager() {
        tokenManagement = new HashMap<>();
    }
    public static TokenManager getInstance() {
        if (instance == null) {
            instance = new TokenManager();
        }
        return instance;
    }

    public void generateToken(String username) {
        CodeGenerator codeGenerator = new CodeGenerator();
        String value =  codeGenerator.generate();
        LocalDateTime expireTime = LocalDateTime.now().plusMinutes(5);
        Token token = new Token(value, expireTime);
        tokenManagement.put(username, token);

        if (isValidToken(value)) {
            ScheduledExecutorService schedule = Executors.newScheduledThreadPool(1);
            schedule.scheduleAtFixedRate(removeToken(value), 0, 4, TimeUnit.MINUTES);
        }

    }


    public boolean isValidToken(String token) {
        return tokenManagement.containsValue(token);
    }

    public Runnable removeToken(String token) {
        for (Map.Entry<String, Token> entry : tokenManagement.entrySet()) {
            Token tokenEntry = entry.getValue();
            if (tokenEntry.getValue().equals(token)) {
                tokenManagement.remove(entry.getKey());
            }
        }
        return null;
    }

    public boolean validateToken(String token, String username) {
        if (tokenManagement.containsKey(username) && isValidToken(token)) {
            if (tokenManagement.get(username).getValue().equals(token)) {
                return  true;
            }
        }
        return false;
    }


}
