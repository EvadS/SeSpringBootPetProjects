package com.se.sample.joinpoint;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class JoinPointAfterThrowingAspect {

    private static final Logger log = Logger.getLogger(JoinPointAfterThrowingAspect.class.getName());

    @Pointcut("execution(* com.se.sample.joinpoint.ArticleService.getArticleList(..))")
    public void articleListPointcut() { }

    @AfterThrowing(
      pointcut = "articleListPointcut()",
      throwing = "e"
    )
    public void logExceptions(JoinPoint jp, Exception e) {
        log.severe(e.getMessage());
    }
}
