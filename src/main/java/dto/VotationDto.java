package dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.Vote;

@ApiModel(value = "VotacaoDTO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VotationDto {

	@ApiModelProperty(value = "ID da votação aberta")
	private Integer id;

	@ApiModelProperty(value = "ID do tema de votação aberta")
	private Integer idTema;

	@ApiModelProperty(value = "ID da sessão de votação aberta")
	private Integer idSessaoVotacao;

	@ApiModelProperty(value = "Voto")
	private Boolean voto;

	@ApiModelProperty(value = "Quantidade de votos positivos")
	private Integer quantidadeVotosSim;

	@ApiModelProperty(value = "Quantidade de votos negativos")
	private Integer quantidadeVotosNao;

	public static Vote toEntity(VotationDto dto) {
		return Vote.builder().id(dto.getIdTema()).idTema(dto.getIdTema()).idSessaoVotacao(dto.getIdSessaoVotacao())
				.voto(dto.getVoto()).build();
	}

	private Object getVoto() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object getIdSessaoVotacao() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object getIdTema() {
		// TODO Auto-generated method stub
		return null;
	}
}
