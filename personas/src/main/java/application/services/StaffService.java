package application.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import application.models.Staff;
import application.models.repositories.IStaffRepository;
import application.services.interfaces.IStaffService;

@Qualifier("staffService")
@Service
public class StaffService implements IStaffService{

	@Autowired
	IStaffRepository staffRepository;
	
	@Override
	public List<Staff> getAllStaff() {	
		return staffRepository.findAll();
	}

	@Override
	public Staff getStaffById(Long id) {
		Optional<Staff> optStaff = staffRepository.findById(id);
		if(optStaff.isPresent()) {
			return optStaff.get();
		}
		return null;
		
	}

	@Override
	public Staff getStaffByDocumentNumber(String documentNumber) {
		
		return staffRepository.findByDocumentNumber(documentNumber);
	}

	@Override
	public Staff saveStaff(Staff staff) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Staff updateStaff(Staff updatedStaff, String documentNumber) {
		Staff dbStaff = staffRepository.findByDocumentNumber(documentNumber);
		if(dbStaff != null) {
			updatedStaff.setId(dbStaff.getId());
			staffRepository.save(updatedStaff);
			return updatedStaff;
		}
		return null;
	}

	@Override
	public List<Staff> deleteStaffById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
