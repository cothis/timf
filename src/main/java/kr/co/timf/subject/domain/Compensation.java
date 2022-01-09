package kr.co.timf.subject.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@SequenceGenerator(name = "compensation_seq_generator", sequenceName = "compensation_seq")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Compensation {
	@Id
	@GeneratedValue(generator = "compensation_seq_generator", strategy = GenerationType.IDENTITY)
	private Long id;
	private BigDecimal amount;
	@OneToOne
	@JoinColumn(name = "voc_id")
	private Voc voc;
}
