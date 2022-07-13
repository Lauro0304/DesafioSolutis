package dto;

import javax.persistence.Enumerated;

import javax.validation.constraints.NotNull;

import enuns.VoteStatus;
import io.swagger.annotations.ApiModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "VoteDto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteDto {

	@NotNull
	private Long sessaoVotacaoId;

	@NotNull(message = "Voto é obrigatório e precisa seguir o padrão: SIM/NAO")
	@Enumerated
	private VoteStatus voto;

	@NotNull(message = "CPF obrigatorio")
	private String cpf;

}
