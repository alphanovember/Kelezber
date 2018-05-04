package pau.kelezber;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class donenyer2 extends AppCompatActivity {
    TextView tvCvp,tvCvp2;
    Button ileri,geri;
    int[] sayil,cvpid;
    int id,i=2;
    int a=0;

    ArrayList<HashMap<String, String>> soru_liste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donenyer2);
        tvCvp = (TextView) findViewById(R.id.tvCvp);
        tvCvp2 = (TextView) findViewById(R.id.tvCvp2);
        ileri = (Button) findViewById(R.id.ileri);
        geri = (Button) findViewById(R.id.geri);
        Database db = new Database(getApplicationContext());
        soru_liste = db.kelimeleriListele();
        sayil = new int[soru_liste.size()+1];


        cvpid = new int[soru_liste.size()];
        for (int i = 0; i < soru_liste.size(); i++) {
            cvpid[i] = Integer.parseInt(soru_liste.get(i).get("id"));
        }

        sayil[0] = 0;
        Random random = new Random();
        id = random.nextInt(soru_liste.size()) + 1;
        for (int i = 1; i < soru_liste.size()+1; i++) {
            for (int j = 0; j <= i; j++) {
                if (sayil[j] == cvpid[id]) {
                    id = (int) (Math.random() * soru_liste.size());
                    j = 0;
                }
            }
            sayil[i] = cvpid[id];
            id = (int) (Math.random() * soru_liste.size());
        }

        HashMap<String, String> map = db.kelimeleriGoster(sayil[1]);
        tvCvp.setText(map.get("soru"));
        tvCvp2.setText(map.get("cevap"));

        ileri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i!=soru_liste.size()+1){
                a+= 1;
                Database db = new Database(getApplicationContext());
                HashMap<String, String> map = db.kelimeleriGoster(sayil[i]);
                tvCvp.setText(map.get("soru"));
                tvCvp2.setText(map.get("cevap"));

                i++;
            }}
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
            ileri.setClickable(true);
        }}
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

        }
        rootLayout.startAnimation(flipAnimation);
    }
}
