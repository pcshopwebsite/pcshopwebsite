package org.stevenguyendev.pcshopwebsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.stevenguyendev.pcshopwebsite.model.Computer;

import java.util.Optional;
import java.util.UUID;

public interface ComputerRepository extends JpaRepository<Computer, UUID>, CustomComputerRepository {
     Optional<Computer> findComputerByIdOrName(String id, String name);
     Optional<Computer> findComputerByName(String name);
     Optional<Computer> findComputerById(Long id);
}
