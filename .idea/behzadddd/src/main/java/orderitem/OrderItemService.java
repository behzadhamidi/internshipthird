package orderitem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    public OrderItem saveOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    public List<OrderItem> findAllOrderItemsByOrderId(Long orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }

    public OrderItem findOrderItemById(Long id) {
        Optional<OrderItem> orderItem = orderItemRepository.findById(id);
        return orderItem.orElse(null);
    }

    public OrderItem updateOrderItem(Long id, OrderItem orderItemDetails) {
        Optional<OrderItem> orderItemOptional = orderItemRepository.findById(id);
        if (orderItemOptional.isPresent()) {
            OrderItem orderItem = orderItemOptional.get();
            orderItem.setQuantity(orderItemDetails.getQuantity());
            orderItem.setPrice(orderItemDetails.getPrice());
            return orderItemRepository.save(orderItem);
        }
        return null;
    }

    public void deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
    }
}
