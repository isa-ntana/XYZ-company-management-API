package br.com.zup.XYZ_company_management.Service;

import br.com.zup.XYZ_company_management.Models.*;
import br.com.zup.XYZ_company_management.Repositories.*;
import br.com.zup.XYZ_company_management.Service.Mappers.ContractMapper;
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

    public Contract saveContractById(UUID supplierId, Contract contract) {
        Supplier supplier = suppliersRepository.findById(supplierId)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        contract.setSupplier(supplier);
        return contractRepository.save(contract);
    }

    public List<Contract> findAllContractsBySupplierId(UUID supplierId) {
        List<Contract> allContracts = contractRepository.findBySupplierId(supplierId);
        selectionSortByActive(allContracts);
        return allContracts;
    }

    private void selectionSortByActive(List<Contract> contracts) {
        for (int index = 0; index < contracts.size() - 1; index++) {
            int minIndex = index;
            for (int subIndex = index + 1; subIndex < contracts.size(); subIndex++) {
                if (!contracts.get(minIndex).isActive() && contracts.get(subIndex).isActive()) {
                    minIndex = subIndex;
                }
            }
            Contract temp = contracts.get(minIndex);
            contracts.set(minIndex, contracts.get(index));
            contracts.set(index, temp);
        }
    }

    public Contract findContractById(UUID id) {
        Optional<Contract> contract = contractRepository.findById(id);
        return contract.orElseThrow(() -> new RuntimeException("Contract not found"));
    }

    public void deleteContract(UUID id) {
        contractRepository.deleteById(id);
    }

    public Contract updateContract(UUID contractId, Contract contractRequest) {
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

        existingContract.setActive(ContractMapper.checkActivity(existingContract.getEndContract()));


        return contractRepository.save(existingContract);
    }

    public List<Contract> findContractsByFilters(UUID supplierId,
                                                 Optional<LocalDate> beginContract,
                                                 Optional<LocalDate> endContract,
                                                 Optional<String> description,
                                                 Optional<Boolean> active) {
        if (beginContract.isPresent()) {
            return contractRepository.findBySupplierIdAndBeginContract(supplierId, beginContract.get());
        } else if (endContract.isPresent()) {
            return contractRepository.findBySupplierIdAndEndContract(supplierId, endContract.get());
        } else if (description.isPresent()) {
            return contractRepository.findBySupplierIdAndDescription(supplierId, description.get());
        } else if (active.isPresent()) {
            return contractRepository.findBySupplierIdAndActive(supplierId, active.get());
        }

            return contractRepository.findBySupplierId(supplierId);
    }
}
