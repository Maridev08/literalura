package br.com.projeto.literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer anoNascimento;
    private Integer anoFalecimento;
   @OneToMany(mappedBy = "autor",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Livro>livros;

    public Autor() {
    }

    public Autor(DadosAutor dados) {
        this.nome= dados.nome();
        this.anoNascimento=dados.anoNascimento();
        this.anoFalecimento=dados.anoFalecimento();
        this.livros=new ArrayList<>();
    }

    public void adicionaLivros(Livro livro){
        if ((livro !=null)){
            this.livros.add(livro);
            livro.setAutor(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public Integer getAnoFalecimento() {
        return anoFalecimento;
    }

    public void setAnoFalecimento(Integer anoFalescimento) {
        this.anoFalecimento = anoFalecimento;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    @Override
    public String toString(){
        StringBuilder livrosNomes=new StringBuilder();
        for(Livro livro:livros){
            livrosNomes.append(livro.getTitulo()).append(",");
        }

        if (!livrosNomes.isEmpty()){
            livrosNomes.setLength(livrosNomes.length()-1);
        }

        return "\nAutor: "+nome+"\n"+
                "Ano de Nascimento: "+anoNascimento+"\n"+
                "Ano de Falecimento: "+anoFalecimento+"\n"+
                "Livros: "+livrosNomes.toString()+"\n";


    }
}
