package pau.kelezber;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class donenyer2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donenyer2);
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
