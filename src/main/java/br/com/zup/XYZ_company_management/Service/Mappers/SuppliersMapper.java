package br.com.zup.XYZ_company_management.Service.Mappers;


import br.com.zup.XYZ_company_management.Controller.Suppliers.dtos.SuppliersRegisterDTO;
import br.com.zup.XYZ_company_management.Controller.Suppliers.dtos.SuppliersUpdateDTO;
import br.com.zup.XYZ_company_management.Models.Suppliers;

public class SuppliersMapper {
    public static Suppliers fromSupplierDTO(SuppliersRegisterDTO registerDTO){
        Suppliers suppliers = new Suppliers();

        suppliers.setSupplierName(registerDTO.getSupplierName());
        suppliers.setCnpj(registerDTO.getCnpj());
        suppliers.setTelephoneNumber(registerDTO.getTelephoneNumber());
        suppliers.setAddress(registerDTO.getAddress());

        return suppliers;
    }

    public static Suppliers fromSupplierUpdateDTO(SuppliersUpdateDTO updateDTO){
        Suppliers suppliers = new Suppliers();

        suppliers.setId(updateDTO.getId());
        suppliers.setSupplierName(updateDTO.getSupplierName());
        suppliers.setCnpj(updateDTO.getCnpj());
        suppliers.setTelephoneNumber(updateDTO.getTelephoneNumber());
        suppliers.setAddress(updateDTO.getAddress());

        return suppliers;
    }
}
