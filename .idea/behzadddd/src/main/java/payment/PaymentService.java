package payment;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Payment findPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    public Payment updatePayment(Long id, Payment paymentDetails) {
        Payment existingPayment = findPaymentById(id);
        if (existingPayment != null) {
            existingPayment.setAmount(paymentDetails.getAmount());
            existingPayment.setPaymentMethod(paymentDetails.getPaymentMethod());
            return paymentRepository.save(existingPayment);
        }
        return null;
    }
}
