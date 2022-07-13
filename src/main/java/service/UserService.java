package service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import dto.UserDto;
import model.User;
import repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
	@Autowired
	CpfService cpfService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public User buscarPorId(Long id) {
		log.info("Buscando por id {}", id);
		return userRepository.findById(id).orElse(null);
	}

	public User buscarPorNome(String nome) {
		log.info("Buscando por nome {}", nome);
		return userRepository.findByNome(nome).orElse(null);
	}
	 
	public Optional<User> criarUsuario(User usuario) {
		if (userRepository.findByCpf(usuario.getCpf()).isPresent()) {
			log.error("CPF ja cadastrado {}", usuario.getCpf());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF já cadastrado", null);
		}
		if (userRepository.findByEmail(usuario.getEmail()).isPresent()) {
			log.error("Email ja cadastrado {}", usuario.getEmail());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "EMAIL já cadastrado", null);
		}
		if (!cpfService.validarCpf(usuario.getCpf())) {
			log.error("CPF invalido {}", usuario.getCpf());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF não é válido", null);
		}

		usuario.setSenha(encriptyPassword(usuario.getSenha()));
		log.info("Salvando usuario");
		return Optional.of(userRepository.save(usuario));
	}

    public User atualizarUsuario(User usuario){

        if (userRepository.findById(usuario.getId()).isPresent()){
            Optional<User> encontrarUsuario = userRepository.findByEmail(usuario.getEmail());

            if (encontrarUsuario.isPresent() && !Objects.equals(encontrarUsuario.get().getId(), usuario.getId())){
                log.info("Usuario inexistente");    
            	throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario inexistente", null);
            }

            if (Boolean.FALSE.equals(cpfService.validarCpf(usuario.getCpf()))){
                log.info("Cpf invalido");
            	throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cpf invalido",null);
            }

            usuario.setSenha(encriptyPassword(usuario.getPassword()));
            log.info("Salvando usuario");
            return userRepository.save(usuario);
        }
        log.info("Usuario nao encontrado");
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario nao encontrado", null);
    }

	public void deletaUsuario(Long id){
		log.info("Deletando usuario");
		userRepository.deleteById(id);
    }

	public String encriptyPassword (String password) {
		BCryptPasswordEncoder enconder = new BCryptPasswordEncoder();
		String passwordEncoder = enconder.encode(password);
		return passwordEncoder;
	}
	
	public User buscarPorCPF(String cpf) {
		log.info("Buscando por CPF");
		return userRepository.findByCpf(cpf).orElse(null);
	}

}
