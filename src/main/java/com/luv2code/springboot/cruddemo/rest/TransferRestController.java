package com.luv2code.springboot.cruddemo.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springboot.cruddemo.dto.TransferInput;
import com.luv2code.springboot.cruddemo.dto.transfer;
import com.luv2code.springboot.cruddemo.entity.Transfer;
import com.luv2code.springboot.cruddemo.service.TransferService;

@RestController
@RequestMapping("/api")
public class TransferRestController {

	private TransferService transferService;
	
	@Autowired 
	public TransferRestController(TransferService theTransferService){
		transferService = theTransferService;
	}
	
	// expose "/employees" and return list of employees
	@GetMapping("/employees")
	public List<Transfer> findAll(){
		return transferService.findAll();
	}
	
	// add mapping for GET /employee/{employeeId}
//	@GetMapping("/employees/{employeeId}")
//	public Transfer getEmployee(@PathVariable int employeeId){
//		Transfer theEmployee = transferService.findById(employeeId);
//		
//		if(theEmployee == null){
//			throw new RuntimeException("Employee ID not found - " + employeeId);
//		}
//		System.out.println(theEmployee);
//		return theEmployee;
//	}
	
	// add mapping for POST /employees - add new employee
	@PostMapping("/employees")
	public Transfer addEmployee(@RequestBody Transfer theEmployee){

		theEmployee.setId(0);
		transferService.save(theEmployee);
		return theEmployee;
	}
	
	// add mapping for PUT /employees - update existing employee
	
//	@PutMapping("/employees")
//	public Transfer updateEmployee(@RequestBody Transfer theEmployee){
//		transferService.save(theEmployee);
//		return theEmployee;	
//	}
	
	// add mapping for DELETE /employees/{employeeId} - delete employee
	
	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId){
		Transfer tempEmployee = transferService.findById(employeeId);
		
		if(tempEmployee == null){
			throw new RuntimeException("Employee id not found - "+employeeId);
		}
		
		transferService.deleteById(employeeId);
		
		return "Deleted employee id - "+employeeId;
	}
	
//	@PutMapping("/employees/{employeeId1}/{employeeId2}/{amount}")
//	public void update(@PathVariable int employeeId1,@PathVariable int employeeId2,@PathVariable int amount){
//		
//		transferService.update(employeeId1, employeeId2, amount);
////		
////		Transfer theEmployee = transferService.findById(employeeId1);
////		Transfer theEmployee1 = transferService.findById(employeeId2);
////
////		int hasil1,hasil2,total;
////		hasil2 = theEmployee1.getJml();
////		hasil1 = theEmployee.getJml();
////		total = hasil1 - amount;
////		theEmployee.setJml(total);
////		transferService.save(theEmployee);
////		if(theEmployee == null){
////			throw new RuntimeException("Employee ID not found - " + employeeId1);
////		}
////		if(theEmployee1 == null){
////			throw new RuntimeException("Employee ID not found - " + employeeId2);
////		}
//		
//	}
	
	@GetMapping("/employees/{id}")
	public transfer fullName(@PathVariable int id){
		return transferService.GetFullName(id);
	}
	
	@PutMapping("/employees")
	public void trfs(@RequestBody TransferInput Trfs) throws Exception{
		transferService.trf(Trfs);
	}
	
}
