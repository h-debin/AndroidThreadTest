package com.example.androidthreadtest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	private Button changeText;
	private TextView text;
	
	public static final int UPDATE_TEXT = 1;
	
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case UPDATE_TEXT:
				text.setText("Nice to meet you");
				break;
			default:
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		changeText = (Button) findViewById(R.id.change_text);
		changeText.setOnClickListener(this);
		text = (TextView) findViewById(R.id.text);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.change_text:
			new Thread(new Runnable() {
				@Override
				public void run() {
					Message message = new Message();
					message.what = UPDATE_TEXT;
					handler.sendMessage(message);
				}
			}).start();
			break;
		default:
		}
	}

}
