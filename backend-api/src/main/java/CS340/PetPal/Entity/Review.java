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
@Table(name = "reviews")
@Getter
@Setter
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    // TRUE = Thumbs Up // FALSE = Thumbs Down
    @Column(nullable = false)
    private Boolean recommended;

    // The review left by the customer
    @Column(nullable = false)
    private String customerComment;

    // The single reply from the provider
    @Column(nullable = true)
    private String providerResponse;

    // Date/Time
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = true)
    private LocalDateTime respondedAt;

    @Column(nullable = true)
    private LocalDateTime commentEditedAt;

    @Column(nullable = true)
    private LocalDateTime responseEditedAt;

    // The Customer who wrote the review
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnoreProperties({ "reviews", "pets" }) // Prevents infinite loops
    private Customer customer;

    // The Provider receiving the review
    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = false)
    @JsonIgnoreProperties({ "reviews", "updates", "jobs" }) // Prevents infinite loops
    private Provider provider;

    public Review(Boolean recommended, String customerComment, String providerResponse, LocalDateTime createdAt, LocalDateTime respondedAt, LocalDateTime commentEditedAt, LocalDateTime responseEditedAt, Customer customer, Provider provider) {
        this.recommended = recommended;
        this.customerComment = customerComment;
        this.providerResponse = providerResponse;
        this.createdAt = createdAt;
        this.respondedAt = respondedAt;
        this.commentEditedAt = commentEditedAt;
        this.responseEditedAt = responseEditedAt;
        this.customer = customer;
        this.provider = provider;
    }

    public boolean getWasEdited() {
        return this.commentEditedAt != null;
    }

    public boolean getHasResponse() {
        return this.providerResponse != null;
    }

    public boolean getWasResponseEdited() {
        return this.getResponseEditedAt() != null;
    }
}