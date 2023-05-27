package biblioteca;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca implements Serializable {

    private List<Livros> livros = new ArrayList<Livros>();
    private List<Usuarios> usuarios = new ArrayList<Usuarios>();
    private List<Emprestimos> emprestimos = new ArrayList<Emprestimos>();
    private String dataEmprestimo, dataDevolucao;

    public Biblioteca() {
    }

    public Biblioteca(List<Livros> livros, List<Usuarios> usuarios, List<Emprestimos> emprestimos,
            String dataEmprestimo, String dataDevolucao) {
        this.livros = livros;
        this.usuarios = usuarios;
        this.emprestimos = emprestimos;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    public void cadastrarLivros(Livros livro) throws Exception {
        try {
            for (Livros l : livros) {
                if (l.getId() == livro.getId()) {
                    throw new Exception("Já existe um livro com o ID que você deseja usar, tente novamente");
                }
            }
            System.out.println("Livro cadastrado com sucesso");
            this.livros.add(livro);
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar livro: " + e.getMessage());
        }
    }

    public void cadastrarUsuario(Usuarios usuario) throws Exception {
        try {
            for (Usuarios u : usuarios) {
                if (u.getId() == usuario.getId()) {
                    throw new Exception("Já existe um usuário com o ID que você deseja usar, tente novamente");
                }
            }
            System.out.println("Usuário cadastrado com sucesso");
            this.usuarios.add(usuario);
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }

    public void mostrarTodosEmprestimos() {
        System.out.println("Empréstimo:");
        for (Emprestimos emprestimo : emprestimos) {
            System.out.println("\n-------Usuário-------");
            System.out.println("ID: " + emprestimo.getUsuario().getId());
            System.out.println("Nome: " + emprestimo.getUsuario().getNome());
            System.out.println("Endereço: " + emprestimo.getUsuario().getEndereco());
            System.out.println("CPF: " + emprestimo.getUsuario().getCpf());
            System.out.println("Email: " + emprestimo.getUsuario().getEmail());
            System.out.println("Telefone: " + emprestimo.getUsuario().getTelefone());
            System.out.println("-------Livros-------");
            for (Livros livro : emprestimo.getLivros()) {
                System.out.println("ID: " + livro.getId() + " | Nome: " + livro.getNome() + " | Autor: "
                        + livro.getAutor() + " | Gênero: " + livro.getGenero() + " | Data de Publicação: "
                        + livro.getDataPublicacao());
            }
            System.out.println("\nData Atual do Empréstimo: " + emprestimo.getDataAtualEmprestimo());
            System.out.println("Data de Devolução: " + emprestimo.getDataDevolucao());
            System.out.println();
        }
        System.out.println("-----------------------------------------");
    }

    public void mostrarTodosUsuarios() {
        for (Usuarios u : usuarios) {
            System.out.println(u.toString() + "\n");
        }
    }

    public void mostrarTodosLivros() {
        for (Livros l : livros) {
            System.out.println(l.toString() + "\n");
        }
    }

    public String pesquisarLivros(String termoBusca) throws Exception {
        List<Livros> livrosEncontrados = new ArrayList<>();
    
        try {
            for (Livros l : livros) {
                String nome = l.getNome().toLowerCase();
                String autor = l.getAutor().toLowerCase();
                String genero = l.getGenero().toLowerCase();
    
                if (nome.contains(termoBusca.toLowerCase()) || autor.contains(termoBusca.toLowerCase())
                        || genero.contains(termoBusca.toLowerCase())) {
                    livrosEncontrados.add(l);
                }
            }
    
            if (livrosEncontrados.isEmpty()) {
                return "Nenhum livro encontrado com base nas informações fornecidas.";
            } else {
                String resultado = "Livros encontrados:\n\n";
                for (Livros livro : livrosEncontrados) {
                    resultado += "ID: " + livro.getId() + " | Nome: " + livro.getNome() + " | Autor: " + livro.getAutor() + " | Gênero: " + livro.getGenero() + 
                    " | Data de Publicação: " + livro.getDataPublicacao() + " | ISBN: " + livro.getISNB() + " | Número de Páginas: " + livro.getNumeroPaginas() +
                    " | Quantidade Disponível: " + livro.getQntdDisponivel() + "\n";
                }
                return resultado;
            }
        } catch (Exception e) {
            return "Ocorreu um erro durante a pesquisa de livros: " + e.getMessage();
        }
    }

    public void emprestarLivros(int id, List<Livros> livros) throws Exception {
        try {
            List<Livros> carrinho = new ArrayList<>();
            boolean todosLivrosEncontrados = true;
            boolean usuarioEncontrado = false;

            for (Usuarios u : usuarios) {
                if (u.getId() == id) {
                    usuarioEncontrado = true;
                    for (Livros livro : livros) {
                        boolean livroEncontrado = false;

                        for (Livros l : this.livros) {
                            if (l.getNome().equals(livro.getNome()) && l.getQntdDisponivel() > 0) {
                                l.setQntdDisponivel(l.getQntdDisponivel() - 1);
                                carrinho.add(l);
                                livroEncontrado = true;
                                break;
                            }
                        }

                        if (!livroEncontrado) {
                            todosLivrosEncontrados = false;
                            break;
                        }
                    }

                    if (todosLivrosEncontrados) {
                        emprestimos.add(new Emprestimos(u, carrinho, LocalDate.now(), LocalDate.now().plusDays(3)));
                        System.out.println("Livros cadastrados com sucesso!");
                    } else {
                        System.out.println("Um ou mais livros não foram encontrados.");
                    }

                    break;
                }
            }

            if (!usuarioEncontrado) {
                System.out.println("ID do usuário não encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro durante o empréstimo de livros: " + e.getMessage());
        }
    }

    public void realizarDevolucao(int idUsuario) throws Exception {
        try {
            boolean emprestimoEncontrado = false;
            List<Emprestimos> emprestimosRemover = new ArrayList<>();

            for (Emprestimos emprestimo : emprestimos) {
                if (emprestimo.getUsuario().getId() == idUsuario) {
                    emprestimoEncontrado = true;

                    LocalDate dataDevolucao = LocalDate.now();
                    if (dataDevolucao.isAfter(emprestimo.getDataDevolucao())) {
                        System.out.println("Empréstimo atrasado! Serão aplicadas penalidades.");
                    }

                    for (Livros livro : emprestimo.getLivros()) {
                        livro.setQntdDisponivel(livro.getQntdDisponivel() + 1);
                    }

                    emprestimosRemover.add(emprestimo);
                    System.out.println("Devolução realizada com sucesso.");
                }
            }

            if (emprestimoEncontrado) {
                emprestimos.removeAll(emprestimosRemover);
            } else {
                System.out.println("Usuário não possui empréstimos ativos.");
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro durante a devolução: " + e.getMessage());
        }
    }

    public String getQuantidadeLivrosDisponiveis() {
        int quantidade = 0;
        for (Livros livro : livros) {
            if (livro.getQntdDisponivel() > 0) {
                quantidade += livro.getQntdDisponivel();
            }
        }

        if (quantidade == 0) {
            return "\nNão há livros disponíveis no momento.";
        } else if (quantidade == 1) {
            return "\nHá 1 livro disponível para empréstimo.";
        } else {
            return "\nHá " + quantidade + " livros disponíveis para empréstimo.";
        }
    }

    public void relatorioLivrosEmprestados() {
        System.out.println("\nRelatório de Livros Emprestados:\n");

        for (Emprestimos emprestimo : emprestimos) {
            System.out.println("Usuário: " + emprestimo.getUsuario().getNome());
            System.out.println("Livros emprestados:");
            for (Livros livro : emprestimo.getLivros()) {
                System.out.println("- " + livro.getNome());
            }
            System.out.println("Data de Empréstimo: " + emprestimo.getDataAtualEmprestimo());
            System.out.println("Data de Devolução: " + emprestimo.getDataDevolucao());
            System.out.println("-----------------------");
        }
    }

    public void relatorioUsuariosComEmprestimos() {
        System.out.println("\nRelatório de Usuários com Empréstimos:\n");

        for (Usuarios usuario : usuarios) {
            boolean possuiEmprestimo = false;
            for (Emprestimos emprestimo : emprestimos) {
                if (emprestimo.getUsuario().getId() == usuario.getId()) {
                    possuiEmprestimo = true;
                    break;
                }
            }

            if (possuiEmprestimo) {
                System.out.println("Usuário: " + usuario.getNome());
                System.out.println("ID do Usuário: " + usuario.getId());
                System.out.println("-----------------------");
            }
        }
    }

    public void relatorioLivrosPorGenero() {
        System.out.println("\nRelatório de Livros por Gênero:\n");

        List<String> generosRegistrados = new ArrayList<>();

        for (Livros livro : livros) {
            String genero = livro.getGenero();
            if (!generosRegistrados.contains(genero)) {
                generosRegistrados.add(genero);
            }
        }

        for (String genero : generosRegistrados) {
            System.out.println("Gênero: " + genero);
            System.out.println("Livros:");
            for (Livros livro : livros) {
                if (livro.getGenero().equals(genero)) {
                    System.out.println("- " + livro.getNome());
                }
            }
            System.out.println("-----------------------");
        }
    }

    public void relatorioLivrosAtrasados() {
        System.out.println("\nRelatório de Livros Atrasados:\n");

        LocalDate dataAtual = LocalDate.now();

        for (Emprestimos emprestimo : emprestimos) {
            if (dataAtual.isAfter(emprestimo.getDataDevolucao())) {
                System.out.println("Usuário: " + emprestimo.getUsuario().getNome());
                System.out.println("Livros em atraso:");
                for (Livros livro : emprestimo.getLivros()) {
                    System.out.println("- " + livro.getNome());
                }
                System.out.println("Data de Empréstimo: " + emprestimo.getDataAtualEmprestimo());
                System.out.println("Data de Devolução: " + emprestimo.getDataDevolucao());
                System.out.println("-----------------------");
            }
        }
    }

    public List<Emprestimos> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(List<Emprestimos> emprestimos) {
        this.emprestimos = emprestimos;
    }

    public List<Livros> getLivros() {
        return livros;
    }

    public void setLivros(List<Livros> livros) {
        this.livros = livros;
    }

    public List<Usuarios> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuarios> usuarios) {
        this.usuarios = usuarios;
    }

    public String getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(String dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public String getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(String dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    @Override
    public String toString() {
        return "Biblioteca:\n" +
                "Livros:\n" + livros +
                "Usuários:\n" + usuarios +
                "Data de Empréstimo: " + dataEmprestimo + "\n" +
                "Data de Devolução: " + dataDevolucao + "\n";
    }

}
