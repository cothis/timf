package kr.co.timf.subject.service;

import kr.co.timf.subject.domain.Voc;
import kr.co.timf.subject.repository.VocRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VocReadService {
	private final VocRepository vocRepository;

	/**
	 * VOC 1개 조회
	 */
	public Voc findOne(Long vocId) {
		return vocRepository.findOne(vocId)
				.orElseThrow(() -> new IllegalStateException("해당 하는 Voc " + vocId + "를 찾을 수 없습니다."));
	}
}
