package com.app.stats;

import com.app.client.Client;
import com.app.client.ClientService;
import com.app.enums.ClientCategory;
import com.app.enums.ClientGender;
import com.app.enums.TourSeason;
import com.app.enums.TourType;
import com.app.system.Result;
import com.app.system.StatusCode;
import com.app.tour.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import static com.app.util.Global.ADMIN;

@RestController
@RequestMapping("/stats")
@RequiredArgsConstructor
@Secured({ADMIN})
public class StatsController {

    private final TourService tourService;
    private final ClientService clientService;

    @GetMapping("/tourSeasons")
    public Result tourSeasons() {
        Map<String, List<?>> res = new HashMap<>();

        List<String> names = new ArrayList<>();
        List<Integer> values = new ArrayList<>();

        List<TourSeason> tourSeasons = List.of(TourSeason.values());

        for (TourSeason i : tourSeasons) {
            names.add(i.getName());
            values.add(tourService.findAllBySeason(i).size());
        }

        res.put("names", names);
        res.put("values", values);

        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Tour Seasons",
                Collections.unmodifiableMap(res)
        );
    }

    @GetMapping("/tourSeasonsOrdering")
    public Result tourSeasonsOrdering() {
        Map<String, List<?>> res = new HashMap<>();

        List<String> names = new ArrayList<>();
        List<Integer> values = new ArrayList<>();

        List<TourSeason> tourSeasons = List.of(TourSeason.values());

        for (TourSeason i : tourSeasons) {
            names.add(i.getName());
            values.add(tourService.findAllBySeason(i).stream().reduce(0, (integer, tour) -> {
                if (!tour.getOrderings().isEmpty()) return integer + 1;
                return integer;
            }, Integer::sum));
        }

        res.put("names", names);
        res.put("values", values);

        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Tour Seasons Ordering",
                Collections.unmodifiableMap(res)
        );
    }

    @GetMapping("/tourTypes")
    public Result tourTypes() {
        Map<String, List<?>> res = new HashMap<>();

        List<String> names = new ArrayList<>();
        List<Integer> values = new ArrayList<>();

        List<TourType> tourTypes = List.of(TourType.values());

        for (TourType i : tourTypes) {
            names.add(i.getName());
            values.add(tourService.findAllByType(i).size());
        }

        res.put("names", names);
        res.put("values", values);

        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Tour Types",
                Collections.unmodifiableMap(res)
        );
    }

    @GetMapping("/clientGenders")
    public Result clientGenders() {
        Map<String, List<?>> res = new HashMap<>();

        List<String> names = new ArrayList<>();
        List<Integer> values = new ArrayList<>();

        List<ClientGender> clientGenders = List.of(ClientGender.values());

        for (ClientGender i : clientGenders) {
            names.add(i.getName());
            values.add(clientService.findAllByGender(i).size());
        }

        res.put("names", names);
        res.put("values", values);

        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Client Genders",
                Collections.unmodifiableMap(res)
        );
    }

    @GetMapping("/clientStatuses")
    public Result clientStatuses() {
        Map<String, List<?>> res = new HashMap<>();

        List<String> names = new ArrayList<>();
        List<Integer> values = new ArrayList<>();

        List<Client> clients = clientService.findAll();

        names.add("Контакт");
        names.add("Клиент");
        names.add("Постоянный клиент");

        int zero = 0;
        int one = 0;
        int more = 0;

        for (Client i : clients) {
            switch (i.getOrderings().size()) {
                case 0 -> zero++;
                case 1 -> one++;
                default -> more++;
            }
        }

        values.add(zero);
        values.add(one);
        values.add(more);

        res.put("names", names);
        res.put("values", values);

        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Client Statuses",
                Collections.unmodifiableMap(res)
        );
    }

    @GetMapping("/clientCategories")
    public Result clientCategories() {
        Map<String, List<?>> res = new HashMap<>();

        List<String> names = new ArrayList<>();
        List<Integer> values = new ArrayList<>();

        List<ClientCategory> clientCategories = List.of(ClientCategory.values());

        for (ClientCategory i : clientCategories) {
            names.add(i.getName());
            values.add(clientService.findAllByCategory(i).size());
        }

        res.put("names", names);
        res.put("values", values);

        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Client Categories",
                Collections.unmodifiableMap(res)
        );
    }

}
