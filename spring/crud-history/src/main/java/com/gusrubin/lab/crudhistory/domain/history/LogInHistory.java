package com.gusrubin.lab.crudhistory.domain.history;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogInHistory {
	
	/**
     * Returns the context of history log.
     * @return the context of history log
     */
	String context();
	
	ActionType action();

}
