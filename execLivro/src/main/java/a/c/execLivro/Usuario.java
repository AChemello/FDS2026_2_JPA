package a.c.execLivro;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;
import java.util.ArrayList;

@Entity
public class Usuario {
    @Id
    private long id;
    private String nome;

    @ManyToMany
    @JoinTable(name = "usuario_livros_lidos", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "livro_id"))
    @JsonIgnoreProperties({ "autor" })
    private List<Livro> livrosLidos = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) { // ✅
        this.nome = nome;
    }

    public List<Livro> getLivrosLidos() {
        return livrosLidos;
    }

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", nome=" + nome + "]";
    }
}
