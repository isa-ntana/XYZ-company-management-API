package br.com.zup.XYZ_company_management.Repositories;

import br.com.zup.XYZ_company_management.Models.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;

@Repository
public interface ContractRepository extends JpaRepository<Contract, String> {
    List<Contract> findBySuppliersId(String supplierId);

    List<Contract> findBySuppliersIdAndBeginContract(String supplierId, LocalDate beginContract);

    List<Contract> findBySuppliersIdAndActive(String supplierId, boolean active);

    List<Contract> findBySuppliersIdAndEndContract(String supplierId, LocalDate endContract);

    List<Contract> findBySuppliersIdAndDescription(String supplierId, String description);
}
