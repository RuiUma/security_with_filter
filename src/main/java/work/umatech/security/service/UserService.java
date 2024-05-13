package work.umatech.security.service;

import org.springframework.stereotype.Service;
import work.umatech.security.repository.UserRepository;
import work.umatech.security.vo.UserTable;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository){
        this.repository = repository;
    }
    public UserTable getUserByEmail(String email) {
        return repository.findByEmail(email);
    }

    public UserTable save(UserTable user) {
        return repository.save(user);
    }
}
