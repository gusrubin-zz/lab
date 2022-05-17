package com.gusrubin.customjavaannotation.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import com.gusrubin.customjavaannotation.domain.UpperCaseUseCase;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ShellComponent
public class CommandLineApplication {

    private final UpperCaseUseCase upperCase;

    @Autowired
    public CommandLineApplication(UpperCaseUseCase upperCaseUseCase) {
	this.upperCase = upperCaseUseCase;
    }
    
    @ShellMethod("Print a word in upper case")
    public String up(String text) {
	return upperCase.toUpperCase(text);
    }
    
    @ShellMethod("Print a text in JSON")
    public String json(String text) {
	return upperCase.toUpperCase(text);
    }

}
