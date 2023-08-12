package springApp.persistence.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import springApp.core.application.interfaces.repositories.IBaseRepository;
import springApp.persistence.repositories.spring.interfaces.IBaseSpringRepository;
import springApp.persistence.repositories.spring.interfaces.IBasketSpringRepository;
import springApp.persistence.repositories.spring.interfaces.IUserSpringRepository;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;


public class BaseRepository<T,
        P> implements IBaseRepository<T, P> {

    protected IBaseSpringRepository<T, P> springRepository;


    public BaseRepository(IBaseSpringRepository<T, P> springRepository) {
        this.springRepository = springRepository;

    }

    @Override
    @Async
    public CompletableFuture<Optional<T>> getById(P id) {
        return CompletableFuture.completedFuture(springRepository.findById(id));
    }

    ;

    @Override
    @Async
    public void save(T entity) {
        springRepository.save(entity);
    }

    @Override
    @Async
    public void delete(T entity) {
        springRepository.delete(entity);
    }

    @Override
    @Async
    public void deleteAll(Iterable<T> entities) {
        springRepository.deleteAll(entities);
    }

    @Override
    @Async
    public CompletableFuture<List<T>> getAll() {
        return CompletableFuture.completedFuture((List<T>) springRepository.findAll());
    }
}
