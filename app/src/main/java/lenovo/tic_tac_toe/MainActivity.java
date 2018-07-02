package lenovo.tic_tac_toe;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public ImageView img;
    public Board board;
    public int chance = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        board = new Board(this);
        setContentView(board);
        StartGame();
    }
    public void StartGame() {

    }
}
