package model;

public class Ingrediente {
	
	private String nomeIngrediente;
	private int codIngrediente;
	
	
	
	public String getNomeIngrediente() {
		return nomeIngrediente;
	}
	public void setNomeIngrediente(String nomeIngrediente) {
		this.nomeIngrediente = nomeIngrediente;
	}
	public int getCodIngrediente() {
		return codIngrediente;
	}
	public void setCodIngrediente(int codIngrediente) {
		this.codIngrediente = codIngrediente;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getNomeIngrediente();
	}
	
}
