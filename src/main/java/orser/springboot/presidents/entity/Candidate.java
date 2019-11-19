package orser.springboot.presidents.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// an entity that maps to the employees of the employee database table
@Entity
@Table(name="candidate")
public class Candidate {

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
	
	@Column(name="election_year")
	private int electionYear;
	
	// define constructors
    // Hibernate requires a no-arg constructor
	public Candidate () {
		
	}
	
	public Candidate(int id, String firstName, String lastName, String party, int electionYear) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.party = party;
		this.electionYear = electionYear;
	}

	@Override
	public String toString() {
		return "Candidate [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", party=" + party
				+ ", electionYear=" + electionYear + "]";
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

	public int getElectionYear() {
		return electionYear;
	}

	public void setElectionYear(int electionYear) {
		this.electionYear = electionYear;
	}

}