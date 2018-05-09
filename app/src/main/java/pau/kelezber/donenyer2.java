package pau.kelezber;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class donenyer2 extends AppCompatActivity {
    TextView tvCvp,tvCvp2;
    Button ileri,geri,test,btDon;
    int[] sayil,cvpid,cvpid2;
String yldz[];
    int id,i=1;
    int a=0;
    int x=0;

    ArrayList<HashMap<String, String>> soru_liste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_donenyer2);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        android.support.v7.app.ActionBar ab=getSupportActionBar();
        ab.hide();

        setContentView(R.layout.activity_donenyer2);
        tvCvp = (TextView) findViewById(R.id.tvCvp);
        tvCvp2 = (TextView) findViewById(R.id.tvCvp2);
        ileri = (Button) findViewById(R.id.ileri);
        test = (Button) findViewById(R.id.geri2);
        geri = (Button) findViewById(R.id.geri);
        btDon = (Button) findViewById(R.id.btGd);
        test.setVisibility(View.INVISIBLE);
        Database db = new Database(getApplicationContext());
        soru_liste = db.kelimeleriListele();
        sayil = new int[soru_liste.size()+1];
        yldz = new String[soru_liste.size()+1];

        int indis=0;
        cvpid = new int[soru_liste.size()+1];
        for (int i = 0; i < soru_liste.size(); i++) {
            cvpid[i] = Integer.parseInt(soru_liste.get(i).get("id"));
            yldz[i] = soru_liste.get(i).get("yildiz");
            if(yldz[i].equals("Calisiliyor")){
                indis++;
            }
        }
        cvpid2 = new int[indis+1];

        for(int i=0;i<soru_liste.size()+1;i++){

               if("Calisiliyor".equals(yldz[i])){
                  cvpid2[x]=cvpid[i];
                  x++;
            }
        }
        sayil = new int[x+1];
        sayil[0] = 0;
        Random random = new Random();
        id = random.nextInt(x) + 1;
        for (int i = 1; i < x+1; i++) {
            for (int j = 0; j <= i; j++) {
                if (sayil[j] == cvpid2[id] ) {
                    id = (int) (Math.random() * x);
                    j = 0;
                }
            }
            sayil[i] = cvpid2[id];
            id = (int) (Math.random() * x);
        }

        HashMap<String, String> map = db.kelimeleriGoster(sayil[1]);
        tvCvp.setText(map.get("soru"));
        tvCvp2.setText(map.get("cevap"));

        ileri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(i!=x){
                a+= 1;
                i++;
                Database db = new Database(getApplicationContext());
                HashMap<String, String> map = db.kelimeleriGoster(sayil[i]);
                tvCvp.setText(map.get("soru"));
                tvCvp2.setText(map.get("cevap"));

                ileri.setEnabled(true);
            }
            else if(i==x+1){
                    ileri.setEnabled(false);
                                    }


            }
        });
        btDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(donenyer2.this,Calis.class);
                startActivity(i);
                finish();

            }
        });

    geri.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(a>0) {
            i--;
            a--;
            Database db = new Database(getApplicationContext());
            HashMap<String, String> map = db.kelimeleriGoster(sayil[i]);
            tvCvp.setText(map.get("soru"));
            tvCvp2.setText(map.get("cevap"));
            ileri.setEnabled(true);
        }

        }
    });
test.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if(i!=x) {
            a += 1;
            i++;
            Database db = new Database(getApplicationContext());
            HashMap<String, String> map = db.kelimeleriGoster(sayil[i]);
            tvCvp.setText(map.get("soru"));
            tvCvp2.setText(map.get("cevap"));
            flipCard();


                    }
        else if(i==x+1){
            ileri.setEnabled(false);
            flipCard();
        }


    }


});

    }

    public void onCardClick(View view)
    {

     flipCard();


    }
    private void flipCard()
    {
        View rootLayout = (View)findViewById(R.id.main_activity_root);
        View cardFace = (View)findViewById(R.id.main_activity_card_face);
        View cardBack = (View)findViewById(R.id.main_activity_card_back);
        beyazclass flipAnimation = new beyazclass(cardFace, cardBack);


        if (cardFace.getVisibility() == View.GONE)
        {
            flipAnimation.reverse();
            test.setVisibility(View.INVISIBLE);
            ileri.setVisibility(View.VISIBLE);
            geri.setVisibility(View.VISIBLE);

        }

        else{
        ileri.setVisibility(View.INVISIBLE);
        geri.setVisibility(View.INVISIBLE);
        test.setVisibility(View.VISIBLE);

    }
        rootLayout.startAnimation(flipAnimation);
    }
}
