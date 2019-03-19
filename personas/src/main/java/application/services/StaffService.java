package application.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import application.models.Staff;
import application.models.repositories.IStaffRepository;
import application.services.interfaces.IStaffService;
import dto.StaffDTO;

@Qualifier("staffService")
@Service
public class StaffService implements IStaffService{
	
	ModelMapper modelMapper = new ModelMapper(); 

	@Autowired
	IStaffRepository staffRepository;
	
	@Override
	public List<StaffDTO> getAllStaff() {	
		
		List<Staff> dbResponse =  staffRepository.findAll();
		List<StaffDTO> dtoResponse = modelMapper.map(dbResponse, new TypeToken<List<StaffDTO>>(){}.getType());
		
		return dtoResponse;
	}

	@Override
	public StaffDTO getStaffById(Long id) {
		Optional<Staff> optStaff = staffRepository.findById(id);
		if(optStaff.isPresent()) {
			StaffDTO response = modelMapper.map(optStaff.get(), StaffDTO.class );
			return response;
		}
		return null;
		
	}

	@Override
	public StaffDTO getStaffByDocumentNumber(String documentNumber) {
		
		Staff dbStaff = staffRepository.findByDocumentNumber(documentNumber);
		StaffDTO response = modelMapper.map(dbStaff, StaffDTO.class);
		return response;
	}

	@Override
	public StaffDTO createStaff(StaffDTO staffDTO) {
		// Validate data?
		Staff staff = modelMapper.map(staffDTO, Staff.class);
		
		staffRepository.save(staff);
		
		return null;
	}

	@Override
	public StaffDTO updateStaff(StaffDTO updatedStaffDTO, String documentNumber) {
		Staff dbStaff = staffRepository.findByDocumentNumber(documentNumber);
		
		if(dbStaff != null) {
			
			Staff updatedStaff = modelMapper.map(updatedStaffDTO, Staff.class);
			
			updatedStaff.setId(dbStaff.getId());
			
			staffRepository.save(updatedStaff);
			return updatedStaffDTO;
		}
		return null;
	}

	@Override
	public StaffDTO deleteStaffByDocumentNumber(String documentNumber) {
		Staff dbStaff = staffRepository.findByDocumentNumber(documentNumber);
		if(dbStaff != null) {
			dbStaff.setDeleted(true);
			staffRepository.save(dbStaff);
			
			StaffDTO staffDTO = modelMapper.map(dbStaff, StaffDTO.class);
			
			return staffDTO;
		}
		return null;
	}

}
