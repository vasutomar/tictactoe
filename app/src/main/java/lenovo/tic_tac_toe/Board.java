package lenovo.tic_tac_toe;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.WindowManager;

public class Board extends AppCompatActivity {

    private int BoardHeight;
    private int BoardWidth;
    private int ScreenWidth;
    private int ScreenHeight;
    private Tile[][] BoardMatrix = new Tile[3][3];

    public Board() {

        //Getting Device Display
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        //Extracting Device Resolution
        ScreenWidth = size.x;
        ScreenHeight = size.y;

        //Assigning Board Dimentions
        BoardWidth = ScreenWidth;
        BoardHeight = (int) (ScreenHeight*0.75);

        //Initializing Board Matrix.
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                BoardMatrix[i][j] = new Tile();
            }
        }
    }
}
