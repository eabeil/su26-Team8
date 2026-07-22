package CS340.PetPal.Entity;

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
@Table(name = "pets")
@Getter
@Setter
@NoArgsConstructor
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String speciesOrBreed;

    private Integer age;

    @Column(name = "image_url", nullable = true)
    private String imageUrl;

    @Column(nullable = false)
    private String specialCareInstructions;

    // Comma seperated string for traits instead.
    @Column(nullable = false)
    private String traits;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnoreProperties({"pets", "reviews"})
    private Customer customer;

    // Updated Constructor
    public Pet(String name, String speciesOrBreed, Integer age, String imageUrl, String specialCareInstructions, String traits, Customer customer) {
        this.name = name;
        this.speciesOrBreed = speciesOrBreed;
        this.age = age;
        this.imageUrl = imageUrl;
        this.specialCareInstructions = specialCareInstructions;
        this.traits = traits;
        this.customer = customer;
    }
}