package br.com.trabalho.silvana.e.applivrariav;

public class LivrariaV {


    public static final int ANO_MINIMO = 2000;

    public int id;
    public String nome, autor;
    private int ano;


    public LivrariaV() {

    }

    public LivrariaV(String nome,String autor, int ano) {
        this.nome = nome;
        this.autor = autor;
        this.setAno(ano);
    }

    public LivrariaV(int id, String nome,String autor, int ano) {
        this.id = id;
        this.nome = nome;
        this.autor = autor;
        this.setAno(ano);
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        if(ano >= ANO_MINIMO)
            this.ano = ano;
    }

    @Override
    public String toString() {
        return id + "-" + nome + autor + " | " + ano;
    }
}
