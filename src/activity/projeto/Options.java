package activity.projeto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Options extends Activity{
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.options);
	}
	
	public void onBackPressed() {  //bot�o voltar
		Intent telacad = new Intent(Options.this,MenuPrincipal.class);
		Options.this.startActivity(telacad);    		
		Options.this.finish(); 		
	    super.onBackPressed(); // OK - bot�o back funciona 
	   return;
	}
}
