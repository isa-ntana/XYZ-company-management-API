package br.com.zup.XYZ_company_management.Service;

import br.com.zup.XYZ_company_management.Controller.Suppliers.dtos.SuppliersRegisterDTO;
import br.com.zup.XYZ_company_management.Models.Suppliers;
import br.com.zup.XYZ_company_management.Repositories.SuppliersRepository;
import br.com.zup.XYZ_company_management.Service.Mappers.SuppliersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SuppliersService {
    @Autowired
    private SuppliersRepository suppliersRepository;

    public Suppliers saveSupplier(SuppliersRegisterDTO suppliers) {
        Suppliers supplierEntity = SuppliersMapper.fromSupplierDTO(suppliers);

        if (supplierEntity.getId() == null) {
            supplierEntity.setId(UUID.randomUUID().toString());
        }

        return this.suppliersRepository.save(supplierEntity);
    }

    public List<Suppliers> getAllSuppliers() { return suppliersRepository.findAll(); }

    public Suppliers findSupplierById(String id) {
        Optional<Suppliers> suppliers = suppliersRepository.findById(id);
        return suppliers.orElseThrow(() -> new RuntimeException("Supplier not found"));
    }

    public void deleteSupplier(String id) { suppliersRepository.deleteById(id); }

    public Suppliers updateSupplier(String supplierId, Suppliers supplierRequest) {
        Suppliers existingSupplier = this.findSupplierById(supplierId);

        if (!existingSupplier.getSupplierName().equals(supplierRequest.getSupplierName())) {
            existingSupplier.setSupplierName(supplierRequest.getSupplierName());
        }

        if (!existingSupplier.getCnpj().equals(supplierRequest.getCnpj())) {
            existingSupplier.setCnpj(supplierRequest.getCnpj());
        }

        if (!existingSupplier.getTelephoneNumber().equals(supplierRequest.getTelephoneNumber())) {
            existingSupplier.setTelephoneNumber(supplierRequest.getTelephoneNumber());
        }

        if (!existingSupplier.getAddress().equals(supplierRequest.getAddress())) {
            existingSupplier.setAddress(supplierRequest.getAddress());
        }

        return suppliersRepository.save(existingSupplier);
    }
}
