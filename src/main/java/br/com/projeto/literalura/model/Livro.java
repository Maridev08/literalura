package br.com.projeto.literalura.model;

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "livros")
public class Livro {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
  @Column(unique = true)
  private String titulo;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "autor_id",nullable = false)
  private Autor autor;
  private String idioma;
  private Integer dowloads;

  public Livro(){}

  public Livro(DadosLivro dados,Autor autor) {
    this.titulo=dados.titulo();
    this.autor=autor;
    this.idioma=dados.idioma().get(0);
    this.dowloads=dados.dowloads();

  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public Autor getAutor() {
    return autor;
  }

  public void setAutor(Autor autor) {
    this.autor = autor;
  }

  public String getIdioma() {
    return idioma;
  }

  public void setIdioma(String idioma) {
    this.idioma = idioma;
  }

  public Integer getDowloads() {
    return dowloads;
  }

  public void setDowloads(Integer dowloads) {
    this.dowloads = dowloads;
  }


  @Override
  public String toString(){
    return "----------LIVRO-----------"+"\n"+
            "Titulo: "+titulo+"\n"+
             "Autor: "+autor.getNome()+"\n"+
            "Idioma: "+idioma+"\n"+
            "Dowloads:"+dowloads+"\n"+
            "----------------------------"+"\n";

  }
}
