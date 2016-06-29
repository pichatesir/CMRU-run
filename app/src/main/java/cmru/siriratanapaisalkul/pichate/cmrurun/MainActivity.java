package cmru.siriratanapaisalkul.pichate.cmrurun;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private static final String urlLogo = "http://www.swiftcodingthai.com/cmru/cmru_logo.png";
    private static final String urlJSON = "http://www.swiftcodingthai.com/cmru/get_user_pichate.php";

    private ImageView imageView;
    private EditText userEditText, passwordEditText;
    private String userString,passwordString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        imageView = (ImageView) findViewById(R.id.imageView6);
        userEditText = (EditText) findViewById(R.id.editText4);
        passwordEditText = (EditText) findViewById(R.id.editText5);

        //Load Logo
        Picasso.with(this).load(urlLogo).resize(150,180).into(imageView);

    }   //Main Method

    //Create Inner Class
    private class SynUser extends AsyncTask<Void, Void, String> {

        //Explicit
        private Context context;
        private String strURL;
        private boolean statusABoolean = true;
        private String truePasswordString, nameUserString;

        public SynUser(Context context, String strURL) {
            this.context = context;
            this.strURL = strURL;
        }

        @Override
        protected String doInBackground(Void... params) {

            try {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(strURL).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();

            } catch (Exception e) {
                Log.d("29JUNE", "e doInBack ==>" + e.toString());
                return null;
            }

            //return null;
        }   //doInBack

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("29JUNE", "JSON ==>" + s);

            try {
                JSONArray jsonArray = new JSONArray(s);
                for (int i=0;i<jsonArray.length();i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    if (userString.equals(jsonObject.getString("User"))) {
                        statusABoolean = false;
                        truePasswordString = jsonObject.getString("Password");
                        nameUserString = jsonObject.getString("Name");

                    }   //if

                }   //for
                if (statusABoolean) {
                    MyAlert myAlert = new MyAlert();
                    myAlert.myDialog(context, "ไม่มี User นี้",
                            "ไม่มี" + userString + "ในฐานข้อมูลของเรา");
                } else if (passwordString.equals(truePasswordString)) {
                    //Password True
                    Toast.makeText(context, "Welcome" + nameUserString,Toast.LENGTH_SHORT).show();
                    Intent intent = new
                } else {
                    //Password false
                    MyAlert myAlert = new MyAlert();
                    myAlert.myDialog(context, "Password False",
                            "Please Try Again Password False");
                }


            } catch (Exception e) {
                Log.d("29JUNE", "e onPost ==>" + e.toString());
            }


        }   //onPost

    }   //SynUser Class


    public void clickSignIn(View view) {
        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

        //Check Space
        if (userString.equals("") || passwordString.equals("")) {
            //Have Space
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "มีช่องว่าง (Have Space)", "กรุณากรอกทุกช่อง คะ (Please Fill All Every Blank");
        } else {
            checkUserPassword();

        }

    }   //clickSignIn

    private void checkUserPassword() {
        SynUser synUser = new SynUser(this,urlJSON);
        synUser.execute();
    }

    public void clickSignUpMain(View view) {
        startActivity(new Intent(MainActivity.this, SignUpActivity.class));
    }

}   //Main Class
