package telran.edutrek.student.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import telran.edutrek.utils.TypePayment;

@Getter
@AllArgsConstructor
public class PaymentDto 
{
	private LocalDateTime date;
	private TypePayment type;
	private int amount;
	private int installments;
	private String details;
}
