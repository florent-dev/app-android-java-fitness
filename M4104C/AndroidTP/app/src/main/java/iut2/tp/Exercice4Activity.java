package iut2.tp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Exercice4Activity extends AppCompatActivity {

    public final static int HELLO_REQUEST = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // On charge le XML pour créer l'arbre graphique
        setContentView(R.layout.activity_exercice4);
    }

    public void onClickSubmit(View view) {
        EditText composantEditText = findViewById(R.id.edit);
        String prenom = composantEditText.getText().toString();
        Intent intent = new Intent(this, HelloActivity.class);
        intent.putExtra(HelloActivity.PRENOM_KEY, prenom);
        startActivityForResult(intent, HELLO_REQUEST);
    }

    public void onActivityResult() {
        //
    }
}
