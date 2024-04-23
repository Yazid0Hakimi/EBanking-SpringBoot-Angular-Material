package ma.enset.paymentbackend.dtos;

import jakarta.persistence.*;
import lombok.*;
import ma.enset.paymentbackend.entities.PaymentStatus;
import ma.enset.paymentbackend.entities.PaymentType;
import ma.enset.paymentbackend.entities.Student;

import java.time.LocalDate;
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public class PaymentDTO {
        private Long id;
        private LocalDate date;
        private double amount;
        private PaymentType type;
        private PaymentStatus status;

    }
