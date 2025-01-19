package com.app.client;

import com.app.client.converter.ClientDtoToClientConverter;
import com.app.client.converter.ClientToClientDtoConverter;
import com.app.system.Result;
import com.app.system.StatusCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

import static com.app.util.Global.ADMIN;
import static com.app.util.Global.MANAGER;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientController {

    public final ClientService service;
    public final ClientToClientDtoConverter toDtoConverter;
    public final ClientDtoToClientConverter toConverter;

    @GetMapping
    @Secured({ADMIN, MANAGER})
    public Result findAll() {
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Find All",
                service.findAll().stream().map(toDtoConverter::convert).collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    @Secured({ADMIN, MANAGER})
    public Result find(@PathVariable String id) {
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Find",
                toDtoConverter.convert(service.find(id))
        );
    }

    @PostMapping
    @Secured({MANAGER})
    public Result save(@Valid @RequestBody ClientDto saveDto) {
        Client save = toConverter.convert(saveDto);
        Client saved = service.save(save);
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Save",
                toDtoConverter.convert(saved)
        );
    }

    @PutMapping("/{id}")
    @Secured({MANAGER})
    public Result update(@PathVariable String id, @Valid @RequestBody ClientDto updateDto) {
        Client update = toConverter.convert(updateDto);
        Client updated = service.update(id, update);
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Update",
                toDtoConverter.convert(updated)
        );
    }

    @PatchMapping("/{id}/passport")
    @Secured({MANAGER})
    public Result updatePassport(@PathVariable String id, @RequestParam String passport) {
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Update Passport",
                toDtoConverter.convert(service.updatePassport(id, passport))
        );
    }

    @PatchMapping("/{id}/gender")
    @Secured({MANAGER})
    public Result updateGender(@PathVariable String id, @RequestParam String gender) {
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Update Gender",
                toDtoConverter.convert(service.updateGender(id, gender))
        );
    }

    @PatchMapping("/{id}/category")
    @Secured({MANAGER})
    public Result updateCategory(@PathVariable String id, @RequestParam String category) {
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Update Category",
                toDtoConverter.convert(service.updateCategory(id, category))
        );
    }

    @PatchMapping("/{id}/tourType")
    @Secured({MANAGER})
    public Result updateTourType(@PathVariable String id, @RequestParam String tourType) {
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Update TourType",
                toDtoConverter.convert(service.updateTourType(id, tourType))
        );
    }

    @DeleteMapping("/{id}")
    @Secured({MANAGER})
    public Result delete(@PathVariable String id) {
        service.delete(id);
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Delete"
        );
    }

}
