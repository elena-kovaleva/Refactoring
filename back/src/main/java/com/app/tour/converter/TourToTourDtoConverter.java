package com.app.tour.converter;

import com.app.tour.Tour;
import com.app.tour.TourDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TourToTourDtoConverter implements Converter<Tour, TourDto> {
    @Override
    public TourDto convert(Tour source) {
        return new TourDto(
                source.getId(),

                source.getName(),
                source.getCountry(),
                source.getDescription(),

                source.getType().name(),
                source.getType().getName(),

                source.getSeason().name(),
                source.getSeason().getName(),

                source.getImg(),
                source.getFile()
        );
    }
}
