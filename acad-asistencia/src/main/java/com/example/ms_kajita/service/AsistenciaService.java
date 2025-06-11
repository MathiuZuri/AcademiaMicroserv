package com.example.ms_kajita.service;

import com.example.ms_kajita.entity.Asistencia;
import com.example.ms_kajita.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface AsistenciaService {


    List<Asistencia> listar();

    Asistencia guardar(Asistencia asistencia);

    Asistencia actualizar(Asistencia asistencia);

    Optional<Asistencia> listarPorId(Integer id);

    public void eliminarPorId(Integer id);


}