package springApp.core.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import springApp.core.application.dto.category.CategoryCreateDTO;
import springApp.core.application.dto.category.CategoryOutputDTO;
import springApp.core.application.dto.category.CategoryUpdateDTO;
import springApp.core.application.exceptions.CategoryNotFoundException;
import springApp.core.application.interfaces.repositories.ICategoryRepository;
import springApp.core.application.interfaces.services.ICategoryService;
import springApp.core.domain.entities.Category;
import springApp.persistence.repositories.spring.interfaces.ICategorySpringRepository;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;
    @Override
    @Async
    public void create(CategoryCreateDTO dto){
        var category = new Category();
        category.setName(dto.getName());
        categoryRepository.save(category);
    }
    @Override
    @Async
    public CompletableFuture<List<CategoryOutputDTO>> getAll() throws InterruptedException, ExecutionException {
        var categories = categoryRepository.getAll().get();
        var result = categories.stream().map(e->{
            var mappedEntity = new CategoryOutputDTO();
            mappedEntity.setName(e.getName());
            mappedEntity.setId(e.getId());
            return mappedEntity;
        }).toList();
        return CompletableFuture.completedFuture(result);
    }
    @Override
    @Async
    public CompletableFuture<CategoryOutputDTO> getById(UUID id) throws InterruptedException,ExecutionException{
        var entity = categoryRepository.getById(id).get();
        if(entity.isEmpty()){
           throw  new CategoryNotFoundException();
        }
        var category = entity.get();
        var result = new CategoryOutputDTO();
        result.setId(category.getId());
        result.setName(category.getName());
        return CompletableFuture.completedFuture(result);
    }
    @Override
    @Async
    public void update(CategoryUpdateDTO dto) throws InterruptedException,ExecutionException{
        var entity = categoryRepository.getById(dto.getId()).get();
        if(entity.isEmpty()){
            throw  new CategoryNotFoundException();
        }
        var category = entity.get();
        category.setId(dto.getId());
        category.setName(dto.getName());
        categoryRepository.save(category);
    }
    @Override
    @Async
    public void delete(UUID id) throws InterruptedException,ExecutionException{
        var entity = categoryRepository.getById(id).get();
        if(entity.isEmpty()){
            throw  new CategoryNotFoundException();
        }
        var category = entity.get();
        categoryRepository.delete(category);
    }
}
