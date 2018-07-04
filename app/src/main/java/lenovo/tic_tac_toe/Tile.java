package lenovo.tic_tac_toe;

import android.support.v7.app.AppCompatActivity;

public class Tile extends AppCompatActivity {
    Piece piece;
    public Tile() {
        setNullPiece();
    }
    public void setNullPiece() {
        //Initialises the tile with null piece, equivalent to no piece at all.
        piece = new Piece("Null");
    }
    public String getPiece() {
        return piece.getForm();
    }
    public void setDefinitePiece(String newForm) {
        //Sets Piece according to user turn.
        piece.changeForm(newForm);
    }
    public boolean isOccupied() {
        //Returns true when the tile has a piece on it.
        if(piece.getForm().equals("Null"))
            return false;
        return true;
    }
}
