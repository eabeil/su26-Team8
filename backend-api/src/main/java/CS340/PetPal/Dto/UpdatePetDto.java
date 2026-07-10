package CS340.PetPal.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdatePetDto {
    private String name;
    private String speciesOrBreed;
    private Integer age;
    private String specialCareInstructions;
    private String traits;

    UpdatePetDto(String name, String speciesOrBreed, Integer age, String specialCareInstructions, String traits) {
        this.name = name;
        this.speciesOrBreed = speciesOrBreed;
        this.age = age;
        this.specialCareInstructions = specialCareInstructions;
        this.traits = traits;
    }
}
