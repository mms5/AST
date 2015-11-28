package minijava.ast;

import minijava.TabSimb;

public class Tipo {
	public static void compativel(TabSimb<Classe> classes, String t1, String t2, int linha) {
		if(t1.equals(t2))
			return;
		Classe c1 = classes.procurar(t1);
		if(c1 != null) {
			if(c1.subClasseDe(classes, t2))
				return;
		}
		throw new RuntimeException("tipo " + t1 + " incompativel com " + t2 + " na linha " + linha);
	}
}
