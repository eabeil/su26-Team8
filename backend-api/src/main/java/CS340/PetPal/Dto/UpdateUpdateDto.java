package CS340.PetPal.Dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateUpdateDto {
    private String title;
    private LocalDateTime time;
    private String description;

    public UpdateUpdateDto(String title, LocalDateTime time, String description) {
        this.title = title;
        this.time = time;
        this.description = description;
    }
}
