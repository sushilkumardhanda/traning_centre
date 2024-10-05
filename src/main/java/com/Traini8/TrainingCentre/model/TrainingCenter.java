package com.Traini8.TrainingCentre.model;
import jakarta.persistence.*; // Ensure this is the import statement
import jakarta.validation.constraints.*;
import java.time.Instant;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "training_centers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingCenter {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 40)
    private String centerName;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]{12}$", message = "Center code must be exactly 12 alphanumeric characters")
    private String centerCode;

    @Embedded
    private Address address;

    private Integer studentCapacity;

    @ElementCollection
    private List<String> coursesOffered;

    private Instant createdOn ;

    @Email
    private String contactEmail;

    @NotBlank
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid phone number")
    private String contactPhone;

    @PrePersist
    protected void onCreate() {
        this.createdOn = Instant.now();
    }
}

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
class Address {
    private String detailedAddress;
    private String city;
    private String state;
    private String pincode;

    // Getters and Setters
}
