package com.app.ordering;

import com.app.appUser.AppUser;
import com.app.client.Client;
import com.app.tour.Tour;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Ordering implements Serializable {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ordering_g")
    @SequenceGenerator(name = "ordering_g", sequenceName = "ordering_seq", allocationSize = 1)
    private Long id;

    private String date;
    private float price;

    @ManyToOne
    private AppUser manager;
    @ManyToOne
    private Tour tour;
    @ManyToOne
    private Client client;

    public Ordering(String date, float price) {
        this.date = date;
        this.price = price;
    }

    public Ordering(Long id, String date, float price) {
        this.id = id;
        this.date = date;
        this.price = price;
    }
}