package br.com.zup.XYZ_company_management.Service;

import br.com.zup.XYZ_company_management.Controller.Suppliers.dtos.SuppliersRegisterDTO;
import br.com.zup.XYZ_company_management.Models.Supplier;
import br.com.zup.XYZ_company_management.Repositories.SuppliersRepository;
import br.com.zup.XYZ_company_management.Service.Mappers.SuppliersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SuppliersService {
    @Autowired
    private SuppliersRepository suppliersRepository;

    public Supplier saveSupplier(SuppliersRegisterDTO suppliers) {
        Supplier supplierEntity = SuppliersMapper.fromSupplierDTO(suppliers);

        return this.suppliersRepository.save(supplierEntity);
    }

    public List<Supplier> getAllSuppliers() { return suppliersRepository.findAll(); }

    public Supplier findSupplierById(UUID id) {
        Optional<Supplier> suppliers = suppliersRepository.findById(id);
        return suppliers.orElseThrow(() -> new RuntimeException("Supplier not found"));
    }

    public void deleteSupplier(UUID id) { suppliersRepository.deleteById(id); }

    public Supplier updateSupplier(UUID supplierId, Supplier supplierRequest) {
        Supplier existingSupplier = this.findSupplierById(supplierId);

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
