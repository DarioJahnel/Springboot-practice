package application.services.interfaces;

import java.util.List;

import application.models.Staff;

public interface IStaffService {
	
	public List<Staff> getAllStaff();
	public Staff getStaffById(Long id);
	public Staff getStaffByDocumentNumber(String documentNumber);
	public Staff saveStaff(Staff staff);
	public Staff updateStaff(Staff updatedStaff, String documentNumber);
	public List<Staff> deleteStaffById(Long id);
	

}
