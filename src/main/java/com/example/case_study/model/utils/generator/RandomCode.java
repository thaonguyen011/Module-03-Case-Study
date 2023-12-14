package com.example.case_study.model.utils.generator;

public class RandomCode implements IGenerator<Integer>{
    private final int CODE_SIZE = 6;
    @Override
    public Integer generate() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < CODE_SIZE; i++) {
            int randomNum = (int) (Math.random()*9);
            result.append(randomNum);
        }
        return Integer.parseInt(result.toString());

    }
}
