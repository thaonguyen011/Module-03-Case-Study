package com.example.case_study.model.utils.regexValidator;

import com.example.case_study.model.utils.login.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailRegexValidator implements Validator {
    private final String email;
    private final String EMAIL_PATTERN = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    public EmailRegexValidator(String email) {
        this.email = email;
    }

    @Override
    public boolean isCheck() {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
