package activity.projeto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class AyepShop extends Activity{
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ayepshop);
	}

	
	public void onBackPressed() {  //bot�o voltar
		Intent telacad = new Intent(AyepShop.this,StageSelection.class);
		AyepShop.this.startActivity(telacad);    		
		AyepShop.this.finish(); 
		
	    super.onBackPressed(); // OK - bot�o back funciona 
	   return;
	}

}
