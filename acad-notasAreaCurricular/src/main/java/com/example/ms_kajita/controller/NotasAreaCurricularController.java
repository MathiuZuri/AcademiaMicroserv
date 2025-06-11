package com.example.ms_kajita.controller;

import com.example.ms_kajita.entity.NotasAreaCurricular; // Asegúrate de que este sea el paquete correcto de tu entidad
import com.example.ms_kajita.service.NotasAreaCurricularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notasAreaCurriculars") // Ruta base para los endpoints de NotasAreaCurricular
public class NotasAreaCurricularController {

    @Autowired
    private NotasAreaCurricularService notasAreaCurricularService;

    @GetMapping
    public ResponseEntity<List<NotasAreaCurricular>> listarTodas() {
        List<NotasAreaCurricular> notas = notasAreaCurricularService.listar();
        return ResponseEntity.ok(notas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotasAreaCurricular> obtenerPorId(@PathVariable Integer id) {
        Optional<NotasAreaCurricular> notaOptional = notasAreaCurricularService.listarPorId(id);
        return notaOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<NotasAreaCurricular> guardar(@RequestBody NotasAreaCurricular notasAreaCurricular) {
        System.out.println("Cursos recibidos:");
        if (notasAreaCurricular.getCursosNotas() != null) {
            notasAreaCurricular.getCursosNotas().forEach(nc ->
                    System.out.println("Curso ID: " + nc.getIdCurso() + ", Nota: " + nc.getNota())
            );
        }

        System.out.println("Competencias recibidas:");
        if (notasAreaCurricular.getNotasCompetencias() != null) {
            notasAreaCurricular.getNotasCompetencias().forEach(comp ->
                    System.out.println("CompetenciaCurso ID: " + comp.getIdCompetenciaCurso() + ", Nota: " + comp.getNota())
            );
        }

        NotasAreaCurricular nuevaNota = notasAreaCurricularService.guardar(notasAreaCurricular);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaNota);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotasAreaCurricular> actualizar(@PathVariable Integer id, @RequestBody NotasAreaCurricular notasAreaCurricular) {
        if (!notasAreaCurricularService.listarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        notasAreaCurricular.setIdNotascompetencia(id); // <--- Ajusta esto si el ID de tu entidad es diferente
        NotasAreaCurricular notasActualizada = notasAreaCurricularService.actualizar(notasAreaCurricular);
        return ResponseEntity.ok(notasActualizada);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        // Verifica si la nota existe antes de intentar eliminarla
        if (!notasAreaCurricularService.listarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        notasAreaCurricularService.eliminarPorId(id);
        return ResponseEntity.noContent().build(); // 204 No Content para eliminaciones exitosas
    }
}