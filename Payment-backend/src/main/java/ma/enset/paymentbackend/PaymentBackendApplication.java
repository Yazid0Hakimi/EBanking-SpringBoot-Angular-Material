package ma.enset.paymentbackend;

import ma.enset.paymentbackend.entities.Payment;
import ma.enset.paymentbackend.entities.PaymentStatus;
import ma.enset.paymentbackend.entities.PaymentType;
import ma.enset.paymentbackend.entities.Student;
import ma.enset.paymentbackend.repositories.PaymentRepository;
import ma.enset.paymentbackend.repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootApplication
public class PaymentBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentBackendApplication.class, args);
    }


    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository, PaymentRepository paymentRepository) {
        return args -> {
            studentRepository.save(Student.builder().
                    id(UUID.randomUUID().toString()).
                    code("ST1").
                    firstName("ahmed").
                    lastName("Alaoui").
                    programId("PR1").
                    build());
            studentRepository.save(Student.builder().
                    id(UUID.randomUUID().toString()).
                    code("ST2").
                    firstName("Mohamed").
                    lastName("Alami").
                    programId("PR2").
                    build());
            studentRepository.save(Student.builder().
                    id(UUID.randomUUID().toString()).
                    code("ST3").
                    firstName("Hassan").
                    lastName("Alami").
                    programId("PR1").
                    build());

            studentRepository.findAll().forEach(student -> {
                for (int i = 0; i < 10; i++) {
                    Payment payment = Payment.builder().
                            amount(1000 * (Math.random() * 2000)).
                            date(LocalDate.now()).
                            status(PaymentStatus.CREATED).
                            type(PaymentType.CASH).
                            student(student).
                            build();
                    paymentRepository.save(payment);
                }
            });

        };
    }
}
