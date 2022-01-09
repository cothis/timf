package kr.co.timf.subject.service;

import kr.co.timf.subject.domain.Compensation;
import kr.co.timf.subject.domain.Voc;
import kr.co.timf.subject.domain.enumeration.Party;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class CompensationServiceTest {

	@Autowired
	private CompensationService compensationService;

	@Autowired
	private VocService vocService;

	@Autowired
	private EntityManager em;

	@Test
	@DisplayName("배상정보 등록")
	public void registerCompensation() throws Exception {
		// given
		Voc voc = Voc.builder()
				.party(Party.SHIPPING)
				.content("voc test")
				.build();
		vocService.registerVoc(voc);

		Compensation compensation = Compensation.builder()
				.amount(new BigDecimal(1000))
				.build();

		// when
		compensationService.registerCompensation(voc.getId(), compensation);

		// then
		assertEquals(compensation.getVoc(), voc, "생성된 후 voc가 일치해야 한다.");
	}

	@Test
	@DisplayName("배상목록 조회")
	public void findALlCompensation() throws Exception {
		// given
		Voc voc = Voc.builder()
				.party(Party.SHIPPING)
				.content("voc test")
				.build();
		vocService.registerVoc(voc);

		Compensation compensation = Compensation.builder()
				.amount(new BigDecimal(1000))
				.build();

		compensationService.registerCompensation(voc.getId(), compensation);

		// when
		List<Compensation> findCompensations = compensationService.findAll();

		// then
		assertTrue(findCompensations.size() > 0, "배상목록 사이즈는 0보단 커야 한다.");
	}
}