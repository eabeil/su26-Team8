package CS340.PetPal.Dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobUiCreateDto {
    private String name;
    private LocalDateTime time;
    private String duration;
    private double price;
}