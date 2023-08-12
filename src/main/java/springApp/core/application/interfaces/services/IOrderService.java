package springApp.core.application.interfaces.services;

import springApp.core.application.dto.order.OrderOutputDTO;
import springApp.core.application.dto.order.ProductsInOrderDTO;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public interface IOrderService {
    void create(List<ProductsInOrderDTO> dtoList) throws InterruptedException, ExecutionException;

    CompletableFuture<List<OrderOutputDTO>> getAllOrders() throws InterruptedException,ExecutionException;
}
