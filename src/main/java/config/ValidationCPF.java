package config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dto.CpfDto;

@FeignClient(name = "CpfValidacao", url = "https://cpf-api-almfelipe.herokuapp.com")
public interface ValidationCPF {
	
	@RequestMapping(method = RequestMethod.GET, value = "/cpf/{cpf}")
	CpfDto validarCPF(@PathVariable String cpf);
	

}
