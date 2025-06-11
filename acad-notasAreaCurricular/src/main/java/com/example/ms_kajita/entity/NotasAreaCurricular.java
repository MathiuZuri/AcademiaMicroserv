package com.example.ms_kajita.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Data
@Entity
public class NotasAreaCurricular {

    public Integer getIdNotascompetencia() {
        return idNotascompetencia;
    }

    public void setIdNotascompetencia(Integer idNotascompetencia) {
        this.idNotascompetencia = idNotascompetencia;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public Integer getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(Integer notaFinal) {
        this.notaFinal = notaFinal;
    }

    public Integer getIdPlanAcademico() {
        return idPlanAcademico;
    }

    public void setIdPlanAcademico(Integer idPlanAcademico) {
        this.idPlanAcademico = idPlanAcademico;
    }

    public String getNombrePlanAcad() {
        return nombrePlanAcad;
    }

    public void setNombrePlanAcad(String nombrePlanAcad) {
        this.nombrePlanAcad = nombrePlanAcad;
    }

    @Override
    public String toString() {
        return "NotasAreaCurricular{" +
                "idNotascompetencia=" + idNotascompetencia +
                ", nota=" + nota +
                ", notaFinal=" + notaFinal +
                ", idPlanAcademico=" + idPlanAcademico +
                ", nombrePlanAcad='" + nombrePlanAcad + '\'' +
                '}';
    }

    public NotasAreaCurricular(Integer idNotascompetencia, Integer nota, Integer notaFinal, Integer idPlanAcademico, String nombrePlanAcad, List<NotaCurso> cursosNotas, List<CursoCompetencia> notasCompetencias) {
        this.idNotascompetencia = idNotascompetencia;
        this.nota = nota;
        this.notaFinal = notaFinal;
        this.idPlanAcademico = idPlanAcademico;
        this.nombrePlanAcad = nombrePlanAcad;
        this.cursosNotas = cursosNotas;
        this.notasCompetencias = notasCompetencias;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idNotascompetencia")
    private Integer idNotascompetencia;
    @Column(name = "nota")
    private Integer nota;
    @Column(name = "notaFinal")
    private Integer notaFinal;

    @Column(name = "idPlanAcademico")
    private Integer idPlanAcademico;
    @Transient
    private String nombrePlanAcad;
    @OneToMany(mappedBy = "notasAreaCurricular", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<NotaCurso> cursosNotas;

    @OneToMany(mappedBy = "notasCompetencias", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<CursoCompetencia> notasCompetencias;

    public List<CursoCompetencia> getNotasCompetencias() {
        return notasCompetencias;
    }

    public void setNotasCompetencias(List<CursoCompetencia> notasCompetencias) {
        this.notasCompetencias = notasCompetencias;
    }



    public List<NotaCurso> getCursosNotas() {
        return cursosNotas;
    }

    public void setCursosNotas(List<NotaCurso> cursosNotas) {
        this.cursosNotas = cursosNotas;
    }




    public NotasAreaCurricular() {

    }


}