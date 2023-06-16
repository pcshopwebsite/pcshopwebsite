package org.stevenguyendev.pcshopwebsite.computer.repository;

import org.stevenguyendev.pcshopwebsite.computer.entity.Computer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ComputerRepository extends JpaRepository<Computer, Integer>, CustomComputerRepository {
    public Optional<Computer> findComputerByName(String name);
}
