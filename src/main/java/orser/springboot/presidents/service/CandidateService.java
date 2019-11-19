package orser.springboot.presidents.service;

import java.time.LocalDate;
import java.util.List;

import orser.springboot.presidents.entity.Candidate;

public interface CandidateService {

	public List<Candidate> findAll();
	
	public Candidate findById(int theId);
	
	public Candidate findByElectionYear(int electionYear);
	
	public void save(Candidate theCandidate);
	
	public void deleteById(int theId);
	
	public void resetDatabase();
}
