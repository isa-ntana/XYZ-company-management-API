package br.com.zup.XYZ_company_management.Service;

import br.com.zup.XYZ_company_management.Models.*;
import br.com.zup.XYZ_company_management.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class ContractService {

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private SuppliersRepository suppliersRepository;

    public Contract saveContractById(String supplierId, Contract contract) {
        Suppliers supplier = suppliersRepository.findById(supplierId)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        contract.setSuppliers(supplier);
        contract.setActive(shouldBeActive(supplierId, contract.getEndContract()));

        return contractRepository.save(contract);
    }

    public Map<String, List<Contract>> findAllContractsBySupplierId(String supplierId) {
        List<Contract> allContracts = contractRepository.findBySuppliersId(supplierId);

        List<Contract> activeContracts = new ArrayList<>();
        List<Contract> inactiveContracts = new ArrayList<>();

        for (Contract contract : allContracts) {
            if (contract.isActive()) {
                activeContracts.add(contract);
            } else {
                inactiveContracts.add(contract);
            }
        }

        Map<String, List<Contract>> organizedContracts = new HashMap<>();
        organizedContracts.put("active", activeContracts);
        organizedContracts.put("inactive", inactiveContracts);

        return organizedContracts;
    }


    public Contract findContractById(String id) {
        Optional<Contract> contract = contractRepository.findById(id);
        return contract.orElseThrow(() -> new RuntimeException("Contract not found"));
    }

    public void deleteContract(String id) {
        contractRepository.deleteById(id);
    }

    public Contract updateContract(String contractId, Contract contractRequest) {
        Contract existingContract = contractRepository.findById(contractId)
                .orElseThrow(() -> new RuntimeException("Contract not found to update"));

        if (!existingContract.getNumberContract().equals(contractRequest.getNumberContract())) {
            existingContract.setNumberContract(contractRequest.getNumberContract());
        }

        if (!existingContract.getBeginContract().equals(contractRequest.getBeginContract())) {
        existingContract.setBeginContract(contractRequest.getBeginContract());
        }

        if (!existingContract.getEndContract().equals(contractRequest.getEndContract())) {
            existingContract.setEndContract(contractRequest.getEndContract());
        }

        if (!existingContract.getValue().equals(contractRequest.getValue())) {
            existingContract.setValue(contractRequest.getValue());
        }

        if (!existingContract.getDescription().equals(contractRequest.getDescription())) {
            existingContract.setDescription(contractRequest.getDescription());
        }

        existingContract.setActive(shouldBeActive(existingContract.getSuppliers().getId(),
                existingContract.getEndContract()));

        return contractRepository.save(existingContract);
    }

    private boolean shouldBeActive(String supplierId, LocalDate endContract) {
        List<Contract> contracts = contractRepository.findBySuppliersId(supplierId);
        return contracts.stream()
                .filter(contract -> contract.getEndContract().isAfter(LocalDate.now()))
                .max(Comparator.comparing(Contract::getEndContract))
                .map(contract -> contract.getEndContract().isAfter(endContract)).orElse(true);
    }
}
