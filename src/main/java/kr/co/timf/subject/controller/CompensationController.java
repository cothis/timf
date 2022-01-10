package kr.co.timf.subject.controller;

import kr.co.timf.subject.controller.dto.CompensationDetailDto;
import kr.co.timf.subject.controller.dto.CreateCompensationDto;
import kr.co.timf.subject.controller.dto.ResultDto;
import kr.co.timf.subject.domain.Compensation;
import kr.co.timf.subject.service.CompensationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
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

	private Compensation toCompensation(CreateCompensationDto compensationDto) {
		return Compensation.builder()
				.amount(compensationDto.getAmount())
				.build();
	}

	@PostMapping
	public ResponseEntity<ResultDto> createCompensation(@RequestBody @Valid CreateCompensationDto compensationDto) throws URISyntaxException {
		Compensation compensation = toCompensation(compensationDto);
		compensationService.registerCompensation(compensationDto.getVocId(), compensation);
		return ResponseEntity.created(new URI("/api/compensations/" + compensation.getId()))
				.body(new ResultDto(true));
	}
}
