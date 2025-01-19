package com.app.tour;

import com.app.enums.TourSeason;
import com.app.enums.TourType;
import com.app.system.exception.BadRequestException;
import com.app.system.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.app.util.Global.saveFile;

@Service
@RequiredArgsConstructor
public class TourService {

    private final TourRepository repository;

    public List<Tour> findAll() {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public List<Tour> findAllForTest() {
        return repository.findAll();
    }

    public List<Tour> findAllByType(TourType type) {
        return repository.findAllByType(type);
    }

    public List<Tour> findAllBySeason(TourSeason season) {
        return repository.findAllBySeason(season);
    }

    public Tour find(String id) {
        return repository.findById(Long.parseLong(id)).orElseThrow(() -> new ObjectNotFoundException("Не найден тур по ИД: " + id));
    }

    private void setTourType(Tour tour, String type) {
        try {
            tour.setType(TourType.valueOf(type));
        } catch (Exception e) {
            throw new BadRequestException("Некорректный тип");
        }
    }

    private void setTourSeason(Tour tour, String season) {
        try {
            tour.setSeason(TourSeason.valueOf(season));
        } catch (Exception e) {
            throw new BadRequestException("Некорректный сезон");
        }
    }

    public Tour save(Tour save, String type, String season) {
        setTourType(save, type);
        setTourSeason(save, season);
        return repository.save(save);
    }

    public Tour saveForTest(Tour save) {
        return repository.save(save);
    }

    public Tour update(String id, Tour update, String type, String season) {
        Tour old = find(id);
        old.update(update);
        setTourType(old, type);
        setTourSeason(old, season);
        return repository.save(old);
    }

    public Tour updateForTest(String id, Tour update) {
        Tour old = find(id);
        old.updateForTest(update);
        return repository.save(old);
    }

    public Tour updateImg(String id, MultipartFile img) {
        Tour tour = find(id);
        try {
            tour.setImg(saveFile(img, "tour"));
        } catch (IOException e) {
            if (tour.getImg().isEmpty()) repository.deleteById(tour.getId());
            throw new BadRequestException("Некорректное изображение");
        }
        return repository.save(tour);
    }

    public Tour updateFile(String id, MultipartFile file) {
        Tour tour = find(id);
        try {
            tour.setFile(saveFile(file, "tour"));
        } catch (IOException e) {
            if (tour.getImg().isEmpty()) repository.deleteById(tour.getId());
            throw new BadRequestException("Некорректный файл");
        }
        return repository.save(tour);
    }

    public void delete(String id) {
        repository.deleteById(find(id).getId());
    }

}
