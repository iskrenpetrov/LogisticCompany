package bg.nbu.logistics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bg.nbu.logistics.domain.entities.Office;

@Repository
public interface OfficeRepository extends JpaRepository<Office, Long> {
}
