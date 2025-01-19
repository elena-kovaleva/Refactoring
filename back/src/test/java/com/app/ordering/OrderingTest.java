package com.app.ordering;

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
class OrderingTest {

    @Mock
    private OrderingRepository repository;
    @InjectMocks
    private OrderingService service;

    List<Ordering> orderings = new ArrayList<>();

    @BeforeEach
    void setUp() {
        orderings.add(new Ordering(1L, "date1", 4.99f));
        orderings.add(new Ordering(2L, "date2", 9.99f));
        orderings.add(new Ordering(3L, "date3", 14.99f));
    }

    @AfterEach
    void tearDown() {
        orderings.clear();
    }

    @Test
    void findSuccess() {
        Ordering ordering = orderings.get(0);

        given(repository.findById(1L)).willReturn(Optional.of(ordering));

        Ordering find = service.find(1 + "");

        assertThat(find.getId()).isEqualTo(1);
        assertThat(find.getDate()).isEqualTo(ordering.getDate());
        assertThat(find.getPrice()).isEqualTo(ordering.getPrice());

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
        Ordering save = orderings.get(0);

        given(repository.save(save)).willReturn(save);

        Ordering saved = service.saveForTest(save);

        assertThat(saved.getDate()).isEqualTo(save.getDate());
        assertThat(saved.getPrice()).isEqualTo(save.getPrice());

        verify(repository, times(1)).save(save);
    }

    @Test
    void deleteByIdSuccess() {
        Ordering wizard = orderings.get(0);

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