package springApp.core.application.interfaces.repositories;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface IBaseRepository<T,
        P>{
    CompletableFuture<Optional<T>> getById(P id);

    void save(T entity);

    void delete(T entity);

    CompletableFuture<List<T>> getAll();
    void deleteAll(Iterable<T> entities);
}
