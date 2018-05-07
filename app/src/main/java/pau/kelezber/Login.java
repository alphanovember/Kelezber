package pau.kelezber;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {

    Button Duzenle;
    Button Rapor;
    Button Cikis;
    Button Calis;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Duzenle=(Button) findViewById(R.id.Duzenle);
        Rapor=(Button) findViewById(R.id.Rapor);
        Cikis=(Button) findViewById(R.id.Cikis);
        Calis=(Button) findViewById(R.id.Calis);
        Duzenle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(Login.this,Edit.class);
                startActivity(i);
            }

        });
        Calis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(Login.this,Calis.class);
                startActivity(i);
            }
        });
        Rapor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(Login.this,Rapor.class);
                startActivity(i);

            }
        });
        Cikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }
}
