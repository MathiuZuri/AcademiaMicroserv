package com.example.ms_kajita.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursoCompetencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Relación con NotasAreaCurricular
    @ManyToOne
    @JoinColumn(name = "id_notas_competencia")
    @JsonBackReference
    private NotasAreaCurricular notasCompetencias;

    @Column(name = "nota")
    private Integer nota;

    @Column(name = "id_competencia_curso")
    private Integer idCompetenciaCurso;

    // Nombre opcional desde Feign (no persistido)
    @Transient
    private String nombreCompetencia;


}
