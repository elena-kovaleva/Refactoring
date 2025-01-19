package com.app.ordering.converter;

import com.app.ordering.Ordering;
import com.app.ordering.OrderingDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderingToOrderingDtoConverter implements Converter<Ordering, OrderingDto> {
    @Override
    public OrderingDto convert(Ordering source) {
        return new OrderingDto(
                source.getId(),

                source.getDate(),
                source.getPrice(),

                source.getTour().getId(),
                source.getTour().getName(),
                source.getTour().getImg(),

                source.getManager().getUsername()
        );
    }
}
