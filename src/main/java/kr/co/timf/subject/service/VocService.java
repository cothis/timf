package kr.co.timf.subject.service;

import kr.co.timf.subject.domain.Compensation;
import kr.co.timf.subject.domain.Penalty;
import kr.co.timf.subject.domain.Voc;
import kr.co.timf.subject.domain.enumeration.Party;
import kr.co.timf.subject.repository.PenaltyRepository;
import kr.co.timf.subject.repository.VocRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class VocService {

	private final VocRepository vocRepository;
	private final PenaltyRepository penaltyRepository;
	private final VocReadService vocReadService;
	private final CompensationService compensationService;

	/**
	 * VOC 등록
	 */
	@Transactional
	public void registerVoc(Voc voc) {
		vocRepository.save(voc);
		// 고객사의 귀책은 바로 배상시스템에 등록
		if (voc.getParty() == Party.SELLER) {
			compensationService.registerCompensation(voc.getId(), Compensation.builder().build());
		}
	}

	/**
	 * VOC 목록 조회
	 */
	public List<Voc> findAll() {
		return vocRepository.findAll();
	}


	/**
	 * 패널티 등록
	 */
	@Transactional
	public void registerPenalty(Long vocId, Penalty penalty) {
		Voc voc = vocReadService.findOne(vocId);
		if (voc.getPenalty() != null) {
			throw new IllegalStateException("이미 패널티가 존재합니다.");
		}
		penaltyRepository.save(penalty);
		voc.registerPenalty(penalty);
	}

	/**
	 * 패널티 확인 여부 등록
	 */
	@Transactional
	public void registerPenaltyConfirm(Long vocId, boolean confirm) {
		Voc voc = vocReadService.findOne(vocId);
		if (voc.getPenalty() == null) {
			throw new IllegalStateException("penalty가 등록되지 않았습니다");
		}
		voc.getPenalty().confirm(confirm);
	}
}
