package com.example.case_study.model.utils.generator;

public class CodeGenerator implements IGenerator<String>{
    private final int CODE_SIZE = 6;
    @Override
    public String generate() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < CODE_SIZE; i++) {
            int randomNum = (int) (Math.random()*9);
            result.append(randomNum);
        }
        return result.toString();

    }
}
