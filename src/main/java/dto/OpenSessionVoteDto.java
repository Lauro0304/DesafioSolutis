package dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "OpenSessionVoteDto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpenSessionVoteDto {

	@ApiModelProperty(value = "ID do tema que se quer abrir sessão para votação")
	@NotNull(message = "ID do tema deve ser preenchido")

	private Integer idTema;

	@ApiModelProperty(value = "TEMPO em MINUTOS que a sessão de votação deverá ficar aberta")

	private Integer tempo;

	

	}

