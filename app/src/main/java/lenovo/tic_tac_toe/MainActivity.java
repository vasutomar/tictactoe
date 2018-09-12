package lenovo.tic_tac_toe;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText player1;
    private EditText player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting up the EditTexts
        player1 = findViewById(R.id.Player1);
        player2 = findViewById(R.id.Player2);
    }
    //OnClick method for a new game.
    public void NewGame(View v) {
        Intent k = new Intent(this,GameActivity.class);
        k.putExtra("Player1",player1.getText().toString());
        k.putExtra("Player2",player2.getText().toString());
        startActivity(k);
    }
    //OnClick method for exiting the application
    public void ExitApp(View v) {
        finishAffinity();
        System.exit(0);
    }
    //onbackPressed exits the game.
    @Override
    public void onBackPressed() {
        finishAffinity();
        System.exit(0);
    }
    public void RulesView(View v) {
        Intent i = new Intent(this,RulesActivity.class);
        startActivity(i);
    }
}
