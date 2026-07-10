package CS340.PetPal.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreatePetDto {
    private String name;
    private String speciesOrBreed;
    private Integer age;
    private String specialCareInstructions;
    private String traits;
    private Long customerId;

    CreatePetDto(String name, String speciesOrBreed, Integer age, String specialCareInstructions, String traits, Long customerId) {
        this.name = name;
        this.speciesOrBreed = speciesOrBreed;
        this.age = age;
        this.specialCareInstructions = specialCareInstructions;
        this.traits = traits;
        this.customerId = customerId;
    }
}
