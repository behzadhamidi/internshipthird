package order;

import orderitem.OrderItem;
import orderitem.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.User;
import user.UserRepository;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private UserRepository userRepository;

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order findOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order updateOrder(Long id, Order orderDetails) {
        Order existingOrder = findOrderById(id);
        if (existingOrder != null) {
            existingOrder.setTotalAmount(orderDetails.getTotalAmount());
            existingOrder.setStatus(orderDetails.getStatus());
            return orderRepository.save(existingOrder);
        }
        return null;
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public List<Order> findOrdersByUser(User user) {
        return orderRepository.findByUser(user);
    }

    public OrderItem addOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    public java.util.List<OrderItem> findOrderItemsByOrder(Order order) {
        return orderItemRepository.findByOrder(order);
    }
}
