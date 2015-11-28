package minijava.ast;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import minijava.TabSimb;

public class Prog {
	public String main;
	public String args;
	public Cmd cmd;
	public List<Classe> classes;
	
	public final static Set<String> predefs = new HashSet<String>();
	
	{
		predefs.add("Object");
		predefs.add("Integer");
	}
	
	public Prog(String _main, String _args, Cmd _cmd, List<Classe> _classes) {
		main = _main;
		args = _args;
		cmd = _cmd;
		classes = _classes;
	}

	public String toString() {
		String res = "class " + main + " {\n  public static void main(String[] " + args +
				") {\n" + cmd + "\n  }\n}\n";
		for(Classe classe: classes)
			res += classe + "\n";
		return res;
	}
	
	public void tipos() {
		TabSimb<Classe> cls = new TabSimb<Classe>();
		for(Classe classe: classes) {
			if(predefs.contains(classe.nome) ||  main.equals(classe.nome) || !cls.inserir(classe.nome, classe))
				throw new RuntimeException("classe redefinida " + classe.nome + " na linha " + classe.lin);
		}
		for(Classe classe: classes) {
			classe.adicionaCampos(cls);
			classe.adicionaMetodos(cls);
		}
		for(Classe classe: classes) {
			classe.tipos(cls);
		}
		cmd.tipos(null, cls, new TabSimb<String>());
	}
}
