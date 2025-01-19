package com.app.client;

import com.app.appUser.AppUser;
import com.app.enums.ClientCategory;
import com.app.enums.ClientGender;
import com.app.enums.TourType;
import com.app.ordering.Ordering;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.app.util.Global.getDateNow;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Client implements Serializable {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "client_g")
    @SequenceGenerator(name = "client_g", sequenceName = "client_seq", allocationSize = 1)
    private Long id;

    private String fio;
    private String tel;
    private String passport = "";
    private String date = getDateNow();

    @Column(length = 5000)
    private String description;

    @Enumerated(EnumType.STRING)
    private ClientCategory category;
    @Enumerated(EnumType.STRING)
    private ClientGender gender;
    @Enumerated(EnumType.STRING)
    private TourType tourType;

    @ManyToOne
    private AppUser manager;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Ordering> orderings = new ArrayList<>();

    public Client(String fio, String tel, String description) {
        this.fio = fio;
        this.tel = tel;
        this.description = description;
    }

    public void update(Client update) {
        this.fio = update.getFio();
        this.tel = update.getTel();
        this.passport = update.getPassport();
        this.description = update.getDescription();
    }

    public Client(Long id, String fio, String tel, String passport, String description, ClientCategory category, ClientGender gender, TourType tourType) {
        this.id = id;
        this.fio = fio;
        this.tel = tel;
        this.passport = passport;
        this.description = description;
        this.category = category;
        this.gender = gender;
        this.tourType = tourType;
    }

    public void updateForTest(Client update) {
        this.fio = update.getFio();
        this.tel = update.getTel();
        this.passport = update.getPassport();
        this.description = update.getDescription();
        this.category = update.getCategory();
        this.gender = update.getGender();
        this.tourType = update.getTourType();
    }

    public String getCategoryName() {
        return category != null ? category.name() : "";
    }

    public String getCategoryGetName() {
        return category != null ? category.getName() : "";
    }

    public String getGenderName() {
        return gender != null ? gender.name() : "";
    }

    public String getGenderGetName() {
        return gender != null ? gender.getName() : "";
    }

    public String getTourTypeName() {
        return tourType != null ? tourType.name() : "";
    }

    public String getTourTypeGetName() {
        return tourType != null ? tourType.getName() : "";
    }

    public List<Ordering> getOrderings() {
        orderings.sort(Comparator.comparing(Ordering::getId));
        Collections.reverse(orderings);
        return orderings;
    }
}