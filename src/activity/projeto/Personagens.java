package activity.projeto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Personagens extends Activity{
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.personagens);
		Button escolhe = (Button) findViewById(R.id.escolher);
		
		
		escolhe.setOnClickListener(new View.OnClickListener(){//evento do bot�o de logar
	    	public void onClick(View arg0) {
	    		Intent trocatela = new Intent(Personagens.this,MenuPrincipal.class);
	    		Personagens.this.startActivity(trocatela);    		
	    		Personagens.this.finish();
	    		
	    	}
	    	
	    });
		
		
		
		
	}
	
	public void onBackPressed() {  //bot�o voltar
		Intent telacad = new Intent(Personagens.this,SpaceAvengerActivity.class);
		Personagens.this.startActivity(telacad);    		
		Personagens.this.finish(); 
		
	    super.onBackPressed(); // OK - bot�o back funciona 
	   return;
	   
	  
	   
	  
	}
	
}
