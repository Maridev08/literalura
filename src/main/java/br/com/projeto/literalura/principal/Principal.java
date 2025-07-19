package br.com.projeto.literalura.principal;

import br.com.projeto.literalura.model.*;
import br.com.projeto.literalura.repository.AutorRepository;
import br.com.projeto.literalura.repository.LivroRepository;
import br.com.projeto.literalura.service.ConsumoApi;
import br.com.projeto.literalura.service.ConverteDados;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {

   Scanner leitor = new Scanner(System.in);

   ConsumoApi consumo = new ConsumoApi();

   ConverteDados conversor = new ConverteDados();

   private final String ENDERECO = "https://gutendex.com/books/?search=";

   private LivroRepository livroRepository;
   private AutorRepository autorRepository;

   public Principal(LivroRepository livroRepository,AutorRepository autorRepository){
      this.livroRepository=livroRepository;
      this.autorRepository=autorRepository;
   }

   public void exibeMenu() {
      var opcao = -1;

      while (opcao != 0) {

         System.out.println("""
                 --------LITERALURA--------
                 Escolha um número de sua opção :
                 
                 1-buscar livro pelo título
                 2-listar livros registrados
                 3-listar autores registrados
                 4-listar autores vivos em um determinado ano
                 5-listar livros em um determinado idioma 
                 6-Top 5 livros mais baixados
                 0-sair
                 ------------------------------
                 """
         );

         opcao = leitor.nextInt();


         switch (opcao) {
            case 1:
                buscarLivroPorTitulo();
               break;
            case 2:
               listarLivrosCadastrados();
               break;
            case 3:
               listarAutoresCadastrados();
               break;
            case 4:
               listarAutoresVivosDeterminadoAno();
               break;
            case 5:
               listarLivrosPorIdioma();
               break;
            case 6:
               listarTop5LivrosBaixados();
               break;
            case 0:
               System.out.println("Encerrando a aplicação !");
               break;
            default:
               System.out.println("Opção inválida !");

         }

      }

   }




   private void buscarLivroPorTitulo() {

      DadosLivro dadosLivro=obterDadosApi();

      if (dadosLivro!=null){

         DadosAutor dadosAutor=dadosLivro.autor().get(0);

         Optional<Autor>autorExistente=autorRepository.findByNome(dadosAutor.nome());
          Autor autor;
            if (autorExistente.isPresent()){
                autor=autorExistente.get();
               System.out.println("O autor "+autor.getNome()+"já está cadastrado");
            }else{
               autor=new Autor(dadosAutor);
               autorRepository.save(autor);
               System.out.println("Novo autor salvo:"+autor.getNome());
            }
             Livro livro=new Livro(dadosLivro,autor);
            Optional<Livro>livroExistente=livroRepository.findByTitulo(livro.getTitulo());
           if (livroExistente.isPresent()){
              System.out.println("O livro "+livro.getTitulo()+" já existe no banco de dados");

           }else {
              livroRepository.save(livro);
              System.out.println("Livro salvo com sucesso !");

              System.out.println(livro);
           }

      }else {
         System.out.println("Não foi possivel obter os dados do livro ");
      }

   }


   private DadosLivro obterDadosApi() {
      leitor.nextLine();


      System.out.println("Digite o nome do livro para busca :");
      var nomeLivro = leitor.nextLine();

      var json = consumo.obterDadosApi(ENDERECO + nomeLivro.replace(" ", "+"));
      var converteDadosApi = conversor.obterDados(json, DadosGutendex.class);



      if (converteDadosApi != null && !converteDadosApi.resultados().isEmpty()) {
         return converteDadosApi.resultados().get(0);
      } else {
         System.out.println("Não encontramos o livro ");
         return null;
      }

   }

   private void listarLivrosCadastrados() {
      Livro livro;
      List<Livro>livrosCadastrados=livroRepository.findAll();
              livrosCadastrados.stream()
                      .sorted(Comparator.comparing(Livro::getTitulo))
                      .forEach(System.out::println);

   }


   private void listarAutoresCadastrados() {

      System.out.println("--------AUTORES----------");
      List<Autor>autoresCadastrados=autorRepository.findAll();
         autoresCadastrados.stream()
                         .collect(Collectors.toList())
                                 .forEach(System.out::println);

      System.out.println("--------------------------");
   }



   private void listarAutoresVivosDeterminadoAno() {

    System.out.println("Insira o ano que deseja pesquisar:");
      var ano=leitor.nextInt();
       leitor.nextLine();
      List<Autor>buscaAutores=autorRepository.buscarAutoresVivosPorAno(ano);

           if (!buscaAutores.isEmpty()){
              System.out.println("------LISTA DE AUTORES VIVOS EM DETERMINADO ANO------");
                 buscaAutores.forEach(System.out::println);
           }else{
              System.out.println("Não encontramos nenhum autor que corresponda a sua pesquiça ");
           }
   }

   private void listarLivrosPorIdioma() {
      leitor.nextLine();
      System.out.println("Insira o idioma para realizar a busca:");
      System.out.println("""
              es-espanhol
              en-inglês
              fr-francês
              pt-português
              """);
      var idioma=leitor.nextLine();

      List<Livro>livros=livroRepository.findByIdiomaContainingIgnoreCase(idioma);

      if (!livros.isEmpty()) {
         livros.forEach(System.out::println);
      }else {
         System.out.println("Não existe livros cadastrados nesse idioma");
      }
   }


   private void listarTop5LivrosBaixados() {
      List<Livro>top5LivrosBaixados=livroRepository. findTop5ByOrderByDowloadsDesc();
     top5LivrosBaixados.forEach(l->
              System.out.println("Livro: "+l.getTitulo()+", Dowloads: "+l.getDowloads()));
   }



}

