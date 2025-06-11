package com.example.ms_kajita.service.impl;

import com.example.ms_kajita.dto.CompetenciaDto;
import com.example.ms_kajita.dto.CursoDto;
import com.example.ms_kajita.dto.PlanAcademicoDto;
import com.example.ms_kajita.entity.NotasAreaCurricular;
import com.example.ms_kajita.feing.CompetenciaFeing;
import com.example.ms_kajita.feing.CursoFeing;
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
    private CursoFeing cursoFeing;

    @Autowired
    private CompetenciaFeing competenciaFeing;

    @Autowired
    private com.example.ms_kajita.repository.notasAreaCurricularRepository notasAreaCurricularRepository;

    @Override
    public List<NotasAreaCurricular> listar() {
        return notasAreaCurricularRepository.findAll().stream()
                .map(notasAreaCurricular -> {
                    notasAreaCurricular.setNombrePlanAcad(obtenerPlanAcad(notasAreaCurricular.getIdPlanAcademico()));

                    if (notasAreaCurricular.getCursosNotas() != null) {
                        notasAreaCurricular.getCursosNotas().forEach(notaCurso ->
                                notaCurso.setNombreCurso(obtenerNombreCurso(notaCurso.getIdCurso()))
                        );
                    }

                    if (notasAreaCurricular.getNotasCompetencias() != null) {
                        notasAreaCurricular.getNotasCompetencias().forEach(cursoCompetencia ->
                                cursoCompetencia.setNombreCompetencia(obtenerNombreCompetencia(cursoCompetencia.getIdCompetenciaCurso()))
                        );
                    }

                    return notasAreaCurricular;
                })
                .collect(Collectors.toList());
    }

    @Override
    public NotasAreaCurricular guardar(NotasAreaCurricular notasAreaCurricular) {
        NotasAreaCurricular guardada = notasAreaCurricularRepository.save(notasAreaCurricular);
        guardada.setNombrePlanAcad(obtenerPlanAcad(guardada.getIdPlanAcademico()));

        if (guardada.getCursosNotas() != null) {
            guardada.getCursosNotas().forEach(notaCurso ->
                    notaCurso.setNombreCurso(obtenerNombreCurso(notaCurso.getIdCurso()))
            );
        }

        if (guardada.getNotasCompetencias() != null) {
            guardada.getNotasCompetencias().forEach(cursoCompetencia ->
                    cursoCompetencia.setNombreCompetencia(obtenerNombreCompetencia(cursoCompetencia.getIdCompetenciaCurso()))
            );
        }

        return guardada;
    }

    @Override
    public NotasAreaCurricular actualizar(NotasAreaCurricular notasAreaCurricular) {
        NotasAreaCurricular actualizada = notasAreaCurricularRepository.save(notasAreaCurricular);
        actualizada.setNombrePlanAcad(obtenerPlanAcad(actualizada.getIdPlanAcademico()));

        if (actualizada.getCursosNotas() != null) {
            actualizada.getCursosNotas().forEach(notaCurso ->
                    notaCurso.setNombreCurso(obtenerNombreCurso(notaCurso.getIdCurso()))
            );
        }

        if (actualizada.getNotasCompetencias() != null) {
            actualizada.getNotasCompetencias().forEach(cursoCompetencia ->
                    cursoCompetencia.setNombreCompetencia(obtenerNombreCompetencia(cursoCompetencia.getIdCompetenciaCurso()))
            );
        }

        return actualizada;
    }

    @Override
    public Optional<NotasAreaCurricular> listarPorId(Integer id) {
        Optional<NotasAreaCurricular> libretaOptional = notasAreaCurricularRepository.findById(id);
        if (libretaOptional.isPresent()) {
            NotasAreaCurricular notasAreaCurricular = libretaOptional.get();
            notasAreaCurricular.setNombrePlanAcad(obtenerPlanAcad(notasAreaCurricular.getIdPlanAcademico()));

            if (notasAreaCurricular.getCursosNotas() != null) {
                notasAreaCurricular.getCursosNotas().forEach(notaCurso ->
                        notaCurso.setNombreCurso(obtenerNombreCurso(notaCurso.getIdCurso()))
                );
            }

            if (notasAreaCurricular.getNotasCompetencias() != null) {
                notasAreaCurricular.getNotasCompetencias().forEach(cursoCompetencia ->
                        cursoCompetencia.setNombreCompetencia(obtenerNombreCompetencia(cursoCompetencia.getIdCompetenciaCurso()))
                );
            }

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
                return planAcademicoDto.getNombrePlan();
            }
        } catch (Exception e) {
            return "Plan académico no encontrado";
        }
        return null;
    }

    private String obtenerNombreCurso(Integer idCurso) {
        try {
            CursoDto cursoDto = cursoFeing.buscarCurso(idCurso).getBody();
            if (cursoDto != null) return cursoDto.getNombre();
        } catch (Exception e) {
            return "Curso no encontrado";
        }
        return null;
    }

    private String obtenerNombreCompetencia(Integer idCompetenciaCurso) {
        try {
            CompetenciaDto competenciaDto = competenciaFeing.buscarCompetencia(idCompetenciaCurso).getBody();
            if (competenciaDto != null) return competenciaDto.getNombreCompetencia();
        } catch (Exception e) {
            return "Competencia no encontrada";
        }
        return null;
    }
}