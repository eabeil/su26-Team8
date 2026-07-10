package CS340.PetPal.Dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateUpdateDto {
    private String title;
    private LocalDateTime time;
    private String duration;
    private double price;

    UpdateUpdateDto(String title, LocalDateTime time, String duration, double price) {
        this.title = title;
        this.time = time;
        this.duration = duration;
        this.price = price;
    }
}
