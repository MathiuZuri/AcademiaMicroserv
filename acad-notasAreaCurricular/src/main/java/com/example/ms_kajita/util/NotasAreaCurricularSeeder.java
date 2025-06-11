package com.example.ms_kajita.util;

import com.example.ms_kajita.entity.NotaCurso;
import com.example.ms_kajita.entity.NotasAreaCurricular;
import com.example.ms_kajita.entity.CursoCompetencia;
import com.example.ms_kajita.repository.notasAreaCurricularRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class NotasAreaCurricularSeeder implements CommandLineRunner {

    private final notasAreaCurricularRepository notasAreaCurricularRepository;

    public NotasAreaCurricularSeeder(notasAreaCurricularRepository notasAreaCurricularRepository1) {
        this.notasAreaCurricularRepository = notasAreaCurricularRepository1;
    }

    @Override
    public void run(String... args) {
        if (notasAreaCurricularRepository.count() == 0) {
            // ======================
            // Primer registro
            // ======================
            NotasAreaCurricular n1 = new NotasAreaCurricular();
            n1.setNota(85);
            n1.setNotaFinal(90);
            n1.setIdPlanAcademico(1);

            NotaCurso c1 = new NotaCurso();
            c1.setIdCurso(101);
            c1.setNota(86);
            c1.setNotaFinal(88);
            c1.setNotasAreaCurricular(n1);

            NotaCurso c2 = new NotaCurso();
            c2.setIdCurso(102);
            c2.setNota(84);
            c2.setNotaFinal(87);
            c2.setNotasAreaCurricular(n1);

            CursoCompetencia comp1 = new CursoCompetencia();
            comp1.setIdCompetenciaCurso(501);
            comp1.setNota(90);
            comp1.setNotasCompetencias(n1);

            CursoCompetencia comp2 = new CursoCompetencia();
            comp2.setIdCompetenciaCurso(502);
            comp2.setNota(88);
            comp2.setNotasCompetencias(n1);

            n1.setCursosNotas(Arrays.asList(c1, c2));
            n1.setNotasCompetencias(Arrays.asList(comp1, comp2));

            // ======================
            // Segundo registro
            // ======================
            NotasAreaCurricular n2 = new NotasAreaCurricular();
            n2.setNota(70);
            n2.setNotaFinal(75);
            n2.setIdPlanAcademico(2);

            NotaCurso c3 = new NotaCurso();
            c3.setIdCurso(201);
            c3.setNota(72);
            c3.setNotaFinal(74);
            c3.setNotasAreaCurricular(n2);

            CursoCompetencia comp3 = new CursoCompetencia();
            comp3.setIdCompetenciaCurso(601);
            comp3.setNota(74);
            comp3.setNotasCompetencias(n2);

            n2.setCursosNotas(Arrays.asList(c3));
            n2.setNotasCompetencias(Arrays.asList(comp3));

            // ======================
            // Tercer registro
            // ======================
            NotasAreaCurricular n3 = new NotasAreaCurricular();
            n3.setNota(92);
            n3.setNotaFinal(95);
            n3.setIdPlanAcademico(1);

            NotaCurso c4 = new NotaCurso();
            c4.setIdCurso(301);
            c4.setNota(93);
            c4.setNotaFinal(94);
            c4.setNotasAreaCurricular(n3);

            NotaCurso c5 = new NotaCurso();
            c5.setIdCurso(302);
            c5.setNota(91);
            c5.setNotaFinal(96);
            c5.setNotasAreaCurricular(n3);

            CursoCompetencia comp4 = new CursoCompetencia();
            comp4.setIdCompetenciaCurso(701);
            comp4.setNota(97);
            comp4.setNotasCompetencias(n3);

            CursoCompetencia comp5 = new CursoCompetencia();
            comp5.setIdCompetenciaCurso(702);
            comp5.setNota(95);
            comp5.setNotasCompetencias(n3);

            n3.setCursosNotas(Arrays.asList(c4, c5));
            n3.setNotasCompetencias(Arrays.asList(comp4, comp5));

            // Guardar todo
            notasAreaCurricularRepository.save(n1);
            notasAreaCurricularRepository.save(n2);
            notasAreaCurricularRepository.save(n3);

            System.out.println("Notas de área curricular de ejemplo insertadas con cursos y competencias correctamente.");
        } else {
            System.out.println("Las notas de área curricular ya existen, no se insertaron datos.");
        }
    }
}

