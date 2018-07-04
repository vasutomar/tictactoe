package lenovo.tic_tac_toe;

import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public Board board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void NewGame(View v) {
        board = new Board(this);
        Drawable drawable = getDrawable(R.drawable.boardbaack);
        board.setBackground(drawable);
        setContentView(board);
    }
    public void ExitApp(View v) {
        finishAffinity();
        System.exit(0);
    }
}
