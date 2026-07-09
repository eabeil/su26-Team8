package CS340.PetPal.Entity;


import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

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
@Table(name = "reviews")
@Getter
@Setter
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long review_id;

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