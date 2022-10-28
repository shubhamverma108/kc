package com.demo.crud.demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigInteger;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@SQLDelete(sql = "UPDATE patient_info SET deleted = true WHERE id=?")
@FilterDef(name = "deletedProductFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedProductFilter", condition = "deleted = :isDeleted")
public class PatientInfo  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "Patient_Name")
    @NotEmpty(message = "Please enter name")
    @Pattern(regexp="^[a-zA-Z\\s]*$",message="not a valid type of name")
    String name;

    String address;

    //@NotNull
    //@Pattern(regexp="^[0-9]{10}$",message = "Not a valid No. According to mobile No.")
    //@Size(min=10,max = 10,message = "Mobile No. not have 10 digit")
    Long phnNumber;

    @Email
    String email;

    String gender;

    Date dob;

    // @OneToOne(mappedBy = "patientInfo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OneToOne(mappedBy = "patientInfo",cascade=CascadeType.ALL)
    @JsonManagedReference
    private EmergencyContact emergencyContact;

    private boolean deleted = Boolean.FALSE;
}
