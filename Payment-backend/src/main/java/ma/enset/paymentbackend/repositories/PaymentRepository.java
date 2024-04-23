package ma.enset.paymentbackend.repositories;

import jakarta.transaction.Status;
import ma.enset.paymentbackend.entities.Payment;
import ma.enset.paymentbackend.entities.PaymentStatus;
import ma.enset.paymentbackend.entities.PaymentType;
import ma.enset.paymentbackend.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByStudentCode(String code);
    List<Payment> findByStatus(PaymentStatus status);
    List<Payment> findByType(PaymentType type);

}
