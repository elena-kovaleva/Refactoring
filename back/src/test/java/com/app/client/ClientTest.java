package com.app.client;

import com.app.enums.ClientCategory;
import com.app.enums.ClientGender;
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
class ClientTest {

    @Mock
    private ClientRepository repository;
    @InjectMocks
    private ClientService service;

    List<Client> clients = new ArrayList<>();

    @BeforeEach
    void setUp() {
        clients.add(new Client(1L, "fio1", "tel1", "passport1", "description1", ClientCategory.C18_20, ClientGender.MAN, TourType.ACTIVE));
        clients.add(new Client(2L, "fio2", "tel2", "passport2", "description2", ClientCategory.C21_25, ClientGender.WOMAN, TourType.BEACH));
        clients.add(new Client(3L, "fio3", "tel3", "passport3", "description3", ClientCategory.C26_35, ClientGender.MAN, TourType.SPORT));
    }

    @AfterEach
    void tearDown() {
        clients.clear();
    }

    @Test
    void findAllSuccess() {
        given(repository.findAll()).willReturn(clients);

        List<Client> findAll = service.findAllForTest();

        assertThat(findAll.size()).isEqualTo(clients.size());

        verify(repository, times(1)).findAll();
    }

    @Test
    void findSuccess() {
        Client client = clients.get(0);

        given(repository.findById(1L)).willReturn(Optional.of(client));

        Client find = service.find(1 + "");

        assertThat(find.getId()).isEqualTo(1);
        assertThat(find.getFio()).isEqualTo(client.getFio());
        assertThat(find.getTel()).isEqualTo(client.getTel());
        assertThat(find.getPassport()).isEqualTo(client.getPassport());
        assertThat(find.getDescription()).isEqualTo(client.getDescription());
        assertThat(find.getCategory()).isEqualTo(client.getCategory());
        assertThat(find.getGender()).isEqualTo(client.getGender());
        assertThat(find.getTourType()).isEqualTo(client.getTourType());

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
        Client save = clients.get(0);

        given(repository.save(save)).willReturn(save);

        Client saved = service.saveForTest(save);

        assertThat(saved.getFio()).isEqualTo(save.getFio());
        assertThat(saved.getTel()).isEqualTo(save.getTel());
        assertThat(saved.getPassport()).isEqualTo(save.getPassport());
        assertThat(saved.getDescription()).isEqualTo(save.getDescription());
        assertThat(saved.getCategory()).isEqualTo(save.getCategory());
        assertThat(saved.getGender()).isEqualTo(save.getGender());
        assertThat(saved.getTourType()).isEqualTo(save.getTourType());

        verify(repository, times(1)).save(save);
    }

    @Test
    void updateSuccess() {
        Client old = clients.get(0);
        Client update = clients.get(1);

        given(repository.findById(1L)).willReturn(Optional.of(old));
        given(repository.save(old)).willReturn(old);

        Client updated = service.updateForTest(1 + "", update);

        assertThat(updated.getId()).isEqualTo(1);
        assertThat(updated.getFio()).isEqualTo(update.getFio());
        assertThat(updated.getTel()).isEqualTo(update.getTel());
        assertThat(updated.getPassport()).isEqualTo(update.getPassport());
        assertThat(updated.getDescription()).isEqualTo(update.getDescription());
        assertThat(updated.getCategory()).isEqualTo(update.getCategory());
        assertThat(updated.getGender()).isEqualTo(update.getGender());
        assertThat(updated.getTourType()).isEqualTo(update.getTourType());

        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(old);
    }

    @Test
    void updateNotFound() {
        Client update = clients.get(0);

        given(repository.findById(1L)).willReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> service.update(1 + "", update));

        verify(repository, times(1)).findById(1L);
    }

    @Test
    void deleteByIdSuccess() {
        Client wizard = clients.get(0);

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