	import javax.swing.SwingUtilities;

import capaVisual.Vista;

public class Aplication {
	
	public Aplication() {
		final Vista view = new Vista();
	}
	
	public static void main(String...args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Aplication();
			}
		});
	}
}