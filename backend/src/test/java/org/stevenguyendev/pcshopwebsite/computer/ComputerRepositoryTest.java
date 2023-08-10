package org.stevenguyendev.pcshopwebsite.computer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContext;
import org.stevenguyendev.pcshopwebsite.AbstractTestcontainers;
import org.stevenguyendev.pcshopwebsite.model.Brand;
import org.stevenguyendev.pcshopwebsite.repository.BrandRepository;
import org.stevenguyendev.pcshopwebsite.model.Category;
import org.stevenguyendev.pcshopwebsite.repository.CategoryRepository;
import org.stevenguyendev.pcshopwebsite.model.Computer;
import org.stevenguyendev.pcshopwebsite.repository.ComputerRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ComputerRepositoryTest extends AbstractTestcontainers {
    @Autowired
    private ComputerRepository underTest;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ApplicationContext applicationContext;

    private Brand sampleBrand;
    private Category sampleCategory;

    @BeforeEach
    void beforeEach() {
        underTest.deleteAll();
        brandRepository.deleteAll();
        categoryRepository.deleteAll();
        sampleBrand = brandRepository.save(new Brand(
                "Apple"
        ));
        sampleCategory = categoryRepository.save(new Category(
                "Macbook"
        ));
        System.out.println(applicationContext.getBeanDefinitionCount());
    }

    @Test
    void whenGetComputerByName_thenReturnsComputer() {
        // Given
        Brand brand = brandRepository.save(new Brand(
                "Apple"
        ));
        String name = "Macbook Pro 13\" 2020";
        Computer computer = null;
//        ComputerEntity computer = new ComputerEntity(
//                name,
//                "The best laptop for developers. My dream",
//                new BigDecimal("1299.99"),
//                5.0f,
//                "https://stevenguyendev.com/images/macbook-pro-13-2020.jpg",
//                sampleCategory,
//                sampleBrand,
//                null
//        );
        underTest.save(computer);
        // When
        Optional<Computer> actual = underTest.findComputerByName(name);
        // Then
        assertThat(actual).isPresent();
//        assertThat(actual.get().getName()).isEqualTo(name);

        Optional<Computer> actual2 = underTest.findComputerByName("Macbook Pro 13\" 2021");
        assertThat(actual2).isNotPresent();
    }


}
