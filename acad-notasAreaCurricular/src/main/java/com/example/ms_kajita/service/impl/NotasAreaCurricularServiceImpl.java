package com.example.ms_kajita.service.impl;

import com.example.ms_kajita.dto.PlanAcademicoDto;
import com.example.ms_kajita.entity.NotasAreaCurricular;
import com.example.ms_kajita.feing.PlanAcademicoFeing;
import com.example.ms_kajita.service.NotasAreaCurricularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotasAreaCurricularServiceImpl implements NotasAreaCurricularService {

    @Autowired
    private PlanAcademicoFeing planAcademicoFeing;
    @Autowired
    private com.example.ms_kajita.repository.notasAreaCurricularRepository notasAreaCurricularRepository;

    @Override
    public List<NotasAreaCurricular> listar() {
        return notasAreaCurricularRepository.findAll().stream()
                .map(notasAreaCurricular -> {
                    notasAreaCurricular.setNombrePlanAcad(obtenerPlanAcad(notasAreaCurricular.getIdPlanAcademico()));

                    return notasAreaCurricular;
                })
                .collect(Collectors.toList());
    }


    @Override
    public NotasAreaCurricular guardar(NotasAreaCurricular notasAreaCurricular) {
        NotasAreaCurricular notasCurricularGuardada = notasAreaCurricularRepository.save(notasAreaCurricular);

        notasCurricularGuardada.setNombrePlanAcad(obtenerPlanAcad(notasAreaCurricular.getIdPlanAcademico()));

        return notasAreaCurricular;
    }

    @Override
    public NotasAreaCurricular actualizar(NotasAreaCurricular notasAreaCurricular) {
        NotasAreaCurricular notasCurrilarActualizada = notasAreaCurricularRepository.save(notasAreaCurricular);
        NotasAreaCurricular notasCurricularGuardada = notasAreaCurricularRepository.save(notasAreaCurricular);

        notasCurrilarActualizada.setNombrePlanAcad(obtenerPlanAcad(notasAreaCurricular.getIdPlanAcademico()));

        return notasAreaCurricular;
    }

    @Override
    public Optional<NotasAreaCurricular> listarPorId(Integer id) {
        Optional<NotasAreaCurricular> libretaOptional = notasAreaCurricularRepository.findById(id);
        if (libretaOptional.isPresent()) {
            NotasAreaCurricular notasAreaCurricular = libretaOptional.get();

            notasAreaCurricular.setNombrePlanAcad(obtenerPlanAcad(notasAreaCurricular.getIdPlanAcademico()));

            return Optional.of(notasAreaCurricular);
        }
        return Optional.empty();
    }

    @Override
    public void eliminarPorId(Integer id) {
        notasAreaCurricularRepository.deleteById(id);
    }


    private String obtenerPlanAcad(Integer idNotasCompetencia) {
        try {
            PlanAcademicoDto planAcademicoDto = planAcademicoFeing.buscarPlanAcademico(idNotasCompetencia).getBody();
            if (planAcademicoDto != null) {
                return  planAcademicoDto.getNombrePlan();
            }
        } catch (Exception e) {
            return "Docente no encontrado";
        }
        return null;
    }

}