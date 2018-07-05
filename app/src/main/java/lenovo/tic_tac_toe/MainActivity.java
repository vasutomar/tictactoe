package lenovo.tic_tac_toe;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Board board;
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

        board = new Board(this,player1.getText().toString(),player2.getText().toString());
        Drawable drawable = getDrawable(R.drawable.b);
        board.setBackground(drawable);
        setContentView(board);
    }
    //OnClick method for exiting the application
    public void ExitApp(View v) {
        finishAffinity();
        System.exit(0);
    }
    //onbackPressed resets the game.
    @Override
    public void onBackPressed() {
        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage( getBaseContext().getPackageName() );
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}
