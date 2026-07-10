package CS340.PetPal.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateReviewDto {
    private Boolean reccomended;
    private String customerComment;
    private Long customerId;
    private Long providerId;

    public CreateReviewDto(Boolean reccomended, String customerComment, Long customerId, Long providerId) {
        this.reccomended = reccomended;
        this.customerComment = customerComment;
        this.customerId = customerId;
        this.providerId = providerId;
    }    
}
