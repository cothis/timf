package kr.co.timf.subject.controller.dto;

import kr.co.timf.subject.domain.enumeration.Party;
import kr.co.timf.subject.validator.EnumPattern;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CreateVocDto {
	@EnumPattern(regexp = "SELLER|SHIPPING", message = "SELLER 또는 SHIPPING 만 입력가능합니다")
	private Party party;

	@NotEmpty(message = "voc 내용이 비어 있습니다")
	private String content;
}
