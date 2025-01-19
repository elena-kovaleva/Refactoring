package com.app.ordering;

import com.app.appUser.UserService;
import com.app.client.ClientService;
import com.app.system.exception.ObjectNotFoundException;
import com.app.tour.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderingService {

    private final OrderingRepository repository;
    private final UserService userService;
    private final ClientService clientService;
    private final TourService tourService;

    public Ordering find(String id) {
        return repository.findById(Long.parseLong(id)).orElseThrow(() -> new ObjectNotFoundException("Не найдена запись по ИД: " + id));
    }

    public Ordering save(Ordering save, String clientId, String tourId) {
        save.setClient(clientService.find(clientId));
        save.setTour(tourService.find(tourId));
        save.setManager(userService.getCurrentUser());
        return repository.save(save);
    }

    public Ordering saveForTest(Ordering save) {
        return repository.save(save);
    }

    public void delete(String id) {
        repository.deleteById(find(id).getId());
    }

}
