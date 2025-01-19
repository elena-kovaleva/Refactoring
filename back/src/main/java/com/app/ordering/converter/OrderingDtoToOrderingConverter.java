package com.app.ordering.converter;

import com.app.ordering.Ordering;
import com.app.ordering.OrderingDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderingDtoToOrderingConverter implements Converter<OrderingDto, Ordering> {
    @Override
    public Ordering convert(OrderingDto source) {
        return new Ordering(
                source.date(),
                source.price()
        );
    }
}
