package kr.co.timf.subject.repository;

import kr.co.timf.subject.domain.Voc;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class VocRepository {
	private final EntityManager em;

	public void save(Voc voc) {
		em.persist(voc);
	}

	public List<Voc> findAllVocAndCompensation() {
		return em.createQuery("select v from Voc v join fetch v.compensation", Voc.class)
				.getResultList();
	}

	public Optional<Voc> findOne(Long id) {
		return Optional.ofNullable(em.find(Voc.class, id));
	}
}
