package a.c.execLivro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LivrosRepository {
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Livro> livroRowMapper = new RowMapper<>() {
        @Override
        public Livro mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Livro(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("autor"),
                    rs.getInt("ano")
            );
        }
    };

    @Autowired
    public LivrosRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        criarTabelaSeNecessario();
    }

    private void criarTabelaSeNecessario() {
        jdbcTemplate.execute("""
                CREATE TABLE IF NOT EXISTS livros (
                    id INT PRIMARY KEY,
                    titulo VARCHAR(255) NOT NULL,
                    autor VARCHAR(255) NOT NULL,
                    ano INT NOT NULL)
                """);
    }

    public List<Livro> findAll() {
        return jdbcTemplate.query(
                "SELECT id, titulo, autor, ano FROM livros",
                livroRowMapper);
    }

    public List<Livro> findByAutor(String autor) {
        return jdbcTemplate.query(
                "SELECT id, titulo, autor, ano FROM livros WHERE autor = ?",
                livroRowMapper, autor);
    }

    public void save(Livro livro) {
        int atualizados = jdbcTemplate.update(
                "UPDATE livros SET titulo = ?, autor = ?, ano = ? WHERE id = ?",
                livro.getTitulo(), livro.getAutor(), livro.getAno(), livro.getId());

        if (atualizados == 0) {
            jdbcTemplate.update(
                    "INSERT INTO livros (id, titulo, autor, ano) VALUES (?, ?, ?, ?)",
                    livro.getId(), livro.getTitulo(), livro.getAutor(), livro.getAno());
        }
    }

    public boolean existsById(int id) {
        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM livros WHERE id = ?",
                Integer.class, id);
        return count != null && count > 0;
    }

    public void deleteById(int id) {
        jdbcTemplate.update("DELETE FROM livros WHERE id = ?", id);
    }

    public int count() {
        Integer total = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM livros", Integer.class);
        return total != null ? total : 0;
    }
}
