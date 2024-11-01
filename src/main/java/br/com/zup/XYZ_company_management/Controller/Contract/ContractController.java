package br.com.zup.XYZ_company_management.Controller.Contract;

import br.com.zup.XYZ_company_management.Controller.Contract.dtos.ContractUpdateDTO;
import br.com.zup.XYZ_company_management.Models.Contract;
import br.com.zup.XYZ_company_management.Service.ContractService;
import br.com.zup.XYZ_company_management.Service.Mappers.ContractMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contracts")
public class ContractController {
    @Autowired
    private ContractService contractService;

    @PutMapping("/{contractId}")
    public Contract updateContract(@PathVariable String contractId, @RequestBody ContractUpdateDTO updateDTO) {
        Contract contract = ContractMapper.fromContractUpdateDTO(updateDTO);
        return contractService.updateContract(contractId, contract);
    }

    @DeleteMapping("/{contractId}")
    public void deleteContract(@PathVariable String contractId) {
        contractService.deleteContract(contractId);
    }
}
