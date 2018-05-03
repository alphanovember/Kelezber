package pau.kelezber;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class Login extends AppCompatActivity {

    Button Duzenle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Duzenle=(Button) findViewById(R.id.Duzenle);
        Intent i =new Intent(Login.this,Edit.class);
        startActivity(i);

    }
}
