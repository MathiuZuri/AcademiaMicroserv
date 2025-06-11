package com.example.ms_kajita.service;

import com.example.ms_kajita.entity.NotasAreaCurricular;

import java.util.List;
import java.util.Optional;

public interface NotasAreaCurricularService {


    List<NotasAreaCurricular> listar();

    NotasAreaCurricular guardar(NotasAreaCurricular notasAreaCurricular);

    NotasAreaCurricular actualizar(NotasAreaCurricular notasAreaCurricular);


    Optional<NotasAreaCurricular> listarPorId(Integer id);

    public void eliminarPorId(Integer id);


}