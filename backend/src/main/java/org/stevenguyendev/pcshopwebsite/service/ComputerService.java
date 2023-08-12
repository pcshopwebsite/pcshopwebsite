package org.stevenguyendev.pcshopwebsite.service;

import org.stevenguyendev.pcshopwebsite.dto.ComputerDTO;
import org.stevenguyendev.pcshopwebsite.dto.ComputerFilterRequest;
import org.stevenguyendev.pcshopwebsite.dto.ComputerLiteDTO;

import java.util.List;

public interface ComputerService {
    List<ComputerLiteDTO> filterComputers(ComputerFilterRequest request);
    ComputerDTO findComputerByIdOrName(String idOrName);
    ComputerDTO createComputer(ComputerDTO computerDTO);
}
