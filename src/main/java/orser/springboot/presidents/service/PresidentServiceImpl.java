package orser.springboot.presidents.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import orser.springboot.presidents.dao.CandidateRepository;
import orser.springboot.presidents.dao.PresidentRepository;
import orser.springboot.presidents.entity.Candidate;
import orser.springboot.presidents.entity.President;

@Service
public class PresidentServiceImpl implements PresidentService {

	@Autowired
	private PresidentRepository presidentRepository;
	
	@Autowired
	private CandidateRepository candidateRepository;
	
	public PresidentServiceImpl() {

	}
	
	@Override
	public List<President> findAll() {
		return presidentRepository.findAllByOrderByTermStartAsc();
	}

	@Override
	public President findById(int theId) {
		Optional<President> result = presidentRepository.findById(theId);
		
		President thePresident = null;
		if (result.isPresent()) {
			thePresident = result.get();
		} else {
			throw new RuntimeException("Didn't find president id");
		}
		return thePresident;
	}
	
	@Override
	public President findByTermStart(LocalDate termStart) {
		try {
			President tempPresident = presidentRepository.findByTermStart(termStart);
			return tempPresident;
		} catch (NullPointerException e) {
			throw new RuntimeException("Didn't find a president with the correct term start");
		}
	}

	@Override
	public void save(President thePresident) {
		// add code to disallow one-day-only presidents
		
		// add code to check if new president would override term of another president
		
		presidentRepository.save(thePresident);
	}

	@Override
	public void deleteById(int theId) {
		presidentRepository.deleteById(theId);
	}
	
	// method to add a single candidate from the candidates list
	@Override
	public void addCandidate(LocalDate termStart) {
		
		// create candidate
		int termStartYear = termStart.getYear();
		LocalDate termEnd = President.getNaturalTermStart(termStartYear + 4);
		int electionYear = termStartYear - 1;
		Candidate theCandidate = candidateRepository.findByElectionYear(electionYear);
		President tempPresident = new President (0, theCandidate.getFirstName(), theCandidate.getLastName(), 
				theCandidate.getParty(), termStart, termEnd);
		
		// get candidate to add, then remove any presidents who only served during the term
	    List<President> tempPresList = presidentRepository.findByTermInclusive(termStart, termStart.plusDays(1));
		President presidentToCandidate = tempPresList.get(0);
		Candidate theCandidateToAdd = presidentToCandidate.makeCandidate(electionYear);
		tempPresList = null;
		removeExistingPresidents(termStart, termEnd);
		
		// get the president who served immediately before term
		LocalDate dayBeforeTerm = termStart.minusDays(1);
		List<President> presListBefore = presidentRepository.findByTermInclusive(dayBeforeTerm, dayBeforeTerm);
		President tempPresidentBefore = null;	
		if (!presListBefore.isEmpty()) {
			tempPresidentBefore = presListBefore.get(0);
			System.out.println("President served before term: " + tempPresidentBefore);
		}
		
		//get the president who served immediately after term
		LocalDate dayAfterTerm = termEnd.plusDays(1);
		List<President> presListAfter = presidentRepository.findByTermInclusive(dayAfterTerm, dayAfterTerm);
		President tempPresidentAfter = null;
		if (!presListAfter.isEmpty()) {
			tempPresidentAfter = presListAfter.get(0);
			System.out.println("President served after term: " + tempPresidentAfter);
		}
		
		boolean needToAdd = true; // variable: whether a new president needs to be added
		boolean beforeAndAfter = false; // variable: whether the same president served before and after term in question
		
		// handle scenarios where president who came before and president who came after term are same person
		if (tempPresidentAfter != null && tempPresidentBefore != null) {
			if (tempPresidentBefore.getFirstName().equals(tempPresidentAfter.getFirstName()) && 
				tempPresidentBefore.getLastName().equals(tempPresidentAfter.getLastName())) {
				
				beforeAndAfter = true;
				// merge before-president, after-president, and candidate if they are the same person
				if (tempPresidentBefore.getFirstName().equals(tempPresident.getFirstName()) &&
					tempPresidentBefore.getLastName().equals(tempPresident.getLastName())){
					System.out.println("Altering president that came before and after because\nCandidate: " + theCandidate + 
					"\nand President: " + tempPresidentBefore + "\nand President: " + tempPresidentAfter + "\nare the same");
					tempPresidentBefore.setTermEnd(termEnd);
					presidentRepository.save(tempPresidentBefore);
				
					// delete second occurrence of same president
					presidentRepository.deleteById(tempPresidentAfter.getId());
					needToAdd = false;
					
				} else { // split before-president and after-president if the candidate won an election in middle of another presidency
					President nonConsecutivePresident = new President(0, tempPresidentBefore.getFirstName(), tempPresidentBefore.getLastName(),
							tempPresidentBefore.getParty(), tempPresidentBefore.getTermStart(), tempPresidentBefore.getTermEnd());
					nonConsecutivePresident.setTermStart(termEnd);
					presidentRepository.save(nonConsecutivePresident);
					tempPresidentBefore.setTermEnd(termStart);
					presidentRepository.save(tempPresidentBefore);
				}
			}
		}
		
		if (tempPresidentBefore != null && beforeAndAfter == false) {
			// merge candidate and president who came before if they are same person
			if (tempPresidentBefore.getFirstName().equals(tempPresident.getFirstName()) && 
				tempPresidentBefore.getLastName().equals(tempPresident.getLastName())) {
					System.out.println("Altering existing president because\nCandidate: " + theCandidate + "\nand President: " + tempPresidentBefore + "\nare the same");
					tempPresidentBefore.setTermEnd(termEnd);
					presidentRepository.save(tempPresidentBefore);
					needToAdd = false;
			} // change term end of president who served in the previous term, but is losing the election in question
			else if (presidentToCandidate == tempPresidentBefore) { 
				tempPresidentBefore.setTermEnd(termStart);
				presidentRepository.save(tempPresidentAfter);
			}
		} 
		
		// merge candidate and president who came after if they are same person
		if (tempPresidentAfter != null && beforeAndAfter == false) {
			System.out.println(tempPresidentAfter.getFirstName() + " " + tempPresident.getFirstName() + " " +  
					tempPresidentAfter.getLastName() + " " + tempPresident.getLastName());
			if (tempPresidentAfter.getFirstName().equals(tempPresident.getFirstName()) && 
			tempPresidentAfter.getLastName().equals(tempPresident.getLastName())) {
				System.out.println("Altering existing president because\nCandidate: " + theCandidate + "\nand President: " + tempPresidentAfter + "\nare the same");
				tempPresidentAfter.setTermStart(termStart);
				presidentRepository.save(tempPresidentAfter);
				needToAdd = false;
			}
			else if (presidentToCandidate == tempPresidentAfter) { 
				tempPresidentAfter.setTermStart(termEnd);
				presidentRepository.save(tempPresidentAfter);
			}
		}
		
		// add candidate as a new president if necessary
		if (needToAdd == true){ 
			// check for unelected presidents who won re-election and need to be altered
			tempPresList = presidentRepository.findByTermInclusive(termStart, termEnd);
			if (!tempPresList.isEmpty()) {
				President unelectedPresident = tempPresList.get(0);
				unelectedPresident.setTermStart(termEnd);
				presidentRepository.save(unelectedPresident);
			}
			
			System.out.println("Will add candidate to presidents: " + theCandidate);
			presidentRepository.save(tempPresident);
		}
		
		// remove existing candidate
		System.out.println("Removing from candidates: " + theCandidate);
		candidateRepository.deleteById(theCandidate.getId());
		
		// add new candidate
		System.out.println("Will add president to candidates: " + theCandidateToAdd);
		candidateRepository.save(theCandidateToAdd);
		
	}

