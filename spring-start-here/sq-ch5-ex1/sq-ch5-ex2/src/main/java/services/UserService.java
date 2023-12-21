package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.CommentRepository;

@Service
public class UserService {

    @Autowired
    private CommentRepository repository;

    public CommentRepository getRepository() {
        return repository;
    }
}
