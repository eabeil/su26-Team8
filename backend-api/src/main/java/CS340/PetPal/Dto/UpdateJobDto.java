package CS340.PetPal.Dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateJobDto {
    private String name;
    private LocalDateTime time;
    private String duration;
    private double price;

    public UpdateJobDto(String name, LocalDateTime time, String duration, double price) {
        this.name = name;
        this.time = time;
        this.duration = duration;
        this.price = price;
    }
}
