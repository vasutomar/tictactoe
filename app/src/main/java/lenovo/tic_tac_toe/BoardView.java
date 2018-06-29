package lenovo.tic_tac_toe;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class BoardView extends View {

    public Paint paint;
    public Canvas canvas;

    public int locX;
    public int locY;
    public char Player;

    public BoardView(Context context) {
        super(context);
    }
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        paint = new Paint();
    }
    public void SetCoord(int locX,int locY) {
        this.locX = locX;
        this.locY = locY;
    }
    public void setPlayer(char Player) {
        this.Player = Player;
    }
    public void drawpiece() {
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPaint(paint);
        if(Player == 'X') {
            paint.setColor(Color.GREEN);
        }
        else {
            paint.setColor(Color.RED);
        }
        canvas.drawCircle((float)locX,(float)locY,20,paint);
    }
}
