package aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect // is not stereotype annotation!
@Component
public class LoggingAspect{

    private Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    @Around("execution(* services.*.*(..))")
    public void Log(ProceedingJoinPoint joinPoint) throws Throwable{
        logger.info("Method will execute");
        joinPoint.proceed(); // delegate to intercepted method
        logger.info("Method executed");
    }
}
