package kr.co.timf.subject.service;

import kr.co.timf.subject.domain.Penalty;
import kr.co.timf.subject.domain.Voc;
import kr.co.timf.subject.domain.enumeration.Party;
import kr.co.timf.subject.repository.VocRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class VocServiceTest {

	@Autowired
	EntityManager em;

	@Autowired
	VocService vocService;

	@Autowired
	VocReadService vocReadService;

	@Autowired
	VocRepository vocRepository;

	private Voc generateVoc(String content) {
		return Voc.builder()
				.party(Party.SELLER)
				.content(content)
				.build();
	}

	private Penalty generatePenalty(String content) {
		return Penalty.builder()
				.amount(new BigDecimal(1000))
				.confirmed(false)
				.content("test penalty")
				.objected(false)
				.build();
	}

	@Test
	@DisplayName("VOC 등록 테스트")
	public void registerVoc() throws Exception {
		// given
		Voc voc = generateVoc("test voc");

		// when
		vocService.registerVoc(voc);

		// then
		Optional<Voc> createdVoc = vocRepository.findOne(voc.getId());

		if (!createdVoc.isPresent()) {
			fail("hi");
		} else {
			assertEquals(createdVoc.get(), voc);
		}
	}

	@Test
	@DisplayName("VOC 목록 조회")
	public void getVocs() throws Exception {
		// given
		List<Voc> vocList = new ArrayList<>();
		vocList.add(generateVoc("test voc1"));
		vocList.add(generateVoc("test voc2"));
		vocList.add(generateVoc("test voc3"));
		for (Voc voc : vocList) {
			vocService.registerVoc(voc);
		}

		// when
		List<Voc> findVocs = vocService.findAll();

		// then
		assertTrue(findVocs.size() > 0, "voc의 사이즈는 0보단 커야 합니다.");
	}

	@Test
	@DisplayName("없는 id 조회")
	public void illegalIdTest() throws Exception {
		// given
		Long id = 100L;

		// when

		// then
		assertThrows(IllegalStateException.class, () -> vocReadService.findOne(id), "해당 하는 Voc " + id + "를 찾을 수 없습니다.");
	}

	@Test
	@DisplayName("패널티 등록")
	public void registerPenalty() throws Exception {
		// given
		Voc voc = generateVoc("test voc");
		vocService.registerVoc(voc);

		Penalty penalty = generatePenalty("test penalty");

		// when
		vocService.registerPenalty(voc.getId(), penalty);

		// then
		Voc findVoc = vocReadService.findOne(voc.getId());
		assertEquals(findVoc.getPenalty(), penalty);
	}

	@Test
	@DisplayName("패널티 확인 여부 등록")
	public void registerPenaltyConfirm() throws Exception {
		// given
		Voc voc = generateVoc("test voc");
		vocService.registerVoc(voc);

		Penalty penalty = generatePenalty("test penalty");
		vocService.registerPenalty(voc.getId(), penalty);

		// when
		vocService.registerPenaltyConfirm(voc.getId(), true);

		// then
		Voc findVoc = vocReadService.findOne(voc.getId());
		assertTrue(findVoc.getPenalty().getConfirmed());
	}
}