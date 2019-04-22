package application.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import application.models.Staff;
import application.models.repositories.IStaffRepository;
import application.services.interfaces.IStaffService;
import dto.StaffDTO;
import exceptions.ValidationException;

@Qualifier("staffService")
@Service
public class StaffService implements IStaffService {

	ModelMapper modelMapper = new ModelMapper();

	@Autowired
	IStaffRepository staffRepository;

	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	Validator validator = factory.getValidator();

	@Override
	public List<StaffDTO> getAllStaff() {

		List<Staff> dbResponse = staffRepository.findAll();
		List<StaffDTO> dtoResponse = modelMapper.map(dbResponse, new TypeToken<List<StaffDTO>>() {
		}.getType());

		return dtoResponse;
	}

	@Override
	public StaffDTO getStaffById(Long id) {
		Optional<Staff> optStaff = staffRepository.findById(id);
		if (optStaff.isPresent()) {
			StaffDTO response = modelMapper.map(optStaff.get(), StaffDTO.class);
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
	public StaffDTO createStaff(StaffDTO staffDTO) throws ValidationException {

		// Map to staff class
		Staff staff = modelMapper.map(staffDTO, Staff.class);

		// Validate
		Set<ConstraintViolation<Staff>> violations = validator.validate(staff);
		if (violations.size() > 0) {

			ArrayList<String> violationMessages = new ArrayList<String>();

			for (ConstraintViolation<Staff> violation : violations) {
				violationMessages.add(violation.getMessage());
			}
			throw new ValidationException(violationMessages);
		}

		staffRepository.save(staff);

		return staffDTO;
	}

	@Override
	public StaffDTO updateStaff(StaffDTO updatedStaffDTO, String documentNumber) {
		Staff dbStaff = staffRepository.findByDocumentNumber(documentNumber);

		if (dbStaff != null) {

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
		if (dbStaff != null) {
			dbStaff.setDeleted(true);
			staffRepository.save(dbStaff);

			StaffDTO staffDTO = modelMapper.map(dbStaff, StaffDTO.class);

			return staffDTO;
		}
		return null;
	}

}
