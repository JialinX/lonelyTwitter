package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;


public class LonelyTwitterActivity extends Activity {

	private static final String FILENAME = "file.sav";
	private EditText bodyText;
	private ListView oldTweetsList;


	ArrayList<Tweet> tweetList;
	ArrayAdapter<Tweet> adapter;


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		bodyText = (EditText) findViewById(R.id.body);
		Button saveButton = (Button) findViewById(R.id.save);

		Button clearButton = (Button) findViewById(R.id.clear);

		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);

		saveButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				setResult(RESULT_OK);
				String text = bodyText.getText().toString();
				//saveInFile(text, new Date(System.currentTimeMillis()));
				//finish();
				Tweet tweet = new NormalTweet(text);
				tweetList.add(tweet);

				saveInFile();
				adapter.notifyDataSetChanged();

			}
		});

		clearButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				clear();
			}
		});

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		//String[] tweets = loadFromFile();
		//ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		//		R.layout.list_item, tweets);
		loadFromFile();
		adapter = new ArrayAdapter<Tweet>(this, R.layout.list_item, tweetList);
		oldTweetsList.setAdapter(adapter);
	}

	private void loadFromFile() {
		/*ArrayList<String> tweets = new ArrayList<String>();
		ArrayList<Tweet> tweetList =  new ArrayList<Tweet>();
		NormalTweet myTweet =  new NormalTweet();
		tweetList.add(myTweet);*/
		try {
			FileInputStream fis = openFileInput(FILENAME);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			/*String line = in.readLine();
			while (line != null) {
				tweets.add(line);
				line = in.readLine();
			}*/

			Gson gson = new Gson();
			Type listType = new TypeToken<ArrayList<NormalTweet>>(){}.getType();
			tweetList = gson.fromJson(in,listType);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			tweetList = new ArrayList<Tweet>();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return tweets.toArray(new String[tweets.size()]);
	}

	private void saveInFile() {
		try {
			FileOutputStream fos = openFileOutput(FILENAME,
					/*
					Context.MODE_APPEND);
			fos.write(new String(date.toString() + " | " + text)
					.getBytes());*/
					Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
			Gson gson = new Gson();
			gson.toJson(tweetList, out);
			out.flush();

			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void clear(){
		adapter.clear();
		adapter.notifyDataSetChanged();

	}
}
