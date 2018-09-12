package lenovo.tic_tac_toe;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

public class Board extends AppCompatActivity {


    public Board_View boardView;
    Board(Context ctx,String player1,String player2) {
        boardView = new Board_View(ctx,player1,player2);
    }

    public class Board_View extends View{
        //Obtaining ScreenHeight and ScreenWidth
        public int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
        public int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

        //Player names
        public String player1;
        public String player2;

        //Main Board Matrix
        public Tile[][] matrix = new Tile[3][3];

        //flag to check first click
        boolean flag;

        //Clicked Points
        float clickedX;
        float clickedY;

        //Check for game end
        public int end_flag;

        //3 Paints
        Paint paint1 = new Paint();
        Paint paint2 = new Paint();
        Paint paint = new Paint();

        // Player turn
        int turn;

        //Application context
        Context ctx;

        public boolean won;
        boolean dispwinner;

        //Matrix Coordinates.
        int matrixX;
        int matrixY;

        //player who won
        String playerWon;

        //Constructor
        Board_View(Context context, String player1, String player2) {
            super(context);
            won = false;
            ctx = context;
            end_flag = 0;

            this.player1 = player1;
            this.player2 = player2;

            //Paint1 corresponds to Red Piece
            paint1.setColor(Color.RED);
            paint1.setStyle(Paint.Style.FILL);

            //Paint2 corresponds to Green Piece
            paint2.setColor(Color.GREEN);
            paint2.setStyle(Paint.Style.FILL);

            paint.setAntiAlias(true);
            paint.setStrokeWidth(10f);
            paint.setColor(Color.rgb(245, 125, 10));
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeJoin(Paint.Join.ROUND);

            init();
        }

