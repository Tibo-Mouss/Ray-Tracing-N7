package IG_test;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

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
	
	@Override
	public Lumieres getElementAt(int index){
		return lumieres.get(index);
	}
	
	@Override
	public int getSize(){
		return lumieres.size();
	}


}
