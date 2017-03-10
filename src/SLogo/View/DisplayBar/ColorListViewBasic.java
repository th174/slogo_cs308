package SLogo.View.DisplayBar;

import java.util.Observable;

import SLogo.View.CommandLineView;

public class ColorListViewBasic extends TextItemList {

	public ColorListViewBasic(CommandLineView commandLineView) {
		super(commandLineView);
		addItem(getMyResources().getString("ColorTab"));
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		
	}

	public void addItem(){
		
	}
	
	public void removeItem() {
		
	}
}
