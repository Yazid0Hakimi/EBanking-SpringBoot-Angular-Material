package ma.enset.paymentbackend.web;

import ma.enset.paymentbackend.entities.Payment;
import ma.enset.paymentbackend.entities.PaymentStatus;
import ma.enset.paymentbackend.entities.PaymentType;
import ma.enset.paymentbackend.entities.Student;
import ma.enset.paymentbackend.repositories.PaymentRepository;
import ma.enset.paymentbackend.repositories.StudentRepository;
import ma.enset.paymentbackend.service.PaymentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
public class PayementRestController {
    private PaymentRepository paymentRepository;
    private StudentRepository studentRepository;
    private PaymentService paymentService;

    public PayementRestController(PaymentRepository paymentRepository, StudentRepository studentRepository) {
        this.paymentRepository = paymentRepository;
        this.studentRepository = studentRepository;
    }

    @GetMapping("/payments")
    public List<Payment> payments() {
        return paymentRepository.findAll();
    }

    @GetMapping("/payments/{id}")
    public Payment payment(@PathVariable Long id) {
        return paymentRepository.findById(id).get();
    }

    @GetMapping("/students")
    public List<Student> Students() {
        return studentRepository.findAll();
    }

    @GetMapping("/students/{code}")
    public Student student(@PathVariable String code) {
        return studentRepository.findStudentByCode(code);
    }

    @GetMapping("/studentByIdProgramId")
    public List<Student> studentByIdProgramId(@RequestParam String id) {
        return studentRepository.findByProgramId(id);
    }

    @GetMapping("/student{code}/payments")
    public List<Payment> studentByCode(@PathVariable String code) {
        return paymentRepository.findByStudentCode(code);
    }

    @GetMapping("/paymentsByStatus")
    public List<Payment> studentByStatus(@RequestParam String status) {
        PaymentStatus status1 = PaymentStatus.valueOf(status);
        return paymentRepository.findByStatus(status1);
    }

    @GetMapping("/paymentsByType")
    public List<Payment> studentByType(@RequestParam String type) {
        PaymentType type1 = PaymentType.valueOf(type);
        return paymentRepository.findByType(type1);
    }

    @PutMapping("/payments/{id}")
    public String updatePayment(@PathVariable Long id, @RequestBody Payment payment) {
        return "not Set";
    }

    @PostMapping(path = "/payments", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Payment savePayment(@RequestParam MultipartFile file,
                               String code, LocalDate date, double amount,
                               PaymentType type, PaymentStatus status) throws IOException {

        return this.paymentService.savePayment(file, code, date, amount, type, status);
    }

    @GetMapping(value = "/paymentsFile/{paymentId}", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getPaymenFile(@PathVariable Long paymentId) throws IOException {
        return this.paymentService.getPaymenFile(paymentId);
    }
}
