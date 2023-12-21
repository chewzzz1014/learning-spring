package services;

import org.springframework.stereotype.Service;

@Service
public class CommentService {

    // for eager instantiation, bean is initialised immediately after context is created
    // this is print right after spring context
    public CommentService() {
        System.out.println("CommentService instance created (eager instantiation)");
    }
}
