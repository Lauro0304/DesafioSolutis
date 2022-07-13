package dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder(toBuilder = true)
@Data
@EqualsAndHashCode
public class SessionDto {

	private Long idTema;
	
	private Integer duracao;

}
