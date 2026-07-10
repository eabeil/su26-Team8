package CS340.PetPal.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RespondReviewDto {
    private String providerResponse;

    public RespondReviewDto(String providerResponse) {
        this.providerResponse = providerResponse;
    }
}
