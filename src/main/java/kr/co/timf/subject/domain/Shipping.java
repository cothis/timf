package kr.co.timf.subject.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SHIPPING")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Shipping extends Company {
	private String driver;
}
