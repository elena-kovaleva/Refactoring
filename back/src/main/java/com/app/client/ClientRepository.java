package com.app.client;

import com.app.enums.ClientCategory;
import com.app.enums.ClientGender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findAllByGender(ClientGender gender);

    List<Client> findAllByCategory(ClientCategory category);
}
