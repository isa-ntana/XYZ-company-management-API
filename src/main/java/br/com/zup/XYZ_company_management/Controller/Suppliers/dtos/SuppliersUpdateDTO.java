package br.com.zup.XYZ_company_management.Controller.Suppliers.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SuppliersUpdateDTO {
    @JsonIgnore
    @NotBlank(message = "insert a valid id")
    private String id;
    @NotBlank(message = "need a valid supplier name")
    private String supplierName;
    @NotBlank(message = "cnpj cant be blank")
    private String cnpj;
    @NotBlank(message = "insert a valid telephone number")
    @Min(value = 10)
    private String telephoneNumber;
    @NotBlank(message = "insert a valid address")
    private String address;

    public SuppliersUpdateDTO() {}

}
