package kr.co.timf.subject.domain.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum Party {
	SHIPPING("SHIPPING"), SELLER("SELLER");

	private final String code;

	Party(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
	public static Party findByCode(String code) {
		return Arrays.stream(Party.values())
				.filter(c -> c.code.equals(code))
				.findFirst()
				.orElse(null);
	}
}
