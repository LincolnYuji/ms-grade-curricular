package com.cliente.escola.grade_curricular.service;

import com.cliente.escola.grade_curricular.dto.MateriaDto;

import java.util.List;

public interface IMateriaService {

    public List<MateriaDto> listar();

    public MateriaDto consultar(final Long id);

    public Boolean cadastrar(final MateriaDto materia);

    public Boolean atualizar(final MateriaDto materia);

    public Boolean excluir(final Long id);
}
