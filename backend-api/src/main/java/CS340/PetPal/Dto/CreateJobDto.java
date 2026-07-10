package CS340.PetPal.Dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateJobDto {
    private String name;
    private LocalDateTime time;
    private String duration;
    private double price;
    private long providerId;

    CreateJobDto(String name, LocalDateTime time, String duration, double price, long providerId) {
        this.name = name;
        this.time = time;
        this.duration = duration;
        this.price = price;
        this.providerId = providerId;
    }
}