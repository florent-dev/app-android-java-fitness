package iut2.tp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TableMultiplicationActivity extends AppCompatActivity {

    public static final String TABLE = "TABLE";
    private EditText[] editTexts;
    private Multiplication[] multiplications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String choixTable = getIntent().getExtras().get(TABLE).toString();
        setContentView(R.layout.activity_table_multiplication);

        Multiplication.setTable(Integer.parseInt(choixTable));
        LinearLayout linear = findViewById(R.id.multiplications);
        editTexts = new EditText[9];
        multiplications = new Multiplication[9];

        // 1. Boucle pour générer la table
        multiplications = Multiplication.getMultiplications();
        for (Multiplication multiplication : multiplications) {

            // 2. Création de la ligne temporaire
            LinearLayout linearTMP = (LinearLayout) getLayoutInflater().inflate(R.layout.template_calcul, null);

            // 3. Création du texte créant l'opération
            TextView calcul = (TextView) linearTMP.findViewById(R.id.template_calcul);
            calcul.setText((multiplication.getOperande1() + " x " + multiplication.getOperande2() + " = "));

            // 4. Création de l'editText pour interagir avec l'user
            EditText resultat = (EditText) linearTMP.findViewById(R.id.template_resultat);
            resultat.setText((Integer.toString(multiplication.getOperande1() * multiplication.getOperande2())));
            editTexts[multiplication.getOperande1()-1] = resultat;

            // 5. Ajout du LinearLayout
            linear.addView(linearTMP);
        }
    }

    public void onClickMultiplications(View view) {
        int mauvaisesReponses = 0;

        for (Multiplication multiplication: multiplications) {
            EditText editText = editTexts[multiplication.getOperande1()-1];

            // Si la réponse donnée est bien correcte
            if (!multiplication.checkResult(Integer.parseInt(editText.getText().toString()))) {
                mauvaisesReponses++;
            }
        }

        Intent intent;

        if (mauvaisesReponses > 0) {
            intent = new Intent(this, ErreurActivity.class);
            intent.putExtra(ErreurActivity.MAUVAISES_REPONSES, mauvaisesReponses);
        } else {
            intent = new Intent(this, FelicitationActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        }

        startActivity(intent);
    }

}
