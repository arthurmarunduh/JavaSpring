package com.luv2code.springboot.cruddemo.service;

import java.util.List;

import com.luv2code.springboot.cruddemo.dto.TransferInput;
import com.luv2code.springboot.cruddemo.dto.transfer;
import com.luv2code.springboot.cruddemo.entity.Transfer;

public interface TransferService {

	public List<Transfer> findAll();
	public Transfer findById(int theId);
	public void save(Transfer theEmployee);
	public void deleteById(int theId);
	public void update(int theId1,int theId2, int transfer);
	public transfer GetFullName(int id);
	public void trf(TransferInput trfs) throws Exception;
}
