package kr.co.timf.subject.controller;

import kr.co.timf.subject.controller.dto.CreateVocDto;
import kr.co.timf.subject.controller.dto.ResultDto;
import kr.co.timf.subject.controller.dto.VocDetailDto;
import kr.co.timf.subject.domain.Voc;
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
	public ResponseEntity<?> createVoc(@RequestBody @Valid CreateVocDto vocDto) throws URISyntaxException {
		Voc voc = toVoc(vocDto);
		vocService.registerVoc(voc);
		return ResponseEntity.created(new URI("/api/vocs/" + voc.getId()))
				.body(new ResultDto(true));
	}
}
