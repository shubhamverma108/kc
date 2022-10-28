package com.demo.crud.demo;

import com.demo.crud.demo.entity.PatientInfo;
import com.demo.crud.demo.service.CrudServices;
//import com.demo.crud.demo.service.EmployeeService;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@SpringBootApplication
@RestController
@Slf4j
@RequestMapping("/patient")
public class SpringBootKeycloakExampleApplication {

    @Autowired
    private CrudServices crudServices;

    @PostMapping("/create")
    @RolesAllowed("admin")
    public PatientInfo create(@Valid @RequestBody PatientInfo patientInfo)
    {
        log.info("hitting the create api");
        return crudServices.createPatient(patientInfo);
    }

    @GetMapping("/get/{Id}")
    @RolesAllowed("user")
    public PatientInfo getPatientById(@PathVariable("Id") Long Id) throws NotFoundException {
        return crudServices.getPatientInfo(Id);
    }

    @PutMapping("/update")
    @RolesAllowed("admin")
    public void updatePatientInfo(@Valid @RequestBody PatientInfo patientInfo){
        crudServices.createPatient(patientInfo);
    }

    @DeleteMapping("/Delete/{id}")
    @RolesAllowed("admin")
    public void deletePatient(@PathVariable Long id){
        crudServices.deletePatient(id);
    }

    @GetMapping
    @RolesAllowed("admin")
    public Iterable<PatientInfo> deletedPatient(@RequestParam(value = "isDeleted", required = false, defaultValue = "false") boolean isDelete){
        return crudServices.findAll(isDelete);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootKeycloakExampleApplication.class, args);
    }

}
