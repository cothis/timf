package kr.co.timf.subject.service;

import kr.co.timf.subject.domain.Penalty;
import kr.co.timf.subject.domain.Voc;
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

	/**
	 * VOC 등록
	 */
	@Transactional
	public void registerVoc(Voc voc) {
		vocRepository.save(voc);
	}

	/**
	 * VOC 목록 조회
	 */
	public List<Voc> findAll() {
		return vocRepository.findAll();
	}

	/**
	 * VOC 1개 조회
	 */
	public Voc findOne(Long vocId) {
		return vocRepository.findOne(vocId)
				.orElseThrow(() -> new IllegalStateException("해당 하는 Voc " + vocId + "를 찾을 수 없습니다."));
	}

	/**
	 * 패널티 등록
	 */
	@Transactional
	public void registerPenalty(Long vocId, Penalty penalty) {
		penaltyRepository.save(penalty);
		Voc voc = findOne(vocId);
		voc.registerPenalty(penalty);
	}
}
