package orser.springboot.presidents.entity;

import java.time.LocalDate;
import java.time.Month;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

//ASSUMPTION: all presidents serve at least two days
@Entity
@Table(name="president")
public class President {

	// define fields, with each one having the @column annotation to line up with database column
	
	// use tags for this field to indicate it's the id column, and to allow auto-generation
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="party")
	private String party;
	
	@Column(name="term_start")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate termStart;
	
	@Column(name="term_end")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate termEnd;
	
	// define constructors
    // Hibernate requires a no-arg constructor
	public President () {
		
	}

	public President(int id, String firstName, String lastName, String party, LocalDate termStart, LocalDate termEnd) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.party = party;
		this.termStart = termStart;
		this.termEnd = termEnd;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public LocalDate getTermStart() {
		return termStart;
	}

	public void setTermStart(LocalDate termStart) {
		this.termStart = termStart;
	}

	public LocalDate getTermEnd() {
		return termEnd;
	}

	public void setTermEnd(LocalDate termEnd) {
		this.termEnd = termEnd;
	}

	@Override
	public String toString() {
		return "President [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", party=" + party
				+ ", termStart=" + termStart + ", termEnd=" + termEnd + "]";
	}
	
	// method to return the start of a term for any given date
	// a "natural term start" is defined as the start of a term to which an individual was elected
	// thus, vice presidents who succeed to the presidency do not begin their terms at a "natural term start"
	public static LocalDate getNaturalTermStart(int year) {
		LocalDate result = LocalDate.of(year, Month.JANUARY, 20);
		if (year == 1789) {
			result = result.withMonth(4);
			result = result.withDayOfMonth(30);
		} else if (year <= 1933) {
			result = result.withMonth(3);
			result = result.withDayOfMonth(4);
		}
		return result;
	}
	
	// method to return the most recent election for any given date
	public static int getMostRecentElectionYear(LocalDate date) {
		int year = date.getYear();
		while ((year - 1788) % 4 != 0) {
			year--;
		}
		return year;
	}
		
	// method to return whether the president's first term started at a "natural term start"
	public boolean firstTermNaturalStart() {
		LocalDate naturalTermStart = getNaturalTermStart(termStart.getYear()); 
		if (termStart.equals(naturalTermStart)) {
			int remainder = (termStart.getYear() - 1789) % 4;
			if (remainder == 0) {
				return true;
			} 
		}
		return false;
	}
		
	// method to return the start date of a president's second term
	// returns null for one-term presidents
	public LocalDate secondTermStart() {
		
		// check for elected presidents who were re-elected
		if (firstTermNaturalStart()) {
			LocalDate secondTermStart = getNaturalTermStart(termStart.getYear() + 4);
			if (secondTermStart.isBefore(termEnd)) {
				return secondTermStart;
			}
		}
		
		// to do: check for VP-turned presidents
		// who acceded in a given year and then won election later in that same year
		
		// check for VP-turned-presidents who won re-election at least one calendar year after taking office
		LocalDate tempDate = getNaturalTermStart(termStart.getYear());
		tempDate = tempDate.plusYears(1);
		while (tempDate.isBefore(termEnd)) {
			if (((tempDate.getYear() - 1789) % 4) == 0) {
				return tempDate;
			}
			tempDate = tempDate.plusYears(1);
			tempDate = getNaturalTermStart(tempDate.getYear());
		}
		
		return null;
	}
	
	// method to get term starts for terms after the second
	// returns null if candidate did not serve the specified number of terms
	public LocalDate thirdTermOrMoreStart(int termNumber) {
		LocalDate tempDate = secondTermStart();
		if (tempDate == null) {
			return null;
		}
		
		int tempTerms = termNumber - 2;
		
		while (tempTerms > 0) {
			tempDate = tempDate.plusYears(4);
			tempTerms--;
		}
		
		// adjust if the year is 1937
		int tempYear = tempDate.getYear();
		if (tempYear == 1937 && tempDate.isAfter(getNaturalTermStart(tempYear))) {
			tempDate = getNaturalTermStart(1937);
		}
		
		if (tempDate.isAfter(termEnd)) {
			return null;
		} else {
			return getNaturalTermStart(tempDate.getYear());
		}
	}
	
	public int getElectionYearBeforeTerm(int termNumber) {
		LocalDate startOfTerm;
		
		if (termNumber == 1) {
			startOfTerm = termStart;
		} else if (termNumber == 2) {
			startOfTerm = secondTermStart();
		} else {
			startOfTerm = thirdTermOrMoreStart(termNumber);
		}
			
		int year = getMostRecentElectionYear(startOfTerm);
		return year;
	}
	
	public int numberTerms() {
		int result = 1;
		LocalDate tempDate = secondTermStart();
		
		// check for two term presidents
		if (tempDate == null) {
			return result;
		} else {
			result = 2;
			// set temp date to end of second term
			tempDate = tempDate.plusYears(4); 
			tempDate = getNaturalTermStart(tempDate.getYear());
		}
		
		// check for >2 term presidents 
		while (!tempDate.isEqual(termEnd) && !tempDate.isAfter(termEnd)) {
			result++;
			
			// move temp date up by four years
			tempDate = tempDate.plusYears(4);
			
			// change term start date if 1937
			if (tempDate.getYear() == 1937) {
				tempDate = getNaturalTermStart(1933);
			}
		}
		
		return result;
	}
	
	// creates a candidate based on president
	public Candidate makeCandidate(int year) {
		Candidate resultCandidate = new Candidate(0, firstName, lastName, party, year);
		return resultCandidate;
	}
	
	/*
	public static void main(String[] args) {
		President washington = new President(1, "George", "Washington","None", LocalDate.of(1789, Month.APRIL, 30), LocalDate.of(1797, Month.MARCH, 4));
	    President adams = new President(2, "John", "Adams","Federalist", LocalDate.of(1797, Month.MARCH, 4), LocalDate.of(1801, Month.MARCH, 4));
	    President tyler = new President(3, "John", "Tyler","Independent", LocalDate.of(1841, Month.APRIL, 4), LocalDate.of(1845, Month.MARCH, 4));
	    President troosevelt = new President(4, "THEODORE", "ROOSEVELT","Republican", LocalDate.of(1901, Month.SEPTEMBER, 14), LocalDate.of(1909, Month.MARCH, 4));
	    President fdr = new President(3, "Franklin D.", "Roosevelt","Democratic", LocalDate.of(1933, Month.MARCH, 4), LocalDate.of(1945, Month.APRIL, 12));
	    
	    System.out.println("Washington: " + washington.firstTermNaturalStart());
	    System.out.println("Washington: " + washington.secondTermStart());
	    System.out.println("Washington: " + washington.numberTerms());
	    System.out.println("Adams1: " + adams.firstTermNaturalStart());
	    System.out.println("Adams1: " + adams.secondTermStart());
	    System.out.println("Adams1: " + adams.numberTerms());
	    System.out.println("Tyler: " + tyler.firstTermNaturalStart());
	    System.out.println("Tyler: " + tyler.secondTermStart());
	    System.out.println("tyler: " + tyler.numberTerms());
	    System.out.println("TRoos: " + troosevelt.firstTermNaturalStart());
	    System.out.println("TRoos: " + troosevelt.secondTermStart());
	    System.out.println("TRoos: " + troosevelt.numberTerms());
	    System.out.println("fdr: " + fdr.firstTermNaturalStart());
	    System.out.println("fdr: " + fdr.secondTermStart());
	    System.out.println("fdr: " + fdr.numberTerms());
	} 
	*/
}