package com.app.client.converter;

import com.app.client.Client;
import com.app.client.ClientDto;
import com.app.ordering.converter.OrderingToOrderingDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ClientToClientDtoConverter implements Converter<Client, ClientDto> {

    private final OrderingToOrderingDtoConverter orderingToOrderingDtoConverter;

    @Override
    public ClientDto convert(Client source) {
        return new ClientDto(
                source.getId(),

                source.getFio(),
                source.getTel(),
                source.getPassport(),
                source.getDate(),
                source.getDescription(),

                source.getCategoryName(),
                source.getCategoryGetName(),

                source.getGenderName(),
                source.getGenderGetName(),

                source.getTourTypeName(),
                source.getTourTypeGetName(),

                source.getManager().getUsername(),

                source.getOrderings().stream().map(orderingToOrderingDtoConverter::convert).collect(Collectors.toList())
        );
    }
}
