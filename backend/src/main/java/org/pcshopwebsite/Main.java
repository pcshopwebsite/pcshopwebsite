package org.pcshopwebsite;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import ch.qos.logback.core.net.SyslogOutputStream;
import com.github.javafaker.Faker;
import com.github.javafaker.Lorem;
import org.pcshopwebsite.product.Product;
import org.pcshopwebsite.product.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@SpringBootApplication
@RestController
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping("/")
    public List<String> helloWorld() {
        return List.of("Hello", "World");
    }

    @Bean
    CommandLineRunner runner(
            ProductRepository productRepository
    ) {
        return args -> {
//            addRandomProduct(productRepository);
        };
    }

    private static void addRandomProduct(ProductRepository productRepository) {
        var faker = new Faker();
        Lorem lorem = faker.lorem();
        String name = lorem.characters(50);
        String description = lorem.characters(100);
        String thumbnail = faker.file().toString();
        Product product = new Product(
                name,
                description,
                thumbnail
        );
        productRepository.save(product);
        System.out.println("Hooray: " + product);
    }
}