package com.luv2code.springboot.cruddemo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.springboot.cruddemo.dao.NameRepo;
import com.luv2code.springboot.cruddemo.dao.TransferRepository;
import com.luv2code.springboot.cruddemo.dto.TransferInput;
import com.luv2code.springboot.cruddemo.dto.transfer;
import com.luv2code.springboot.cruddemo.entity.Transfer;

@Service
public class TransferServiceImplm implements TransferService {
	
	private TransferRepository employeeRepository;
	
	@Autowired
	NameRepo repo;
	
	@Autowired
	public TransferServiceImplm(TransferRepository theEmployeeRepository){
		employeeRepository = theEmployeeRepository;
	}
	
	@Override
	public List<Transfer> findAll() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}

	@Override
	public Transfer findById(int theId) {
		// TODO Auto-generated method stub
		Optional<Transfer> Result = employeeRepository.findById(theId);
		Transfer theEmployee = null;
		if(Result.isPresent()){
			theEmployee = Result.get();
		}else{
			throw new RuntimeException("Did not find employee id - "+ theId);
		}
		return theEmployee;
	}

	@Override
	public void save(Transfer theEmployee) {
		// TODO Auto-generated method stub
		employeeRepository.save(theEmployee);
	}

	@Override
	public void deleteById(int theId) {
		// TODO Auto-generated method stub
		employeeRepository.deleteById(theId);
	}
	
	
	@Override
	public void update(int theId1,int theId2, int transfer){
		Optional<Transfer> Result1 = employeeRepository.findById(theId1);
		Optional<Transfer> Result2 = employeeRepository.findById(theId2);
		
		Transfer theEmployee;
		Transfer theEmployee1;
		
		theEmployee = Result1.get();
		theEmployee1 = Result2.get();
		
		if(theEmployee == null){
			throw new RuntimeException("Employee ID not found - " + theId1);
		}
		if(theEmployee1 == null){
			throw new RuntimeException("Employee ID not found - " + theId2);
		}
		
		int hasil1,hasil2,total;
		hasil1 = theEmployee.getJml();
		
		if(hasil1<transfer){
			throw new RuntimeErrorException(null, "Saldo kurang");
		}
		
		total = hasil1 - transfer;
		theEmployee.setJml(total);
		employeeRepository.save(theEmployee);
		
		hasil2 = theEmployee1.getJml();
		total = hasil2 + transfer;
		theEmployee1.setJml(total);
		employeeRepository.save(theEmployee1);
	}
	
	
	public transfer GetFullName (int id){
		String lastName=null;
		String firstName = null;
		
		transfer trf = new transfer();
		
		ArrayList<Object[]> result = repo.getName(id);
		for (Object[] items:result){
			lastName = items[1].toString();
			firstName = items[0].toString();
		}
		
		trf.setFirst_name(firstName);
		trf.setLast_name(lastName);
		
		return trf;
	}
	
	public void trf(TransferInput trfs) throws Exception{
		try{
			
		if(trfs.getId1()==null){
			throw new RuntimeErrorException(null,"id sumber salah");
		}
		if((trfs.getId2()) == null){
			throw new RuntimeErrorException(null,"id penerima salah");
		}
		int amount1 = repo.getAmount(Integer.parseInt(trfs.getId1()));
		System.out.println(amount1);
		
		int amount2 = repo.getAmount(Integer.parseInt(trfs.getId2()));
		System.out.println(amount2);
		if (amount1 < Integer.parseInt(trfs.getAmount())){
			throw new RuntimeErrorException(null,"Saldo tidak cukup");
		}
		int hslTerima = amount2+Integer.parseInt(trfs.getAmount());
		System.out.println(hslTerima);
		int hslTrf = amount1-Integer.parseInt(trfs.getAmount());
		System.out.println(hslTrf);
		repo.updateAcc(hslTrf, Integer.parseInt(trfs.getId1()));
		repo.updateAcc(hslTerima, Integer.parseInt(trfs.getId2()));
		}catch (Exception ex) {
			System.out.println("ERROR - transfer - errorMessage >> " + ex.getMessage());
			
			throw new Exception(ex.getMessage());
		}
	}
}
