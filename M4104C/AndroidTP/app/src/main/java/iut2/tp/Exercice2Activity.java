package iut2.tp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Exercice2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // On charge le XML pour créer l'arbre graphique
        setContentView(R.layout.activity_exercice2);
    }

    public void submitQuizz(View view) {
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        TextView textViewInfo = (TextView) findViewById(R.id.quizz_info);

        if (radioGroup.getCheckedRadioButtonId() == R.id.quizz_nul) {
            textViewInfo.setText("Bonne réponse !");
        } else {
            textViewInfo.setText("Mauvaise réponse");
        }
    }
}