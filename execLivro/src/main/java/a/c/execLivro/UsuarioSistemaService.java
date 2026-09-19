package a.c.execLivro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioSistemaService {
    private final UsuarioSistemaRepository usuarioSistemaRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioSistemaService(UsuarioSistemaRepository usuarioSistemaRepository,
                                  PasswordEncoder passwordEncoder) {
        this.usuarioSistemaRepository = usuarioSistemaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioSistema cadastraUsuarioSistema(NovoUsuarioSistemaRequest request) {
        if (usuarioSistemaRepository.existsByUsername(request.username())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username já cadastrado.");
        }

        String senhaCriptografada = passwordEncoder.encode(request.senha());

        UsuarioSistema usuario = new UsuarioSistema(
                request.username(),
                senhaCriptografada,
                request.perfilNormalizado()
        );

        return usuarioSistemaRepository.save(usuario);
    }
}