	// method to remove existing presidents whose entire terms overlap with a given date range
	// returns a list of candidates to be added to candidate list
	@Override
	public List<Candidate> removeExistingPresidents(LocalDate startOfTerm, LocalDate endOfTerm) {
		
		// create a candidate list to return 
		List<Candidate> resultsList = new ArrayList<Candidate>();
		
		// delete presidents who only served between term start and end of term
		List<President> presList = presidentRepository.findByTermExclusive(startOfTerm, endOfTerm);
		while (!presList.isEmpty()) {
			President tempPresident = presList.get(0);
			int tempId = tempPresident.getId();
			
			// add to candidate list for elected president
			if (tempPresident.firstTermNaturalStart() == true) {
				int electionYear = tempPresident.getElectionYearBeforeTerm(1);
				Candidate tempCandidate = tempPresident.makeCandidate(electionYear);
				resultsList.add(tempCandidate);
			}
			
			// add to candidate list for multi-term presidents
			int numberTerms = tempPresident.numberTerms();
			for (int i = 2; i <= numberTerms; i++) {
				int electionYear = tempPresident.getElectionYearBeforeTerm(i);
				Candidate tempCandidate = tempPresident.makeCandidate(electionYear);
				resultsList.add(tempCandidate);
			}
			
			presidentRepository.deleteById(tempId);
			System.out.println("Removed president: " + tempPresident);
			presList.remove(0);
		}
		
		return resultsList;
	}
	
