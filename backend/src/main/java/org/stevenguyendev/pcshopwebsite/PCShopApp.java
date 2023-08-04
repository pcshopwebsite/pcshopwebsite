package org.stevenguyendev.pcshopwebsite;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import org.springframework.context.annotation.Profile;
import org.stevenguyendev.pcshopwebsite.computer.entity.Brand;
import org.stevenguyendev.pcshopwebsite.computer.entity.Category;
import org.stevenguyendev.pcshopwebsite.computer.entity.Computer;
import org.stevenguyendev.pcshopwebsite.computer.repository.BrandRepository;
import org.stevenguyendev.pcshopwebsite.computer.repository.CategoryRepository;
import org.stevenguyendev.pcshopwebsite.computer.repository.ComputerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class PCShopApp {
    public static void main(String[] args) {
        SpringApplication.run(PCShopApp.class, args);
    }

    @Bean
    @Profile({"dev", "local", "default"})
    CommandLineRunner runner(
            ComputerRepository computerRepository,
            BrandRepository brandRepository,
            CategoryRepository categoryRepository
    ) {
        return args -> {
            cleanUp(computerRepository, brandRepository, categoryRepository);
            addSampleComputers(computerRepository, brandRepository, categoryRepository);
        };
    }

    private void cleanUp(ComputerRepository computerRepository, BrandRepository brandRepository, CategoryRepository categoryRepository) {
        computerRepository.deleteAll();
        brandRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    private void addSampleComputers(
            ComputerRepository computerRepository,
            BrandRepository brandRepository,
            CategoryRepository categoryRepository) {

        // Save brands
        Brand apple = brandRepository.save(new Brand("Apple"));
        Brand dell = brandRepository.save(new Brand("Dell"));
        Brand lenovo = brandRepository.save(new Brand("Lenovo"));

        // Save categories
        Category laptop = categoryRepository.save(new Category("Laptop"));
        Category desktop = categoryRepository.save(new Category("Desktop"));
        Category macbook = categoryRepository.save(new Category("Macbook"));

        // Insert sample computers
        computerRepository.save(new Computer(
                "Macbook Pro 13\", Intel i5, 8GB RAM, 256GB SSD",
                "13-inch Macbook Pro with Retina Display",
                new BigDecimal("1299.99"),
                4.5f,
                "macbook-pro-13.jpg",
                macbook,
                apple,
                "Specs for Macbook Pro 13-inch"
        ));

        computerRepository.save(new Computer(
                "Dell XPS 15, Intel i7, 16GB RAM, 512GB SSD",
                "15-inch Dell XPS laptop",
                new BigDecimal("1799.99"),
                4.7f,
                "dell-xps-15.jpg",
                laptop,
                dell,
                "Specs for Dell XPS 15"
        ));

        computerRepository.save(new Computer(
                "Lenovo ThinkPad X1 Carbon, Intel i5, 16GB RAM, 512GB SSD",
                "14-inch Lenovo ThinkPad X1 Carbon",
                new BigDecimal("1399.99"),
                4.6f,
                "thinkpad-x1-carbon.jpg",
                laptop,
                lenovo,
                "Specs for ThinkPad X1 Carbon"
        ));

        computerRepository.save(new Computer(
                "Apple MacBook Air 13\", Intel i3, 8GB RAM, 256GB SSD",
                "13-inch MacBook Air",
                new BigDecimal("1099.99"),
                4.4f,
                "macbook-air-13.jpg",
                macbook,
                apple,
                "Specs for MacBook Air 13-inch"
        ));

        computerRepository.save(new Computer(
                "Dell Inspiron 14, Intel i5, 8GB RAM, 256GB SSD",
                "14-inch Dell Inspiron laptop",
                new BigDecimal("799.99"),
                4.2f,
                "dell-inspiron-14.jpg",
                laptop,
                dell,
                "Specs for Dell Inspiron 14"
        ));

        computerRepository.save(new Computer(
                "Dell XPS 13, Intel i7, 16GB RAM, 1TB SSD",
                "13-inch Dell XPS laptop",
                new BigDecimal("2099.99"),
                4.8f,
                "dell-xps-13.jpg",
                laptop,
                dell,
                "Specs for Dell XPS 13"
        ));

        computerRepository.save(new Computer(
                "Dell Latitude 15, Intel i5, 8GB RAM, 512GB SSD",
                "15-inch Dell Latitude laptop",
                new BigDecimal("1099.99"),
                4.3f,
                "dell-latitude-15.jpg",
                laptop,
                dell,
                "Specs for Dell Latitude 15"
        ));

        computerRepository.save(new Computer(
                "Apple iMac 21.5-inch, Intel i5, 8GB RAM, 512GB SSD",
                "21.5-inch Apple iMac",
                new BigDecimal("1299.99"),
                4.6f,
                "apple-imac-21.jpg",
                desktop,
                apple,
                "Specs for Apple iMac 21.5-inch"
        ));

        computerRepository.save(new Computer(
                "Apple Mac Mini, Intel i3, 8GB RAM, 256GB SSD",
                "Apple Mac Mini desktop",
                new BigDecimal("799.99"),
                4.4f,
                "apple-mac-mini.jpg",
                desktop,
                apple,
                "Specs for Apple Mac Mini"
        ));

        computerRepository.save(new Computer(
                "Apple Mac Pro, Intel Xeon, 32GB RAM, 1TB SSD",
                "Apple Mac Pro workstation",
                new BigDecimal("4999.99"),
                4.9f,
                "apple-mac-pro.jpg",
                desktop,
                apple,
                "Specs for Apple Mac Pro"
        ));

        computerRepository.save(new Computer(
                "Lenovo ThinkPad E14, Intel i5, 8GB RAM, 256GB SSD",
                "14-inch Lenovo ThinkPad",
                new BigDecimal("999.99"),
                4.5f,
                "thinkpad-e14.jpg",
                laptop,
                lenovo,
                "Specs for ThinkPad E14"
        ));

        computerRepository.save(new Computer(
                "Lenovo IdeaPad 15, Intel i3, 4GB RAM, 1TB HDD",
                "15-inch Lenovo IdeaPad laptop",
                new BigDecimal("599.99"),
                4.1f,
                "ideapad-15.jpg",
                laptop,
                lenovo,
                "Specs for Lenovo IdeaPad 15"
        ));

        computerRepository.save(new Computer(
                "Lenovo Legion 5, AMD Ryzen 7, 16GB RAM, 512GB SSD",
                "15-inch Lenovo gaming laptop",
                new BigDecimal("1399.99"),
                4.7f,
                "legion-5.jpg",
                laptop,
                lenovo,
                "Specs for Lenovo Legion 5"
        ));

        computerRepository.save(new Computer(
                "Apple MacBook Pro 16\", Intel i7, 16GB RAM, 512GB SSD",
                "16-inch MacBook Pro",
                new BigDecimal("2399.99"),
                4.9f,
                "macbook-pro-16.jpg",
                laptop,
                apple,
                "Specs for MacBook Pro 16-inch"
        ));

        computerRepository.save(new Computer(
                "Apple MacBook Air 15\", Intel i3, 8GB RAM, 256GB SSD",
                "15-inch MacBook Air",
                new BigDecimal("1099.99"),
                4.4f,
                "macbook-air-15.jpg",
                laptop,
                apple,
                "Specs for MacBook Air 15-inch"
        ));

        computerRepository.save(new Computer(
                "Dell OptiPlex 3070, Intel i5, 8GB RAM, 256GB SSD",
                "Dell OptiPlex desktop",
                new BigDecimal("899.99"),
                4.3f,
                "dell-optiplex-3070.jpg",
                desktop,
                dell,
                "Specs for Dell OptiPlex 3070"
        ));

        computerRepository.save(new Computer(
                "Dell Precision 5750, Intel i7, 32GB RAM, 1TB SSD",
                "Dell Precision workstation",
                new BigDecimal("2799.99"),
                4.8f,
                "dell-precision-5750.jpg",
                desktop,
                dell,
                "Specs for Dell Precision 5750"
        ));

        computerRepository.save(new Computer(
                "Apple MacBook Pro 13\", M1, 8GB RAM, 256GB SSD",
                "13-inch MacBook Pro",
                new BigDecimal("1499.99"),
                4.7f,
                "macbook-pro-13-m1.jpg",
                laptop,
                apple,
                "Specs for MacBook Pro 13-inch M1"
        ));

        computerRepository.save(new Computer(
                "Apple MacBook Air 11\", Intel Core 2 Duo, 4GB RAM, 128GB SSD",
                "11-inch MacBook Air",
                new BigDecimal("799.99"),
                4.1f,
                "macbook-air-11.jpg",
                laptop,
                apple,
                "Specs for MacBook Air 11-inch"
        ));

        computerRepository.save(new Computer(
                "Lenovo ThinkPad X1 Carbon, Intel i7, 16GB RAM, 1TB SSD",
                "14-inch Lenovo ThinkPad",
                new BigDecimal("1899.99"),
                4.9f,
                "thinkpad-x1-carbon.jpg",
                laptop,
                lenovo,
                "Specs for ThinkPad X1 Carbon"
        ));

        computerRepository.save(new Computer(
                "Lenovo IdeaPad 14, Intel i3, 8GB RAM, 512GB SSD",
                "14-inch Lenovo IdeaPad laptop",
                new BigDecimal("799.99"),
                4.3f,
                "ideapad-14.jpg",
                laptop,
                lenovo,
                "Specs for Lenovo IdeaPad 14"
        ));

        computerRepository.save(new Computer(
                "Lenovo ThinkCentre M90n, Intel i5, 8GB RAM, 512GB SSD",
                "Lenovo ThinkCentre desktop",
                new BigDecimal("999.99"),
                4.6f,
                "thinkcentre-m90n.jpg",
                desktop,
                lenovo,
                "Specs for ThinkCentre M90n"
        ));

        computerRepository.save(new Computer(
                "Lenovo Legion Tower 5, Intel i7, 16GB RAM, 1TB SSD",
                "Lenovo Legion gaming desktop",
                new BigDecimal("1599.99"),
                4.8f,
                "legion-tower-5.jpg",
                desktop,
                lenovo,
                "Specs for Legion Tower 5"
        ));
    }

}