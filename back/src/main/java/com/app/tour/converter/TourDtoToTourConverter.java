package com.app.tour.converter;

import com.app.tour.Tour;
import com.app.tour.TourDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TourDtoToTourConverter implements Converter<TourDto, Tour> {
    @Override
    public Tour convert(TourDto source) {
        return new Tour(
                source.name(),
                source.country(),
                source.description()
        );
    }
}
