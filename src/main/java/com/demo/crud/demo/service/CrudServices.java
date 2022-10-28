package com.demo.crud.demo.service;


import com.demo.crud.demo.entity.PatientInfo;
import com.demo.crud.demo.repository.Crud;
import javassist.NotFoundException;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;


@Component
public class CrudServices {

    @Autowired
    Crud crud;

    @Autowired
    private EntityManager entityManager;

    public PatientInfo createPatient(PatientInfo patientInfo){
        //crud.saveAndFlush(patientInfo);
        return crud.save(patientInfo);
    }

    public void deletePatient(long id){
        crud.deleteById(id);
    }
    public PatientInfo getPatientInfo(Long id) throws NotFoundException {

        if(crud.findById(id).isPresent())
        {
            return crud.findById(id).get();
        }
        return null;
    }

    public Iterable<PatientInfo> findAll(boolean isDeleted){
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedProductFilter");
        filter.setParameter("isDeleted", isDeleted);
        Iterable<PatientInfo> patientInfo=  crud.findAll();
        session.disableFilter("deletedProductFilter");
        return patientInfo;

    }
}
