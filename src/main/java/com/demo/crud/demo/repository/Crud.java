package com.demo.crud.demo.repository;

import com.demo.crud.demo.entity.PatientInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public interface Crud extends JpaRepository<PatientInfo,Long>{

//   @Override
//   <S extends PatientInfo> S saveAndFlush(S entity);

  // @Query(value = "Delete FROM patient_info p  JOIN emergency_contact e ON p.id=e.pid where id=?1",nativeQuery = true)
   @Override
   void deleteById(Long id);

   @Override
   PatientInfo getById(Long id);

}