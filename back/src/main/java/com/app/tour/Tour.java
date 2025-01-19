package com.app.tour;

import com.app.enums.TourSeason;
import com.app.enums.TourType;
import com.app.ordering.Ordering;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Tour implements Serializable {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "tour_g")
    @SequenceGenerator(name = "tour_g", sequenceName = "tour_seq", allocationSize = 1)
    private Long id;

    private String name;
    private String country;

    @Column(length = 5000)
    private String description;
    @Column(length = 1000)
    private String img = "";
    @Column(length = 1000)
    private String file = "";

    @Enumerated(EnumType.STRING)
    private TourType type;
    @Enumerated(EnumType.STRING)
    private TourSeason season;

    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL)
    private List<Ordering> orderings = new ArrayList<>();

    public Tour(String name, String country, String description) {
        this.name = name;
        this.country = country;
        this.description = description;
    }

    public void update(Tour update) {
        this.name = update.getName();
        this.country = update.getCountry();
        this.season = update.getSeason();
        this.description = update.getDescription();
    }

    public Tour(Long id, String name, String country, String description, TourType type, TourSeason season) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.description = description;
        this.type = type;
        this.season = season;
    }

    public void updateForTest(Tour update) {
        this.name = update.getName();
        this.country = update.getCountry();
        this.description = update.getDescription();
        this.type = update.getType();
        this.season = update.getSeason();
    }
}