package uz.pdp.apprelationshipuniver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.apprelationshipuniver.entity.University;

@Repository
public interface UniversityRepository extends JpaRepository<University, Integer> {
}
