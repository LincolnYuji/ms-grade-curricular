package com.cliente.escola.grade_curricular.service;

import com.cliente.escola.grade_curricular.dto.MateriaDto;
import com.cliente.escola.grade_curricular.entity.MateriaEntity;
import com.cliente.escola.grade_curricular.exception.MateriaException;
import com.cliente.escola.grade_curricular.repository.IMateriaRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MateriaService implements IMateriaService{

    @Autowired
    private IMateriaRepository materiaRepository;
    private ModelMapper mapper;

    @Autowired
    public MateriaService(IMateriaRepository materiaRepository) {
        this.mapper = new ModelMapper();
        this.materiaRepository = materiaRepository;
    }

    @Override
    public List<MateriaDto> listar() {
        try
        {
            return mapper.map(this.materiaRepository.findAll(),new TypeToken<List<MateriaDto>>() {}.getType());
        }
        catch (Exception e)
        {
            return new ArrayList<>();
        }
    }

    @Override
    public MateriaDto consultar(Long id)
    {
        try
        {
            Optional<MateriaEntity> materiaOptional = this.materiaRepository.findById(id);

            if (materiaOptional.isPresent())
                return this.mapper.map(materiaOptional.get(), MateriaDto. class);

            throw new MateriaException("Matéria não Encontrada", HttpStatus.NOT_FOUND);
        } catch (MateriaException m) {
            throw m;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Boolean cadastrar(MateriaDto materia) {
        try{
            MateriaEntity materiaEntity = this.mapper.map(materia, MateriaEntity.class);

            this.materiaRepository.save(materiaEntity);
            return true;
        } catch(Exception e) {
            return false;
        }
    }
}
