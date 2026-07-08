package CS340.PetPal.Entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Update {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @JsonFormat(pattern = "yyy-MM-dd HH:mm")
    private LocalDateTime time;

    @Column
    private String duration;

    @Column(nullable = false)
    private double price;

    public Update(String name, LocalDateTime time, String duration, double price) {
        this.name = name;
        this.time = time;
        this.duration = duration;
        this.price = price;
    }
}
