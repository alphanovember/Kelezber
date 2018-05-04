package pau.kelezber;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class Edit extends AppCompatActivity {
    EditText etSoru;
    EditText etCevap;
    Button btListele;
    Button btDon;
    Button btVeriEkle;
    Button btSil;
    Button btDuzenle;
    ArrayList<HashMap<String, String>> soru_liste;
    Database db;
    int gelenid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        etSoru = (EditText) findViewById(R.id.etSoru);
        etCevap = (EditText) findViewById(R.id.etCevap);
        btListele = (Button) findViewById(R.id.btListele);
        btDon = (Button) findViewById(R.id.btDon);
        btDuzenle = (Button) findViewById(R.id.btDuzenle);
        btSil=(Button)findViewById(R.id.btSil);
        btVeriEkle = (Button) findViewById(R.id.btVeriEkle);
        db = new Database(getApplicationContext());

        btDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Edit.this,Login.class);
                startActivity(i);

            }
        });
        btListele.setOnClickListener(new View.OnClickListener()


        {
            @Override
            public void onClick (View view){

                Intent i = new Intent(Edit.this, Secim.class);
                startActivity(i);
                finish();
            }
        });

       /* btVeriEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String soru, cevap;
                soru = etSoru.getText().toString();
                cevap = etCevap.getText().toString();
                if ( soru.matches("") || cevap.matches("") ) {
                    Toast.makeText(getApplicationContext(), "Bilgileri Eksiksiz Doldurmalısınız...", Toast.LENGTH_LONG).show();
                }

                else {
                    Database db = new Database(getApplicationContext());
                    soru_liste= db.kelimeleriListele();
                    int [] idlist = new int[soru_liste.size()];
                    for (int i = 0; i < soru_liste.size(); i++) {
                        idlist[i] = Integer.parseInt(soru_liste.get(i).get("id"));
                    }
                    db.kelimeEkle(soru, cevap);
                    db.close();
                    Toast.makeText(getApplicationContext(), "Verileriniz Başarıyla Eklendi...", Toast.LENGTH_LONG).show();
                    etSoru.setText("");
                    etCevap.setText("");
                }
            }
        });
       */ /*Intent intent=getIntent();
        gelenid = intent.getIntExtra("id",0);
        Database db = new Database(getApplicationContext());
        HashMap<String, String> map = db.kelimeleriGoster(gelenid);

        etSoru.setText(map.get("soru"));
        etCevap.setText(map.get("cevap"));
*/
        btDuzenle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id,soru,cevap;
                soru = etSoru.getText().toString();
                cevap = etCevap.getText().toString();

                if(soru.matches("") || cevap.matches("")   ){
                    Toast.makeText(getApplicationContext(), "Tum Bilgileri Eksiksiz Doldurunuz", Toast.LENGTH_LONG).show();
                }else{
                    Database db = new Database(getApplicationContext());
                    db.kelimeDuzenle(soru, cevap, gelenid);
                    db.close();
                    Toast.makeText(getApplicationContext(), "Verileriniz Başarıyla Düzenlendi.", Toast.LENGTH_LONG).show();
                    etSoru.setText("");
                    etCevap.setText("");
                    Intent intent = new Intent(getApplicationContext(), Secim.class);
                    intent.putExtra("id", (int)gelenid);
                    startActivity(intent);
                    finish();
                }
            }
        });
        btSil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(Edit.this);
                alertDialog.setTitle("Uyarı");
                alertDialog.setMessage("Seçilen Veriler Silinsin mi?");
                alertDialog.setPositiveButton("Evet", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Database db = new Database(getApplicationContext());
                        db.kelimeSil(gelenid);
                        Toast.makeText(getApplicationContext(), "Seçilen Veriler Başarıyla Silindi", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(getApplicationContext(), Secim.class);
                        startActivity(intent);
                        finish();
                    }
                });
                alertDialog.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
            }
        });

        if(gelenid>=0)
        {
            btDuzenle.setClickable(true);
            btSil.setClickable(true);
        }
        else
        {
            btDuzenle.setClickable(false);
            btSil.setClickable(false);
        }
    }
    }


