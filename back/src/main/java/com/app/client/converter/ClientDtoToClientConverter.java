package com.app.client.converter;

import com.app.client.Client;
import com.app.client.ClientDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ClientDtoToClientConverter implements Converter<ClientDto, Client> {
    @Override
    public Client convert(ClientDto source) {
        return new Client(
                source.fio(),
                source.tel(),
                source.description()
        );
    }
}
