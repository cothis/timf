package kr.co.timf.subject.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@SequenceGenerator(name = "company_seq_generator", sequenceName = "company_seq")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Company {
	@Id
	@GeneratedValue(generator = "company_seq_generator", strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
}
