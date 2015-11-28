package minijava.ast;

import java.util.List;

import minijava.TabSimb;

public class Classe {
	public String nome;
	public String pai;
	public List<Var> campos;
	public List<Metodo> metodos;
	public int lin;
	
	// Tipos de todos os campos, incluindo campos nas superclasses
	public TabSimb<String> todosCampos;
	// Todos os metodos, incluindo metodos nas superclasses
	public TabSimb<Metodo> todosMetodos;
	
	public Classe(String _nome, List<Var> _campos, List<Metodo> _metodos, int _lin) {
		nome = _nome;
		pai = "Object";
		campos = _campos;
		metodos = _metodos;
		lin = _lin;
	}

	public Classe(String _nome, String _pai, List<Var> _campos, List<Metodo> _metodos, int _lin) {
		nome = _nome;
		pai = _pai;
		campos = _campos;
		metodos = _metodos;
		lin = _lin;
	}

	public String toString() {
		String res = "class " + nome + " extends " + pai + "{\n";
		for(Var campo: campos) {
			res += campo + ";\n";
		}
		for(Metodo metodo: metodos) {
			res += metodo + "\n";
		}
		res += "}";
		return res;
	}
	
	
	public void adicionaCampos(TabSimb<Classe> classes, TabSimb<String> vars) {
		
		
	}
	
	public void adicionaCampos(TabSimb<Classe> classes) {
		todosCampos = new TabSimb<String>();
		this.adicionaCampos(classes, todosCampos);
	}
	
	public void adicionaMetodos(TabSimb<Classe> classes) {
		Classe cpai = classes.procurar(pai);
		if(cpai != null) {
			cpai.adicionaMetodos(classes);
			todosMetodos = new TabSimb<Metodo>(cpai.todosMetodos);
		} else todosMetodos = new TabSimb<Metodo>();
		for(Metodo metodo: metodos) {
			Metodo mpai = todosMetodos.procurar(metodo.nome);
			if(mpai != null) {
				if(mpai.params.size() != metodo.params.size())
					throw new RuntimeException("metodo " + metodo.nome + 
							" redefinido com aridade diferente na linha " + metodo.lin);
				try {
					for(int i = 0; i < mpai.params.size(); i++) {
						Tipo.compativel(classes, mpai.params.get(i).tipo, metodo.params.get(i).tipo, metodo.lin);
					}
					Tipo.compativel(classes, metodo.tret, mpai.tret, metodo.lin);
				} catch(RuntimeException e) {
					throw new RuntimeException("erro na redefinicao de metodo: " + e.getMessage());
				}
			}
			if(!todosMetodos.inserir(metodo.nome, metodo))
				throw new RuntimeException("metodo " + metodo.nome + " redeclarado na linha " + metodo.lin);
		}
	}
	
	public void tipos(TabSimb<Classe> classes) {
		for(Metodo metodo: metodos) {
			TabSimb<String> escMetodo = new TabSimb<String>(todosCampos);
			for(Var param: metodo.params) {
				if(!escMetodo.inserir(param.nome, param.tipo))
					throw new RuntimeException("parametro " + param.nome + " redeclarado na linha " + param.lin);
			}
			for(Var var: metodo.vars) {
				if(!escMetodo.inserir(var.nome, var.tipo))
					throw new RuntimeException("variavel " + var.nome + 
							" redeclarada na linha " + var.lin);
			}
			for(Cmd cmd: metodo.cmds) {
				cmd.tipos(this, classes, escMetodo);
			}
			String tret = metodo.ret.tipo(this, classes, escMetodo);
			Tipo.compativel(classes, tret, metodo.tret, metodo.lin);
		}
	}

	public boolean subClasseDe(TabSimb<Classe> classes, String t2) {
		// TODO Auto-generated method stub
		return true;
	}

}
