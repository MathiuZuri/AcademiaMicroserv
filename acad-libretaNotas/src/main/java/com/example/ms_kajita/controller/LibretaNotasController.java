package com.example.ms_kajita.controller;

import com.example.ms_kajita.entity.LibretaNotas; // Asegúrate de que este sea el paquete correcto de tu entidad
import com.example.ms_kajita.service.LibretaNotasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus; // Importación para HttpStatus
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/libretasNotas") // Ruta base para los endpoints de LibretaNotas
public class LibretaNotasController {

    @Autowired
    private LibretaNotasService libretaNotasService;

    @GetMapping
    public ResponseEntity<List<LibretaNotas>> listarTodas() {
        List<LibretaNotas> libretas = libretaNotasService.listar();
        return ResponseEntity.ok(libretas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibretaNotas> obtenerPorId(@PathVariable Integer id) {
        Optional<LibretaNotas> libretaOptional = libretaNotasService.listarPorId(id);
        return libretaOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<LibretaNotas> guardar(@RequestBody LibretaNotas libretaNotas) {
        // En tu servicio, asumes que libretaNotas tiene IdMatricula, etc., para enriquecerse.
        // Asegúrate de que el DTO/Entidad que llega aquí tiene los IDs necesarios.
        LibretaNotas nuevaLibretaNotas = libretaNotasService.guardar(libretaNotas);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaLibretaNotas); // Código 201 Created es más apropiado para POST
    }

    @PutMapping("/{id}")
    public ResponseEntity<LibretaNotas> actualizar(@PathVariable Integer id, @RequestBody LibretaNotas libretaNotas) {
        // Primero, verifica si la libreta existe para asegurar que se está actualizando y no creando una nueva.
        // También puedes manejar esta validación dentro del servicio si lo prefieres.
        if (!libretaNotasService.listarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        // Asegura que el ID de la entidad a actualizar sea el del PathVariable
        libretaNotas.setIdLibretaNotas(id); // Asumo que el campo ID de LibretaNotas es 'idLibretaNotas'
        LibretaNotas libretaActualizada = libretaNotasService.actualizar(libretaNotas);
        return ResponseEntity.ok(libretaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        // Verifica si la libreta existe antes de intentar eliminarla
        if (!libretaNotasService.listarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        libretaNotasService.eliminarPorId(id);
        return ResponseEntity.noContent().build(); // 204 No Content para eliminaciones exitosas
    }
}