package kr.co.timf.subject.domain;

import kr.co.timf.subject.domain.enumeration.Party;
import lombok.*;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "voc_seq_generator", sequenceName = "voc_seq")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Voc {
	@Id
	@GeneratedValue(generator = "voc_seq_generator", strategy = GenerationType.IDENTITY)
	private Long id;
	@Enumerated(EnumType.STRING)
	private Party party;
	private String content;

	@OneToOne
	@JoinColumn(name = "penalty_id")
	private Penalty penalty;

	@OneToOne(mappedBy = "voc", fetch = FetchType.LAZY)
	private Compensation compensation;

	public void registerPenalty(Penalty penalty) {
		this.penalty = penalty;
	}
}
