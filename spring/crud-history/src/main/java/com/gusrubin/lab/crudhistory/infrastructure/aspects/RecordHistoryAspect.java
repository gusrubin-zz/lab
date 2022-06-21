package com.gusrubin.lab.crudhistory.infrastructure.aspects;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.gusrubin.lab.crudhistory.domain.history.ActionLog;
import com.gusrubin.lab.crudhistory.domain.history.LogInHistory;
import com.gusrubin.lab.crudhistory.domain.history.LogInHistoryPort;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class RecordHistoryAspect {

	private final LogInHistoryPort logInHistory;

	public RecordHistoryAspect(LogInHistoryPort logInHistory) {
		this.logInHistory = logInHistory;
	}

	@Around("@annotation(com.gusrubin.lab.crudhistory.domain.history.LogInHistory)")
	public Object recordHistory(ProceedingJoinPoint joinPoint) throws Throwable {

		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		LogInHistory annotationParams = method.getAnnotation(LogInHistory.class);

		ActionLog actionLog = ActionLog.builder()
		// @formatter:off
				.occurredAt(LocalDateTime.now())
				.username(null)
				.context(annotationParams.context())
				.action(annotationParams.action())
				.commandArgs(Arrays.toString(joinPoint.getArgs()))
				.build();
		// @formatter:on

		Object result = null;

		try {
			result = joinPoint.proceed();
			actionLog.setSuccessful(Boolean.TRUE);
			actionLog.setCommandResult(result != null ? result.toString() : null);
			logInHistory.save(actionLog);

		} catch (Exception e) {
			actionLog.setSuccessful(Boolean.FALSE);
			actionLog.setCommandResult(e.getMessage());
			logInHistory.save(actionLog);
			
			throw e;
		}

		// Example logging sync way directly
//		save(actionLog);

		// Example logging async way creating own threads
//		saveWithThread(actionLog);

		// Example logging async way using Spring @Async
//		logInHistory.save(actionLog);

		return result;
	}

	private void save(ActionLog actionLog) throws InterruptedException {
		Thread.sleep(2000);
		log.info("[HISTORY] {}", actionLog);
	}

	// Perform async with new thread
	private void saveWithThread(ActionLog actionLog) {
		Thread newThread = new Thread(() -> {
			try {
				save(actionLog);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		newThread.start();
	}

}
