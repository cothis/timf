package kr.co.timf.subject.controller;

import kr.co.timf.subject.controller.dto.VocDetailDto;
import kr.co.timf.subject.domain.Voc;
import kr.co.timf.subject.service.VocService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
