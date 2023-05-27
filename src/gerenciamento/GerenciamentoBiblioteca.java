package gerenciamento;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class GerenciamentoBiblioteca implements Serializable{
    protected int id;
    protected String nome;
    protected LocalDate dataCadastro = LocalDate.now();
    
    public GerenciamentoBiblioteca(int id, String nome, LocalDate dataCadastro) {
        this.id = id;
        this.nome = nome;
        this.dataCadastro = dataCadastro;
    }

    public GerenciamentoBiblioteca(String nome){
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    @Override
    public abstract String toString();
    
}
