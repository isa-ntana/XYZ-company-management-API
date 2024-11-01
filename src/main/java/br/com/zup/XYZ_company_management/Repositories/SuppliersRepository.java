package br.com.zup.XYZ_company_management.Repositories;

import br.com.zup.XYZ_company_management.Models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SuppliersRepository extends JpaRepository<Supplier, UUID> {
}
