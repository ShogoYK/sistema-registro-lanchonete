package model;

public class Lanche {
	
    private String nomeLanche;
    private Double preco;
    private Integer codLanche;

    public Lanche() {
    }

    public Lanche(String nome, Double preco, int codLanche) {
        this.nomeLanche = nome;
        this.preco = preco;
        this.codLanche = codLanche;
    }

    public String getNomeLanche() {
        return nomeLanche;
    }

    public void setNomeLanche(String nome) {
        this.nomeLanche = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(double d) {
        this.preco = d;
    }

    public int getCodLanche() {
        return codLanche;
    }

    public void setCodLanche(int codLanche) {
        this.codLanche = codLanche;
    }
    
    @Override
    public String toString() {
        return nomeLanche;
    }
}
