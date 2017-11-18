package Telas.Menu;

import java.awt.event.*;
import javax.swing.*;

public class MenuBuilder {
	public static JMenu newMenu(String nm, int ac, String itens[],
			ActionListener al) {
		JMenu menu = new JMenu(nm);
		menu.setMnemonic(ac);
		JMenuItem mi;
		for (int i = 0; i < itens.length; i += 3) {
			if (itens[i] != null) {
				if (itens[i + 1] != null) {
					ImageIcon icon = new ImageIcon(itens[i + 1]);
					mi = new JMenuItem(itens[i], icon);
				} else {
					mi = new JMenuItem(itens[i]);
				}
				if (itens[i + 2] != null) {
					mi.setMnemonic(itens[i + 2].charAt(0));
				}
				mi.addActionListener(al);
				menu.add(mi);
			} else {
				i -= 2;
				menu.addSeparator();
			}
		}
		return menu;
	}
}
