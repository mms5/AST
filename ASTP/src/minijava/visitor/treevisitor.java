package minijava.visitor;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


//implementacao do visitor numa arvore
//como dito em aula, visitando cada
//no da arvore e visitando suas referencias;

//a classe visitor recebe uma arvore
//a implementacao da arvore nao tem relevancia
//mas, para um visitor de arvore, serao
//necessarios metodos get e set para
//os nos (e as referencias right e left)

public class treevisitor {
	public static void main(String[] a) {
		System.out.println(new TV().Start());
	}
}

class TV {

	public int Start() {
		Tree root;
		boolean ntb;
		int nti;
		MyVisitor v;

		root = new Tree();
		ntb = root.Init(16);
		ntb = root.Print();
		System.out.println("----------------------");
		ntb = root.Insert(8);
		ntb = root.Insert(24);
		ntb = root.Insert(4);
		ntb = root.Insert(12);
		ntb = root.Insert(20);
		ntb = root.Insert(28);
		ntb = root.Insert(14);
		ntb = root.Print();
		System.out.println("----------------------");
		v = new MyVisitor();
		System.out.println("----------------------");
		nti = root.accept(v);
		System.out.println("----------------------");
		System.out.println(root.Search(24));
		System.out.println(root.Search(12));
		System.out.println(root.Search(16));
		System.out.println(root.Search(50));
		System.out.println(root.Search(12));
		ntb = root.Delete(12);
		ntb = root.Print();
		System.out.println(root.Search(12));

		return 0;
	}

}

class Tree {
	Tree left;
	Tree right;
	int key;
	boolean has_left;
	boolean has_right;
	Tree my_null;

	public boolean Init(int v_key) {
		key = v_key;
		has_left = false;
		has_right = false;
		return true;
	}

	public boolean SetRight(Tree rn) {
		right = rn;
		return true;
	}

	public boolean SetLeft(Tree ln) {
		left = ln;
		return true;
	}

	public Tree GetRight() {
		return right;
	}

	public Tree GetLeft() {
		return left;
	}

	public int GetKey() {
		return key;
	}

	public boolean SetKey(int v_key) {
		key = v_key;
		return true;
	}

	public boolean GetHas_Right() {
		return has_right;
	}

	public boolean GetHas_Left() {
		return has_left;
	}

	public boolean SetHas_Left(boolean val) {
		has_left = val;
		return true;
	}

	public boolean SetHas_Right(boolean val) {
		has_right = val;
		return true;
	}

	public boolean Compare(int num1, int num2) {
		boolean ntb;
		int nti;

		ntb = false;
		nti = num2 + 1;
		if (num1 < num2)
			ntb = false;
		else if (!(num1 < nti))
			ntb = false;
		else
			ntb = true;
		return ntb;
	}

	public boolean Insert(int v_key) {
		Tree new_node;
		boolean ntb;
		Tree current_node;
		boolean cont;
		int key_aux;

		new_node = new Tree();
		ntb = new_node.Init(v_key);
		current_node = this;
		cont = true;
		while (cont) {
			key_aux = current_node.GetKey();
			if (v_key < key_aux) {
				if (current_node.GetHas_Left())
					current_node = current_node.GetLeft();
				else {
					cont = false;
					ntb = current_node.SetHas_Left(true);
					ntb = current_node.SetLeft(new_node);
				}
			} else {
				if (current_node.GetHas_Right())
					current_node = current_node.GetRight();
				else {
					cont = false;
					ntb = current_node.SetHas_Right(true);
					ntb = current_node.SetRight(new_node);
				}
			}
		}
		return true;
	}

	public boolean Delete(int v_key) {
		Tree current_node;
		Tree parent_node;
		boolean cont;
		boolean found;
		boolean ntb;
		boolean is_root;
		int key_aux;

		current_node = this;
		parent_node = this;
		cont = true;
		found = false;
		is_root = true;
		while (cont) {
			key_aux = current_node.GetKey();
			if (v_key < key_aux)
				if (current_node.GetHas_Left()) {
					parent_node = current_node;
					current_node = current_node.GetLeft();
				} else
					cont = false;
			else if (key_aux < v_key)
				if (current_node.GetHas_Right()) {
					parent_node = current_node;
					current_node = current_node.GetRight();
				} else
					cont = false;
			else {
				if (is_root)
					if (!current_node.GetHas_Right() && !current_node.GetHas_Left())
						ntb = true;
					else
						ntb = this.Remove(parent_node, current_node);
				else
					ntb = this.Remove(parent_node, current_node);
				found = true;
				cont = false;
			}
			is_root = false;
		}
		return found;
	}

