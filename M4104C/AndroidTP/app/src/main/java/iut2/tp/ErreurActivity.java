package iut2.tp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ErreurActivity extends AppCompatActivity {

    public static final String MAUVAISES_REPONSES = "MAUVAISES_REPONSES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String nbErreurs = getIntent().getExtras().get(MAUVAISES_REPONSES).toString();
        setContentView(R.layout.activity_erreur);
        TextView nbErreursText = findViewById(R.id.errorsNb);
        nbErreursText.setText((nbErreursText.getText() + nbErreurs));
    }

    public void onClickRetourCorriger(View view) {
        finish();
    }

    public void onClickRetourTables(View view) {
        Intent intent = new Intent(getApplicationContext(), Exercice5Activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
