package br.com.zup.XYZ_company_management.Repositories;

import br.com.zup.XYZ_company_management.Models.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;

@Repository
public interface ContractRepository extends JpaRepository<Contract, UUID> {
    List<Contract> findBySupplierId(UUID supplierId);

    List<Contract> findBySupplierIdAndBeginContract(UUID supplierId, LocalDate beginContract);

    List<Contract> findBySupplierIdAndActive(UUID supplierId, boolean active);

    List<Contract> findBySupplierIdAndEndContract(UUID supplierId, LocalDate endContract);

    List<Contract> findBySupplierIdAndDescription(UUID supplierId, String description);
}
