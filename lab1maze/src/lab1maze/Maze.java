package lab1maze;

import java.util.*;

public class Maze {
	private final int row = 60;
	private final int col = 80;	
	private final int percObs = 20; 
	private char[][] maze;
	
	private List<Node> closeSet;
	private List<Node> openSet;
	
	private int goalrow;
	private int goalcol;
	private int inirow;
	private int inicol;
	
	
	public Maze() {
		maze = new char[row][col];    //Maze matrix
		createMatrix();
		closeSet = new ArrayList<>(); //explorados
		openSet = new ArrayList<>(); //para explorar
	}
	
	public void createMatrix() { //llamamos a las funciones de dentro para crear todos los elementos de la matrix
		setG();
		setI();
		setObs();
		setSpaces();
	}
	
	protected void setG() { // colocamos goal
		
		this.goalrow =numRandom(row);
		goalcol= numRandom(col);
		maze[goalrow][goalcol]='G';
		
		
	}
	
	protected void setI() { //colocamos I
		do {
			inirow =numRandom(row);
			inicol= numRandom(col);
			maze[inirow][inicol]='I';
		}while(maze[inirow][inicol] == 'G'); // se hace mientras la casilla no este ocupada por el goal
	}
	
	protected void setObs() {
		int numObs= row*col*percObs/100; //numero de Obstaculos	
		int obsrow, obscol;
		while(numObs>0) {			
			obsrow =numRandom(row); //numero de fila al azar
			obscol= numRandom(col); //numero de columna al azar
			if(maze[obsrow][obscol]!='G' && maze[obsrow][obscol]!='I' && maze[obsrow][obscol]!='#') { //mientras no este ocupada la casilla se hace
				maze[obsrow][obscol]='#';
				numObs--;
			}
			
		}
	}
	
	protected void setSpaces() { //colocamos espacios
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				if(maze[i][j]!='G' && maze[i][j]!='I' && maze[i][j]!='#') { // comprobamos que no este ocupado el hueco
					maze[i][j]=' ';
				}
			}
		}
	}
	
	private int numRandom(int n) {  // metodo privado para cada vez que necesitamos numero aleatorio entre 0 y n
		int nRandom = (int)Math.floor(Math.random()*n);
		return nRandom;
	}
	
	public void showMatrix() {  //mostramos matrix
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				System.out.print(maze[i][j]);	
				
			}
			System.out.print("\n");  //salto de linea por cada fila terminada
		}
	}

//****************** PARTE DE ALGORITMO ******************************* 
	
	public boolean haveSolution() {
		boolean res = false;  //sabremos si tiene solucion o no
		Node i = new Node(inirow,inicol,0); //creamos nodo del que partimos
		i.setF(calculateF(i));  // añadiendole F
		openSet.add(i);			// añadimos al arrayList que vamos a evaluar
		int rowaux = i.getNum_row();
		int colaux = i.getNum_col();
		int gaux = i.getG();
		double faux = i.getF();
		while(res == false && !openSet.isEmpty()) { //mientras que no encontremos respuesta o no tengamos elementos para evaluar
			Node e = new Node(rowaux,colaux,gaux);
			if(maze[rowaux][colaux] != 'G' && maze[rowaux][colaux] != 'I') {
				maze[rowaux][colaux] = '+';
			}
			
			e.setF(faux);
			closeSet.add(e);    // lo introducimos en la lista a evaluar
			openSet.remove(0);  // eliminamos el que vamos a evaluar(siempre el primero ya que es el que tiene menos f
			
			if (maze[e.getNum_row()][e.getNum_col()] == 'G') { // comprobamos si el que evaluamos es G
				res = true;
			}	
			
			
			if (e.getNum_col() > 0 && maze[e.getNum_row()][e.getNum_col()-1] != '#'){ //Vecino izquierdo
				Node s1 = new Node(e.getNum_row(),e.getNum_col()-1, e.getG()+1);
				s1.setF(calculateF(s1));
				if (InClose(s1) == false && InOpen(s1) == false) { //comprobamos que no este en ninguna de las lista para no volvera añadirlo
					addOpenSet(s1);
				}
			}
			
				
			if (e.getNum_row() > 0 && maze[e.getNum_row()-1][e.getNum_col()] != '#'){ //vecino arriba
				Node s2 = new Node(e.getNum_row()-1,e.getNum_col(), e.getG()+1);
				s2.setF(calculateF(s2));
				if (InClose(s2) == false && InOpen(s2) == false) {
					addOpenSet(s2);
				}
			}
			
				
			if (e.getNum_col() < this.col-1 && maze[e.getNum_row()][e.getNum_col()+1] != '#'){ //vecino derecha
				Node s3 = new Node(e.getNum_row(),e.getNum_col()+1, e.getG()+1);
				s3.setF(calculateF(s3));
				if (InClose(s3) == false && InOpen(s3) == false) {
					addOpenSet(s3);
				}
			}
			

			if (e.getNum_row() < this.row-1 && maze[e.getNum_row()+1][e.getNum_col()] != '#'){ //vecino abajo
				Node s4 = new Node(e.getNum_row()+1,e.getNum_col(), e.getG()+1);
				s4.setF(calculateF(s4));
				if (InClose(s4) == false && InOpen(s4) == false) {
					addOpenSet(s4);
				}
			}
			
			if(!openSet.isEmpty()) {  // evitamos index out of bounds
				colaux = openSet.get(0).getNum_col();   //cambiamos variables  auxiliares para evaluar un Nodo distinto en cada iteración
				rowaux = openSet.get(0).getNum_row();
				gaux = openSet.get(0).getG();
				faux = openSet.get(0).getF();
			}			
	
		}
		return res;
	}
		
	public void addOpenSet(Node e) {  // añadimos al openSet 
		boolean introducido = false;
		int i = 0;
		if (openSet.isEmpty()) {
			openSet.add(e);
		} else {
			while(introducido == false && i < openSet.size()) {
				if(openSet.get(i).getF() >= e.getF()) {
					openSet.add(i, e);
					introducido = true;
				}
				i++;
			}
		}
	}
	
	public double calculateF(Node e) {
		double distancia = Math.abs(goalrow-e.getNum_row()) + Math.abs(goalcol-e.getNum_col());
		return distancia + e.getG();
	}
	
	public boolean InClose (Node e) {
		boolean ev = false;
		int i = 0;
		while (ev == false && i < closeSet.size()) {
			if (e.getNum_col() == closeSet.get(i).getNum_col() && e.getNum_row() == closeSet.get(i).getNum_row()) {
				ev = true;
			}
			i++;
		}
		return ev;
	}
	
	public boolean InOpen (Node e) {
		boolean ev = false;
		int i = 0;
		while (ev == false && i < openSet.size()) {
			if (e.getNum_col() == openSet.get(i).getNum_col() && e.getNum_row() == openSet.get(i).getNum_row()) {
				ev = true;
			}
			i++;
		}
		return ev;
	}
	
	public String showOpenAndClose() {
		String o = openSet.toString();
		String c = closeSet.toString();
		return "open: " + o + "\n" + "close: " + c;
	}

}
