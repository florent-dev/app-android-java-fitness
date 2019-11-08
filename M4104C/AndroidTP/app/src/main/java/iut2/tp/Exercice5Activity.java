package iut2.tp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;

public class Exercice5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // On charge le XML pour créer l'arbre graphique
        setContentView(R.layout.activity_exercice5);

        NumberPicker np = findViewById(R.id.numberPicker);
        np.setMinValue(1);
        np.setMaxValue(9);
        np.setWrapSelectorWheel(true);
    }

    public void validerChoixTable(View view) {
        NumberPicker np = findViewById(R.id.numberPicker);
        int choixTable = np.getValue();

        Intent intent = new Intent(this, TableMultiplicationActivity.class);
        intent.putExtra(TableMultiplicationActivity.TABLE, choixTable);
        startActivity(intent);
    }
}
