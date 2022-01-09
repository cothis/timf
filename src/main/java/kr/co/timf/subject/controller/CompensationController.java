package kr.co.timf.subject.controller;

import kr.co.timf.subject.controller.dto.CompensationDetailDto;
import kr.co.timf.subject.domain.Compensation;
import kr.co.timf.subject.service.CompensationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/compensations")
@RequiredArgsConstructor
public class CompensationController {

	private final CompensationService compensationService;

	@GetMapping
	public List<CompensationDetailDto> findAllCompensation() {
		List<Compensation> compensations = compensationService.findAll();
		return compensations.stream()
				.map(CompensationDetailDto::new)
				.collect(Collectors.toList());
	}
}
