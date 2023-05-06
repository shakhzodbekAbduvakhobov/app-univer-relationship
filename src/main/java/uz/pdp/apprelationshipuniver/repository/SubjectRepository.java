package uz.pdp.apprelationshipuniver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.apprelationshipuniver.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    boolean existsByName(String name);
}
