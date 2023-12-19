import model.Comment;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import proxies.EmailCommentNotificationProxy;
import repositories.DBCommentRepository;
import services.CommentService;

public class Main {
    public static void main(String[] args) {
//        var commentRepository = new DBCommentRepository();
//        var commentNotificationProxy = new EmailCommentNotificationProxy();
//
//        var commentService = new CommentService(commentRepository, commentNotificationProxy);

        var comment = new Comment();
        comment.setAuthor("chewzzz");
        comment.setText("Hello World!");
//        commentService.publishComment(comment);

        var context = new AnnotationConfigApplicationContext(ProjectConfiguration.class);
        var commentService = context.getBean(CommentService.class);
        commentService.publishComment(comment);
    }
}
