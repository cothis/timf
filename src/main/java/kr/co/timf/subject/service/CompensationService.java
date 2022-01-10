package kr.co.timf.subject.service;

import kr.co.timf.subject.domain.Compensation;
import kr.co.timf.subject.domain.Voc;
import kr.co.timf.subject.repository.CompensationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CompensationService {

	private final VocReadService vocReadService;
	private final CompensationRepository compensationRepository;

	/**
	 * 배상정보 등록
	 */
	@Transactional
	public void registerCompensation(Long vocId, Compensation compensation) {
		Voc voc = vocReadService.findOne(vocId);
		compensation.registerVoc(voc);
		compensationRepository.save(compensation);
	}

	/**
	 * 배상 목록 조회
	 */
	public List<Compensation> findAll() {
		return compensationRepository.findAll();
	}
}
