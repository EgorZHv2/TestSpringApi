package springApp.persistence.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import springApp.core.application.interfaces.repositories.IUserRepository;
import springApp.core.domain.entities.User;
import springApp.persistence.repositories.spring.interfaces.IUserSpringRepository;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Repository
public class UserRepository extends BaseRepository<User, UUID> implements IUserRepository {
    @Autowired
    public UserRepository(IUserSpringRepository userSpringRepository) {
        super(userSpringRepository);
    }

    public Optional<User> getByPhoneNumber(String phoneNumber) {
        return ((IUserSpringRepository) springRepository).findFirstByPhoneNumber(phoneNumber);
    }


}
