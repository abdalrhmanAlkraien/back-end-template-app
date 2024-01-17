package com.marinabits.energietechs;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Starting point of EnergieTechs Application.
 *
 * @author Omar Ismail
 * @version 1.0
 */
@SpringBootApplication
@Log4j2
@RequiredArgsConstructor
public class EnergieTechsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnergieTechsApplication.class, args);
    }

}
