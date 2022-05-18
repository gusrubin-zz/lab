package com.gusrubin.lab.shell.application.cli;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class MyCommands {
	
	@ShellMethod("Print a input text in uppercase")
	public String upper(@ShellOption String msg) {
		return msg.toUpperCase();
	}

}
