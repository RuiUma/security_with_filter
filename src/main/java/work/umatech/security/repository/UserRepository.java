package work.umatech.security.repository;

import org.springframework.data.repository.CrudRepository;
import work.umatech.security.vo.UserTable;

public interface UserRepository extends CrudRepository<UserTable, Long> {
    UserTable save(UserTable user);

    UserTable findByEmail(String email);
}
