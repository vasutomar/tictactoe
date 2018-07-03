package lenovo.tic_tac_toe;

import android.graphics.Point;
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
        setContentView(board);
    }
}
