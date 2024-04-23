package ma.enset.paymentbackend.service;

import ma.enset.paymentbackend.entities.Payment;
import ma.enset.paymentbackend.entities.PaymentStatus;
import ma.enset.paymentbackend.entities.PaymentType;
import ma.enset.paymentbackend.entities.Student;
import ma.enset.paymentbackend.repositories.PaymentRepository;
import ma.enset.paymentbackend.repositories.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

@Service
@Transactional
public class PaymentService {
    PaymentRepository paymentRepository;
    StudentRepository studentRepository;

    public PaymentService(PaymentRepository paymentRepository, StudentRepository studentRepository) {
        this.paymentRepository = paymentRepository;
        this.studentRepository = studentRepository;
    }

    public Payment savePayment(MultipartFile file,
                               String code, LocalDate date, double amount,
                               PaymentType type, PaymentStatus status) throws IOException {
        Path folderPath = Paths.get(System.getProperty("user.home"), "enset-data", "payments");
        if (!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }
        String fileName = UUID.randomUUID().toString();
        Path filePath = Paths.get(System.getProperty("user.home"), "enset-data", "payments", fileName + ".pdf");

        Files.copy(file.getInputStream(), filePath);
        Student student = studentRepository.findStudentByCode(code);
        Payment payment = Payment.builder().date(date).amount(amount).
                status(status).type(type)
                .file(filePath.toUri().toString())
                .student(student)
                .build();
        paymentRepository.save(payment);
        return payment;
    }

    public byte[] getPaymenFile(@PathVariable Long paymentId) throws IOException {
        Payment payment = paymentRepository.findById(paymentId).get();
        return Files.readAllBytes(Path.of(URI.create(payment.getFile())));
    }
}
