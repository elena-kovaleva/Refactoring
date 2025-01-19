package com.app.client;

import com.app.appUser.UserService;
import com.app.enums.ClientCategory;
import com.app.enums.ClientGender;
import com.app.enums.TourType;
import com.app.system.exception.BadRequestException;
import com.app.system.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;
    private final UserService userService;

    public List<Client> findAll() {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public List<Client> findAllForTest() {
        return repository.findAll();
    }

    public List<Client> findAllByGender(ClientGender gender) {
        return repository.findAllByGender(gender);
    }

    public List<Client> findAllByCategory(ClientCategory category) {
        return repository.findAllByCategory(category);
    }


    public Client find(String id) {
        return repository.findById(Long.parseLong(id)).orElseThrow(() -> new ObjectNotFoundException("Не найден клиент по ИД: " + id));
    }

    public Client save(Client save) {
        save.setManager(userService.getCurrentUser());
        return repository.save(save);
    }

    public Client saveForTest(Client save) {
        return repository.save(save);
    }

    public Client update(String id, Client update) {
        Client old = find(id);
        old.update(update);
        return repository.save(old);
    }

    public Client updateForTest(String id, Client update) {
        Client old = find(id);
        old.updateForTest(update);
        return repository.save(old);
    }

    public Client updatePassport(String id, String passport) {
        try {
            Client client = find(id);
            client.setPassport(passport);
            return repository.save(client);
        } catch (Exception e) {
            throw new BadRequestException("Некорректные данные");
        }
    }

    public Client updateGender(String id, String gender) {
        try {
            Client client = find(id);
            client.setGender(ClientGender.valueOf(gender));
            return repository.save(client);
        } catch (Exception e) {
            throw new BadRequestException("Некорректные данные");
        }
    }

    public Client updateCategory(String id, String category) {
        try {
            Client client = find(id);
            client.setCategory(ClientCategory.valueOf(category));
            return repository.save(client);
        } catch (Exception e) {
            throw new BadRequestException("Некорректные данные");
        }
    }

    public Client updateTourType(String id, String tourType) {
        try {
            Client client = find(id);
            client.setTourType(TourType.valueOf(tourType));
            return repository.save(client);
        } catch (Exception e) {
            throw new BadRequestException("Некорректные данные");
        }
    }

    public void delete(String id) {
        repository.deleteById(find(id).getId());
    }


}
