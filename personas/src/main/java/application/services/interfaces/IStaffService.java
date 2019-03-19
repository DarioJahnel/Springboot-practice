package application.services.interfaces;

import java.util.List;

import application.models.Staff;
import dto.StaffDTO;

public interface IStaffService {
	
	public List<StaffDTO> getAllStaff();
	public StaffDTO getStaffById(Long id);
	public StaffDTO getStaffByDocumentNumber(String documentNumber);
	public StaffDTO createStaff(StaffDTO staff);
	public StaffDTO updateStaff(StaffDTO updatedStaff, String documentNumber);
	public StaffDTO deleteStaffByDocumentNumber(String documentNumber);
	

}
