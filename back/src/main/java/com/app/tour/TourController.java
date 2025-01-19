package com.app.tour;

import com.app.system.Result;
import com.app.system.StatusCode;
import com.app.tour.converter.TourDtoToTourConverter;
import com.app.tour.converter.TourToTourDtoConverter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.stream.Collectors;

import static com.app.util.Global.MANAGER;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tours")
public class TourController {

    private final TourService service;
    private final TourToTourDtoConverter toDtoConverter;
    private final TourDtoToTourConverter toConverter;

    @GetMapping
    public Result findAll() {
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Find All",
                service.findAll().stream().map(toDtoConverter::convert).collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
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
    public Result save(@Valid @RequestBody TourDto saveDto, @RequestParam String type, @RequestParam String season) {
        Tour save = toConverter.convert(saveDto);
        Tour saved = service.save(save, type, season);
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Save",
                toDtoConverter.convert(saved)
        );
    }

    @PutMapping("/{id}")
    @Secured({MANAGER})
    public Result update(@PathVariable String id, @Valid @RequestBody TourDto updateDto, @RequestParam String type, @RequestParam String season) {
        Tour update = toConverter.convert(updateDto);
        Tour updated = service.update(id, update, type, season);
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Update",
                toDtoConverter.convert(updated)
        );
    }

    @PatchMapping("/{id}/img")
    @Secured({MANAGER})
    public Result updateImg(@PathVariable String id, @RequestParam MultipartFile files) {
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Update Img",
                toDtoConverter.convert(service.updateImg(id, files))
        );
    }

    @PatchMapping("/{id}/file")
    @Secured({MANAGER})
    public Result updateFile(@PathVariable String id, @RequestParam MultipartFile files) {
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Update File",
                toDtoConverter.convert(service.updateFile(id, files))
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