	@Override
	public void resetDatabase() {
		
	}
	/*
		presidentRepository.deleteAll();
	    President tempPresident; 
	    tempPresident = new President(1, "George", "Washington","None", "1789", "1797");
	    presidentRepository.save(tempPresident);
	    tempPresident = new President(2, "John", "Adams", "Federalist", "1797", "1801");
	    presidentRepository.save(tempPresident);
	    tempPresident = new President(3,"Thomas","Jefferson","Democratic-Republican","1801","1809");
	    presidentRepository.save(tempPresident);
	    tempPresident = new President(4,"James","Madison","Democratic-Republican","1809","1817");
		presidentRepository.save(tempPresident);
		tempPresident = new President(5,"James","Monroe","Democratic-Republican","1817","1825");
		presidentRepository.save(tempPresident);
		tempPresident = new President(6,"John Quincy","Adams","Democratic-Republican","1825","1829");
		presidentRepository.save(tempPresident);
		tempPresident = new President(7,"Andrew","Jackson","Democratic","1829","1837");
		presidentRepository.save(tempPresident);
		tempPresident = new President(8,"Martin","Van Buren","Democratic","1837","1841");
		presidentRepository.save(tempPresident);
		tempPresident = new President(9,"William Henry","Harrison","Whig","1841","1841");
		presidentRepository.save(tempPresident);
		tempPresident = new President(10,"John","Tyler","Independent","1841","1845");
		presidentRepository.save(tempPresident);
		tempPresident = new President(11,"James K.","Polk","Democratic","1845","1849");
		presidentRepository.save(tempPresident);
		tempPresident = new President(12,"Zachary","Taylor","Whig","1849","1850");
		presidentRepository.save(tempPresident);
		tempPresident = new President(13,"Millard","Fillmore","Whig","1850","1853");
		presidentRepository.save(tempPresident);
		tempPresident = new President(14,"Franklin","Pierce","Democratic","1853","1857");
		presidentRepository.save(tempPresident);
		tempPresident = new President(15,"James","Buchanan","Democratic","1857","1861");
		presidentRepository.save(tempPresident);
		tempPresident = new President(16,"Abraham","Lincoln","Republican","1861","1865");
		presidentRepository.save(tempPresident);
		tempPresident = new President(17,"Andrew","Johnson","Independent","1865","1869");
		presidentRepository.save(tempPresident);
		tempPresident = new President(18,"Ulysses S.","Grant","Republican","1869","1877");
		presidentRepository.save(tempPresident);
		tempPresident = new President(19,"Rutherford B.","Hayes","Republican","1877","1881");
		presidentRepository.save(tempPresident);
		tempPresident = new President(20,"James","Garfield","Republican","1881","1881");
		presidentRepository.save(tempPresident);
		tempPresident = new President(21,"Chester A.","Arthur","Republican","1881","1885");
		presidentRepository.save(tempPresident);
		tempPresident = new President(22,"Grover","Cleveland","Democratic","1885","1889");
		presidentRepository.save(tempPresident);
		tempPresident = new President(23,"Benjamin","Harrison","Republican","1889","1893");
		presidentRepository.save(tempPresident);
		tempPresident = new President(24,"Grover","Cleveland","Democratic","1893","1897");
		presidentRepository.save(tempPresident);
		tempPresident = new President(25,"William","McKinley","Republican","1897","1901");
		presidentRepository.save(tempPresident);
		tempPresident = new President(26,"Theodore","Roosevelt","Republican","1901","1909");
		presidentRepository.save(tempPresident);
		tempPresident = new President(27,"William Howard","Taft","Republican","1909","1913");
		presidentRepository.save(tempPresident);
		tempPresident = new President(28,"Woodrow","Wilson","Democratic","1913","1921");
		presidentRepository.save(tempPresident);
		tempPresident = new President(29,"Warren","Harding","Republican","1921","1923");
		presidentRepository.save(tempPresident);
		tempPresident = new President(30,"Calvin","Coolidge","Republican","1923","1929");
		presidentRepository.save(tempPresident);
		tempPresident = new President(31,"Herbert","Hoover","Republican","1929","1933");
		presidentRepository.save(tempPresident);
		tempPresident = new President(32,"Franklin","Roosevelt","Democratic","1933","1945");
		presidentRepository.save(tempPresident);
		tempPresident = new President(33,"Harry","Truman","Democratic","1945","1953");
		presidentRepository.save(tempPresident);
		tempPresident = new President(34,"Dwight","Eisenhower","Republican","1953","1961");
		presidentRepository.save(tempPresident);
		tempPresident = new President(35,"John F.","Kennedy","Democratic","1961","1963");
		presidentRepository.save(tempPresident);
		tempPresident = new President(36,"Lyndon","Johnson","Democratic","1963","1969");
		presidentRepository.save(tempPresident);
		tempPresident = new President(37,"Richard","Nixon","Republican","1969","1974");
		presidentRepository.save(tempPresident);
		tempPresident = new President(38,"Gerald","Ford","Republican","1974","1977");
		presidentRepository.save(tempPresident);
		tempPresident = new President(39,"Jimmy","Carter","Democratic","1977","1981");
		presidentRepository.save(tempPresident);
		tempPresident = new President(40,"Ronald","Reagan","Republican","1981","1989");
		presidentRepository.save(tempPresident);
		tempPresident = new President(41,"George H. W.","Bush","Republican","1989","1993");
		presidentRepository.save(tempPresident);
		tempPresident = new President(42,"Bill","Clinton","Democratic","1993","2001");
		presidentRepository.save(tempPresident);
		tempPresident = new President(43,"George W.","Bush","Republican","2001","2009");
		presidentRepository.save(tempPresident);
		tempPresident = new President(44,"Barack","Obama","Democratic","2009","2017");
		presidentRepository.save(tempPresident);
		tempPresident = new President(45,"Donald","Trump","Republican","2017","2019");
		presidentRepository.save(tempPresident);
	}
*/

}
