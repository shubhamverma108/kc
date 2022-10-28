package com.demo.crud.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class EmergencyContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String localGuardianName;

    String guardianAddress;

    @OneToOne
    @JoinColumn(name = "pid")
    @JsonBackReference
    private PatientInfo patientInfo;
}
