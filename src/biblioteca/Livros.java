package biblioteca;

import java.io.Serializable;
import java.time.LocalDate;

import gerenciamento.GerenciamentoBiblioteca;

public class Livros extends GerenciamentoBiblioteca implements Serializable {
    private String autor, genero, dataPublicacao;
    private int ISNB, numeroPaginas, qntdDisponivel;

    public Livros(int id, String nome, LocalDate dataCadastro, String autor, String genero, String dataPublicacao,
            int iSNB, int numeroPaginas, int qntdDisponivel) {
        super(id, nome, dataCadastro);
        this.autor = autor;
        this.genero = genero;
        this.dataPublicacao = dataPublicacao;
        ISNB = iSNB;
        this.numeroPaginas = numeroPaginas;
        this.qntdDisponivel = qntdDisponivel;
    }

    public Livros(String nome) {
        super(nome);
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(String dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public int getISNB() {
        return ISNB;
    }

    public void setISNB(int iSNB) {
        ISNB = iSNB;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public int getQntdDisponivel() {
        return qntdDisponivel;
    }

    public void setQntdDisponivel(int qntdDisponivel) {
        this.qntdDisponivel = qntdDisponivel;
    }

    @Override
    public String toString() {
        return String.format("Livro:\n" +
                "ID: %d | " + "Nome: %s | " + "Autor: %s | " + "Gênero: %s | " + "Data de Publicação: %s | "
                + "ISBN: %s | " + "Número de Páginas: %d | " + "Quantidade Disponível: %d",
                id, nome, autor, genero, dataPublicacao, ISNB, numeroPaginas, qntdDisponivel);
    }

}
