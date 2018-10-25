package com.ddfdesign.msclients.repository;

import com.ddfdesign.msclients.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClientsRepository extends JpaRepository<Client, Long > {
}
