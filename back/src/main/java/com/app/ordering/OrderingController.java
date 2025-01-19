package com.app.ordering;

import com.app.ordering.converter.OrderingDtoToOrderingConverter;
import com.app.ordering.converter.OrderingToOrderingDtoConverter;
import com.app.system.Result;
import com.app.system.StatusCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import static com.app.util.Global.MANAGER;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orderings")
public class OrderingController {

    private final OrderingService service;
    private final OrderingToOrderingDtoConverter toDtoConverter;
    private final OrderingDtoToOrderingConverter toConverter;

    @PostMapping
    @Secured({MANAGER})
    public Result save(@Valid @RequestBody OrderingDto saveDto, @RequestParam String clientId, @RequestParam String tourId) {
        Ordering save = toConverter.convert(saveDto);
        Ordering saved = service.save(save, clientId, tourId);
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Save",
                toDtoConverter.convert(saved)
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