	public boolean Remove(Tree p_node, Tree c_node) {
		boolean ntb;
		int auxkey1;
		int auxkey2;

		if (c_node.GetHas_Left())
			ntb = this.RemoveLeft(p_node, c_node);
		else if (c_node.GetHas_Right())
			ntb = this.RemoveRight(p_node, c_node);
		else {
			auxkey1 = c_node.GetKey();
			auxkey2 = (p_node.GetLeft()).GetKey();
			if (this.Compare(auxkey1, auxkey2)) {
				ntb = p_node.SetLeft(my_null);
				ntb = p_node.SetHas_Left(false);
			} else {
				ntb = p_node.SetRight(my_null);
				ntb = p_node.SetHas_Right(false);
			}
		}
		return true;
	}

	public boolean RemoveRight(Tree p_node, Tree c_node) {
		boolean ntb;
		while (c_node.GetHas_Right()) {
			ntb = c_node.SetKey((c_node.GetRight()).GetKey());
			p_node = c_node;
			c_node = c_node.GetRight();
		}
		ntb = p_node.SetRight(my_null);
		ntb = p_node.SetHas_Right(false);
		return true;
	}

	public boolean RemoveLeft(Tree p_node, Tree c_node) {
		boolean ntb;
		while (c_node.GetHas_Left()) {
			ntb = c_node.SetKey((c_node.GetLeft()).GetKey());
			p_node = c_node;
			c_node = c_node.GetLeft();
		}
		ntb = p_node.SetLeft(my_null);
		ntb = p_node.SetHas_Left(false);
		return true;
	}

	public int Search(int v_key) {
		Tree current_node;
		int ifound;
		boolean cont;
		int key_aux;

		current_node = this;
		cont = true;
		ifound = 0;
		while (cont) {
			key_aux = current_node.GetKey();
			if (v_key < key_aux)
				if (current_node.GetHas_Left())
					current_node = current_node.GetLeft();
				else
					cont = false;
			else if (key_aux < v_key)
				if (current_node.GetHas_Right())
					current_node = current_node.GetRight();
				else
					cont = false;
			else {
				ifound = 1;
				cont = false;
			}
		}
		return ifound;
	}

	public boolean Print() {
		boolean ntb;
		Tree current_node;

		current_node = this;
		ntb = this.RecPrint(current_node);
		return true;
	}

	public boolean RecPrint(Tree node) {
		boolean ntb;

		if (node.GetHas_Left()) {
			ntb = this.RecPrint(node.GetLeft());
		} else
			ntb = true;
		System.out.println(node.GetKey());
		if (node.GetHas_Right()) {
			ntb = this.RecPrint(node.GetRight());
		} else
			ntb = true;
		return true;
	}

	public int accept(Visitor v) {
		int nti;

		System.out.println(333);
		nti = v.visit(this);
		return 0;
	}
}

public class ChecadorTipos {

    static class Program {
        Declaration dec;
        Expression ex;
        public Program(Declaration dec1, Expression ex1) {
            this.dec = dec1;
            this.ex = ex1;
        }
        void accept(Visitor vis) {
            vis.visit(this);
        }
    }
    
    static class Declaration {
        Id id;
        Type type;
        public Declaration(Id x, Type arg) {
            this.id = x;
            this.type = arg;
        }
        
        void accept(Visitor vis) {
            vis.visit(this);
        }
    }
    
    /************************/
    static class Type {
        
        void accept(Visitor vis) {
            vis.visit(this);
        }
    }
    
    static class CharType extends Type {}
    
    static class IntType extends Type {}

    static class ArrayType extends Type {
        int size;
        Type t;
        public ArrayType(int s, IntType intType) {
            this.size = s;
            this.t = intType;
        }
    }
    
    /************************/
    static class Expression {
        
        void accept(Visitor vis) {
            vis.visit(this);
        }
    }
    
    static class Literal extends Expression {
        char l;
        public Literal(char c) {
            this.l = c;
        }
    }
    
    static class Num extends Expression {
        int n;
        public Num(int num) {
            this.n = num;
        }
    }
    
    static class Id extends Expression {
        String name;
        public Id(String name) {
            this.name = name;
        }
        public String toString() {
            return name;
        }
    }
    
