package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import enuns.StatusEnum;
import lombok.*;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.time.Instant;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_session_vote")
public class SessionVote {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "data_hora_inicio")
	private LocalDateTime dataHoraInicio;

	@Column(name = "Fechado")
	private Instant fechado;

	@OneToOne
	@JoinColumn(name = "id_tema")
	private Theme theme;

	@Column(name = "duracao")
	private Integer duracao = 0;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "sessionVote", cascade = CascadeType.ALL)
	private List<Vote> vote = new ArrayList<>();

	public boolean aberta() {
		return getTheme().getStatus().equals(StatusEnum.valueOf("ABERTA"));
	}

	public boolean fechada() {
		return this.getFechado() != null && this.getFechado().isBefore(Instant.now());
	}

}
