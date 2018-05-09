package pau.kelezber;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import java.util.Random;

public class sorutest extends AppCompatActivity {
    boolean kontrol=true;
    Button btSira;
    Button btCevapla;
    Button btBitir;
    Button bBilmiyom;
    EditText etCvp;
    TextView tvSoru;
    TextView txGizli;
    String st1,st2;
    TextView txAdet;
    String[] cvp;
    int dogru=0,yanlis=0;
    int[] cvpid;
    int[] sayil;
    int id;
    int c=1,f=0;
    int a=2;
    ArrayList<HashMap<String, String>> soru_liste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sorutest);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        android.support.v7.app.ActionBar ab=getSupportActionBar();
        ab.hide();

        setContentView(R.layout.activity_sorutest);

        btSira=(Button)findViewById(R.id.btSira);
        btBitir=(Button)findViewById(R.id.btBitir);
        btCevapla=(Button)findViewById(R.id.btCevapla);
        bBilmiyom=(Button)findViewById(R.id.bBilmiyom);
        etCvp=(EditText)findViewById(R.id.etCvp);
        tvSoru=(TextView)findViewById(R.id.tvSoru);
        txGizli=(TextView)findViewById(R.id.txGizli);
        txAdet=(TextView)findViewById(R.id.txAdet);
        btBitir.setClickable(false);


        Database db2 = new Database(getApplicationContext());
        soru_liste=db2.kelimeleriListele();

       cvp= new String[soru_liste.size()];
        cvpid = new int[soru_liste.size()];
        for (int i = 0; i < soru_liste.size(); i++) {
            cvp[i] = soru_liste.get(i).get("cevap");
            cvpid[i] = Integer.parseInt(soru_liste.get(i).get("id"));
        }

        sayil = new int[11];
        sayil[0] = 0;
        Random random = new Random();
        id = random.nextInt(soru_liste.size()) + 1;
        for (int i = 1; i < 11; i++) {
            for (int j = 0; j <= i; j++) {
                if (sayil[j] == cvpid[id]) {
                    id = (int) (Math.random() * soru_liste.size());
                    j = 0;
                }
            }
            sayil[i] = cvpid[id];
            id = (int) (Math.random() * soru_liste.size());
        }

        Database db = new Database(getApplicationContext());
        HashMap<String, String> map = db.kelimeleriGoster(sayil[1]);
        tvSoru.setText(map.get("soru"));
        txGizli.setText(map.get("cevap"));
        txAdet.setText(String.valueOf(c+".Soru"));

        st1 = txGizli.getText().toString();

bBilmiyom.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        txGizli.setVisibility(View.VISIBLE);
        kontrol=false;


    }
});
        btSira.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btCevapla.setClickable(true);
                bBilmiyom.setClickable(true);
                c++;
                txGizli.setVisibility(View.INVISIBLE);
                kontrol=true;
                if (c == 10) {

                    btSira.setVisibility(View.INVISIBLE);
                    btBitir.setVisibility(View.VISIBLE);
                }

                btSira.setClickable(false);
                id = sayil[c];
                etCvp.setText("");
                Database db = new Database(getApplicationContext());

                HashMap<String, String> map = db.kelimeleriGoster(id);
                tvSoru.setText(map.get("soru"));
                txGizli.setText(map.get("cevap").toString());
                st1 = txGizli.getText().toString();


                a++;

                txAdet.setText(String.valueOf(c+".Soru"));
            }
        });


        btCevapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String cvp = null;
                cvp = etCvp.getText().toString();

                if (cvp.equals(st1) || cvp.toLowerCase().equals(st1) || cvp.toUpperCase().equals(st1.toUpperCase()))
                {
                    if(kontrol==true) {
                        dogru++;
                    }
                    else{
                        yanlis++;
                    }
                    btCevapla.setClickable(false);
                    btSira.setClickable(true);

                    bBilmiyom.setClickable(false);
                    f++;
                    if(f==10) {
                        btBitir.setClickable(true);
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(sorutest.this);
                        alertDialog.setTitle("TEBRİKLER DOĞRU CEVAP... ");
                        alertDialog.setMessage("OYUNU BİTİR BUTONUNA BASARAK PUANINIZI LİSTELEYEBİLİRSİNİZ...");
                        alertDialog.setPositiveButton("TAMAM", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }

                        });
                        alertDialog.show();

                    }
                    else{

                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(sorutest.this);
                        alertDialog.setTitle("TEBRİKLER DOĞRU CEVAP... ");
                        alertDialog.setMessage("HAZIR OLDUĞUNUZDA SIRADAKİ SORUYA GEÇEBİLİRSİNİZ...");
                        alertDialog.setPositiveButton("TAMAM", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        alertDialog.show();

                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "YANLIŞ CEVAP TEKRAR DENEYİNİZ... ", Toast.LENGTH_LONG).show();
                    yanlis++;
                }


        }});

        btBitir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(sorutest.this, sonuc.class);

                i.putExtra("dogru", dogru);
                i.putExtra("yanlis", yanlis);
                startActivity(i);
                finish();

            }
        });
        btBitir.setClickable(false);
        btSira.setClickable(false);
    }
}
