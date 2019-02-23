package application.controllers;

import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.models.Staff;
import application.services.interfaces.IStaffService;

@RestController
@RequestMapping("/staff")
public class StaffController {
	
	@Autowired
	IStaffService staffService;
	
	@GetMapping("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<?> getAllStaff(){
		
		List<Staff> response = staffService.getAllStaff();
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
	
	@GetMapping("/get/{documentNumber}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<?> getByDocumentNumber(@PathVariable String documentNumber){
		
		Staff response = staffService.getStaffByDocumentNumber(documentNumber);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PutMapping("/get/{documentNumber}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<?> updateStaff(@PathVariable String documentNumber, @RequestBody Staff staff){
		
		Staff response = staffService.updateStaff(staff, documentNumber);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
