package springApp.core.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import springApp.core.application.dto.order.OrderOutputDTO;
import springApp.core.application.dto.order.ProductsInOrderDTO;
import springApp.core.application.dto.order.ProductsInOrderOutputDTO;
import springApp.core.application.exceptions.UserNotFoundException;
import springApp.core.application.interfaces.repositories.IOrderRepository;
import springApp.core.application.interfaces.repositories.IProductRepository;
import springApp.core.application.interfaces.repositories.IProductsInOrdersRepository;
import springApp.core.application.interfaces.repositories.IUserRepository;
import springApp.core.application.interfaces.services.IOrderService;
import springApp.core.domain.entities.Order;
import springApp.core.domain.entities.ProductsInOrders;
import springApp.core.domain.entities.ProductsInOrdersId;
import springApp.infrastructure.security.models.UserDetailsImpl;
import springApp.persistence.repositories.spring.interfaces.IOrderSpringRepository;
import springApp.persistence.repositories.spring.interfaces.IProductSpringRepository;
import springApp.persistence.repositories.spring.interfaces.IProductsInOrdersSpringRepository;
import springApp.persistence.repositories.spring.interfaces.IUserSpringRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class OrderService implements IOrderService {
    @Autowired
    IOrderRepository orderRepository;
    @Autowired
    IProductsInOrdersRepository productsInOrdersRepository;
    @Autowired
    IUserRepository userRepository;
    @Autowired
    IProductRepository productRepository;

    @Override
    @Async
    public void create(List<ProductsInOrderDTO> dtoList) throws InterruptedException, ExecutionException {
        var order = new Order();
        var principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var user = userRepository.getById(principal.getId()).get();
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }
        order.setUser(user.get());
        orderRepository.save(order);

        for (var i : dtoList) {
            var product = productRepository.getById(i.getProductId()).get();
            var productInOrder = new ProductsInOrders();
            var productInOrderId = new ProductsInOrdersId();
            productInOrderId.setOrder(order);
            productInOrderId.setProduct(product.get());
            productInOrder.setPk(productInOrderId);
            productInOrder.setProductQuantity(i.getProductQuantity());
            productsInOrdersRepository.save(productInOrder);

        }


    }

    @Override
    @Async
    public CompletableFuture<List<OrderOutputDTO>> getAllOrders() throws InterruptedException,ExecutionException {
        var principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var user = userRepository.getById(principal.getId()).get();
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }
        var orders = orderRepository.getAllByUserId(user.get().getId()).get();
        var result = orders.stream().map(e -> {
            var orderOutputDTO = new OrderOutputDTO();
            orderOutputDTO.setOrderId(e.getId());
            orderOutputDTO.setProducts(e.getProductsInOrders().stream().map(ee -> {
                var productInOrder = new ProductsInOrderOutputDTO();
                productInOrder.setProductId(ee.getPk().getProduct().getId());
                productInOrder.setProductName(ee.getPk().getProduct().getName());
                productInOrder.setProductQuantity(ee.getProductQuantity());
                return productInOrder;
            }).toList());
            return orderOutputDTO;
        }).toList();
        return CompletableFuture.completedFuture(result);
    }

}
