package com.catchlogger.backend.model;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import com.catchlogger.backend.model.FishSpecies;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;


@Entity
@Table(name= "catches")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Catch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FishSpecies species;

    @Column(nullable = false)
    private Double weight;

    @Column(nullable = true)
    private Double distance;

    @Column(nullable = true)
    private String location;

    @Column(nullable = true, length = 1024)
    private String notes;

    @Column(nullable = false)
    private LocalDateTime caughtAt;

    @Column(nullable = true)
    private String rigInfo;

    @Column(nullable = true)
    private String baitInfo;
}
