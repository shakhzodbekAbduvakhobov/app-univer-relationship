package com.example.appuniversityrelationships.controller;

import com.example.appuniversityrelationships.entity.Subject;
import com.example.appuniversityrelationships.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/subject")
public class SubjectController {

    @Autowired
    SubjectRepository subjectRepository;

    @RequestMapping(method = RequestMethod.POST)
    public String addSubject(@RequestBody Subject subject){

        System.out.println(subjectRepository.existsByName(subject.getName()));

//        boolean existsByName = subjectRepository.existsByName(subject.getName());
//        if (existsByName)
//            return "This subject already exist";

        subjectRepository.save(subject);
        return "added";

    }

}
