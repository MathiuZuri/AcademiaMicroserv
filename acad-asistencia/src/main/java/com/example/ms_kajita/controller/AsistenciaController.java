package com.example.ms_kajita.controller;

import com.example.ms_kajita.entity.Asistencia;
import com.example.ms_kajita.service.AsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/asistencias")
public class AsistenciaController {

    @Autowired
    private AsistenciaService asistenciaService;

    @GetMapping
    public ResponseEntity<List<Asistencia>> listarTodas() {
        List<Asistencia> asistencias = asistenciaService.listar();
        return ResponseEntity.ok(asistencias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asistencia> obtenerPorId(@PathVariable Integer id) {
        Optional<Asistencia> asistenciaOptional = asistenciaService.listarPorId(id);
        return asistenciaOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Asistencia> guardar(@RequestBody Asistencia asistencia) {
        Asistencia nuevaAsistencia = asistenciaService.guardar(asistencia);
        return ResponseEntity.ok(nuevaAsistencia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asistencia> actualizar(@PathVariable Integer id, @RequestBody Asistencia asistencia) {
        if (!asistenciaService.listarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        asistencia.setIdAsistencia(id); // Asegura que se actualice el registro correcto
        Asistencia asistenciaActualizada = asistenciaService.actualizar(asistencia);
        return ResponseEntity.ok(asistenciaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (!asistenciaService.listarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        asistenciaService.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
