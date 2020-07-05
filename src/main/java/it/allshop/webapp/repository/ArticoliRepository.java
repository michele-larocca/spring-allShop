package it.allshop.webapp.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import it.allshop.webapp.entity.ArticoliEntity;

public interface ArticoliRepository extends PagingAndSortingRepository<ArticoliEntity, String>{

	@Query(value = "SELECT * FROM ARTICOLI WHERE lower(DESCRIZIONE) LIKE lower(:desArt)", nativeQuery = true)
	public List<ArticoliEntity> selByDescrizioneLike(@Param("desArt") String descrizione);

	public List<ArticoliEntity> findByDescrizioneLike(String description, Pageable pageable);

	public ArticoliEntity findByCodArt(String codArt);

}
