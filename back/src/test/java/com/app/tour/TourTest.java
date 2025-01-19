package com.app.tour;

import com.app.enums.TourSeason;
import com.app.enums.TourType;
import com.app.system.exception.ObjectNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TourTest {

    @Mock
    private TourRepository repository;
    @InjectMocks
    private TourService service;

    List<Tour> tours = new ArrayList<>();

    @BeforeEach
    void setUp() {
        tours.add(new Tour(1L, "name1", "country1", "description1", TourType.ACTIVE, TourSeason.WINTER));
        tours.add(new Tour(2L, "name2", "country2", "description2", TourType.BEACH, TourSeason.AUTUMN));
        tours.add(new Tour(3L, "name3", "country3", "description3", TourType.SPORT, TourSeason.SUMMER));
    }

    @AfterEach
    void tearDown() {
        tours.clear();
    }

    @Test
    void findAllSuccess() {
        given(repository.findAll()).willReturn(tours);

        List<Tour> findAll = service.findAllForTest();

        assertThat(findAll.size()).isEqualTo(tours.size());

        verify(repository, times(1)).findAll();
    }

    @Test
    void findSuccess() {
        Tour tour = tours.get(0);

        given(repository.findById(1L)).willReturn(Optional.of(tour));

        Tour find = service.find(1 + "");

        assertThat(find.getId()).isEqualTo(1);
        assertThat(find.getName()).isEqualTo(tour.getName());
        assertThat(find.getCountry()).isEqualTo(tour.getCountry());
        assertThat(find.getDescription()).isEqualTo(tour.getDescription());
        assertThat(find.getType()).isEqualTo(tour.getType());
        assertThat(find.getSeason()).isEqualTo(tour.getSeason());

        verify(repository, times(1)).findById(1L);
    }

    @Test
    void findNotFound() {
        given(repository.findById(Mockito.any(Long.class))).willReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> service.find(1 + ""));

        verify(repository, times(1)).findById(1L);
    }

    @Test
    void saveSuccess() {
        Tour save = tours.get(0);

        given(repository.save(save)).willReturn(save);

        Tour saved = service.saveForTest(save);

        assertThat(saved.getName()).isEqualTo(save.getName());
        assertThat(saved.getCountry()).isEqualTo(save.getCountry());
        assertThat(saved.getDescription()).isEqualTo(save.getDescription());
        assertThat(saved.getType()).isEqualTo(save.getType());
        assertThat(saved.getSeason()).isEqualTo(save.getSeason());

        verify(repository, times(1)).save(save);
    }

    @Test
    void updateSuccess() {
        Tour old = tours.get(0);
        Tour update = tours.get(1);

        given(repository.findById(1L)).willReturn(Optional.of(old));
        given(repository.save(old)).willReturn(old);

        Tour updated = service.updateForTest(1 + "", update);

        assertThat(updated.getId()).isEqualTo(1);
        assertThat(updated.getName()).isEqualTo(update.getName());
        assertThat(updated.getCountry()).isEqualTo(update.getCountry());
        assertThat(updated.getDescription()).isEqualTo(update.getDescription());
        assertThat(updated.getType()).isEqualTo(update.getType());
        assertThat(updated.getSeason()).isEqualTo(update.getSeason());

        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(old);
    }

    @Test
    void updateNotFound() {
        Tour update = tours.get(0);

        given(repository.findById(1L)).willReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> service.updateForTest(1 + "", update));

        verify(repository, times(1)).findById(1L);
    }

    @Test
    void deleteByIdSuccess() {
        Tour wizard = tours.get(0);

        given(repository.findById(1L)).willReturn(Optional.of(wizard));
        doNothing().when(repository).deleteById(1L);

        service.delete(1 + "");

        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void deleteByIdNotFound() {
        given(repository.findById(1L)).willReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> service.delete(1 + ""));

        verify(repository, times(1)).findById(1L);
    }
}