package com.example.ms_kajita.dto;

public class CompetenciaDto {


    public String getNombreCompetencia() {
        return nombreCompetencia;
    }

    public void setNombreCompetencia(String nombreCompetencia) {
        this.nombreCompetencia = nombreCompetencia;
    }

    public String getDescripcionCompetencia() {
        return descripcionCompetencia;
    }

    public void setDescripcionCompetencia(String descripcionCompetencia) {
        this.descripcionCompetencia = descripcionCompetencia;
    }

    public Integer getIdCompetencia() {
        return idCompetencia;
    }

    public void setIdCompetencia(Integer idCompetencia) {
        this.idCompetencia = idCompetencia;
    }

    public CompetenciaDto(Integer idCompetencia, String nombreCompetencia, String descripcionCompetencia) {
        this.idCompetencia = idCompetencia;
        this.nombreCompetencia = nombreCompetencia;
        this.descripcionCompetencia = descripcionCompetencia;
    }

    public CompetenciaDto() {
    }

    @Override
    public String toString() {
        return "CompetenciaDto{" +
                "idCompetencia=" + idCompetencia +
                ", nombreCompetencia='" + nombreCompetencia + '\'' +
                ", descripcionCompetencia='" + descripcionCompetencia + '\'' +
                '}';
    }

    private Integer idCompetencia;
    private String nombreCompetencia;
    private String descripcionCompetencia;
}
