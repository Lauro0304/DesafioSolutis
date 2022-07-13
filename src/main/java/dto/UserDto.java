package dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import br.com.desafiosolutis.dto.UsuarioDto;
import br.com.desafiosolutis.model.Usuario;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.User;

@ApiModel(value = "UserDto")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	private Integer id;

	

	@ApiModelProperty(value = "CPF válido referente ao usuario")
	@CPF(message = "Não é um CPF valido")
	@NotBlank(message = "CPF do usuario deve ser preenchido")
	private String cpfUsuario;

	private String email;

	@NotBlank(message = "Nome do usuario deve ser preenchido")
	private String nome;

	@ApiModelProperty(value = "ID do tema a ser votado")
	@NotNull(message = "idTema deve ser preenchido")
	private Integer idTema;

	public UserDto(Object o, String cpfUsuario, Integer idTema) {
	    }

	public static User toEntity(UserDto dto) {
		return User.builder().id(dto.getId()).cpfUsuario(dto.getCpfUsuario()).idTema(dto.getIdTema()).build();

	}

	
}

