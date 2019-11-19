package orser.springboot.presidents.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import orser.springboot.presidents.entity.President;

public interface PresidentRepository extends JpaRepository<President, Integer> {

	// method to sort by last name
	public List<President> findAllByOrderByLastNameAsc();
	
	// method to sort by term start
	public List<President> findAllByOrderByTermStartAsc();
	
	// method to find by term start
	public President findByTermStart(LocalDate termStart);
	
	// method to find by term end
	public President findByTermEnd(LocalDate termEnd);
	
	// method to find all presidents who *only* served during a particular period of time
	@Query(value = "SELECT * FROM president WHERE term_start >= :termStart AND term_end <= :termEnd", nativeQuery=true)
	public List<President> findByTermExclusive(LocalDate termStart, LocalDate termEnd);
	
	// method to find all presidents who served during a particular period of time
	// does not include presidents who overlapped by a single day
	@Query(value = "SELECT * FROM president WHERE term_end > :termStart AND term_start < :termEnd", nativeQuery=true)
	public List<President> findByTermInclusive(LocalDate termStart, LocalDate termEnd);
	
	// method to delete all entries
	public void deleteAll();

}
