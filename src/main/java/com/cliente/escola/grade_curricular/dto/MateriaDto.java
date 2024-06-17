package com.cliente.escola.grade_curricular.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class MateriaDto extends RepresentationModel<MateriaDto> {

    private Long id;

    @NotBlank(message = "Informe o nome da Matéria.")
    private String nome;

    @Min(value = 34, message = "Permitido o mínimo de 34 Horas por matéria.")
    @Max(value = 120, message = "Permitido o máximo de 120 Horas por matéria.")
    private int horas;

    @NotBlank(message = "Informe o nome o código da matéria.")
    private String codigo;

    @Min(value = 1, message = "Permitido o mínimo de 1 vez ao ano.")
    @Max(value = 2, message = "Permitido o máximo de 2 vezes ao ano.")
    private int frequencia;
}
