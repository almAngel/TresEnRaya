package alm.android.tresenraya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.content.res.Resources;
import android.media.Image;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.casillas)
    GridLayout layout;

    @BindView(R.id.round)
    TextView roundView;

    @BindView(R.id.player)
    TextView playerView;

    private static int width = 3;
    private static int height = 3;
    //private static int[][] values = new int[width][height];
    private static TIRCell[][] imageViews = new TIRCell[width][height];
    private static final int BLANK = -1;
    private static final int CIRCLE = 0;
    private static final int CROSS = 1;
    private static int round = 0;
    private static boolean someoneWon = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        findViews();
        checkRound();

    }

    private void checkRound() {
        if(round < 9) {
            if(round%2 == 0) {
                roundView.setText("TURNO " + round);
                playerView.setText("JUGADOR 1 (circulo)");
            } else if(round%2 == 1) {
                roundView.setText("TURNO " + round);
                playerView.setText("JUGADOR 2 (cruz)");
            }
        }

    }

    private void findViews() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Resources res = getResources();
                int id = res.getIdentifier("i" + i + j, "id", this.getPackageName());
                TIRCell iv = findViewById(id);
                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(iv.value == -1) {
                            iv.setValue(round%2);
                            setSources(iv);
                            round++;
                            checkForWinner();
                            checkRound();
                            checkForDraw();
                        }
                    }
                });
                imageViews[i][j] = iv;
            }
        }
    }

    private void setSources(TIRCell v) {
        switch (v.value) {
            case BLANK:
                v.setImageResource(0);
                break;

            case CIRCLE:
                v.setImageResource(R.drawable.ic_circle);
                break;

            case CROSS:
                v.setImageResource(R.drawable.ic_cross);
                break;
        }
    }

    //CHECK IN EVERY DIRECTION
    private void checkForWinner() {
        Intent intent = new Intent(this, FinalActivity.class);

        if(round <= 9) {
            intent.putExtra("winner", (round-1)%2);
            int steps = 0;

            for(int i = 0; i < width; i++) {
                for(int j = 0; j < height; j++) {
                    //System.out.println("X: " + i + " Y: " + j);
                    //ORIGINAL VALUE
                    int og = imageViews[i][j].value;

                    if(og != -1) {
                        // NORTH DIRECTION
                        while(steps < 3) {
                            int val = (inbounds(i,j-steps))?imageViews[i][j-steps].value: -1;

                            if(val == og) {
                                steps++;
                            } else {
                                break;
                            }

                            if(steps == 3) {
                                someoneWon = true;
                                startActivity(intent);
                            }
                        }
                        steps = 0;

                        // NORTH-EAST DIRECTION
                        while(steps < 3) {
                            int val = (inbounds(i+steps,j-steps))?imageViews[i+steps][j-steps].value: -1;

                            if(val == og) {
                                steps++;
                            } else {
                                break;
                            }

                            if(steps == 3) {
                                someoneWon = true;
                                startActivity(intent);
                            }
                        }
                        steps = 0;

                        // EAST DIRECTION
                        while(steps < 3) {
                            int val = (inbounds(i+steps,j))?imageViews[i+steps][j].value: -1;

                            if(val == og) {
                                steps++;
                            } else {
                                break;
                            }

                            if(steps == 3) {
                                someoneWon = true;
                                startActivity(intent);
                            }
                        }
                        steps = 0;

                        // SOUTH-EAST DIRECTION
                        while(steps < 3) {
                            int val = (inbounds(i+steps,j+steps))?imageViews[i+steps][j+steps].value: -1;

                            if(val == og) {
                                steps++;
                            } else {
                                break;
                            }

                            if(steps == 3) {
                                someoneWon = true;
                                startActivity(intent);
                            }
                        }
                        steps = 0;

                        // SOUTH DIRECTION
                        while(steps < 3) {
                            int val = (inbounds(i,j+steps))?imageViews[i][j+steps].value: -1;

                            if(val == og) {
                                steps++;
                            } else {
                                break;
                            }

                            if(steps == 3) {
                                someoneWon = true;
                                startActivity(intent);
                            }
                        }
                        steps = 0;

                        // SOUTH-WEST DIRECTION
                        while(steps < 3) {
                            int val = (inbounds(i-steps,j-steps))?imageViews[i-steps][j-steps].value: -1;

                            if(val == og) {
                                steps++;
                            } else {
                                break;
                            }

                            if(steps == 3) {
                                someoneWon = true;
                                startActivity(intent);
                            }
                        }
                        steps = 0;

                        // WEST DIRECTION
                        while(steps < 3) {
                            int val = (inbounds(i-steps,j))?imageViews[i-steps][j].value: -1;

                            if(val == og) {
                                steps++;
                            } else {
                                break;
                            }

                            if(steps == 3) {
                                someoneWon = true;
                                startActivity(intent);
                            }
                        }
                        steps = 0;

                        // NORTH-WEST DIRECTION
                        while(steps < 3) {
                            int val = (inbounds(i-steps,j-steps))?imageViews[i-steps][j-steps].value: -1;

                            if(val == og) {
                                steps++;
                            } else {
                                break;
                            }

                            if(steps == 3) {
                                someoneWon = true;
                                startActivity(intent);
                            }
                        }
                        steps = 0;
                    }

                }
            }
        }
    }

    public void checkForDraw() {
        Intent intent = new Intent(this, FinalActivity.class);
        if(round == 9 && !someoneWon) {
            startActivity(intent);
        }
    }

    public static boolean inbounds(int x, int y) {
        if(x >= 0 && x < width) {
            if(y >= 0 && y < height) {
                return true;
            }
        }
        return false;
    }

    public int[] getPos(TIRCell v) {
        int[] point = new int[2];
        for(int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if(imageViews[i][j].getId() == v.getId()) {
                    point[0] = i;
                    point[1] = j;
                }
            }
        }
        return point;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        outState.putInt("round", round);


        super.onSaveInstanceState(outState, outPersistentState);
    }
}
