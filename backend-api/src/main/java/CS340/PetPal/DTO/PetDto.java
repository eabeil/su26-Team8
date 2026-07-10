package CS340.PetPal.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PetDto {

    private Long id;
    private String name;
    private String speciesOrBreed;
    private Integer age;
    private String specialCareInstructions;
    private String traits;
    
    private Long customerId;
}