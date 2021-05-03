package IG;

import java.util.ArrayList;

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
	
	@Override
	public Objet getElementAt(int index){
		return objets.get(index);
	}
	
	@Override
	public int getSize(){
		return objets.size();
	}

}
