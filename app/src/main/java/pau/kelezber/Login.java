package pau.kelezber;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
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
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        android.support.v7.app.ActionBar ab=getSupportActionBar();
        ab.hide();

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
                finish();
            }

        });
        Calis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(Login.this,Calis.class);
                startActivity(i);
                finish();
            }
        });
        Rapor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(Login.this,Rapor.class);
                startActivity(i);
                finish();

            }
        });
        Cikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);

            }
        });



    }
}
