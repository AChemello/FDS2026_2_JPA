package a.c.execLivro;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NovoUsuarioSistemaRequest(
        @NotBlank
        @Size(min = 3, max = 40)
        String username,

        @NotBlank
        @Size(min = 6, max = 100)
        String senha,

        String perfil
) {
    public String perfilNormalizado() {
        if (perfil == null || perfil.isBlank()) {
            return "CONSULTA";
        }
        return perfil.trim().toUpperCase();
    }
}
