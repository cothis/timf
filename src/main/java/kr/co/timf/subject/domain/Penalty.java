package kr.co.timf.subject.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@SequenceGenerator(name = "penalty_seq_generator", sequenceName = "penalty_seq")
@Table(indexes = {
		@Index(name = "confirmedIndex", columnList = "confirmed"),
		@Index(name = "objectedIndex", columnList = "objected")
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Penalty {
	@Id
	@GeneratedValue(generator = "penalty_seq_generator", strategy = GenerationType.IDENTITY)
	private Long id;
	private BigDecimal amount;
	private String content;
	private Boolean confirmed;
	private Boolean objected;
}
