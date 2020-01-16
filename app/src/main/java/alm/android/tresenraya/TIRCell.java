package alm.android.tresenraya;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;

import java.io.Serializable;

public class TIRCell extends AppCompatImageView implements Serializable {

    public int value = -1;

    public TIRCell(Context context) {
        super(context);
    }

    public TIRCell(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TIRCell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setValue(int player) {
        if(value == -1) {
            value = player;
        }
    }

}
