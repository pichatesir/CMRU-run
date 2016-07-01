package cmru.siriratanapaisalkul.pichate.cmrurun;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class ShowFriendListView extends AppCompatActivity {

    //Explicit
    private ListView listView;
    private MyData myData;
    private int[] myavataInts;
    private int[] mygoldInts = new int[]{R.drawable.friend,
            R.drawable.brnz,R.drawable.siver,R.drawable.gold,R.drawable.gold};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_friend_list_view);

        listView = (ListView) findViewById(R.id.listView);

        myData = new MyData();
        myavataInts = myData.getAvataInts();

        SynGold synGold = new SynGold();
        synGold.execute();

    }   //Main Method

    private class SynGold extends AsyncTask<Void, Void, String> {

        private static final String
                urlJSON = "http://www.swiftcodingthai.com/cmru/get_user_pichate.php";


        @Override
        protected String doInBackground(Void... voids) {

            try {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(urlJSON).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();

            } catch (Exception e) {
                return null;
            }

        }   //doIn

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONArray jsonArray = new JSONArray();
                String[] avataStrings = new String[jsonArray.length()];
                String[] goldStrings = new String[jsonArray.length()];
                String[] nameStrings = new String[jsonArray.length()];
                int[] avataInts = new int[jsonArray.length()];
                int[] goldInts = new int[jsonArray.length()];

                for (int i=0;i<jsonArray.length();i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    avataStrings[i] = jsonObject.getString("Avata");
                    goldStrings[i] = jsonObject.getString("Gold");
                    nameStrings[i] = jsonObject.getString("Name");

                    avataInts[i] = myavataInts[Integer.parseInt(avataStrings[i])];
                    goldInts[i] = mygoldInts[Integer.parseInt(goldStrings[i])];

                }//for

                FriendAdapter friendAdapter = new FriendAdapter(ShowFriendListView.this,
                        avataInts, goldInts, nameStrings);
                listView.setAdapter(friendAdapter);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }   //SynGold



}   //Main Class
