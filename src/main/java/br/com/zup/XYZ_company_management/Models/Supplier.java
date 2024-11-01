package br.com.zup.XYZ_company_management.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Setter
@Getter
@Table(name = "supplier")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String supplierName;
    private String cnpj;
    private String telephoneNumber;
    private String address;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Contract> contracts;

    public Supplier() { }
}
