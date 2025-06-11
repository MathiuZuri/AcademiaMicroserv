package com.example.ms_kajita.util; // Ensure this package is correct

import com.example.ms_kajita.entity.NotasAreaCurricular; // Ensure this is the correct package for your entity
import com.example.ms_kajita.repository.notasAreaCurricularRepository; // Ensure this is the correct package for your repository
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class NotasAreaCurricularSeeder implements CommandLineRunner {

    private final notasAreaCurricularRepository notasAreaCurricularRepository;

    // Dependency injection via constructor
    public NotasAreaCurricularSeeder(notasAreaCurricularRepository notasAreaCurricularRepository1) {
        this.notasAreaCurricularRepository = notasAreaCurricularRepository1;
    }

    @Override
    public void run(String... args) {
        // Only insert data if the table is empty
        if (notasAreaCurricularRepository.count() == 0) {
            // Creating instances of NotasAreaCurricular
            NotasAreaCurricular n1 = new NotasAreaCurricular();
            // idNotascompetencia is @GeneratedValue, so we don't set it here
            n1.setNota(85);        // Example note
            n1.setNotaFinal(90);   // Example final note
            n1.setIdPlanAcademico(1); // Example academic plan ID
            // The @Transient field 'nombrePlanAcad' is not set here as it's populated by the service

            NotasAreaCurricular n2 = new NotasAreaCurricular();
            n2.setNota(70);
            n2.setNotaFinal(75);
            n2.setIdPlanAcademico(2);

            NotasAreaCurricular n3 = new NotasAreaCurricular();
            n3.setNota(92);
            n3.setNotaFinal(95);
            n3.setIdPlanAcademico(1); // Another entry for academic plan ID 1

            // Save the entities to the database
            notasAreaCurricularRepository.save(n1);
            notasAreaCurricularRepository.save(n2);
            notasAreaCurricularRepository.save(n3);

            System.out.println("Notas de área curricular de ejemplo insertadas correctamente.");
        } else {
            System.out.println("Las notas de área curricular ya existen, no se insertaron datos.");
        }
    }
}