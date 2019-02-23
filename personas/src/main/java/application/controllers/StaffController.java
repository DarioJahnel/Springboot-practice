package application.controllers;

import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
//		Staff response = staffService.getStaffById(1L);
		
		
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
	

}
