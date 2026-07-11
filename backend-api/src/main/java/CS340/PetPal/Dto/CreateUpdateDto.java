package CS340.PetPal.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateUpdateDto {
    private String title;
    private String description;
    private long providerId;

    public CreateUpdateDto(String title, String description, long providerId) {
        this.title = title;
        this.description = description;
        this.providerId = providerId;
    }
}
