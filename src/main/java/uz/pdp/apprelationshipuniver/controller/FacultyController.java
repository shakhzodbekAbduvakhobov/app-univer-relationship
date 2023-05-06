package uz.pdp.apprelationshipuniver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.apprelationshipuniver.entity.Faculty;
import uz.pdp.apprelationshipuniver.entity.University;
import uz.pdp.apprelationshipuniver.payload.FacultyDto;
import uz.pdp.apprelationshipuniver.repository.FacultyRepository;
import uz.pdp.apprelationshipuniver.repository.UniversityRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    UniversityRepository universityRepository;

    @PostMapping
    public String addFaculty(@RequestBody FacultyDto dto){
        if (facultyRepository.existsByNameAndAndUniversityId(dto.getName(), dto.getUniversityId()))
            return "This Faculty already exist";

        Faculty faculty = new Faculty();
        faculty.setName(dto.getName());
        Optional<University> optionalUniversity = universityRepository.findById(dto.getUniversityId());
        if (optionalUniversity.isPresent()){
            faculty.setUniversity(optionalUniversity.get());



            facultyRepository.save(faculty);
            return "Added";
        }
        return "This University is not found!";
    }

    @GetMapping
    public List<Faculty> getAllFaculty(){
        return facultyRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Faculty getOneFaculty(@PathVariable Integer id){
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        if (optionalFaculty.isPresent())
            return optionalFaculty.get();

        return new Faculty();
    }

    @PutMapping("/{id}")
    public List<Faculty> updateFaculty(@PathVariable Integer id, @RequestBody FacultyDto dto){
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        if (optionalFaculty.isPresent()){
            Faculty faculty = optionalFaculty.get();
            faculty.setName(dto.getName());

            Optional<University> universityOptional = universityRepository.findById(dto.getUniversityId());
            if (universityOptional.isPresent()){
                University university = universityOptional.get();
                faculty.setUniversity(university);
                facultyRepository.save(faculty);

                return facultyRepository.findAll();
            }
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }

    @DeleteMapping("/{id}")
    public List<Faculty> deleteFaculty(@PathVariable Integer id){
        facultyRepository.deleteById(id);
        return facultyRepository.findAll();
    }
}
