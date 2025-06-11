package com.example.ms_kajita.util;

import com.example.ms_kajita.entity.Asistencia;
import com.example.ms_kajita.repository.AsistenciaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AsistenciaSeeder implements CommandLineRunner {

    private final AsistenciaRepository asistenciaRepository;

    public AsistenciaSeeder(AsistenciaRepository asistenciaRepository) {
        this.asistenciaRepository = asistenciaRepository;
    }

    @Override
    public void run(String... args) {
        if (asistenciaRepository.count() == 0) {
            Asistencia a1 = new Asistencia();
            a1.setFechaRegistroAsistencia(LocalDateTime.now());
            a1.setUsuarioIdUsuario(1L);
            a1.setCursoIdCurso(1);
            a1.setDocenteIdDocente(4);
            a1.setEstadoAsistencia("PRESENTE");
            a1.setPlanAcademicoIdPlanAcademico(7);

            Asistencia a2 = new Asistencia();
            a2.setFechaRegistroAsistencia(LocalDateTime.now().minusDays(1));
            a2.setUsuarioIdUsuario(2L);
            a2.setCursoIdCurso(2);
            a2.setDocenteIdDocente(5);
            a2.setEstadoAsistencia("AUSENTE");
            a2.setPlanAcademicoIdPlanAcademico(8);

            Asistencia a3 = new Asistencia();
            a3.setFechaRegistroAsistencia(LocalDateTime.now().minusDays(2));
            a3.setUsuarioIdUsuario(3L);
            a3.setCursoIdCurso(3);
            a3.setDocenteIdDocente(6);
            a3.setEstadoAsistencia("TARDANZA");
            a3.setPlanAcademicoIdPlanAcademico(9);

            asistenciaRepository.save(a1);
            asistenciaRepository.save(a2);
            asistenciaRepository.save(a3);

            System.out.println("Datos de asistencias insertados correctamente.");
        } else {
            System.out.println("Las asistencias ya existen, no se insertaron datos.");
        }
    }
}
