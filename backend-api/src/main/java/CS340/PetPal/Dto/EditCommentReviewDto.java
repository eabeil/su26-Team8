
package CS340.PetPal.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EditCommentReviewDto {
    private Boolean reccomended;
    private String customerComment;
    
    EditCommentReviewDto(Boolean reccomended, String customerComment) {
        this.reccomended = reccomended;
        this.customerComment = customerComment;
    }
}
