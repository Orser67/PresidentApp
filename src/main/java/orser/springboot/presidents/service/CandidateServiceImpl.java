package orser.springboot.presidents.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import orser.springboot.presidents.dao.CandidateRepository;
import orser.springboot.presidents.dao.PresidentRepository;
import orser.springboot.presidents.entity.Candidate;
import orser.springboot.presidents.entity.President;

@Service
public class CandidateServiceImpl implements CandidateService {

	@Autowired
	private CandidateRepository candidateRepository;
	
	@Autowired
	private PresidentRepository presidentRepository;
	
	@Override
	public List<Candidate> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Candidate findById(int theId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Candidate findByElectionYear(int theElectionYear) {
		try {
			Candidate tempCandidate = candidateRepository.findByElectionYear(theElectionYear);
			System.out.println(tempCandidate);
			return tempCandidate;
		} catch (NullPointerException e) {
			throw new RuntimeException("Didn't find candidate with the correct election year");
		}
	}

	@Override
	public void save(Candidate theCandidate) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(int theId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resetDatabase() {
		// TODO Auto-generated method stub

	}

}
