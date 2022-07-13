package dto;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotBlank;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.Theme;

@ApiModel(value = "ThemeDto")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThemeDto {

	@ApiModelProperty(value = "ID Tema", required = true)
	private Integer id;

	private String nomeTema;

	@ApiModelProperty(value = "Descrição sobre o que será voltado")
	@NotBlank(message = "Descrição deve ser preenchido")
	private String descricao;

	

    public static Theme toEntity(ThemeDto dto) {
        
    	return Theme.builder()
                .id(dto.getId())
                .descricao(dto.getDescricao())
                .build();
    }
	
	
	
	public static ThemeDto toDto(Theme theme) {
		
		return  ThemeDto.builder()
				.id(theme.getId())
				.descricao(theme.getDescricao())
				.build();
		

	}

	





	public ThemeDto(Theme theme) {

	}

	public static List<ThemeDto> converter(List<Theme> theme) {
		return theme.stream().map(ThemeDto::new).collect(Collectors.toList());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeTema() {
		return nomeTema;
	}

	public void setNomeTema(String nomeTema) {
		this.nomeTema = nomeTema;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
