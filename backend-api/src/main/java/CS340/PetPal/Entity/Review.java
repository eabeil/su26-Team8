package CS340.PetPal.Entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TRUE = Thumbs Up // FALSE = Thumbs Down
    @Column(nullable = false)
    private Boolean recommended;

    // The review left by the customer
    @Column(length = 1000, nullable = false)
    private String customerComment;

    // The single reply from the provider
    @Column(length = 1000)
    private String providerResponse;

    // Date/Time
    @CreationTimestamp
    private LocalDateTime createdAt;

    // The Customer who wrote the review
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnoreProperties({"reviews", "pets"}) // Prevents infinite loops
    private Customer customer;

    // The Provider receiving the review
    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = false)
    @JsonIgnoreProperties({"reviews"}) // Prevents infinite loops
    private Provider provider; 

    
    public Review(Boolean recommended, String customerComment, Customer customer, Provider provider) {
        this.recommended = recommended;
        this.customerComment = customerComment;
        this.customer = customer;
        this.provider = provider;
    }
}