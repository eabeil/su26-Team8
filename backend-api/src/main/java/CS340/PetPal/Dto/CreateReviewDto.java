package CS340.PetPal.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateReviewDto {
    private Boolean recommended;
    private String customerComment;
    private Long customerId;
    private Long providerId;

    public CreateReviewDto(Boolean recommended, String customerComment, Long customerId, Long providerId) {
        this.recommended = recommended;
        this.customerComment = customerComment;
        this.customerId = customerId;
        this.providerId = providerId;
    }    
}
