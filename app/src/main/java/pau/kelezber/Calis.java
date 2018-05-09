package pau.kelezber;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Calis extends AppCompatActivity {

    Button Calis,btGD;
    Button Test;
    int indis=0;

    ListView ListV;
    ArrayAdapter<String> adapter;
    ArrayList<HashMap<String, String>> kelimeliste;
    String idler[],sorular[],cevaplar[],liste[],yldz[];
    int kelimeidler[];
    ArrayList<HashMap<String, String>> soru_liste;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_calis);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        android.support.v7.app.ActionBar ab=getSupportActionBar();
        ab.hide();

        setContentView(R.layout.activity_calis);
        Database db = new Database(getApplicationContext());
        kelimeliste = db.kelimeleriListele();
        soru_liste = db.kelimeleriListele();
        yldz = new String[soru_liste.size()+1];

        for (int i = 0; i < soru_liste.size(); i++) {
            yldz[i] = soru_liste.get(i).get("yildiz");
            if (yldz[i].equals("Calisiliyor")) {
                indis++;
            }
        }
        Calis=(Button)findViewById(R.id.Calis);
        btGD=(Button)findViewById(R.id.btGd);
        Test=(Button)findViewById(R.id.Test);
        ListV=(ListView)findViewById(R.id.ListV);



        Test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Calis.this);
        alertDialog.setTitle("UYARI");
        alertDialog.setMessage("10 soruluk çıkış yapılamayan bir teste gireceksiniz, hazır mısınız?");
        alertDialog.setPositiveButton("Evet", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(kelimeliste.size()>=10){

                    Intent i =new Intent(Calis.this,sorutest.class);
                    startActivity(i);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Lütfen en az 10 soru ekleyiniz... ", Toast.LENGTH_LONG).show();
                }

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



        Calis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(indis>=1){
                Intent i =new Intent(Calis.this,donenyer2.class);
                startActivity(i);
                    finish();
            }
        else
        {
            Toast.makeText(getApplicationContext(), "Lütfen en az 1 soru veya yıldız ekleyin... ", Toast.LENGTH_LONG).show();
        }}});


        btGD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Calis.this,Login.class);
                startActivity(i);
                finish();

            }
        });

        if (kelimeliste.size() == 0) {
            Toast.makeText(getApplicationContext(), "Burada hiç soru yok.\n Bir önceki sayfadan ekleyiniz", Toast.LENGTH_LONG).show();
        } else {
            idler=new String[kelimeliste.size()];
            sorular=new String[kelimeliste.size()];
            cevaplar = new String[kelimeliste.size()];
            liste=new String[kelimeliste.size()];
            kelimeidler = new int[kelimeliste.size()];
            for (int i = 0; i < kelimeliste.size(); i++) {
                idler[i] = kelimeliste.get(i).get("id");
                sorular[i]= kelimeliste.get(i).get("soru");
                cevaplar[i]=kelimeliste.get(i).get("cevap");
                kelimeidler[i] = Integer.parseInt(kelimeliste.get(i).get("id"));
                liste[i]=sorular[i]+"    ------------>  "+cevaplar[i];
            }

            adapter = new ArrayAdapter<String>(this, R.layout.viewer, R.id.kelimeles, liste);
            ListV.setAdapter(adapter);
        }
    }

    }


