package uz.pdp.apprelationshipuniver.payload;

import lombok.Data;

@Data
public class UniversityDto {
    private String name;
    private String city;
    private String district;
    private String street;
    private String homeNumber;
}
