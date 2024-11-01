package br.com.zup.XYZ_company_management.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Setter
@Getter
@Table(name = "contract")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String numberContract;
    private LocalDate beginContract;
    private LocalDate endContract;
    private BigDecimal value;
    private String description;
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    public Contract() {}

}
