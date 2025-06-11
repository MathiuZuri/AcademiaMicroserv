package com.example.ms_kajita.feing;

import com.example.ms_kajita.dto.CompetenciaDto;
import com.example.ms_kajita.dto.CursoDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "acad-competencia-service", path = "/competencias")

public interface CompetenciaFeing {

    @GetMapping("/{id}")
    @CircuitBreaker(name = "CompetenciaPorIdCB", fallbackMethod = "fallbackCompetenciaById")
    public ResponseEntity<CompetenciaDto> buscarCompetencia(@PathVariable Integer id);

    default ResponseEntity<CompetenciaDto> fallbackCompetenciaById(Integer id, Exception e) {
        CompetenciaDto competenciaDto = new CompetenciaDto();
        competenciaDto.setNombreCompetencia("Servicio no disponible de competencia");
        competenciaDto.setDescripcionCompetencia("Servicio no disponible de competencia");
        return ResponseEntity.ok(competenciaDto);
    }
}