package IG_test;

import java.util.ArrayList;
import java.util.List;

import elements3D.*;
import javax.swing.AbstractListModel;

public class ListeObjets extends AbstractListModel<Objet> {
	
	private ArrayList<Objet> objets;
	
	public ListeObjets() {
		this.objets = new ArrayList<Objet>();
	}
	
	public ArrayList<Objet> getObjets(){
		return this.objets;
	}
	

	
	public void addElement(Objet o){
		objets.add(o);
		fireContentsChanged(o, getSize() - 1, getSize() - 1);
	}
	
	public void initialiser(List<Objet3D> o) {	
		for (int i = 0; i < o.size(); i++) {
			this.objets.add(new Objet(o.get(i)));
		}
	}
	
	@Override
	public Objet getElementAt(int index){
		return objets.get(index);
	}
	
	@Override
	public int getSize(){
		return objets.size();
	}

}
