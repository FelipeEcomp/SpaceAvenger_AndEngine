package activity.projeto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Esqueci extends Activity{
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.esquece);
	}

	
	public void onBackPressed() {  //bot�o voltar
		Intent telacad = new Intent(Esqueci.this,SpaceAvengerActivity.class);
		Esqueci.this.startActivity(telacad);    		
		Esqueci.this.finish(); 
		
	    super.onBackPressed(); // OK - bot�o back funciona 
	   return;
	}

}
