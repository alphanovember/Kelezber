package pau.kelezber;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Calis extends AppCompatActivity {

    Button Calis;
    Button Test;
    ListView ListV;
    ArrayAdapter<String> adapter;
    ArrayList<HashMap<String, String>> kelimeliste;
    String idler[],sorular[],cevaplar[],liste[];
    int kelimeidler[];
    ArrayList<HashMap<String, String>> soru_liste;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calis);
        Database db = new Database(getApplicationContext());
        kelimeliste = db.kelimeleriListele();

        Calis=(Button)findViewById(R.id.Calis);
        Test=(Button)findViewById(R.id.Test);
        ListV=(ListView)findViewById(R.id.ListV);

            Test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(kelimeliste.size()>=10){
                Intent i =new Intent(Calis.this,sorutest.class);
                startActivity(i);
                  }
                else{
                    Toast.makeText(getApplicationContext(), "Lütfen en az 10 soru ekleyiniz... ", Toast.LENGTH_LONG).show();
                }

            }
            });


        Calis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(Calis.this,donenyer2.class);
                startActivity(i);
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
                liste[i]=sorular[i]+" "+cevaplar[i];
            }

            adapter = new ArrayAdapter<String>(this, R.layout.viewer, R.id.kelimeles, liste);
            ListV.setAdapter(adapter);
        }
    }

    }


