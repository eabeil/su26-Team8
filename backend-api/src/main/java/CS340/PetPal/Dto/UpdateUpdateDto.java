package CS340.PetPal.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateUpdateDto {
    private String title;
   private String description;

    public UpdateUpdateDto(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
