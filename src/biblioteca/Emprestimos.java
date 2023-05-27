package biblioteca;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Emprestimos implements Serializable {
    private Usuarios usuario;
    private List<Livros> livros;
    private LocalDate dataAtualEmprestimo = LocalDate.now();
    private LocalDate dataDevolucao;

    public Emprestimos(Usuarios usuario, List<Livros> livros, LocalDate dataAtualEmprestimo, LocalDate dataDevolucao) {
        this.usuario = usuario;
        this.livros = livros;
        this.dataAtualEmprestimo = dataAtualEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public List<Livros> getLivros() {
        return livros;
    }

    public void setLivros(List<Livros> livros) {
        this.livros = livros;
    }

    public LocalDate getDataAtualEmprestimo() {
        return dataAtualEmprestimo;
    }

    public void setDataAtualEmprestimo(LocalDate dataAtualEmprestimo) {
        this.dataAtualEmprestimo = dataAtualEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    @Override
    public String toString() {
        return String.format("Empréstimo:\n" +
                "Usuário: %s\n" +
                "Livros: %s\n" +
                "Data Atual do Empréstimo: %s\n" +
                "Data de Devolução: %s\n",
                usuario, livros, dataAtualEmprestimo, dataDevolucao);
    }

}
