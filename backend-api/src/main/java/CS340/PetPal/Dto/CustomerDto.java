package CS340.PetPal.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDto {

    private Long id;
    private String fullName;
    private String email;
    private String phoneNumber;
}