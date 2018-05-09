package pau.kelezber;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class Edit extends AppCompatActivity {
    EditText etSoru;
    EditText etCevap,etyaldiz;
    Button btListele;
    Button btDon;
    Button btVeriEkle;

    Button btSil;
    Button yaldiz;
    Button btDuzenle;
    boolean yildizbas=false;
    ArrayList<HashMap<String, String>> soru_liste;
    Database db;
    int gelenid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        android.support.v7.app.ActionBar ab=getSupportActionBar();
        ab.hide();

        setContentView(R.layout.activity_edit);
        etSoru = (EditText) findViewById(R.id.etSoru);
        etCevap = (EditText) findViewById(R.id.etCevap);
        etyaldiz = (EditText) findViewById(R.id.etyaldiz);
        btListele = (Button) findViewById(R.id.btListele);
        btDon = (Button) findViewById(R.id.btDon);
        yaldiz = (Button) findViewById(R.id.yaldiz);
        btDuzenle = (Button) findViewById(R.id.btDuzenle);
        btSil=(Button)findViewById(R.id.btSil);
        btVeriEkle = (Button) findViewById(R.id.btVeriEkle);

        Resources res2 = getResources();
        Drawable dr2 = res2.getDrawable(R.drawable.beyazyildiz);
        yaldiz.setBackgroundDrawable(dr2);
        db = new Database(getApplicationContext());

        btDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Edit.this,Login.class);
                startActivity(i);
                finish();

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

        btVeriEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String soru, cevap,yaldiz3 = null;
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
                    if(yildizbas == true){
                        yaldiz3="Ezberlendi";
                    }
                    if(yildizbas==false){
                        yaldiz3="Calisiliyor";
                    }

                    db.kelimeEkle(soru, cevap,yaldiz3);
                    db.close();
                    Toast.makeText(getApplicationContext(), "Verileriniz Başarıyla Eklendi...", Toast.LENGTH_LONG).show();
                    etSoru.setText("");
                    etCevap.setText("");
                    yildizbas=false;
                    Resources res = getResources();
                    Drawable d = res.getDrawable(R.drawable.beyazyildiz);
                    yaldiz.setBackgroundDrawable(d);
                }
            }
        });
        Intent intent=getIntent();
        gelenid = intent.getIntExtra("id",0);
        Database db = new Database(getApplicationContext());
        HashMap<String, String> map = db.kelimeleriGoster(gelenid);
if(gelenid!=0) {
    etSoru.setText(map.get("soru"));
    etCevap.setText(map.get("cevap"));
    etyaldiz.setText(map.get("yildiz"));
    String yl;
    yl = etyaldiz.getText().toString();
    if (yl.equals("Calisiliyor")) {
        yildizbas = false;
        Resources res = getResources();
        Drawable d = res.getDrawable(R.drawable.beyazyildiz);
        yaldiz.setBackgroundDrawable(d);
    }
    if (yl.equals("Ezberlendi")) {
        yildizbas = true;
        Resources res = getResources();
        Drawable d = res.getDrawable(R.drawable.sariyildiz);
        yaldiz.setBackgroundDrawable(d);
    }
}

        btDuzenle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id,soru,cevap,yaldiz2 = null;
                soru = etSoru.getText().toString();
                cevap = etCevap.getText().toString();




                if(yildizbas == true){
                    yaldiz2="Ezberlendi";
                }
                if(yildizbas == false){
                    yaldiz2="Calisiliyor";
                }

                if(soru.matches("") || cevap.matches("")   ){
                    Toast.makeText(getApplicationContext(), "Tum Bilgileri Eksiksiz Doldurunuz", Toast.LENGTH_LONG).show();
                }else{
                    Database db = new Database(getApplicationContext());
                    db.kelimeDuzenle(soru, cevap,yaldiz2, gelenid);
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
        yaldiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (yildizbas == false) {
                    yildizbas = true;
                    Resources res = getResources();
                    Drawable d = res.getDrawable(R.drawable.sariyildiz);
                    yaldiz.setBackgroundDrawable(d);
                } else if(yildizbas == true){
                    yildizbas = false;
                Resources res = getResources();
                Drawable d = res.getDrawable(R.drawable.beyazyildiz);
                yaldiz.setBackgroundDrawable(d);
            }
        }});

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


