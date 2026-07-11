
package CS340.PetPal.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EditCommentReviewDto {
    private Boolean recommended;
    private String customerComment;
    
    public EditCommentReviewDto(Boolean recommended, String customerComment) {
        this.recommended = recommended;
        this.customerComment = customerComment;
    }
}
