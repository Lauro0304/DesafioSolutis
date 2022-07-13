package dto;

import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.SessionVote;

@ApiModel(value = "SessionVoteDto")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SessionVoteDto {

	@ApiModelProperty(value = "Id da sessão de votação aberta")
	private Integer id;

	@ApiModelProperty(value = "Data Hora de inicio da sessão de votação aberta")
	private LocalDateTime dataHoraInicio;

	@ApiModelProperty(value = "Data Hora fim da sessão de votação aberta")
	private LocalDateTime dataHoraFim;

	@ApiModelProperty(value = "Status da sessão de votação aberta")
	private Boolean ativa;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDataHoraInicio() {
		return dataHoraInicio;
	}

	public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	public LocalDateTime getDataHoraFim() {
		return dataHoraFim;
	}

	public void setDataHoraFim(LocalDateTime dataHoraFim) {
		this.dataHoraFim = dataHoraFim;
	}

	public Boolean getAtiva() {
		return ativa;
	}

	public void setAtiva(Boolean ativa) {
		this.ativa = ativa;
	}

	public static SessionVote toEntity(SessionVoteDto dto) {
		return SessionVote.builder().id(dto.getId()).dataHoraInicio(dto.getDataHoraInicio())
				.dataHoraFim(dto.getDataHoraFim()).ativa(dto.getAtiva()).build();
	}

	public static SessionVoteDto toDTO(SessionVote sessaoVotacao) {
		return SessionVoteDto.builder().id(sessaoVotacao.getId()).dataHoraInicio(sessaoVotacao.getDataHoraInicio())
				.dataHoraFim(sessaoVotacao.getDataHoraFim()).ativa(sessaoVotacao.getAtiva()).build();
	}
}
