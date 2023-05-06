package uz.pdp.apprelationshipuniver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.apprelationshipuniver.entity.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Integer> {

    boolean existsByNameAndAndUniversityId(String name, Integer universityId);
}
