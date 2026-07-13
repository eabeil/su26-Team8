package CS340.PetPal.Dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JobUpdateDto {
    private String name;
    private LocalDateTime time;
    private String duration;
    private double price;
}
