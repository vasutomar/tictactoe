package lenovo.tic_tac_toe;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class Board extends View{

    //Obtaining ScreenHeight and ScreenWidth
    public int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    public int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

    //Main Board Matrix
    public Tile[][] matrix = new Tile[3][3];

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

    boolean won = false;
    boolean dispwinner = false;

    //Matrix Coordinates.
    int matrixX;
    int matrixY;

    //player who won
    String playerWon;

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
                matrix[row][col] = new Tile();
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

        //updating matrix
        if(flag==true && !won) {
            matrixX = (int)(clickedX/(screenWidth/3));
            matrixY = (int)(clickedY/(screenWidth/3));
            updateMatrix(matrixX, matrixY);
        }
        //Drawing Pieces
        for(i=0;i<3;i++) {
            for(j=0;j<3;j++) {
                if(matrix[i][j].getPiece().equals("Player1"))
                    canvas.drawCircle(getDrawPoint(i),getDrawPoint(j),40,paint1);
                else if(matrix[i][j].getPiece().equals("Player2"))
                    canvas.drawCircle(getDrawPoint(i),getDrawPoint(j),40,paint2);
            }
        }
        checkWonCondition();
        if(won && dispwinner == false) {
            String y;
            dispwinner = true;
            if(getPlayerWhoWon().equals("Player1"))
                y="RED";
            else
                y="GREEN";
            Toast.makeText(ctx,"Player "+y+" Won",Toast.LENGTH_SHORT).show();
        }
        if(allfilled() && !won) {
            Toast.makeText(ctx,"Tie",Toast.LENGTH_SHORT).show();
        }
    }
    public String getPlayerWhoWon() {
        return playerWon;
    }
    public boolean allfilled() {
        for(int row = 0;row <3;row ++) {
            for(int col = 0;col<3;col++) {
                if(!matrix[row][col].isOccupied())
                    return false;
            }
        }
        return true;
    }
    public void updateMatrix(int i,int j) {
        if(turn == 0 && !matrix[i][j].isOccupied()) {
            matrix[i][j].setDefinitePiece("Player1");
            turn = 1;
        }
        else if(turn == 1 && !matrix[i][j].isOccupied()) {
            matrix[i][j].setDefinitePiece("Player2");
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
    public void checkWonCondition() {
        if(matrix[0][0].getPiece().equals(matrix[1][1].getPiece()) &&
                matrix[2][2].getPiece().equals(matrix[1][1].getPiece()) && matrix[0][0].isOccupied()) {
            won = true;
            playerWon = matrix[0][0].getPiece();
        }
        else if(matrix[0][2].getPiece().equals(matrix[1][1].getPiece()) &&
                matrix[1][1].getPiece().equals(matrix[2][0].getPiece()) && matrix[0][2].isOccupied()) {
            won = true;
            playerWon = matrix[1][1].getPiece();
        }
        else if(matrix[0][0].getPiece().equals(matrix[0][1].getPiece()) &&
                matrix[0][1].getPiece().equals(matrix[0][2].getPiece()) && matrix[0][0].isOccupied()) {
            won = true;
            playerWon = matrix[0][0].getPiece();
        }
        else if(matrix[1][0].getPiece().equals(matrix[1][1].getPiece()) &&
                matrix[1][1].getPiece().equals(matrix[1][2].getPiece()) && matrix[1][0].isOccupied()) {
            won = true;
            playerWon = matrix[1][0].getPiece();
        }
        else if(matrix[2][0].getPiece().equals(matrix[2][1].getPiece()) &&
                matrix[2][1].getPiece().equals(matrix[2][2].getPiece()) && matrix[2][0].isOccupied()) {
            won = true;
            playerWon = matrix[2][0].getPiece();
        }
        else if(matrix[0][0].getPiece().equals(matrix[1][0].getPiece()) &&
                matrix[1][0].getPiece().equals(matrix[2][0].getPiece()) && matrix[0][0].isOccupied()) {
            won = true;
            playerWon = matrix[0][0].getPiece();
        }
        else if(matrix[0][1].getPiece().equals(matrix[1][1].getPiece()) &&
                matrix[1][1].getPiece().equals(matrix[2][1].getPiece()) && matrix[0][1].isOccupied()) {
            won = true;
            playerWon = matrix[0][1].getPiece();
        }
        else if(matrix[0][2].getPiece().equals(matrix[1][2].getPiece()) &&
                matrix[1][2].getPiece().equals(matrix[2][2]) && matrix[0][2].isOccupied()) {
            won = true;
            playerWon = matrix[0][2].getPiece();
        }
    }
}
