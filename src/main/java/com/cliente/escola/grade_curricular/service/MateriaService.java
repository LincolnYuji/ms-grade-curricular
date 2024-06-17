package com.cliente.escola.grade_curricular.service;

import com.cliente.escola.grade_curricular.controller.MateriaController;
import com.cliente.escola.grade_curricular.dto.MateriaDto;
import com.cliente.escola.grade_curricular.entity.MateriaEntity;
import com.cliente.escola.grade_curricular.exception.MateriaException;
import com.cliente.escola.grade_curricular.repository.IMateriaRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CacheConfig(cacheNames = "materia")
@Service
public class MateriaService implements IMateriaService{

    private static final String MENSAGEM_ERRO = "Erro interno identificado. Contate do suporte";
    private static final String MATERIA_NAO_ENCONTRADA = "Matéria não Encontrada";
    private IMateriaRepository materiaRepository;
    private ModelMapper mapper;

    @Autowired
    public MateriaService(IMateriaRepository materiaRepository) {
        this.mapper = new ModelMapper();
        this.materiaRepository = materiaRepository;
    }

    //@CachePut(unless = "result.size() < 3")
    @Override
    public List<MateriaDto> listar() {
        try
        {
            List<MateriaDto> materiaDto = this.mapper.map(this.materiaRepository.findAll(),new TypeToken<List<MateriaDto>>() {
            }.getType());

            materiaDto.forEach(materia->{
                materia.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).consultarMateria(materia.getId()))
                        .withSelfRel());
            });

            return materiaDto;
        }
        catch (Exception e)
        {
            return new ArrayList<>();
        }
    }

    //@CachePut(key = "#id")
    @Override
    public MateriaDto consultar(Long id)
    {
        try
        {
            Optional<MateriaEntity> materiaOptional = this.materiaRepository.findById(id);

            if (materiaOptional.isPresent())
                return this.mapper.map(materiaOptional.get(), MateriaDto. class);

            throw new MateriaException(MATERIA_NAO_ENCONTRADA, HttpStatus.NOT_FOUND);
        } catch (MateriaException m) {
            throw m;
        } catch (Exception e) {
            throw new MateriaException(MENSAGEM_ERRO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //@CacheEvict(key = "#materia.id")
    @Override
    public Boolean cadastrar(MateriaDto materia) {
        try{
            MateriaEntity materiaEntity = this.mapper.map(materia, MateriaEntity.class);
            this.materiaRepository.save(materiaEntity);
            return Boolean.TRUE;
        } catch(Exception e) {
            throw new MateriaException(MENSAGEM_ERRO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //@CacheEvict(key = "#materia.id")
    @Override
    public Boolean atualizar(MateriaDto materia) {
        try {
            this.consultar(materia.getId());
            MateriaEntity materiaEntityAtualizada = this.mapper.map(materia,MateriaEntity.class);

            this.materiaRepository.save(materiaEntityAtualizada);

            return Boolean.TRUE;

        }catch (MateriaException m) {
            throw m;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Boolean excluir(Long id) {
        try {
            this.consultar(id);
            this.materiaRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (MateriaException m) {
            throw m;
        } catch (Exception e) {
            throw e;
        }
    }
}
