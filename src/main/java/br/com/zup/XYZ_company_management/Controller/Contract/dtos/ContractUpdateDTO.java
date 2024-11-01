package br.com.zup.XYZ_company_management.Controller.Contract.dtos;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
public class ContractUpdateDTO {
    @NotNull(message = "You need a valid ID")
    private UUID id;
    @NotNull(message = "The number of contract cant be null")
    private String numberContract;
    @NotNull(message = "The begin of contract cant be null")
    private LocalDate beginContract;
    @NotNull(message = "The end of contract cant be null")
    private LocalDate endContract;
    @Min(value = 1, message = "Invalid value")
    private BigDecimal value;
    @NotNull(message = "Description cant be null")
    private String description;
    private boolean active;

    public ContractUpdateDTO() {}

}
