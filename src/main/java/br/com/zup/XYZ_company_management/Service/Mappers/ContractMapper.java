package br.com.zup.XYZ_company_management.Service.Mappers;

import br.com.zup.XYZ_company_management.Controller.Contract.dtos.ContractRegisterDTO;
import br.com.zup.XYZ_company_management.Controller.Contract.dtos.ContractUpdateDTO;
import br.com.zup.XYZ_company_management.Models.Contract;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ContractMapper {
    public static Contract fromContractDTO(ContractRegisterDTO registerDTO){
        Contract contract = new Contract();

        contract.setNumberContract(registerDTO.getNumberContract());
        contract.setBeginContract(registerDTO.getBeginContract());
        contract.setEndContract(registerDTO.getEndContract());
        contract.setValue(registerDTO.getValue());
        contract.setDescription(registerDTO.getDescription());
        contract.setActive(checkActivity(registerDTO.getEndContract()));

        return contract;
    }

    public static boolean checkActivity(LocalDate endDate) {
        LocalDate now = LocalDate.now();
        return endDate.isAfter(now);
    }

    public static Contract fromContractUpdateDTO(ContractUpdateDTO updateDTO){
        Contract contract = new Contract();

        contract.setId(updateDTO.getId());
        contract.setNumberContract(updateDTO.getNumberContract());
        contract.setBeginContract(updateDTO.getBeginContract());
        contract.setEndContract(updateDTO.getEndContract());
        contract.setValue(updateDTO.getValue());
        contract.setDescription(updateDTO.getDescription());
        contract.setActive(checkActivity(updateDTO.getEndContract()));

        return contract;
    }
}
