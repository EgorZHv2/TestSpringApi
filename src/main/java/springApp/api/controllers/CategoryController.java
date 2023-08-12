package springApp.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springApp.core.application.dto.category.CategoryCreateDTO;
import springApp.core.application.dto.category.CategoryOutputDTO;
import springApp.core.application.dto.category.CategoryUpdateDTO;
import springApp.core.application.interfaces.services.ICategoryService;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public void create(@RequestBody CategoryCreateDTO dto) {
        categoryService.create(dto);
    }

    @GetMapping
    @Async
    public CompletableFuture<List<CategoryOutputDTO>> getAll() throws InterruptedException, ExecutionException {

        return  categoryService.getAll();

    }

    @GetMapping("/{id}")
    @Async
    public CompletableFuture<CategoryOutputDTO> getById(@PathVariable UUID id) throws InterruptedException, ExecutionException{
        return categoryService.getById(id);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping
    @Async
    public void update(@RequestBody CategoryUpdateDTO dto)throws InterruptedException, ExecutionException {
        categoryService.update(dto);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("/{id}")
    @Async
    public void delete(@PathVariable UUID id)throws InterruptedException, ExecutionException {
        categoryService.delete(id);
    }

}
