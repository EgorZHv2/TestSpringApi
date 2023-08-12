package springApp.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springApp.core.application.dto.order.OrderOutputDTO;
import springApp.core.application.dto.order.ProductsInOrderDTO;
import springApp.core.application.interfaces.services.IOrderService;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {

    @Autowired
    IOrderService orderService;
    @PreAuthorize("hasAuthority('User') or hasAuthority('Admin')")
    @PostMapping
    @Async
    public void create(@RequestBody List<ProductsInOrderDTO> dtoList)throws InterruptedException, ExecutionException {
        orderService.create(dtoList);
    }

    @PreAuthorize("hasAuthority('User') or hasAuthority('Admin')")
    @GetMapping
    @Async
    public CompletableFuture<List<OrderOutputDTO>> getAll()throws InterruptedException, ExecutionException{
      return  orderService.getAllOrders();
    }
}
