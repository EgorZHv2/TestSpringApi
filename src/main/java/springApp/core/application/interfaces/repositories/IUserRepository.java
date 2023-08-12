package springApp.core.application.interfaces.repositories;

import springApp.core.domain.entities.User;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface IUserRepository extends IBaseRepository<User, UUID> {
    Optional<User> getByPhoneNumber(String phoneNumber);
}
