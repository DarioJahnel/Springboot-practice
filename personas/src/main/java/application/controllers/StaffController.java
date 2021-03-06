package application.controllers;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.services.interfaces.IStaffService;
import dto.StaffDTO;
import dto.StaffGridDTO;
import exceptions.ValidationException;

@RestController
@CrossOrigin
@RequestMapping("/staff")
public class StaffController {

	@Autowired
	IStaffService staffService;

	private static final Logger logger = LogManager.getLogger();

	@GetMapping("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<?> getAllStaff() {

		List<StaffDTO> response = staffService.getAllStaff();
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@GetMapping("/getAllNonDeleted")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<?> getAllNonDeletedStaff() {

		List<StaffDTO> response = staffService.getAllNonDeletedStaff();
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@GetMapping("/getAllGridNonDeleted")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<?> getAllGridNonDeletedStaff() {

		List<StaffGridDTO> response = staffService.getAllGridNonDeletedStaff();
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@GetMapping("/get/{documentNumber}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<?> getByDocumentNumber(@PathVariable String documentNumber) {

		StaffDTO response = staffService.getStaffByDocumentNumber(documentNumber);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/create")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<?> createStaff(@RequestBody StaffDTO staffDTO) throws ValidationException {

		StaffDTO response = new StaffDTO();

		try {
			response = staffService.createStaff(staffDTO);

		} catch (ValidationException e) {
			return new ResponseEntity<>(e.getMessages(), HttpStatus.BAD_REQUEST);
		} catch (DataIntegrityViolationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping("/get/{documentNumber}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<?> updateStaff(@PathVariable String documentNumber, @RequestBody StaffDTO staffDTO) {

		StaffDTO response = new StaffDTO();
		try {
			response = staffService.updateStaff(staffDTO, documentNumber);
		} catch (ConstraintViolationException e) {
			logger.error(e.getConstraintViolations());
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/get/{documentNumber}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<?> deleteStaff(@PathVariable String documentNumber) {

		StaffDTO response = staffService.deleteStaffByDocumentNumber(documentNumber);
		if (response != null) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Staff does not exist", HttpStatus.BAD_REQUEST);
		}
	}
}
