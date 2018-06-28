package lenovo.tic_tac_toe;

import android.support.v7.app.AppCompatActivity;

public class Piece extends AppCompatActivity {
    private String type;
    public Piece(String form) {
        type = form;
    }
    public void changeForm(String form) {
        type = form;
    }
    public String getForm() {
        return type;
    }
}
