package IG_test;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

import elements3D.*;
import rayTracing.Lumiere;
import rayTracing.*;

public class ListeLumieres extends AbstractListModel<Lumieres> {
	
	private ArrayList<Lumieres> lumieres;
	
	public ListeLumieres() {
		this.lumieres = new ArrayList<Lumieres>();
	}
	
	public ArrayList<Lumieres> getObjets(){
		return this.lumieres;
	}
	
	public void addElement(Lumieres l){
		lumieres.add(l);
		fireContentsChanged(l, getSize() - 1, getSize() - 1);
	}
	
	public void initialiser(List<Lumiere> l) {	
		for (int i = 0; i < l.size(); i++) {
			this.lumieres.add(new Lumieres(l.get(i)));
		}
	}
	
	
	@Override
	public Lumieres getElementAt(int index){
		return lumieres.get(index);
	}
	
	@Override
	public int getSize(){
		return lumieres.size();
	}


}
