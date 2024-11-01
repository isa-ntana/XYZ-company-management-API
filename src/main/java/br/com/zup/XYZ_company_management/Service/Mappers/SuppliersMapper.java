package br.com.zup.XYZ_company_management.Service.Mappers;


import br.com.zup.XYZ_company_management.Controller.Suppliers.dtos.SuppliersRegisterDTO;
import br.com.zup.XYZ_company_management.Controller.Suppliers.dtos.SuppliersUpdateDTO;
import br.com.zup.XYZ_company_management.Models.Supplier;

public class SuppliersMapper {
    public static Supplier fromSupplierDTO(SuppliersRegisterDTO registerDTO){
        Supplier supplier = new Supplier();

        supplier.setSupplierName(registerDTO.getSupplierName());
        supplier.setCnpj(registerDTO.getCnpj());
        supplier.setTelephoneNumber(registerDTO.getTelephoneNumber());
        supplier.setAddress(registerDTO.getAddress());

        return supplier;
    }

    public static Supplier fromSupplierUpdateDTO(SuppliersUpdateDTO updateDTO){
        Supplier supplier = new Supplier();

        supplier.setId(updateDTO.getId());
        supplier.setSupplierName(updateDTO.getSupplierName());
        supplier.setCnpj(updateDTO.getCnpj());
        supplier.setTelephoneNumber(updateDTO.getTelephoneNumber());
        supplier.setAddress(updateDTO.getAddress());

        return supplier;
    }
}
