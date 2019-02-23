package application.services.interfaces;

import java.util.List;

import application.models.Staff;

public interface IStaffService {
	
	public List<Staff> getAllStaff();
	public Staff getStaffById(Long id);
	public Staff getStaffByDocumentNumber(Long id);
	public Staff saveStaff(Staff staff);
	public Staff updateStaff(Staff staff);
	public List<Staff> deleteStaffById(Long id);
	

}
