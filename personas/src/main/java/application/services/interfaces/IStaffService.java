package application.services.interfaces;

import java.util.List;

import dto.StaffDTO;
import dto.StaffGridDTO;
import exceptions.ValidationException;

public interface IStaffService {

	public List<StaffDTO> getAllStaff();
	
	public List<StaffDTO> getAllNonDeletedStaff();

	public List<StaffGridDTO> getAllGridNonDeletedStaff();

	public StaffDTO getStaffById(Long id);

	public StaffDTO getStaffByDocumentNumber(String documentNumber);

	public StaffDTO createStaff(StaffDTO staff) throws ValidationException;

	public StaffDTO updateStaff(StaffDTO updatedStaff, String documentNumber);

	public StaffDTO deleteStaffByDocumentNumber(String documentNumber);

}
