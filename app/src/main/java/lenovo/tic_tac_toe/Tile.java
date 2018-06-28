package lenovo.tic_tac_toe;

import android.support.v7.app.AppCompatActivity;

public class Tile extends AppCompatActivity {
    Piece piece;
    public Tile() {
        setNullPiece();
    }
    public void setNullPiece() {
        piece = new Piece("Null");
    }
    public void setDefinitePiece(String newForm) {
        piece.changeForm(newForm);
    }
    public boolean isOccupied() {
        if(piece.getForm().equals("Null"))
            return false;
        return true;
    }
}
