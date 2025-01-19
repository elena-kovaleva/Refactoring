package com.app.tour;

import com.app.enums.TourSeason;
import com.app.enums.TourType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {
    List<Tour> findAllByType(TourType type);

    List<Tour> findAllBySeason(TourSeason season);
}
