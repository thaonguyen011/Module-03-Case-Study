package com.example.case_study.model.utils.regexValidator;

import com.example.case_study.model.utils.login.Validator;

public class PassRegexValidator implements Validator {
    private final String PASSWORD_REGEX = "";
    private final String password;

    public PassRegexValidator(String password) {
        this.password = password;
    }

    @Override
    public boolean isCheck() {
        return true;
    }
}
