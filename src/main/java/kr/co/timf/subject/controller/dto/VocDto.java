package kr.co.timf.subject.controller.dto;

import kr.co.timf.subject.domain.Voc;
import kr.co.timf.subject.domain.enumeration.Party;
import lombok.Getter;

@Getter
public class VocDto {
	private Party party;
	private String content;
	private String penaltyContent;
	private boolean confirmed;
	private boolean objected;

	public VocDto(Voc voc) {
		party = voc.getParty();
		content = voc.getContent();
		if (voc.getPenalty() != null) {
			penaltyContent = voc.getPenalty().getContent();
			confirmed = voc.getPenalty().getConfirmed();
			objected = voc.getPenalty().getObjected();
		}
	}
}
