package jp.ccube.bookManager;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class BookEditor extends Activity {

	private BookEntity book;
	
	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.book_editor);
		
		//hook up button presses to the appropriate event handler
		((Button) findViewById(R.id.ButtonOK)).setOnClickListener(mOkListener);
		((Button) findViewById(R.id.ButtonCancel)).setOnClickListener(mCancelListener);
		((Button) findViewById(R.id.ButtonPlus)).setOnClickListener(mPlusListener);
		((Button) findViewById(R.id.ButtonMinus)).setOnClickListener(mMinusListener);
		
		//引数があるとき、画面に表示する
		if ((savedInstanceState == null) || (savedInstanceState.isEmpty())) {
			if (getIntent().hasExtra("id")) {
				int id = getIntent().getExtras().getInt("id");
				String title = getIntent().getExtras().getString("title");
				
				book = new BookEntity();
				book.setId(id);
				book.setTitle(title);
				
				EditText edit = (EditText) findViewById(R.id.BookTitle);
				edit.setText(title);
			}
		}
	};
	
    /**
     * A call-back for when the user presses the cancel button.
     */
    OnClickListener mCancelListener = new OnClickListener() {
        public void onClick(View v) {
            finish();
        }
    };

    /**
     * A call-back for when the user presses the ok button.
     */
    OnClickListener mOkListener = new OnClickListener() {
        public void onClick(View v) {
        	Log.d("BookEditor", "OK Click");
        	
        	String title = getTextByTextView(R.id.BookTitle);
            String auther = getTextByTextView(R.id.Auther);
        	int volume = getIntByTextView(R.id.Volume);
        	
        	Boolean isNew = false;
        	
        	if (book == null) {
            	book = new BookEntity();
            	isNew = true;
        	}
            book.setTitle(title);
            book.setAuther(auther);
            book.setVolume(volume);
            
            TableHelper helper = new TableHelper(BookEditor.this);
            
            if (isNew) {
            	helper.add(book);
            } else {
            	helper.update(book);
            }
        	Log.d("BookEditor", "OK Click end");
        	
        	finish();
        }
    };
    
    private String getTextByTextView(int TextViewID) {
    	EditText edit = (EditText) findViewById(TextViewID);
        String ret = edit.getText().toString();
    	Log.d("BookEditor", ">>getTextByTextView:" + ret);
    	
    	return ret;
    }
    
    private int getIntByTextView(int TextViewID) {
    	String buf = getTextByTextView(TextViewID);
    	return Integer.parseInt(buf);
    }

    /**
     * A call-back for when the user presses the plus button.
     */
    OnClickListener mPlusListener = new OnClickListener() {
        public void onClick(View v) {
        	Log.d("BookEditor", "Plus Click");
        	
        	EditText edit = (EditText) findViewById(R.id.Num);
        	int num = getIntByTextView(R.id.Volume);
        	num++;
        	edit.setText(Integer.toString(num));        	
        	
        	Log.d("BookEditor", "Plus Click end");
        }
    };
    
    /**
     * A call-back for when the user presses the minus button.
     */
    OnClickListener mMinusListener = new OnClickListener() {
        public void onClick(View v) {
        	Log.d("BookEditor", "Minus Click");
        	
        	EditText edit = (EditText) findViewById(R.id.Num);
        	int num = Integer.parseInt(edit.getText().toString());
        	num--;
        	if (num < 0)
        		num = 0;
        	edit.setText(Integer.toString(num));        	
        	
        	Log.d("BookEditor", "Minus Click end");
        }
    };
}
