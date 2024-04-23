package ma.enset.paymentbackend.mappers.dtos;

import lombok.*;
import ma.enset.paymentbackend.dtos.PaymentDTO;
import ma.enset.paymentbackend.entities.Payment;
import ma.enset.paymentbackend.entities.PaymentStatus;
import ma.enset.paymentbackend.entities.PaymentType;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PaymentMapper {
    public PaymentDTO PaymentToPaymentDTO(Payment payment){

        return null;
    }
//    public Payment PaymentDTOToPayment(PaymentDTO){}

}
