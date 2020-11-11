package com.luv2code.springboot.cruddemo.dao;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.cruddemo.entity.Transfer;

@Repository
public interface NameRepo extends JpaRepository<Transfer, String> {
	
	@Autowired
	
	@Query(value = "SELECT first_name, last_name "
			+ "FROM transfer x "
			+ "where id = ?1",nativeQuery=true)
	ArrayList<Object[]> getName(int id);
	//	String output(int id);
	
	@Query(value = "SELECT jml_uang "
			+ "FROM transfer x "
			+ "where id = ?1",nativeQuery=true)
	int getAmount(int id);
	
	@Transactional
	@Modifying
	@Query(value = "update transfer set jml_uang=?1 where id=?2 ;",
	nativeQuery=true)
	void updateAcc(int amount,int id);
	
}
