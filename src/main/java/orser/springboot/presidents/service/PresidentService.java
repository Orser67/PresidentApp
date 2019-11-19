package orser.springboot.presidents.service;

import java.time.LocalDate;
import java.util.List;

import orser.springboot.presidents.entity.Candidate;
import orser.springboot.presidents.entity.President;

public interface PresidentService {

	public List<President> findAll();
	
	public President findById(int theId);
	
	public President findByTermStart(LocalDate termStart);
	
	public void save(President theEmployee);
	
	public void deleteById(int theId);
	
	public void addCandidate(LocalDate termStart);
	
	public List<Candidate> removeExistingPresidents(LocalDate termStart, LocalDate termEnd);
	
	public void resetDatabase();
	
}
