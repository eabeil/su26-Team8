package CS340.PetPal.DTO;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewDto {

    private Long id;
    private Boolean recommended;
    private String customerComment;
    private String providerResponse;
    private LocalDateTime createdAt;
    
    private Long customerId;
    private Long providerId;
}