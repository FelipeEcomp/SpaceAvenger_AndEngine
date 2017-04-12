package activity.projeto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class CashShop extends Activity{
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cashshop);
	}

	
	public void onBackPressed() {  //botão voltar
		Intent telacad = new Intent(CashShop.this,MenuPrincipal.class);
		CashShop.this.startActivity(telacad);    		
		CashShop.this.finish(); 
		
	    super.onBackPressed(); // OK - botão back funciona 
	   return;
	}

}
