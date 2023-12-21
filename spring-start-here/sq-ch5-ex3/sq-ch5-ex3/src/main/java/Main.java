import config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import services.UserService;

public class Main {
    public static void main(String[] args) {
        // 1. created context
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        // 2. eager instantiation: CommentService bean is created

        // 3. lazy instantiation (instantiate when calls)
        System.out.println("Before retrieving the UserService");
        var service = context.getBean(UserService.class);
        System.out.println("After retrieving the UserService");
    }
}
