package org.stevenguyendev.pcshopwebsite.computer.controller;

import org.stevenguyendev.pcshopwebsite.computer.dto.CompleteComputerDTO;
import org.stevenguyendev.pcshopwebsite.computer.dto.ComputerDTO;
import org.stevenguyendev.pcshopwebsite.computer.enums.ComputerSort;
import org.stevenguyendev.pcshopwebsite.computer.request.ComputerFilterRequest;
import org.stevenguyendev.pcshopwebsite.computer.service.ComputerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/computers")
@CrossOrigin("*")
public class ComputerController {
    private final ComputerService computerService;

    public ComputerController(ComputerService computerService) {
        this.computerService = computerService;
    }

    @GetMapping
    public List<ComputerDTO> getAllComputers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) List<String> categories,
            @RequestParam(required = false) List<String> brands,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) Float minRating,
            @RequestParam(required = false, defaultValue = "updatedAt") String sort,
            @RequestParam(required = false, defaultValue = "desc") String order
    ) {
        /**
         * Make sure the sort value is valid, otherwise set it to default value
         */
        if (sort != null) {
            try {
                ComputerSort.valueOf(sort.toUpperCase());
            } catch (IllegalArgumentException e) {
                sort = "updatedAt";
            }
        }
        ComputerFilterRequest filterRequest = new ComputerFilterRequest(
                name,
                categories,
                brands,
                minPrice,
                maxPrice,
                minRating,
                sort,
                order,
                page,
                size
        );
        return computerService.getFilteredAndSortedComputers(filterRequest);
    }

    @GetMapping("/{name}")
    public ResponseEntity<CompleteComputerDTO> getComputerByName(@PathVariable String name) {
        CompleteComputerDTO computer = computerService.getComputerByName(name);
        if (computer != null) {
            return ResponseEntity.ok(computer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
