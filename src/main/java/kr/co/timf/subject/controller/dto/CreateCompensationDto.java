package kr.co.timf.subject.controller.dto;

import lombok.Getter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
public class CreateCompensationDto {
	@NotNull(message = "voc Id가 반드시 필요합니다")
	private Long vocId;

	@NotNull
	@DecimalMin(value = "0.000001", message = "배상 금액은 0보다 크게 설정해주세요")
	private BigDecimal amount;
}
