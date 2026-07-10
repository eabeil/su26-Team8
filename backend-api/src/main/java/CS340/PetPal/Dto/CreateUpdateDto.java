package CS340.PetPal.Dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateUpdateDto {
    private String title;
    private LocalDateTime time;
    private String description;
    private long providerId;

    public CreateUpdateDto(String title, LocalDateTime time, String description, long providerId) {
        this.title = title;
        this.time = time;
        this.description = description;
        this.providerId = providerId;
    }    
}
