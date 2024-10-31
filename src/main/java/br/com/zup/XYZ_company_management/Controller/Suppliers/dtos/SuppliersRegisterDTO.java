package br.com.zup.XYZ_company_management.Controller.Suppliers.dtos;

import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.br.CNPJ;

@Setter
@Getter
public class SuppliersRegisterDTO {
    @NotNull(message = "Supplier name cant be null")
    private String supplierName;
    @NotNull(message = "cnpj cant be null")
    @CNPJ(message = "you need a valid cnpj")
    private String cnpj;
    @NotBlank(message = "you need a valid telephone number")
    @Min(value = 10)
    private String telephoneNumber;
    @NotBlank(message = "please, insert a valid address, cant be null")
    private String address;

    public SuppliersRegisterDTO() {}

}
