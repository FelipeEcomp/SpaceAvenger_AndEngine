package activity.projeto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StageSelection extends Activity{
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stageselection);
		
Button Loja = (Button) findViewById(R.id.loja);
Button pvp = (Button) findViewById(R.id.pvp);	
Button planeta = (Button) findViewById(R.id.planeta);	
		
		Loja.setOnClickListener(new View.OnClickListener(){//evento do botão de logar
	    	public void onClick(View arg0) {
	    		Intent trocatela = new Intent(StageSelection.this,AyepShop.class);
	    		StageSelection.this.startActivity(trocatela);    		
	    		StageSelection.this.finish();
	    		
	    	}
	    	
	    });
		
		
		pvp.setOnClickListener(new View.OnClickListener(){//evento do botão de logar
	    	public void onClick(View arg0) {
	    		Intent trocapvp = new Intent(StageSelection.this,PVP.class);
	    		StageSelection.this.startActivity(trocapvp);    		
	    		StageSelection.this.finish();
	    		
	    	}
	    	
	    });
		
		
		
		planeta.setOnClickListener(new View.OnClickListener(){//evento do botão de logar
	    	public void onClick(View arg0) {
	    		Intent trocaplaneta = new Intent(StageSelection.this,navegacaoActivity.class);
	    		StageSelection.this.startActivity(trocaplaneta);    		
	    		StageSelection.this.finish();
	    		
	    	}
	    	
	    }); 
	}

	
	public void onBackPressed() {  //botão voltar
		Intent telacad = new Intent(StageSelection.this,MenuPrincipal.class);
		StageSelection.this.startActivity(telacad);    		
		StageSelection.this.finish(); 
		
	    super.onBackPressed(); // OK - botão back funciona 
	   return;
	}
}
