package activity.projeto;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;


public class Cadastrar extends Activity{
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cadastrar);
	}
	
	public void onBackPressed() {  //bot�o voltar
		Intent telacad = new Intent(Cadastrar.this,SpaceAvengerActivity.class);
		Cadastrar.this.startActivity(telacad);    		
		Cadastrar.this.finish(); 
		
	    super.onBackPressed(); // OK - bot�o back funciona 
	   return;
	}


}
