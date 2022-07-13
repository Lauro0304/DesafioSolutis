package controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import model.User;
import repository.UserRepository;
import service.UserService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usuario")
public class UserController {
	
	@Autowired
    private UserRepository userRepository;

	
	private  UserService service;

    @GetMapping
    public ResponseEntity<List<User>> listarTodos(){
        List<User> usuarios = service.listarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> buscarPorId(@PathVariable Integer id){
        Optional<User> usuario = service.buscarPorId(id);

        if(usuario.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(usuario.get());
    }

    @PostMapping
    public ResponseEntity<User> cadastrar(@Valid @RequestBody User usuario){
    	User salvarUsuario = service.salvar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvarUsuario);
    }

    @PutMapping
    public ResponseEntity<User> atualizar(@Valid @RequestBody User userDto) {
    	User salvar = service.salvar(userDto);
        return ResponseEntity.status(HttpStatus.OK).body(userDto);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
       service.deletar(id);
       return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
