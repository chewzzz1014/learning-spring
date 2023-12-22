package aspect;


import modal.Comment;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {
    private Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    @Around("execution(* services.*.*(..))")
    public void Log(ProceedingJoinPoint joinPoint) throws Throwable {
        // method name
        String methodName = joinPoint.getSignature().getName();
        // method parameter
        Object[] arguments = joinPoint.getArgs();

        logger.info("Method " + methodName + " with parameters " + Arrays.asList(arguments) + " will execute");

        // change intercepted method parameters
        Comment comment = new Comment();
        comment.setText("Some other text");
        Object[] newArguments = {comment};
        joinPoint.proceed(newArguments);

        logger.info("Method executed");
    }

    @AfterReturning(value = "@annotation(annotation.ToLog)", returning = "returnedValue")
    public void log(Object returnedValue) throws Throwable {
        logger.info("Method executed and returned " + returnedValue);
    }
}