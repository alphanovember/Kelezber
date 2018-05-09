package pau.kelezber;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class sonuc extends AppCompatActivity {


    ListView lvAd;
    Button btTemiz;
    Button btdn;
    TextView tvPuan;
    TextView tvSure;

    ArrayAdapter<String> adapter;
    ArrayList<HashMap<String, String>> listele;
    String puan[],puan2[];
    String listeyaz[],listeyaz2[],sure[],sure2[];
    String dogru,yanlis;
    int b,d,x,y;
    int id[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sonuc);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        android.support.v7.app.ActionBar ab=getSupportActionBar();
        ab.hide();

        setContentView(R.layout.activity_sonuc);
        Intent i = getIntent();
        lvAd = (ListView) findViewById(R.id.lvAd);
        btTemiz = (Button) findViewById(R.id.btTemiz);

        tvPuan = (TextView) findViewById(R.id.tvPuan);
        tvSure = (TextView) findViewById(R.id.tvSure);
        btdn = (Button) findViewById(R.id.btdn);

        b = i.getIntExtra("dogru", -1);
        d = i.getIntExtra("yanlis", -1);
        if (b != -1) {
        tvPuan.setText(String.valueOf(b));
        tvSure.setText(String.valueOf(d));

        dogru = tvPuan.getText().toString();
        yanlis = tvSure.getText().toString();



            skordata db2 = new skordata(getApplicationContext());
            db2.skorEkle(dogru, yanlis);
            db2.close();
            Toast.makeText(getApplicationContext(), "YENİ SKOR EKLENDİ", Toast.LENGTH_LONG).show();
            btdn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(sonuc.this, Login.class);
                    startActivity(i);
                    finish();
                }
            });
        }
        btdn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(sonuc.this,Login.class);
                startActivity(i);
                finish();
            }
        });
            btTemiz.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(sonuc.this);
                    alertDialog.setTitle("UYARI");
                    alertDialog.setMessage("BÜTÜN SKORLAR SİLİNSİN Mİ?");

                    alertDialog.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            skordata db2 = new skordata(getApplicationContext());
                            db2.skorTemizle();
                            db2.close();
                            tvPuan.setText("");
                            tvSure.setText("");
                            onResume();
                            Toast.makeText(getApplicationContext(), "BÜTÜN SKORLAR SİLİNDİ", Toast.LENGTH_LONG).show();

                        }

                    });


                    alertDialog.setNegativeButton("Hayır", new DialogInterface.OnClickListener()

                            {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }
                    );
                    alertDialog.show();

                }
            });
        }

    public void onResume() {
        super.onResume();
        skordata db2 = new skordata(getApplicationContext());
        listele = db2.skorlar();
        String gecici,gecici2,gecici4;

        puan = new String[listele.size()];
        puan2=new String[10];
        sure=new String[listele.size()];
        sure2=new String[10];

        id = new int[listele.size()];
        listeyaz=new String[10];
        listeyaz2=new String[listele.size()];

        for (int i = 0; i < listele.size(); i++) {
            puan[i] = listele.get(i).get("dogru");
            sure[i] = listele.get(i).get("yanlis");
            id[i] = Integer.parseInt(listele.get(i).get("id"));
        }

        for (int i = 0; i < listele.size(); i++) {
            for (int j = 0; j < listele.size(); j++) {
                if (Integer.parseInt(puan[j]) < Integer.parseInt(puan[i])) {
                    gecici = puan[i];
                    puan[i] = puan[j];
                    puan[j] = gecici;

                    gecici4 = sure[i];
                    sure[i]= sure[j];
                    sure[j] = gecici4;

                }
                if (Integer.parseInt(puan[j]) == Integer.parseInt(puan[i]) && Integer.parseInt(sure[j]) > Integer.parseInt(sure[i])) {
                    gecici4 = sure[i];
                    sure[i] = sure[j];
                    sure[j] = gecici4;

                    gecici = puan[i];
                    puan[i] = puan[j];
                    puan[j] = gecici;

                }
            }

        }
        if(listele.size()>0) {
            x = Integer.valueOf(puan[0]);
            y = Integer.valueOf(sure[0]);

            if (x < b) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(sonuc.this);
                alertDialog.setTitle("TEBRİKLER... ");
                alertDialog.setMessage("YENİ REKOR...");
                alertDialog.setPositiveButton("TAMAM", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }

                });
                alertDialog.show();
            }
            if (x == b) {
                if (y <= d) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(sonuc.this);
                    alertDialog.setTitle("TEBRİKLER... ");
                    alertDialog.setMessage("YENİ REKOR...");
                    alertDialog.setPositiveButton("TAMAM", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }

                    });
                    alertDialog.show();
                }
            }
        }



        if(listele.size()>=10) {
            for (int i = 0; i < 10; i++) {
                gecici = puan[i];
                puan2[i] = gecici;

                gecici4 = sure[i];
                sure2[i] = gecici4;

                listeyaz[i] = i + 1 +"-"+ "\t"  + puan2[i] + "                                           "+sure2[i]+"Y";
            }
            adapter = new ArrayAdapter<String>(this, R.layout.lyo, R.id.lyolar, listeyaz);
        }
        else {
            for (int i = 0; i < listele.size(); i++) {
                gecici = puan[i];
                puan2[i] = gecici;


                gecici4 = sure[i];
                sure2[i] = gecici4;

                listeyaz2[i] = i + 1 +"-"+ "\t"  + puan2[i] +"D"+ "                                    " +sure2[i]+"Y";
            }
            adapter = new ArrayAdapter<String>(this, R.layout.lyo, R.id.lyolar, listeyaz2);
        }

        lvAd.setAdapter(adapter);

    }

}