    static class Mod extends Expression {
        Expression e1, e2;
    }
    
    static class ArrayIndexing extends Expression {
        Expression e1, e2;
        public ArrayIndexing(Expression e3, Expression e4) {
            this.e1 = e3;
            this.e2 = e4;
        }
    }
    
    /*******************************/
    static interface Visitor {
        public void visit(Program p);
        public void visit(Expression e);
        public void visit(Type t);
        public void visit(Declaration d);
    }
    
    /*******************************/
    static class IdVisitor implements Visitor {
        Set<Id> ids = new HashSet<Id>();
        
        @Override
        public void visit(Program p) {
            p.d.accept(this);
            p.e.accept(this);
        }
        
        @Override
        public void visit(Expression e) {
            if (e instanceof Id) {
                ids.add((Id)e);
            }
        }
        
        @Override
        public void visit(Type t) { }
        
        @Override
        public void visit(Declaration d) {
            d.id.accept(this);
            d.type.accept(this);
        }
    }
	
    /*******************************/
    static class CheckTypesVisitor implements Visitor {
        
        // tipo de uma variavel (identificador)
        Map<Id, String> types = new HashMap<Id, String>();
        
        // tipo de uma expressao
        Map<Expression, String> typesExpression = new HashMap<Expression, String>();
	
        @Override
	public void visit(Program p) {
            p.d.accept(this);
            p.e.accept(this);
        }
        
        @Override
	public void visit(Expression e) {
            String type;
            if (e instanceof Id) {
                type = types.get((Id)e);
                if (type == null) {
                    throw new RuntimeException("TYPE ERROR!  Could not find type for " + e);
                }
            } else if (e instanceof Literal) {
                type = "char";
            } else if (e instanceof Num) {
                type = "int";
            } else if (e instanceof Mod) {
                //@TODO missing
                throw new UnsupportedOperationException("Please, implement this");
            } else if (e instanceof ArrayIndexing) {
                ArrayIndexing ai = (ArrayIndexing) e;
                
                ai.e1.accept(this);
                ai.e2.accept(this);
                
                // check expression is indeed array
                Expression exp1 = ai.e1;
                String type1 = typesExpression.get(exp1);
                if (!type1.startsWith("array")) {
                    throw new RuntimeException("TYPE ERROR! Expecting array, found " + type1);
                }
                
                // check indexing expression is integer
                Expression exp2 = ai.e2;
                String type2 = typesExpression.get(exp2);
                if (!type2.equals("int")) {
                    throw new RuntimeException("TYPE ERROR!");
                }
                // take the 3rd element of string "array:<number>:<type>"
                type = type1.split(":")[2];
            } else {
                throw new RuntimeException();
            }
            
            typesExpression.put(expression, type);
        }
        
        @Override
        public void visit(Type t) { }
        
        @Override
        public void visit(Declaration d) {
            Id id = d.id;
            String typeName;
            if (d.type instanceof CharType) {
                typeName = "char";
            } else if (d.type instanceof IntType) {
                typeName = "int";
            } else if (d.type instanceof ArrayType) {
                ArrayType at = (ArrayType) d.type;
                typeName = "array:"+at.size+":"+at.type;
            } else {
                throw new RuntimeException();
            }
            types.put(id, typeName);
        }
    }
	
    public static void main(String[] args) {
	
        Id x = new Id("x");
        IntType intType = new IntType();
        Declaration d1 = new Declaration(x, intType);
        Program pro = new Program(d1, x);
	
        IdVisitor vis = new IdVisitor();
        prog.accept(vis);
        
        CheckTypesVisitor vis2 = new CheckTypesVisitor();
        prog.accept(vis2);
	
        Id y = new Id("y");
        ArrayType atype = new ArrayType(1, intType);
        Declaration d2 = new Declaration(y, atype);
        Expression e1 = new ArrayIndexing(y new Num(5));
        Program pro2 = new Program(d2, e1);
	
        pro2.accept(vis2);
    }
}

class MyVisitor extends Visitor {

	public int visit(Tree n) {
		int nti;

		if (n.GetHas_Right()) {
			r = n.GetRight();
			nti = r.accept(this);
		} else
			nti = 0;

		System.out.println(n.GetKey());

		if (n.GetHas_Left()) {
			l = n.GetLeft();
			nti = l.accept(this);
		} else
			nti = 0;

		return 0;
	}

}
