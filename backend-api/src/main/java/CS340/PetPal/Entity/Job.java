package CS340.PetPal.Entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "jobs")
@Getter
@Setter
@NoArgsConstructor
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime time;

    @Column(nullable = false)
    private String duration;

    @Column(nullable = false)
    private double price;

    @ManyToOne()
    @JoinColumn(name = "provider_id", nullable = false)
    @JsonIgnoreProperties({ "jobs", "updates", "reviews" })
    private Provider provider;

    public Job(String name, LocalDateTime time, String duration, double price, Provider provider) {
        this.name = name;
        this.time = time;
        this.duration = duration;
        this.price = price;
        this.provider = provider;
    }

    public String getFormattedTime() {
        return this.time.format(DateTimeFormatter.ofPattern("EEE, MMM d, yyyy '•' h:mm a"));
    }
}
