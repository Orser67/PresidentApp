package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.PresidentDAO;
import entities.President;


@Service
public class PresidentServiceImpl implements PresidentService {

	@Autowired
	private PresidentDAO presidentDAO;
	
	@Override
	@Transactional
	public List<President> getPresidents() {
		return presidentDAO.getPresidents();
	}

	@Override
	@Transactional
	public void savePresident(President thePresident) {

		presidentDAO.savePresident(thePresident);
	}

	@Override
	@Transactional
	public President getPresident(int theId) {
		
		return presidentDAO.getPresident(theId);
	}

	@Override
	@Transactional
	public void deletePresident(int theId) {
		
		presidentDAO.deletePresident(theId);
	}
}





