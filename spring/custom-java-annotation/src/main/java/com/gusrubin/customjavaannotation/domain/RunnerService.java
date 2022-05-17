package com.gusrubin.customjavaannotation.domain;

import com.gusrubin.customjavaannotation.infrastructure.PrintUpperCase;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RunnerService implements UpperCaseUseCase {

    @PrintUpperCase
    @Override
    public String toUpperCase(String text) {
	return text.toUpperCase();
    }

}
