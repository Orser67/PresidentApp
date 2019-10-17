package dao;

import java.util.List;

import entities.President;

public interface PresidentDAO {

	public List<President> getPresidents();

	public void savePresident(President thePresident);

	public President getPresident(int theId);

	public void deletePresident(int theId);
	
}
