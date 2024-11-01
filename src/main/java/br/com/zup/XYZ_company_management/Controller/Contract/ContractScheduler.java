package br.com.zup.XYZ_company_management.Controller.Contract;

import br.com.zup.XYZ_company_management.Models.Contract;
import br.com.zup.XYZ_company_management.Repositories.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ContractScheduler {

    @Autowired
    private ContractRepository contractRepository;

    @Scheduled(fixedRate = 3600000)
    public void updateContractStatuses() {
        List<Contract> contracts = contractRepository.findAll();
        LocalDate now = LocalDate.now();

        for (Contract contract : contracts) {
            if (contract.getEndContract().isBefore(now)) {
                contract.setActive(false);
                contractRepository.save(contract);
            }
        }
    }
}
