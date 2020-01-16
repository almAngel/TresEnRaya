package alm.android.tresenraya;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FinalActivity extends AppCompatActivity {

    @BindView(R.id.winner)
    TextView winnerText;

    @BindView(R.id.backButton)
    Button backButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null) {
            int winner = bundle.getInt("winner");

            if(winner == 0) {
                winnerText.setText(winnerText.getText() + "circulo");
            } else if(winner == 1){
                winnerText.setText(winnerText.getText() + "cruz");
            }
        } else {
            winnerText.setText("Tablas");
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(FinalActivity.class, StartActivity.class);
            }
        });
    }

}
