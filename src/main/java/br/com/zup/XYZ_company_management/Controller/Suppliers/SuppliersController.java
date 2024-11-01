package br.com.zup.XYZ_company_management.Controller.Suppliers;

import br.com.zup.XYZ_company_management.Controller.Contract.dtos.ContractRegisterDTO;
import br.com.zup.XYZ_company_management.Controller.Suppliers.dtos.SuppliersRegisterDTO;
import br.com.zup.XYZ_company_management.Controller.Suppliers.dtos.SuppliersUpdateDTO;
import br.com.zup.XYZ_company_management.Models.Contract;
import br.com.zup.XYZ_company_management.Models.Supplier;
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
    public List<Supplier> getAllSuppliers() { return suppliersService.getAllSuppliers(); }

    @GetMapping("/{supplierId}")
    public Supplier findSupplierById(@PathVariable UUID supplierId) {
        return suppliersService.findSupplierById(supplierId);
    }

    @PostMapping
    public Supplier addSupplier(@Valid @RequestBody SuppliersRegisterDTO registerDTO) {
        return suppliersService.saveSupplier(registerDTO);
    }

    @PutMapping("/{supplierId}")
    public Supplier updateSupplier(@PathVariable UUID supplierId, @Valid @RequestBody SuppliersUpdateDTO updateDTO) {
            return suppliersService.updateSupplier(supplierId, SuppliersMapper.fromSupplierUpdateDTO(updateDTO));
    }

    @DeleteMapping("/{supplierId}")
    public void deleteSupplier(@PathVariable UUID supplierId) {
        suppliersService.deleteSupplier(supplierId);
    }

    @GetMapping("/{contractId}")
    public Contract findContractById(@PathVariable UUID contractId) {
        return contractService.findContractById(contractId);
    }

    @GetMapping("/{supplierId}/contracts")
    public List<Contract> getContractsBySupplierId(@PathVariable UUID supplierId) {
        return contractService.findAllContractsBySupplierId(supplierId);
    }

    @PostMapping("/{supplierId}/newContract")
    public Contract addContractById(@PathVariable UUID supplierId, @RequestBody ContractRegisterDTO registerDTO) {
        Contract contract = ContractMapper.fromContractDTO(registerDTO);
        return contractService.saveContractById(supplierId, contract);
    }

    @GetMapping("/{supplierId}/findContracts")
    public List<Contract> getContractsByFilters(
            @PathVariable UUID supplierId,
            @RequestParam Optional<LocalDate> beginContract,
            @RequestParam Optional<LocalDate> endContract,
            @RequestParam Optional<String> description,
            @RequestParam Optional<Boolean> active) {

        return contractService.findContractsByFilters(
                supplierId, beginContract, endContract, description, active);
    }
}
