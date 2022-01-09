package kr.co.timf.subject.controller.dto;

import kr.co.timf.subject.domain.Compensation;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CompensationDetailDto {
	private VocDto voc;
	private BigDecimal amount;

	public CompensationDetailDto(Compensation compensation) {
		voc = new VocDto(compensation.getVoc());
		amount = compensation.getAmount();
	}
}
