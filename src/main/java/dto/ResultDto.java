package dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "ResultDTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultDto {

	@ApiModelProperty(value = "Objeto TemaDto com os dados do que foi votado")
	private ThemeDto themaDto;
	@ApiModelProperty(value = "Objeto VotacaoDTO com os dados do resultado da votação")
	private VotationDto votationDto;
}
