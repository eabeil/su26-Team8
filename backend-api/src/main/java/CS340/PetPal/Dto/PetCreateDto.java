package CS340.PetPal.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PetCreateDto {
    private String name;
    private String speciesOrBreed;
    private Integer age;
    private String imageUrl;
    private String specialCareInstructions;
    private String traits;
    private Long customerId;
}
