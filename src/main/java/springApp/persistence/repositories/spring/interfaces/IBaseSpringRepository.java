package springApp.persistence.repositories.spring.interfaces;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.UUID;

@NoRepositoryBean
public interface IBaseSpringRepository<T, P> extends CrudRepository<T, P> {
}
