package br.com.zup.XYZ_company_management.Controller.Contract;

import br.com.zup.XYZ_company_management.Models.Contract;
import br.com.zup.XYZ_company_management.Repositories.ContractRepository;
import br.com.zup.XYZ_company_management.Service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ContractScheduler {

    @Autowired
    private ContractService contractService;

    @Scheduled(cron = "0 0/1 * ? * *")
    public void updateContractStatus() {
        List<Contract> expiredContracts = contractService.findContractsExpiringBefore(LocalDate.now());

        for (Contract contrato : expiredContracts) {
            contrato.setActive(false);
            contractService.saveContract(contrato);
        }
    }
}
