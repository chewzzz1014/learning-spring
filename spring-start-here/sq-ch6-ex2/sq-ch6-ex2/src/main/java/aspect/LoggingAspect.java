package aspect;


import modal.Comment;
import org.aspectj.lang.ProceedingJoinPoint;
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
    public Object Log(ProceedingJoinPoint joinPoint) throws Throwable {
        // method name
        String methodName = joinPoint.getSignature().getName();
        // method parameter
        Object[] arguments = joinPoint.getArgs();

        logger.info("Method " + methodName + " with parameters " + Arrays.asList(arguments) + " will execute");

        // change intercepted method parameters
        // get intercepted method returned value
        Comment comment = new Comment();
        comment.setText("Some other text");
        Object[] newArguments = {comment};
        Object returnedByMethod = joinPoint.proceed(newArguments);

        logger.info("Method executed and returned " + returnedByMethod);
        return returnedByMethod;
    }
}
