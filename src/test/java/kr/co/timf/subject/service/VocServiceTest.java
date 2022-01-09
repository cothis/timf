package kr.co.timf.subject.service;

import kr.co.timf.subject.domain.Voc;
import kr.co.timf.subject.domain.enumeration.Party;
import kr.co.timf.subject.repository.VocRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
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
	VocRepository vocRepository;

	@Test
	@DisplayName("VOC 등록 테스트")
	public void registerVoc() throws Exception {
		// given
		Voc voc = Voc.builder()
				.party(Party.SELLER)
				.content("test voc")
				.build();

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
		vocList.add(Voc.builder()
				.party(Party.SELLER)
				.content("test voc1")
				.build());
		vocList.add(Voc.builder()
				.party(Party.SELLER)
				.content("test voc2")
				.build());
		vocList.add(Voc.builder()
				.party(Party.SELLER)
				.content("test voc3")
				.build());
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
		assertThrows(IllegalStateException.class, () -> vocService.findOne(id), "해당 하는 Voc " + id + "를 찾을 수 없습니다.");
	}
}