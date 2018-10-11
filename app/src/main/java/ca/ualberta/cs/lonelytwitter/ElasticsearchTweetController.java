package ca.ualberta.cs.lonelytwitter;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;


public class ElasticsearchTweetController {
    private static JestDroidClient client;

    // TODO we need a function which adds tweets to elastic search
    public static class AddTweetsTask extends AsyncTask<NormalTweet, Void, Void> {

        @Override
        protected Void doInBackground(NormalTweet... tweets) {
            verifySettings();

            for (NormalTweet tweet : tweets) {
                Index index = new Index.Builder(tweet).index("testing").type("tweet").build();

                try {
                    DocumentResult result = client.execute(index);
                    if (result.isSucceeded()){
                        tweet.setId(result.getId());
                    }else{
                        Log.e("Error","Elastic search was not able to add the tweets");
                    }
                }
                catch (Exception e) {
                    Log.i("Error", "The application failed to build and send the tweets");
                }

            }
            return null;
        }
    }

    // TODO we need a function which gets tweets from elastic search
    public static class GetTweetsTask extends AsyncTask<String, Void, ArrayList<NormalTweet>> {
        @Override
        protected ArrayList<NormalTweet> doInBackground(String... search_parameters) {
            verifySettings();

            ArrayList<NormalTweet> tweets = new ArrayList<NormalTweet>();

            JSONObject wrapper = new JSONObject();
            JSONObject query = new JSONObject();
            JSONObject match = new JSONObject();

            try {
                if (search_parameters[0].length() == 0){
                    match.put("message", search_parameters[0]+ "*");
                    query.put("wildcard", match);
                }
                else {
                    match.put("message", search_parameters[0]);
                    query.put("match", match);
                }

                wrapper.put("query", query);
            } catch (JSONException e1) {
                e1.printStackTrace();
            }

            Search search = new Search.Builder(wrapper.toString())
                    .addIndex("testing")
                    .addType("tweet")
                    .build();


            try {
                SearchResult result = client.execute(search);
                if(result.isSucceeded()){
                    List<SearchResult.Hit<NormalTweet, Void>> hits = result.getHits(NormalTweet.class);
                    for (SearchResult.Hit<NormalTweet, Void> hit: hits) {
                        NormalTweet tweet = hit.source;
                        tweets.add(tweet);
                    }

                }else{
                    Log.e("Error","The search query failed to find any tweets that matched.");
                }
            }
            catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
            }

            return tweets;
        }
    }


    public static void verifySettings() {
        if (client == null) {
            DroidClientConfig.Builder builder = new DroidClientConfig.Builder("http://cmput301.softwareprocess.es:8080");
            DroidClientConfig config = builder.build();

            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);
            client = (JestDroidClient) factory.getObject();
        }
    }
}