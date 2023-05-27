package biblioteca;

import java.io.Serializable;
import java.time.LocalDate;

import gerenciamento.GerenciamentoBiblioteca;

public class Usuarios extends GerenciamentoBiblioteca implements Serializable {

    private String endereco, cpf, email;
    private int telefone;

    public Usuarios(int id, String nome, LocalDate dataCadastro, String endereco, String cpf, String email,
            int telefone) {
        super(id, nome, dataCadastro);
        this.endereco = endereco;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String toString() {
        return String.format("Usuário:\n" + "ID: %d" + " | Nome: %s" + " | Endereço: %s" + " | CPF: %s" + " | Email: %s" + " | Telefone: %s",
                id, nome, endereco, cpf, email, telefone);
    }

}
