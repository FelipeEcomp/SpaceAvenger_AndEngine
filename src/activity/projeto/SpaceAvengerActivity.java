package activity.projeto;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class SpaceAvengerActivity extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button entrar = (Button) findViewById(R.id.entrar);
        Button cad = (Button) findViewById(R.id.cadastra);
        Button esq = (Button) findViewById(R.id.esqueci);
        
        
    
    entrar.setOnClickListener(new View.OnClickListener(){//evento do botão de logar
    	public void onClick(View arg0) {
    		Intent trocatela = new Intent(SpaceAvengerActivity.this,Personagens.class);
    		SpaceAvengerActivity.this.startActivity(trocatela);    		
    		SpaceAvengerActivity.this.finish();
    		
    	}
    	
    });
    	
    
    cad.setOnClickListener(new View.OnClickListener(){//evento do botão de cadastrar
    	public void onClick(View arg0) {
    		Intent telacad = new Intent(SpaceAvengerActivity.this,Cadastrar.class);
    		SpaceAvengerActivity.this.startActivity(telacad);    		
    		SpaceAvengerActivity.this.finish();
    		
    	}
    	
    });
    
    esq.setOnClickListener(new View.OnClickListener(){//evento do botão de cadastrar
    	public void onClick(View arg0) {
    		Intent telaesq = new Intent(SpaceAvengerActivity.this,Esqueci.class);
    		SpaceAvengerActivity.this.startActivity(telaesq);    		
    		SpaceAvengerActivity.this.finish();
    		
    	}
    	
    });
        
   
    //Adicionando Audio
  MediaPlayer mp = new MediaPlayer();
  //....em um onCreate, Metodo, Liestener, etc....
                 
             Context  context = SpaceAvengerActivity.this; /*especifique o context ou obtenha usando um getBaseContext(); */

              mp.release(); //prepara activity
              mp = MediaPlayer.create(context , R.raw.starwars); //cria
              mp.start();//executa
    
    }
}