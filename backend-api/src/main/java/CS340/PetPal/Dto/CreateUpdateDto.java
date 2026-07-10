package CS340.PetPal.Dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateUpdateDto {
    private String title;
    private LocalDateTime time;
    private String duration;
    private double price;
    private long providerId;

    public CreateUpdateDto(String title, LocalDateTime time, String duration, double price, long providerId) {
        this.title = title;
        this.time = time;
        this.duration = duration;
        this.price = price;
        this.providerId = providerId;
    }    
}
