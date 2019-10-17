package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import entities.President;

@Repository
public class PresidentDAOImpl implements PresidentDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
			
	@Override
	public List<President> getPresidents() {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
				
		// create a query, sort by last name
		Query<President> theQuery = 
				currentSession.createQuery("from President order by lastName",
											President.class);
		
		// execute query and get result list
		List<President> presidents = theQuery.getResultList();
				
		// return the results		
		return presidents;
	}

	@Override
	public void savePresident(President thePresident) {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save/update the president
		currentSession.saveOrUpdate(thePresident);
		
	}

	@Override
	public President getPresident(int theId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// now retrieve/read from database using the primary key
		President thePresident = currentSession.get(President.class, theId);
		
		return thePresident;
	}

	@Override
	public void deletePresident(int theId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete object with primary key
		Query theQuery = 
				currentSession.createQuery("delete from President where id=:presidentId");
		theQuery.setParameter("presidentId", theId);
		
		theQuery.executeUpdate();		
	}

}











