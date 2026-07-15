package CS340.PetPal.Entity;

import java.time.LocalDateTime;

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
@Table(name = "updates")
@Getter
@Setter
@NoArgsConstructor
public class Update {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false) 
    private String imageUrl;

    @ManyToOne(optional = false)
    @JoinColumn(name = "provider_id", nullable = false)
    @JsonIgnoreProperties({ "jobs", "updates", "reviews" })
    private Provider provider;

    public Update(String title, LocalDateTime createdAt, String description, String imageUrl, Provider provider) {
        this.title = title;
        this.createdAt = createdAt;
        this.description = description;
        this.imageUrl = imageUrl;
        this.provider = provider;
    }
}
