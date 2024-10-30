package telran.edutrek.student.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import telran.edutrek.utils.TypePayment;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentUpdateDto {

	private TypePayment type;
	private int amount;
	private int installments;
	private String details;
}