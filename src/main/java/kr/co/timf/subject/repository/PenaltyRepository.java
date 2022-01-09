package kr.co.timf.subject.repository;

import kr.co.timf.subject.domain.Penalty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class PenaltyRepository {

	private final EntityManager em;

	public void save(Penalty penalty) {
		em.persist(penalty);
	}
}
