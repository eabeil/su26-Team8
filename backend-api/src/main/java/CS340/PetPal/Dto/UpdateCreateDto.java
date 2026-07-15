package CS340.PetPal.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCreateDto {
    private String title;
    private String description;
    private String imageUrl;
    private long providerId;
}
