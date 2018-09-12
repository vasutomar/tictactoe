package lenovo.tic_tac_toe;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class GameActivity extends AppCompatActivity {

    public int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    public int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private Board board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Bundle extras = getIntent().getExtras();
        String player1 = extras.getString("Player1");
        String player2 = extras.getString("Player2");

        board = new Board(this,player1,player2);
        Drawable drawable = getDrawable(R.drawable.b);
        board.boardView.setBackground(drawable);
        super.onCreate(savedInstanceState);
        setContentView(board.boardView);
    }
}
