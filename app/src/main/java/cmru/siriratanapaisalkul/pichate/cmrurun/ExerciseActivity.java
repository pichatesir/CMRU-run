package cmru.siriratanapaisalkul.pichate.cmrurun;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Random;

public class ExerciseActivity extends AppCompatActivity {

    //Explicit
    private String userIDString,nameString,goldString, avataString;
    private ImageView imageView;
    private TextView nameTextView, stationTextView;
    private TextView questionTextView;
    private RadioGroup radioGroup;
    private RadioButton choice1RadioButton, choice2RadioButton,
            choice3RadioButton,choice4RadioButton;
    private String[] myQuestionStrings, myChoice1Strings,
            myChoice2Strings, myChoice3Strings,
            myChoice4Strings,myAnswerStrings;
    private int timesAnInt =0, scoreAnInt=0, userChooseAnInt;
    private int intgold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        //Bind Widget
        imageView = (ImageView) findViewById(R.id.imageView7);
        nameTextView = (TextView) findViewById(R.id.textView7);
        stationTextView = (TextView) findViewById(R.id.textView8);
        questionTextView = (TextView) findViewById(R.id.textView9);
        radioGroup = (RadioGroup) findViewById(R.id.ragChoice);
        choice1RadioButton = (RadioButton) findViewById(R.id.radioButton6);
        choice2RadioButton = (RadioButton) findViewById(R.id.radioButton7);
        choice3RadioButton = (RadioButton) findViewById(R.id.radioButton8);
        choice4RadioButton = (RadioButton) findViewById(R.id.radioButton9);


        //Get Value form Intent
        userIDString = getIntent().getStringExtra("userID");
        nameString = getIntent().getStringExtra("Name");
        goldString = getIntent().getStringExtra("Gold");
        avataString = getIntent().getStringExtra("Avata");

        //Show View
        MyData myData = new MyData();
        int[] iconInts = myData.getAvataInts();
        int intIndex = Integer.parseInt(avataString);
        imageView.setImageResource(iconInts[intIndex]);

        nameTextView.setText(nameString);
        stationTextView.setText("ฐานที่ "
                + Integer.toString(Integer.parseInt(goldString) + 1));

        SynQuestion synQuestion =new SynQuestion();
        synQuestion.execute();

