package CS340.PetPal.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewCreateDto {
    private Boolean recommended;
    private String customerComment;
    private Long customerId;
    private Long providerId;
}
