package Repositorio;

import Telas.Vagao.*;
import Telas.Locomotiva.*;
import Telas.Composicao.*;
import Telas.Outras.*;

public class FactoryLayout {
		
	public void openAdicionarVagao(){
		new AdicionarVagao().setVisible(true);
	}
	
	public void openListarVagao(){
		new ListarVagao().setVisible(true);
	}
	
	public void openAdicionarLocomotiva(){
		new AdicionarLocomotiva().setVisible(true);
	}
	
	public void openListarLocomotiva(){
		new ListarLocomotiva().setVisible(true);
	}
	
	public void openAdicionarComposicao(){
		new AdicionarComposicao().setVisible(true);
	}
	
	public void openListarComposicao(){
		new ListarComposicao().setVisible(true);
	}
	
	public void openSobre(){
		new Sobre().setVisible(true);
	}
	
}
