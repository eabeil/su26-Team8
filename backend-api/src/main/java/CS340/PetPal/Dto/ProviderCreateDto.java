package CS340.PetPal.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProviderCreateDto {
    private String name;
    private String description;
    private String imageUrl;
    private String address;
    private String phone;
    private String email;
    private String password;
}