        //Get Value From RadioGroup
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i) {
                    case R.id.radioButton6:
                        userChooseAnInt = 1;
                    case R.id.radioButton7:
                        userChooseAnInt = 2;
                    case R.id.radioButton8:
                        userChooseAnInt = 3;
                    case R.id.radioButton9:
                        userChooseAnInt = 4;
                }

            }   //onCheck
        });

    }   //Main Method

    private class SynQuestion extends AsyncTask<Void, Void, String> {

        //Explicit
        private static final String urlJSON = "http://swiftcodingthai.com/cmru/get_question.php";

        @Override
        protected String doInBackground(Void... voids) {
            try {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(urlJSON).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();



            } catch (Exception e) {
                Log.d("1JulyV1", "e doIn ==> " + e.toString());
                return null;
            }

        }   //doIn Method

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("1JulyV1" , "JSON ==>" + s);

            try {
                JSONArray jsonArray = new JSONArray(s);
                String[] questionStrings = new String[jsonArray.length()];
                String[] choice1Strings = new String[jsonArray.length()];
                String[] choice2Strings = new String[jsonArray.length()];
                String[] choice3Strings = new String[jsonArray.length()];
                String[] choice4Strings = new String[jsonArray.length()];
                String[] answerStrings = new String[jsonArray.length()];

                for (int i=0;i<jsonArray.length();i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    questionStrings[i] = jsonObject.getString("Question");
                    choice1Strings[i] = jsonObject.getString("Choice1");
                    choice2Strings[i] = jsonObject.getString("Choice2");
                    choice3Strings[i] = jsonObject.getString("Choice3");
                    choice4Strings[i] = jsonObject.getString("Choice4");
                    answerStrings[i] = jsonObject.getString("Answer");

                    Log.d("1JulyV2", "question(" + i + ") = " + questionStrings[i]);
                }   //for

                myQuestionStrings = new String[5];
                myChoice1Strings = new String[5];
                myChoice2Strings = new String[5];
                myChoice3Strings = new String[5];
                myChoice4Strings = new String[5];
                myAnswerStrings = new String[5];


                    for (int i=0;i<5;i++){
                        Random random = new Random();
                        int randomIndex = random.nextInt(jsonArray.length());
                        myQuestionStrings[i] = questionStrings[randomIndex];
                        myChoice1Strings[i] = choice1Strings[randomIndex];
                        myChoice2Strings[i] = choice2Strings[randomIndex];
                        myChoice3Strings[i] = choice3Strings[randomIndex];
                        myChoice4Strings[i] = choice4Strings[randomIndex];
                        myAnswerStrings[i] = answerStrings[randomIndex];
                    }   //for

                //Show View Time
                questionTextView.setText("1. " + myQuestionStrings[0]);
                choice1RadioButton.setText(myChoice1Strings[0]);
                choice2RadioButton.setText(myChoice2Strings[0]);
                choice3RadioButton.setText(myChoice3Strings[0]);
                choice4RadioButton.setText(myChoice4Strings[0]);


            } catch (Exception e) {
                Log.d("1JulyV1" , "e onPost ==>" + e.toString());
            }



        }   //onPost
    }   //SynQuestion class

    public void clickAnswer(View view) {

        for (int i=0;i<5;i++) {
            Log.d(("1JulyV3"), "myQuesion(" + i + ")= " + myQuestionStrings[i]);
        }   //for

        //Check Choose
        if (checkChoose()) {
            //UnChecked
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "ไม่ได้ตอบคำถาม", "กรุณาตอบคำถาม");

        } else if (timesAnInt<5) {

            //Check Score
            if (userChooseAnInt == Integer.parseInt(myAnswerStrings[timesAnInt])) {
                scoreAnInt += 1;
                Log.d("1JulyV4" , "Score ==> " + scoreAnInt);
            }
            if (timesAnInt != 4) {
                timesAnInt += 1;
            } else {
                //timesAnInt = 4
                //End of Times
                Log.d("1JulyV4", "End of Times");
                checkUserScore();
            }

            radioGroup.clearCheck();

            questionTextView.setText(Integer.toString(timesAnInt + 1)
                    + ". " + myQuestionStrings[timesAnInt]);
            choice1RadioButton.setText(myChoice1Strings[timesAnInt]);
            choice2RadioButton.setText(myChoice2Strings[timesAnInt]);
            choice3RadioButton.setText(myChoice3Strings[timesAnInt]);
            choice4RadioButton.setText(myChoice4Strings[timesAnInt]);

        } else {


        }

    }   //clickAnswer

    private void checkUserScore() {
        if (scoreAnInt>=3) {
            //Update Gold
            Toast.makeText(this, "ยินดีด้วยคุณผ่านด่านแล้ว",Toast.LENGTH_SHORT).show();
            editGoldOnServer();

        } else {
            //Play Again
            Toast.makeText(this,"คะแนนของคุณ "
                    + Integer.toString(scoreAnInt)
                    + " คะแนน ต้องเล่นใหม่",Toast.LENGTH_SHORT).show();
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
    }   //checkUserScore

    private void editGoldOnServer() {
        String urlPHP = "http://swiftcodingthai.com/cmru/edit_gold_pichate.php";

        if (Integer.parseInt(goldString) < 4) {
            int intgold = Integer.parseInt(goldString) + 1;
        } else {
            //Finish at Station4

        }

        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormEncodingBuilder()
                .add("isAdd", "true")
                .add("id", userIDString)
                .add("Gold", Integer.toString(intgold))
                .build();

        Request.Builder builder = new  Request.Builder();
        Request request = builder.url(urlPHP).post(requestBody).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {

            }
        });

        Intent intent = new Intent(ExerciseActivity.this , MapsActivity.class);
        intent.putExtra("userID", userIDString);
        intent.putExtra("Name", nameString);
        intent.putExtra("Gold", Integer.toString(intgold));
        startActivity(intent);
        finish();


    }   //editGoldOnServer

    private boolean checkChoose() {
        boolean result = true;
        if (choice1RadioButton.isChecked()
                || choice2RadioButton.isChecked()
                || choice3RadioButton.isChecked()
                || choice4RadioButton.isChecked()) {
            //Checked
            result = false;
        } else {
            //UnChecked
            result = true;
        }
        return result;
    }


}   //Main Class
