package kr.co.timf.subject.controller.dto;

import kr.co.timf.subject.domain.Compensation;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CompensationDto {
	private BigDecimal amount;

	public CompensationDto(Compensation compensation) {
		if (compensation != null) {
			amount = compensation.getAmount();
		}
	}
}
