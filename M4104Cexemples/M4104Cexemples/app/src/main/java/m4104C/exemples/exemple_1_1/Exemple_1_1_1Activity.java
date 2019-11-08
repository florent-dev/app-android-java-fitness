package m4104C.exemples.exemple_1_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import m4104C.exemples.R;

public class Exemple_1_1_1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Gestionnaire d'agencement
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        // To
        EditText to = new EditText(this);
        to.setText(R.string.to);
        linearLayout.addView(to);

        // Subject
        EditText subject = new EditText(this);
        subject.setText(R.string.subject);
        linearLayout.addView(subject);

        // Message
        EditText message = new EditText(this);
        message.setText(R.string.message);
        LinearLayout.LayoutParams layoutParamsMessage =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT, 1);
        linearLayout.addView(message, layoutParamsMessage);

        // Send
        Button send = new Button(this);
        ViewGroup.LayoutParams layoutParamsSend =
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        send.setLayoutParams(layoutParamsSend);
        send.setText(R.string.send);
        linearLayout.setHorizontalGravity(Gravity.CENTER);
        linearLayout.addView(send);

        setContentView(linearLayout);
    }
}
