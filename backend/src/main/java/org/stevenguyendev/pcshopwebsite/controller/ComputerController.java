package org.stevenguyendev.pcshopwebsite.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.stevenguyendev.pcshopwebsite.dto.ComputerDTO;
import org.stevenguyendev.pcshopwebsite.dto.ComputerLiteDTO;
import org.stevenguyendev.pcshopwebsite.dto.ComputerFilterRequest;
import org.stevenguyendev.pcshopwebsite.dto.ComputerSort;
import org.stevenguyendev.pcshopwebsite.dto.mapper.ComputerDTOMapper;
import org.stevenguyendev.pcshopwebsite.dto.mapper.ComputerLiteDTOMapper;
import org.stevenguyendev.pcshopwebsite.model.Computer;
import org.stevenguyendev.pcshopwebsite.service.ComputerService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/computers")
@CrossOrigin("*")
public class ComputerController {
    private final ComputerService computerService;
    private final ComputerDTOMapper computerDTOMapper;
    private final ComputerLiteDTOMapper computerLiteDTOMapper;

    public ComputerController(
            ComputerService computerService,
            ComputerDTOMapper computerDTOMapper,
            ComputerLiteDTOMapper computerLiteDTOMapper
    ) {
        this.computerService = computerService;
        this.computerDTOMapper = computerDTOMapper;
        this.computerLiteDTOMapper = computerLiteDTOMapper;

    }

    @GetMapping
    public List<ComputerLiteDTO> getAllComputers(
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
        return computerService.getFilteredAndSortedComputers(filterRequest).stream()
                .map(computerLiteDTOMapper)
                .toList();
    }

    @GetMapping("/{idOrName}")
    public ResponseEntity<ComputerDTO> getComputerByIdOrName(@PathVariable String idOrName) {
        Computer computer = computerService.getComputerByIdOrName(idOrName);
        if (computer != null) {
            return ResponseEntity.ok(computerDTOMapper.apply(computer));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ComputerDTO> createComputer(@RequestBody ComputerDTO createRequest) {
        Computer computer = Computer.builder()
                .name(createRequest.name())
                .price(createRequest.price())
                .rating(createRequest.rating())
                .description(createRequest.description())
                .thumbnail(createRequest.thumbnail())
                .build();
        return ResponseEntity.ok(computerDTOMapper.apply(computerService.createComputer(computer)));
    }

//    @PutMapping("/{name}")
//    public ResponseEntity<ComputerDTO> updateComputer(@PathVariable String name, @RequestBody ComputerDTO updateRequest) {
//        Computer computer = modelMapper.map(updateRequest, Computer.class);
//        return ResponseEntity.ok(modelMapper.map(computerService.updateComputer(name, computer), ComputerDTO.class));
//    }
//
//    @DeleteMapping("/{name}")
//    public ResponseEntity<Void> deleteComputer(@PathVariable String name) {
//        computerService.deleteComputer(name);
//        return ResponseEntity.noContent().build();
//    }
}
