package a.c.execLivro;

public record LivroResumoDTO(long id, String titulo, String autor, int ano) {
    public static LivroResumoDTO from(Livro livro) {
        return new LivroResumoDTO(livro.getId(),
        livro.getTitulo(),
        livro.getAutor() != null ? livro.getAutor().getNome() : null,
        livro.getAno());
    }

}
