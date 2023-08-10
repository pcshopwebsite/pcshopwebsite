package org.stevenguyendev.pcshopwebsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.stevenguyendev.pcshopwebsite.model.Computer;

import java.util.Optional;

public interface ComputerRepository extends JpaRepository<Computer, Long>, CustomComputerRepository {
     Optional<Computer> findComputerByIdOrName(String idOrName);
     Optional<Computer> findComputerByName(String name);
     Optional<Computer> findComputerById(Long id);
}
