package activity.projeto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MenuJogo extends Activity{
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menujogo);
	}
	
	public void onBackPressed() {  //bot�o voltar
		Intent telacad = new Intent(MenuJogo.this,Personagens.class);
		MenuJogo.this.startActivity(telacad);    		
		MenuJogo.this.finish(); 
		
	    super.onBackPressed(); // OK - bot�o back funciona 
	   return;
	}

}
