package kr.co.timf.subject.controller.dto;

import lombok.Getter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Getter
public class CreatePenaltyDto {

	@DecimalMin(value = "0.000001", message = "배상 금액은 0보다 크게 설정해주세요")
	private BigDecimal amount;

	@NotEmpty(message = "content가 비어있습니다")
	private String content;
}
