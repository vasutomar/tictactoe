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
    private Tile[][] BoardMatrix = new Tile[4][4];

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
        BoardHeight = ScreenWidth;

        //Initializing Board Matrix.
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                //BoardMatrix[i][j] = new Tile();
            }
        }
    }
    public int getTileHeight() {
        //Returns the Tile Height
        return (int)(BoardHeight/3);
    }
    public int getTileWidth() {
        //Returns the Tile Width
        return (int)(BoardWidth/3);
    }
}
