package kr.co.timf.subject.controller;

import kr.co.timf.subject.controller.dto.CreatePenaltyDto;
import kr.co.timf.subject.controller.dto.CreateVocDto;
import kr.co.timf.subject.controller.dto.ResultDto;
import kr.co.timf.subject.controller.dto.VocDetailDto;
import kr.co.timf.subject.domain.Penalty;
import kr.co.timf.subject.domain.Voc;
import kr.co.timf.subject.service.VocReadService;
import kr.co.timf.subject.service.VocService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/vocs")
@RequiredArgsConstructor
public class VocController {

	private final VocService vocService;
	private final VocReadService vocReadService;

	@GetMapping
	public List<VocDetailDto> findAllVoc() {
		List<Voc> vocs = vocService.findAll();
		return vocs.stream()
				.map(VocDetailDto::new)
				.collect(Collectors.toList());
	}

	private Voc toVoc(CreateVocDto vocDto) {
		return Voc.builder()
				.party(vocDto.getParty())
				.content(vocDto.getContent()).build();
	}

	@PostMapping
	public ResponseEntity<ResultDto> createVoc(@RequestBody @Valid CreateVocDto vocDto) throws URISyntaxException {
		Voc voc = toVoc(vocDto);
		vocService.registerVoc(voc);
		return ResponseEntity.created(new URI("/api/vocs/" + voc.getId()))
				.body(new ResultDto(true));
	}

	private Penalty toPenalty(CreatePenaltyDto penaltyDto) {
		return Penalty.builder()
				.amount(penaltyDto.getAmount())
				.content(penaltyDto.getContent())
				.build();
	}

	@PostMapping("/{id}/penalties")
	public ResponseEntity<ResultDto> createdPenalty(@RequestBody @Valid CreatePenaltyDto penaltyDto, @PathVariable("id") Long vocId) throws URISyntaxException {
		Penalty penalty = toPenalty(penaltyDto);
		vocService.registerPenalty(vocId, penalty);
		return ResponseEntity.created(new URI("/api/vocs/" + vocId + "/penalties/" + penalty.getId()))
				.body(new ResultDto(true));
	}

	@GetMapping("/{id}")
	public ResponseEntity<VocDetailDto> findOneVoc(@PathVariable("id") Long vocId) {
		Voc voc = vocReadService.findOne(vocId);
		return ResponseEntity.ok(new VocDetailDto(voc));
	}
}
