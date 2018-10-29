package com.ddfdesign.msclients.repository;

import com.ddfdesign.msclients.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IClientsRepository extends JpaRepository<Client, Long > {
    List<Client> findByDni(String name);
}
