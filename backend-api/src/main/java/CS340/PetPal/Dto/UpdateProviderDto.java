package CS340.PetPal.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateProviderDto {
    private String name;
    private String description;
    private String imageUrl;
    private String address;
    private String phone;
    private String email;

    public UpdateProviderDto(String name, String description, String imageUrl, String address, String phone,
            String email) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }
}
