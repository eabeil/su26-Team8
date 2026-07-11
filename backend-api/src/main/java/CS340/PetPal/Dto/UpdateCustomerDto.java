package CS340.PetPal.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateCustomerDto {
    private String name;
    private String email;
    private String phone;
    private String password;

    public UpdateCustomerDto(String name, String email, String phone, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }
}
