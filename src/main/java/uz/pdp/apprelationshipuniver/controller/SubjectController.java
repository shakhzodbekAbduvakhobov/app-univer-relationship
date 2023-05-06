package uz.pdp.apprelationshipuniver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.apprelationshipuniver.entity.Subject;
import uz.pdp.apprelationshipuniver.repository.SubjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/subject")
public class SubjectController {

    @Autowired
    SubjectRepository subjectRepository;

    @RequestMapping(method = RequestMethod.POST)
    public String addSubject(@RequestBody Subject subject){
        boolean existsByName = subjectRepository.existsByName(subject.getName());
        if (existsByName)
            return "This subject already exist";

        subjectRepository.save(subject);
        return "added";
    }

    @GetMapping
    public List<Subject> getSubject(){
        return subjectRepository.findAll();
    }

    @PutMapping(value = "/{id}")
    public List<Subject> updateSubject(@PathVariable Integer id, @RequestBody Subject subject){
        Optional<Subject> repositoryById = subjectRepository.findById(id);
        if (repositoryById.isPresent()){
            Subject subject1 = repositoryById.get();
            subject1.setName(subject.getName());
            subjectRepository.save(subject1);
            return subjectRepository.findAll();
        }
        return new ArrayList<>();
    }

    @DeleteMapping(value = "/{id}")
    public String deleteSubject(@PathVariable Integer id){
        subjectRepository.deleteById(id);
        return "Deleted!";
    }



}
