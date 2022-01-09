package kr.co.timf.subject.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SELLER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Seller extends Company {
	private String managerName;
	private String managerPhone;
}
