package lenovo.tic_tac_toe;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class Board extends View {

    //Obtaining ScreenHeight and ScreenWidth
    public int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    public int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

    //Main Board Matrix
    public int[][] matrix = new int[3][3];

    //Clicked Points
    float clickedX;
    float clickedY;

    // 3 Paints
    Paint paint1 = new Paint();
    Paint paint2 = new Paint();
    Paint paint = new Paint();

    // Player turn
    int turn;

    //Application context
    Context ctx;

    //Matrix Coordinates.
    int matrixX;
    int matrixY;
    public Board(Context context) {

        super(context);

        ctx = context;

        paint1.setColor(Color.RED);
        paint1.setStyle(Paint.Style.FILL);

        paint2.setColor(Color.GREEN);
        paint2.setStyle(Paint.Style.FILL);

        paint.setAntiAlias(true);
        paint.setStrokeWidth(10f);
        paint.setColor(Color.rgb(245, 125, 10));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);

        init();
    }
    public void init() {

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                matrix[row][col] = 2;
            }
        }
        turn = 5;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        int i,j;
        super.onDraw(canvas);

        //Setting up grid
        canvas.drawLine(0,screenWidth/3,screenWidth,screenWidth/3, paint);
        canvas.drawLine(0,2*(screenWidth/3),screenWidth,2*(screenWidth/3), paint);
        canvas.drawLine(0,screenWidth,screenWidth,screenWidth, paint);
        canvas.drawLine(screenWidth/3,0,screenWidth/3,screenWidth, paint);
        canvas.drawLine(2*(screenWidth/3),0,2*(screenWidth/3),screenWidth,paint);

        //getting points for matrix
        matrixX = getMatrixPointX();
        matrixY = getMatrixPointY();

        //updating matrix
        updateMatrix(matrixX,matrixY);

        //Drawing Pieces
        for(i=0;i<3;i++) {
            for(j=0;j<3;j++) {
                if(matrix[i][j]==0) {
                    canvas.drawCircle(getDrawPoint(i),getDrawPoint(j),40,paint1);
                }
                else if(matrix[i][j]==1) {
                    canvas.drawCircle(getDrawPoint(i),getDrawPoint(j),40,paint2);
                }
            }
        }
    }
    public void updateMatrix(int i,int j) {
        if(turn == 0) {
            matrix[i][j] = turn;
            turn = 1;
        }
        else if(turn == 1) {
            matrix[i][j] = turn;
            turn = 0;
        }
    }
    public float getDrawPoint(int x) {
        if(x==0)
            return screenWidth/6;
        else if(x==1)
            return screenWidth/2;
        else
            return 5*(screenWidth/6);
    }
    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if(e.getAction() == MotionEvent.ACTION_DOWN) {
            turn = 0;
            clickedX = e.getX();
            clickedY = e.getY();
            invalidate();
        }
        return true;
    }
    public int getMatrixPointX() {
        if(clickedX<screenWidth/3)
            return 0;
        else if(clickedX>screenWidth/3 && clickedX<screenWidth*0.66)
            return 1;
        else if(clickedX>0.66*screenWidth && clickedX<screenWidth)
            return 2;
        else
            return 0;
    }
    public int getMatrixPointY() {
        if(clickedY<screenWidth/3)
            return 0;
        else if(clickedY>screenWidth/3 && clickedY<screenWidth*0.66)
            return 1;
        else if(clickedY>0.66*screenWidth && clickedY<screenWidth)
            return 2;
        else
            return 0;
    }
}
