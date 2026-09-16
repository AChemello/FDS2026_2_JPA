package a.c.execLivro;

public record UsuarioDTO(long id, String nome) {
    public static UsuarioDTO from(Usuario usuario) {
        return new UsuarioDTO(usuario.getId(), usuario.getNome());
    }
}
