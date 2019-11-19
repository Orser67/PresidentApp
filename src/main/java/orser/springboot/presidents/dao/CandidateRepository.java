package orser.springboot.presidents.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import orser.springboot.presidents.entity.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Integer> {

	
	// method to sort by last name
	public List<Candidate> findAllByOrderByLastNameAsc();
	
	// method to sort by election year
	public List<Candidate> findAllByOrderByElectionYearAsc();
	
	// method to find by election year
	public Candidate findByElectionYear(int electionYear);
	
	// method to delete all entries
	public void deleteAll();

}
