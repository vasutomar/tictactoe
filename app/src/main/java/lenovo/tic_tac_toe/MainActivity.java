package lenovo.tic_tac_toe;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public Board board;
    public int chance = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BoardView boardView = new BoardView(this);
        setContentView(boardView);
        Initialize_Board();
        StartGame();
    }
    public void Initialize_Board() {

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        board = new Board();

        int x = board.getTileWidth();
        int y = board.getTileHeight();
    }
    public void StartGame() {

    }
}
