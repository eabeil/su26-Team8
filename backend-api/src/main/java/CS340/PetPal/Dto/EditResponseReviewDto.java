package CS340.PetPal.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EditResponseReviewDto {
    private String providerResponse;

    public EditResponseReviewDto(String providerResponse) {
        this.providerResponse = providerResponse;
    }
}
