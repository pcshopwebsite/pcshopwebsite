package org.stevenguyendev.pcshopwebsite;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import org.stevenguyendev.pcshopwebsite.computer.repository.ComputerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PCShopApp {
    public static void main(String[] args) {
        SpringApplication.run(PCShopApp.class, args);
    }

    @Bean
    CommandLineRunner runner(
            ComputerRepository computerRepository
    ) {
        return args -> {
//            addRandomProduct(productRepository);
        };
    }

//    private static void addRandomProduct(ProductRepository productRepository) {
//        var faker = new Faker();
//        Lorem lorem = faker.lorem();
//        String name = lorem.characters(50);
//        String description = lorem.characters(100);
//        String thumbnail = faker.file().toString();
//        Product product = new Product(
//                name,
//                description,
//                thumbnail
//        );
//        productRepository.save(product);
//        System.out.println("Hooray: " + product);
//    }
}