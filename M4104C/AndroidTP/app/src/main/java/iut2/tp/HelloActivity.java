package iut2.tp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import iut2.tp.R;

public class HelloActivity extends AppCompatActivity {

    public static final String PRENOM_KEY = "prenom_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hello_activity);

        String prenom = getIntent().getStringExtra(PRENOM_KEY);

        TextView textView = (TextView) findViewById(R.id.prenom_hello);
        textView.setText(prenom);
    }

    public void onClickUpdatePrenom(View view) {
        setResult(RESULT_OK);
        super.finish();
    }

    public void onClickBackToListeExos(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
