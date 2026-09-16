package a.c.execLivro;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Autor {
    @Id
    private long id;
    private String nome;
    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    private List<Livro> livros = new ArrayList<>();

    public Autor() {
    }

    public Autor(long id, String nome, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    @Override
    public String toString() {
        return "Autor [id=" + id +
                ", nome=" + nome +
                ", dataNascimento=" + dataNascimento + "]";
    }
}
