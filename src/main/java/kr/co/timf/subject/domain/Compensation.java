package kr.co.timf.subject.domain;

import kr.co.timf.subject.domain.enumeration.Party;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@SequenceGenerator(name = "compensation_seq_generator", sequenceName = "compensation_seq")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Compensation {
	@Id
	@GeneratedValue(generator = "compensation_seq_generator", strategy = GenerationType.IDENTITY)
	private Long id;
	private BigDecimal amount;
	@OneToOne
	@JoinColumn(name = "voc_id")
	private Voc voc;

	public void registerVoc(Voc voc) {
		checkPenaltyStatus(voc);
		this.voc = voc;
		voc.registerCompensation(this);
	}

	private void checkPenaltyStatus(Voc voc) {
		if (voc.getParty() == Party.SHIPPING) {
			if (voc.getPenalty() == null) {
				throw new IllegalStateException("penalty를 먼저 등록해야 합니다");
			}
			if (!voc.getPenalty().getConfirmed()) {
				throw new IllegalStateException("아직 기사님의 패널티 확인이 완료되지 않았습니다");
			}
			if (voc.getPenalty().getObjected()) {
				throw new IllegalStateException("기사님이 이의제기를 한 상황입니다.");
			}
		}
	}
}
