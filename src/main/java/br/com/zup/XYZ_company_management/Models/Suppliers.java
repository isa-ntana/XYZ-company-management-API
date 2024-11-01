package br.com.zup.XYZ_company_management.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.*;

@Entity
@Setter
@Getter
@Table(name = "suppliers")
public class Suppliers {
    @Id
    @Column(columnDefinition = "varchar", unique = true)
    private String id;
    private String supplierName;
    private String cnpj;
    private String telephoneNumber;
    private String address;

    @OneToMany(mappedBy = "suppliers", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Contract> contracts;

    public Suppliers() { this.id = UUID.randomUUID().toString(); }
}
