package a.c.execLivro;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class UsuarioSistemaController {
    private final UsuarioSistemaService usuarioSistemaService;

    @Autowired
    public UsuarioSistemaController(UsuarioSistemaService usuarioSistemaService) {
        this.usuarioSistemaService = usuarioSistemaService;
    }

    @PostMapping("usuarios-sistema")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Void> cadastrar(@Valid @RequestBody NovoUsuarioSistemaRequest request) {
        UsuarioSistema criado = usuarioSistemaService.cadastraUsuarioSistema(request);
        return ResponseEntity
                .created(URI.create("/usuarios-sistema/" + criado.getId()))
                .build();
    }
}
