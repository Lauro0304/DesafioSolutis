package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import enuns.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "tb_theme")
public class Theme {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Long id;

	@Column(name = "Nome")
	@Size(min = 3)
	@NotNull(message = "Nome da pauta nao pode ser nulo")
	private String nome;

	@Column(name = "Descricao")
	@Size(min = 3)
	@NotNull(message = "Descricao da pauta nao pode ser nulo")
	private String descricao;

	@Column(name = "Status")
	@Enumerated
	private StatusEnum status;

	@Column(name = "QtdVotos")
	private Integer qtdVotos;

	@Column(name = "QtdVotosSim")
	private Integer qtdVotosSim = 0;

	@Column(name = "QtdVotosNao")
	private Integer qtdVotosNao = 0;

	@Column(name = "PercentualSim")
	private Double percentualSim = 0.00;

	@Column(name = "PercentualNao")
	private Double percentualNao = 0.00;

	@Column(name = "Vencedor")
	private String vencedor;
}
