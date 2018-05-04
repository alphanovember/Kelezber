package pau.kelezber;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class Secim extends AppCompatActivity {
    ListView lvKelime;
    ArrayAdapter<String> adapter;
    ArrayList<HashMap<String, String>> kelimeliste;
    String idler[],sorular[],cevaplar[],liste[];
    Button btGd;
    int kelimeidler[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secim);
        onResume();
    }
    public void onResume() {
        super.onResume();
        btGd = (Button)findViewById(R.id.btGd);

        btGd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Secim.this,Edit.class);
                startActivity(i);
                finish();
            }
        });
        Database db = new Database(getApplicationContext());
        kelimeliste = db.kelimeleriListele();
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
                liste[i]= idler[i]+"   "+sorular[i]+"\n"+cevaplar[i];
            }

            lvKelime = (ListView) findViewById(R.id.lvKelime);
            adapter = new ArrayAdapter<String>(this, R.layout.activity_test, R.id.kelimeler, liste);
            lvKelime.setAdapter(adapter);
            lvKelime.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                        long arg3) {

                    Intent intent = new Intent(getApplicationContext(), Edit.class);
                    intent.putExtra("id", (int)kelimeidler[arg2]);
                    startActivity(intent);
                    finish();
                }
            });

        }
    }

}

