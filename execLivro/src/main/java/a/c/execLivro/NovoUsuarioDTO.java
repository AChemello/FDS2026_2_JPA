package a.c.execLivro;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NovoUsuarioDTO(
    @Positive
    long id,

    @NotBlank
    @Size(min = 3, max = 80)
    String nome
) {}
