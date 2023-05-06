package uz.pdp.apprelationshipuniver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.apprelationshipuniver.entity.Address;
import uz.pdp.apprelationshipuniver.entity.University;
import uz.pdp.apprelationshipuniver.payload.UniversityDto;
import uz.pdp.apprelationshipuniver.repository.AddressRepository;
import uz.pdp.apprelationshipuniver.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/university")
public class UniversityController {

    @Autowired
    UniversityRepository universityRepository;
    @Autowired
    AddressRepository addressRepository;

    @RequestMapping( method = RequestMethod.GET)
    public List<University> universities(){
        return universityRepository.findAll();
    }

    @RequestMapping( method = RequestMethod.POST)
    public List<University> addUniversities(@RequestBody UniversityDto universityDto){

        Address address = new Address();
        address.setCity(universityDto.getCity());
        address.setDistrict(universityDto.getDistrict());
        address.setStreet(universityDto.getStreet());
        address.setHomeNumber(universityDto.getHomeNumber());
        Address savedAddress = addressRepository.save(address);

        University university = new University();
        university.setName(universityDto.getName());
        university.setAddress(savedAddress);
        universityRepository.save(university);
        return universityRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public University getOneUniversity(@PathVariable Integer id){
        Optional<University> universityRepositoryById = universityRepository.findById(id);
        if (universityRepositoryById.isPresent()){
            return universityRepositoryById.get();
        }
        return new University();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String updateUniversity(@PathVariable Integer id, @RequestBody UniversityDto dto){

        Optional<University> universityOptional = universityRepository.findById(id);
        if (universityOptional.isPresent()){
            University university = universityOptional.get();
            university.setName(dto.getName());
            Address address = university.getAddress();
            address.setCity(dto.getCity());
            address.setDistrict(dto.getDistrict());
            address.setStreet(dto.getStreet());
            address.setHomeNumber(dto.getHomeNumber());
            universityRepository.save(university);
            return "O'zartirildi";
        }
        return "";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public List<University> deleteUniversity(@PathVariable Integer id){
        universityRepository.deleteById(id);
        return universityRepository.findAll();
    }
}
