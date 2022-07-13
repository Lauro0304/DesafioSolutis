package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_vote")
public class Vote {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "voto")
	private Boolean voto;

	@Column(name = "id_tema")
	private Integer idTema;

	@Column(name = "id_sessao_votacao")
	private Integer idSessaoVotacao;

	public static Object builder() {
		// TODO Auto-generated method stub
		return null;
	}

}
