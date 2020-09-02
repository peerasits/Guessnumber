package th.ac.su.cp.guessnumber2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import th.ac.su.cp.guessnumber2.model.Answer;

public class MainActivity extends AppCompatActivity {

    Answer a;
    int score = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //answer = new Random().nextInt(100)+1;
        a = new Answer();


        //Debug
        //Log.i("MainActivity","ค่าของ answer ที่สุ่มได้ คือ "+a.getValue());

        Button guessButton = findViewById(R.id.guess_button);
        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               /* Toast t = Toast.makeText(MainActivity.this, "Hello Android", Toast.LENGTH_LONG);
                t.show();*/
                EditText numberEditText = findViewById(R.id.number_edit_text);
                String numText = numberEditText.getText().toString();
                int num = Integer.parseInt(numText);
                Answer.GuessResult result = a.checkAnswer(num);

                //refactor code with method
                //Toast t = Toast.makeText(MainActivity.this, a.checkAnswer(num), Toast.LENGTH_LONG);
                //t.show();

                TextView resultTextView = findViewById(R.id.result_text_view);
                switch (result) {
                    case OK:
                        score++;
                        Log.i("MainActivity","คะแนนทั้งหมด "+ score);
                        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                        dialog.setTitle("ผลลัพธ์");
                        dialog.setMessage("ถูกต้องครับ\n\nคุณต้องการเล่นเกมส์ใหม่หรือไม่");
                        dialog.setPositiveButton("ํYES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                a = new Answer();
                            }
                        });
                        dialog.setNegativeButton("NO", null);
                        dialog.show();
                        resultTextView.setText("");
                        break;
                    case OVER:
                        resultTextView.setText("ผิดครับ มากเกินไป");
                        /*dialog.setMessage("ผิดครับ มากเกินไป");
                        dialog.setPositiveButton("OK", null);*/
                        break;
                    case UNDER:
                        resultTextView.setText("ผิดครับ น้อยเกินไป");
                        /*dialog.setMessage("ผิดครับ น้อยกินไป");
                        dialog.setPositiveButton("OK", null);*/
                        break;
                }


                //  เพิ่มเติม


            }

        });

        //Toast t = Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_LONG);
        //t.show();

        Button exitButton = findViewById(R.id.exit_button);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle(R.string.exit_message);
                dialog.setMessage("Are you sure you want to exit ?");
                dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                dialog.setNegativeButton("No", null);
                dialog.show();
                //finish();
            }
        });

        Button scoreButton = findViewById(R.id.score_button);
        scoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,ScoreActivity.class);
                i.putExtra("score",score);
                startActivity(i);
            }
        });

    }

}