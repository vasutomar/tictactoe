package lenovo.tic_tac_toe;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class Board extends View {

    //Obtaining ScreenHeight and ScreenWidth
    public int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    public int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

    //Main Board Matrix
    public int[][] matrix = new int[3][3];

    //flag to check first click
    boolean flag = false;

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

    boolean won;

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

        //Initializing all matrix elements to 2 which mean no element at all
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                matrix[row][col] = 2;
            }
        }
        turn = 0;
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


        //updating matrix
        if(flag==true) {
            matrixX = (int)(clickedX/(screenWidth/3));
            matrixY = (int)(clickedY/(screenWidth/3));
            updateMatrix(matrixX, matrixY);
        }
        //Drawing Pieces
        for(i=0;i<3;i++) {
            for(j=0;j<3;j++) {
                if(matrix[i][j]==0)
                    canvas.drawCircle(getDrawPoint(i),getDrawPoint(j),40,paint1);
                else if(matrix[i][j]==1)
                    canvas.drawCircle(getDrawPoint(i),getDrawPoint(j),40,paint2);
            }
        }
        int x = won();
        if(won == true) {
            Log.e("Won",String.valueOf(x));

        }
    }
    public void updateMatrix(int i,int j) {
        if(turn == 0 && matrix[i][j]==2) {
            matrix[i][j] = turn;
            turn = 1;
        }
        else if(turn == 1 && matrix[i][j]==2) {
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
            flag = true;
            clickedX = e.getX();
            clickedY = e.getY();
            invalidate();
        }
        return true;
    }
    public int won() {
        if(matrix[0][0] == matrix[1][1] && matrix[2][2] == matrix[1][1] && matrix[0][0]!=2) {
            won = true;
            return matrix[0][0];
        }
        else if(matrix[0][2] == matrix[1][1] && matrix[1][1] == matrix [2][0] && matrix[0][2]!=2) {
            won=true;
            return matrix[1][1];
        }
        for(int i=0;i<3;i++) {
            if(matrix[i][0] == matrix[i][1] && matrix[i][1] == matrix[i][2] && matrix[i][0]!=2)
                won = true;
                return matrix[i][0];
        }
        for(int i=0;i<3;i++) {
            if(matrix[0][i] == matrix[1][i] && matrix[1][i] == matrix[2][i] && matrix[0][i]!=2)
                won = true;
                return matrix[0][i];
        }
        won = false;
        return 2;
    }
}
