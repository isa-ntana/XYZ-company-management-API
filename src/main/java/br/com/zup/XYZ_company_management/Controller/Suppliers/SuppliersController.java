package br.com.zup.XYZ_company_management.Controller.Suppliers;

import br.com.zup.XYZ_company_management.Controller.Contract.dtos.ContractRegisterDTO;
import br.com.zup.XYZ_company_management.Controller.Suppliers.dtos.SuppliersRegisterDTO;
import br.com.zup.XYZ_company_management.Controller.Suppliers.dtos.SuppliersUpdateDTO;
import br.com.zup.XYZ_company_management.Models.Contract;
import br.com.zup.XYZ_company_management.Models.Suppliers;
import br.com.zup.XYZ_company_management.Service.ContractService;
import br.com.zup.XYZ_company_management.Service.Mappers.ContractMapper;
import br.com.zup.XYZ_company_management.Service.Mappers.SuppliersMapper;
import br.com.zup.XYZ_company_management.Service.SuppliersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/suppliers")
public class SuppliersController {
    @Autowired
    private SuppliersService suppliersService;

    @Autowired
    private ContractService contractService;

    @GetMapping
    public List<Suppliers> getAllSuppliers() { return suppliersService.getAllSuppliers(); }

    @GetMapping("/{supplierId}")
    public Suppliers findSupplierById(@PathVariable String supplierId) {
        return suppliersService.findSupplierById(supplierId);
    }

    @PostMapping
    public Suppliers addSupplier(@Valid @RequestBody SuppliersRegisterDTO registerDTO) {
        return suppliersService.saveSupplier(registerDTO);
    }

    @PutMapping("/{supplierId}")
    public Suppliers updateSupplier(@PathVariable String supplierId, @Valid @RequestBody SuppliersUpdateDTO updateDTO) {
            return suppliersService.updateSupplier(supplierId, SuppliersMapper.fromSupplierUpdateDTO(updateDTO));
    }

    @DeleteMapping("/{supplierId}")
    public void deleteSupplier(@PathVariable String supplierId) {
        suppliersService.deleteSupplier(supplierId);
    }

    @GetMapping("/{contractId}")
    public Contract findContractById(@PathVariable String contractId) {
        return contractService.findContractById(contractId);
    }

    @GetMapping("/{supplierId}/contracts")
    public List<Contract> getContractsBySupplierId(@PathVariable String supplierId) {
        return contractService.findAllContractsBySupplierId(supplierId);
    }

    @PostMapping("/{supplierId}/newContract")
    public Contract addContractById(@PathVariable String supplierId, @RequestBody ContractRegisterDTO registerDTO) {
        Contract contract = ContractMapper.fromContractDTO(registerDTO);
        return contractService.saveContractById(supplierId, contract);
    }

    @GetMapping("/{supplierId}/findContracts")
    public List<Contract> getContractsByFilters(
            @PathVariable String supplierId,
            @RequestParam Optional<LocalDate> beginContract,
            @RequestParam Optional<LocalDate> endContract,
            @RequestParam Optional<String> description,
            @RequestParam Optional<Boolean> active) {

        return contractService.findContractsByFilters(
                supplierId, beginContract, endContract, description, active);
    }
}
