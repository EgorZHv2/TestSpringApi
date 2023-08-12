package springApp.persistence.repositories.spring.interfaces;

import jdk.jfr.Name;
import org.springframework.scheduling.annotation.Async;
import springApp.core.domain.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface IUserSpringRepository extends IBaseSpringRepository<User, UUID> {


   Optional<User> findFirstByPhoneNumber(String phoneNumber);





}
