package soumyadeep.com.backend.Street_light.Security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soumyadeep.com.backend.Street_light.Security.model.Validate;

import java.util.Optional;

@Repository
public interface ValidateRepository extends JpaRepository<Validate, Long> {
    Optional<Validate> findByJobId (String jodId);
}
