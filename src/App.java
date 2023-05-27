import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import biblioteca.Biblioteca;
import biblioteca.Livros;
import biblioteca.Usuarios;
import gerenciamento.Salvar;

public class App {
    public static void main(String[] args) throws Exception {
        Biblioteca biblioteca = (Biblioteca) Salvar.lerArquivo();
        if (biblioteca == null) {
            biblioteca = new Biblioteca();
        }
        Scanner scan = new Scanner(System.in);
        boolean menu = true;

        System.out.println("Bem vindo a nossa biblioteca");
        while (menu) {
            System.out.println("\nEscolha o que deseja fazer\n" +
                    "1 - Cadastrar Livros\n" +
                    "2 - Pesquisar Livros\n" +
                    "3 - Mostrar Todos os Livros\n" +
                    "4 - Cadastrar Usuário\n" +
                    "5 - Mostrar Usuários\n" +
                    "6 - Emprestar Livros\n" +
                    "7 - Devolver Livros\n" +
                    "8 - Mostrar Emprestimos\n" +
                    "9 - Relatórios\n" +
                    "0 - Sair");
            int op = scan.nextInt();
            scan.nextLine();
            switch (op) {
                case 0:
                    Salvar.salvarArquivo(biblioteca);
                    System.out.println("\nDados da biblioteca salvos com sucesso!");
                    menu = false;
                    break;
                case 1:
                    System.out.println("\nDigite um id para o livro");
                    int idLivro = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Digite o nome do Livro");
                    String nomeLivro = scan.nextLine();
                    System.out.println("Digite o nome do autor dor livro");
                    String autor = scan.nextLine();
                    System.out.println("Digite o genero do Livro");
                    String genero = scan.nextLine();
                    System.out.println("Digite a data de publicação do livro");
                    String dataPublicacao = scan.nextLine();
                    System.out.println("Informe o ISBN do livro");
                    int isbn = scan.nextInt();
                    System.out.println("Informe o numero de paginas do livro");
                    int numPaginas = scan.nextInt();
                    System.out.println("Quantos exemplares há disponiveis");
                    int exemplares = scan.nextInt();
                    biblioteca.cadastrarLivros(new Livros(idLivro, nomeLivro, LocalDate.now(), autor, genero,
                            dataPublicacao, isbn, numPaginas, exemplares));
                    break;
                case 2:
                    System.out.println("\nDigite o termo de busca:");
                    String termoBusca = scan.nextLine();
                    String resultadoPesquisa = biblioteca.pesquisarLivros(termoBusca);
                    System.out.println(resultadoPesquisa);
                    break;
                case 3:
                    biblioteca.mostrarTodosLivros();
                    break;
                case 4:
                    System.out.println("\nDigite um Id pra você");
                    int idUser = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Digite seu nome");
                    String nome = scan.nextLine();
                    System.out.println("Digite seu endereço");
                    String endereco = scan.nextLine();
                    System.out.println("Digite seu cpf");
                    String cpf = scan.nextLine();
                    System.out.println("Digite seu email");
                    String email = scan.nextLine();
                    System.out.println("Digite seu telefone (Somente numeros)");
                    int tell = scan.nextInt();
                    biblioteca
                            .cadastrarUsuario(new Usuarios(idUser, nome, LocalDate.now(), endereco, cpf, email, tell));
                    break;
                case 5:
                    biblioteca.mostrarTodosUsuarios();
                    break;
                case 6:
                    System.out.println("\nDigite o ID do usuário:");
                    int idUsuario = scan.nextInt();
                    scan.nextLine(); // Consumir a quebra de linha pendente

                    System.out.println("Digite o número de livros a serem emprestados:");
                    int numeroLivros = scan.nextInt();
                    scan.nextLine(); // Consumir a quebra de linha pendente

                    List<Livros> livrosEmprestimo = new ArrayList<>();
                    for (int i = 0; i < numeroLivros; i++) {
                        System.out.println("Digite o nome do livro " + (i + 1) + ":");
                        String nomeLivro2 = scan.nextLine();
                        livrosEmprestimo.add(new Livros(nomeLivro2));
                    }

                    biblioteca.emprestarLivros(idUsuario, livrosEmprestimo);
                    break;
                case 7:
                    System.out.println("Digite o ID do usuário:");
                    int idUsuarioDevolucao = scan.nextInt();
                    biblioteca.realizarDevolucao(idUsuarioDevolucao);
                    break;
                case 8:
                    biblioteca.mostrarTodosEmprestimos();
                    break;
                case 9:
                    Boolean menu2 = true;
                    while (menu2) {
                        System.out.println("\nEscolha o relatório que deseja vizualizar\n" +
                                "1 - Quantidade de Livros Disponiveis\n" +
                                "2 - Livros Emprestados\n" +
                                "3 - Usuários com Emprestimo\n" +
                                "4 - Livros por Genero\n" +
                                "5 - Livros Atrasados\n" +
                                "0 - Voltar");
                        int op2 = scan.nextInt();
                        switch (op2) {
                            case 1:
                                System.out.println(biblioteca.getQuantidadeLivrosDisponiveis());
                                break;
                            case 2:
                                biblioteca.relatorioLivrosEmprestados();
                                break;
                            case 3:
                                biblioteca.relatorioUsuariosComEmprestimos();
                                break;
                            case 4:
                                biblioteca.relatorioLivrosPorGenero();
                                break;
                            case 5:
                                biblioteca.relatorioLivrosAtrasados();
                                break;
                            case 0:
                                menu2 = false;
                                break;
                        }
                    }
                    break;
            }
        }
    }
}
