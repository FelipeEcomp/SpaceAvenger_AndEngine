package activity.projeto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuPrincipal extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menuprincipal);
		
Button opcoes = (Button) findViewById(R.id.opcoes);
Button inicia = (Button) findViewById(R.id.iniciar);		
Button cashshop = (Button) findViewById(R.id.lojars);

		
		opcoes.setOnClickListener(new View.OnClickListener(){//evento do botão de logar
	    	public void onClick(View arg0) {
	    		Intent opc = new Intent(MenuPrincipal.this,Options.class);
	    		MenuPrincipal.this.startActivity(opc);    		
	    		MenuPrincipal.this.finish();
	    		
	    	}
	    	
	    });
		
		
		
		inicia.setOnClickListener(new View.OnClickListener(){//evento do botão de logar
	    	public void onClick(View arg0) {
	    		Intent ini = new Intent(MenuPrincipal.this,StageSelection.class);
	    		MenuPrincipal.this.startActivity(ini);    		
	    		MenuPrincipal.this.finish();
	    		
	    	}
	    	
	    });
				
		
		cashshop.setOnClickListener(new View.OnClickListener(){//evento do botão de logar
	    	public void onClick(View arg0) {
	    		Intent cash = new Intent(MenuPrincipal.this,CashShop.class);
	    		MenuPrincipal.this.startActivity(cash);    		
	    		MenuPrincipal.this.finish();
	    		
	    	}
	    	
	    });
		
		
		
		
	}
	
	public void onBackPressed() {  //botão voltar
		Intent telacad = new Intent(MenuPrincipal.this,Personagens.class);
		MenuPrincipal.this.startActivity(telacad);    		
		MenuPrincipal.this.finish(); 		
	    super.onBackPressed(); // OK - botão back funciona 
	   return;
	}
	
}
