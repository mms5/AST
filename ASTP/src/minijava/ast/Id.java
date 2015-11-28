package minijava.ast;

import minijava.TabSimb;

public class Id implements Exp {
	public String nome;
	public int lin;
	
	public Id(String _nome, int _lin) {
		nome = _nome;
		lin = _lin;
	}

	public String toString() {
		return nome;
	}

	@Override
	public String tipo(Classe self, TabSimb<Classe> classes,
			TabSimb<String> vars) {
		String tid = vars.procurar(nome);
		if(tid == null)
			throw new RuntimeException("variavel " + nome + " nao declarada na linha " + lin);
		return tid;
	}
}
