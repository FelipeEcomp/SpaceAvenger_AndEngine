package activity.projeto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class PVP extends Activity{
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pvpsalas);
	}
	
	
	public void onBackPressed() {  //bot�o voltar
		Intent telacad = new Intent(PVP.this,StageSelection.class);
		PVP.this.startActivity(telacad);    		
		PVP.this.finish(); 
		
	    super.onBackPressed(); // OK - bot�o back funciona 
	   return;
	}

}
