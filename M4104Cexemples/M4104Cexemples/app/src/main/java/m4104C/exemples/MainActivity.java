package m4104C.exemples;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.TreeMap;

import m4104C.exemples.exemple_1_1.Exemple_1_1_1Activity;
import m4104C.exemples.exemple_1_1.Exemple_1_1_2Activity;
import m4104C.exemples.exemple_1_1.Exemple_1_1_3Activity;
import m4104C.exemples.exemple_1_1.Exemple_1_1_4Activity;
import m4104C.exemples.exemple_1_1.Exemple_1_1_5Activity;
import m4104C.exemples.exemple_1_1.Exemple_1_1_6Activity;
import m4104C.exemples.exemple_1_1.Exemple_1_1_7Activity;

/**
 * Activité affichant une listView des différents exemples du cours
 * Lors d'un clique sur un exemple, l'activité associée est lancée
 *
 */
public class MainActivity extends AppCompatActivity {

    // VIEWS
    private ListView listView;

    // DATA
    private TreeMap<String, Class> exemples = new TreeMap<String, Class>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Récupération des vues
        listView = (ListView) findViewById(R.id.listView);

        // Initialisation des data
        exemples.put("Exemple 1.1.1 : hiérarchie de vues dans le code", Exemple_1_1_1Activity.class);
        exemples.put("Exemple 1.1.2 : hiérarchie de vues dans un fichier XML", Exemple_1_1_2Activity.class);
        exemples.put("Exemple 1.1.3 : utilisation du RelativeLayout", Exemple_1_1_3Activity.class);
        exemples.put("Exemple 1.1.4 : évenement avec listener spécifique", Exemple_1_1_4Activity.class);
        exemples.put("Exemple 1.1.5 : évenement avec l'activité comme listener", Exemple_1_1_5Activity.class);
        exemples.put("Exemple 1.1.6 : évenement avec simplification d'écriture pour le onClick", Exemple_1_1_6Activity.class);
        exemples.put("Exemple 1.1.7 : un code propre", Exemple_1_1_7Activity.class);

        // Récupération de la liste des clés pour l'adapter de la listView
        final String[] keyExemples = exemples.keySet().toArray(new String[0]);

        // Création d'un adapter et ajout des clés dans la listView
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, keyExemples);
        listView.setAdapter(adapter);

        // Ecoute d'un évenement clique sur chaque item de la listView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // Lancer l'activité associée à l'item (clé)
                Intent intent = new Intent(MainActivity.this, exemples.get(keyExemples[i]));
                startActivity(intent);
            }
        });
    }
}