        //Initializes the board
        public void init() {

            //Initializing all matrix elements to 2 which mean no element at all
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    matrix[row][col] = new Tile();
                }
            }
            turn = 0;
            flag = false;
            won = false;
            end_flag = 0;
            dispwinner = false;
            invalidate();
        }

        @Override
        protected void onDraw(Canvas canvas) {

            int i, j;
            super.onDraw(canvas);

            //Setting up grid
            canvas.drawLine(0, screenWidth / 3, screenWidth, screenWidth / 3, paint);
            canvas.drawLine(0, 2 * (screenWidth / 3), screenWidth, 2 * (screenWidth / 3), paint);
            canvas.drawLine(0, screenWidth, screenWidth, screenWidth, paint);
            canvas.drawLine(screenWidth / 3, 0, screenWidth / 3, screenWidth, paint);
            canvas.drawLine(2 * (screenWidth / 3), 0, 2 * (screenWidth / 3), screenWidth, paint);

            //updating matrix
            if (flag == true && !won) {
                matrixX = (int) (clickedX / (screenWidth / 3));
                matrixY = (int) (clickedY / (screenWidth / 3));
                updateMatrix(matrixX, matrixY);
            }

            //Drawing Pieces
            for (i = 0; i < 3; i++) {
                for (j = 0; j < 3; j++) {
                    if (matrix[i][j].getPiece().equals("Player1"))
                        canvas.drawCircle(getDrawPoint(i), getDrawPoint(j), 40, paint1);
                    else if (matrix[i][j].getPiece().equals("Player2"))
                        canvas.drawCircle(getDrawPoint(i), getDrawPoint(j), 40, paint2);
                }
            }
            checkWonCondition();
            if (won && !dispwinner) {
                String y;
                dispwinner = true;
                if (getPlayerWhoWon().equals("Player1")) {
                    y = player1;
                    if (y.equals("")) {
                        y = "RED ";
                    }
                } else {
                    y = player2;
                    if (y.equals("")) {
                        y = "GREEN ";
                    }
                }
                AlertDialog.Builder builder1 = new AlertDialog.Builder(ctx);
                builder1.setTitle("Game Over");
                builder1.setMessage(y + " Won");
                builder1.setCancelable(false);
                builder1.setPositiveButton("Play again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        init();
                    }
                });
                builder1.setNegativeButton("Main Menu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog alert11 = builder1.create();
                alert11.setCancelable(false);
                alert11.show();
            }
            if (allfilled() && !won) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(ctx);
                builder1.setMessage("Tie");
                builder1.setCancelable(false);
                builder1.setPositiveButton("Play again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        init();
                    }
                });
                builder1.setNegativeButton("Main Menu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog alert11 = builder1.create();
                alert11.show();
                alert11.setCancelable(false);
                end_flag = 1;
            }
        }

        //Returns the Player who won the game
        public String getPlayerWhoWon() {
            return playerWon;
        }

        //Checks if all tiles on the board are filled.
        public boolean allfilled() {
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (!matrix[row][col].isOccupied())
                        return false;
                }
            }
            return true;
        }

        //Updates the Tile matrix
        public void updateMatrix(int i, int j) {
            Random rand = new Random();
            int chance = rand.nextInt(50) % 2;
            if (chance == 0 && !matrix[i][j].isOccupied()) {
                matrix[i][j].setDefinitePiece("Player1");
                turn = 1;
            } else if (chance == 1 && !matrix[i][j].isOccupied()) {
                matrix[i][j].setDefinitePiece("Player2");
                turn = 0;
            }
        }

        //returns the coordinate for drawing of piece
        public float getDrawPoint(int x) {
            if (x == 0)
                return screenWidth / 6;
            else if (x == 1)
                return screenWidth / 2;
            else
                return 5 * (screenWidth / 6);
        }

        //Sets the Touch coordinates
        @Override
        public boolean onTouchEvent(MotionEvent e) {
            if (e.getAction() == MotionEvent.ACTION_DOWN) {
                flag = true;
                clickedX = e.getX();
                clickedY = e.getY();
                invalidate();
            }
            return true;
        }

        //Sets boolean won to true if any player has won the game.
        //also sets the playerWon value
        public void checkWonCondition() {
            if (matrix[0][0].getPiece().equals(matrix[1][1].getPiece()) &&
                    matrix[2][2].getPiece().equals(matrix[1][1].getPiece()) && matrix[0][0].isOccupied()) {
                won = true;
                playerWon = matrix[0][0].getPiece();
            } else if (matrix[0][2].getPiece().equals(matrix[1][1].getPiece()) &&
                    matrix[1][1].getPiece().equals(matrix[2][0].getPiece()) && matrix[0][2].isOccupied()) {
                won = true;
                playerWon = matrix[1][1].getPiece();
            } else if (matrix[0][0].getPiece().equals(matrix[0][1].getPiece()) &&
                    matrix[0][1].getPiece().equals(matrix[0][2].getPiece()) && matrix[0][0].isOccupied()) {
                won = true;
                playerWon = matrix[0][0].getPiece();
            } else if (matrix[1][0].getPiece().equals(matrix[1][1].getPiece()) &&
                    matrix[1][1].getPiece().equals(matrix[1][2].getPiece()) && matrix[1][0].isOccupied()) {
                won = true;
                playerWon = matrix[1][0].getPiece();
            } else if (matrix[2][0].getPiece().equals(matrix[2][1].getPiece()) &&
                    matrix[2][1].getPiece().equals(matrix[2][2].getPiece()) && matrix[2][2].isOccupied()) {
                won = true;
                playerWon = matrix[2][2].getPiece();
            } else if (matrix[0][0].getPiece().equals(matrix[1][0].getPiece()) &&
                    matrix[1][0].getPiece().equals(matrix[2][0].getPiece()) && matrix[0][0].isOccupied()) {
                won = true;
                playerWon = matrix[0][0].getPiece();
            } else if (matrix[0][1].getPiece().equals(matrix[1][1].getPiece()) &&
                    matrix[1][1].getPiece().equals(matrix[2][1].getPiece()) && matrix[0][1].isOccupied()) {
                won = true;
                playerWon = matrix[0][1].getPiece();
            } else if (matrix[0][2].getPiece().equals(matrix[1][2].getPiece()) &&
                    matrix[1][2].getPiece().equals(matrix[2][2].getPiece()) && matrix[2][2].isOccupied()) {
                won = true;
                playerWon = matrix[2][2].getPiece();
            }
        }
    }
}
