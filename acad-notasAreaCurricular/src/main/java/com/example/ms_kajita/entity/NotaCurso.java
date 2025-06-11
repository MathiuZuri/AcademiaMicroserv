package com.example.ms_kajita.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotaCurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Relación con NotasAreaCurricular
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_notas_competencia")
    private NotasAreaCurricular notasAreaCurricular;

    // ID del curso (podría venir de otro microservicio)
    @Column(name = "id_curso")
    private Integer idCurso;

    // Nota específica para el curso
    @Column(name = "nota")
    private Integer nota;

    // Nota final (opcional)
    @Column(name = "nota_final")
    private Integer notaFinal;

    // Nombre del curso obtenido por Feign (no persistido)
    @Transient
    private String nombreCurso;
}