package jp.ccube.bookManager;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BookList extends Activity {

	private BookEntity activeBook;
	
	private ArrayList<BookEntity> getData() {
		TableHelper helper = new TableHelper(this);
		return helper.getAllBook();
	}
	
	private void showList() {
		BookListAdapter<BookEntity> adapter =
        	new BookListAdapter<BookEntity>(this, R.layout.book_row, this.getData());
        
        ListView list = (ListView)findViewById(R.id.ListView01);
        list.setAdapter(adapter);	
        list.setOnItemSelectedListener(itemSelected);
        list.setOnItemClickListener(onItemClick);
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_list);
        showList();
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	
    	showList();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	super.onCreateOptionsMenu(menu);
    	
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.booklist, menu);
    	
    	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	
    	switch (item.getItemId()) {
	    	case R.id.menu_addnew:
	    	{
	    		goAddNewScreen();
	    		return true;
	    	}
	    	case R.id.menu_edit:
	    	{
	    		//move to edit screen
	    		goEditScreen();
	    		return true;
	    	}
	    	case R.id.menu_delete:
	    	{
	    		Log.d("BookList", "delete start");
	    		//delete
	    		if (activeBook != null) {
	    			TableHelper helper = new TableHelper(this);
	    			helper.delete(activeBook);
		    		Log.d("BookList", "db delete");
		    		
		    		showList();
	    		}
	    		Log.d("BookList", "delete end");
	    		return true;
	    	}
    	}
    	
    	return super.onOptionsItemSelected(item);
    }
    
    private void goAddNewScreen() {
		//move to add new screen.
		Intent intent = new Intent(BookList.this, BookEditor.class);
		startActivity(intent);
    }
    
    private void goEditScreen() {
		Intent intent = new Intent(BookList.this, BookEditor.class);
		intent.putExtra("id", activeBook.getId());
		intent.putExtra("title", activeBook.getTitle());
		startActivity(intent);
    }
    
    OnItemSelectedListener itemSelected = new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position,
				long id) {
			ListView list = (ListView)parent;
			activeBook = (BookEntity)list.getItemAtPosition(position);
			Log.d("BookList", "set activeBook(select):" + activeBook.getTitle());
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
    	
    };
    
    OnItemClickListener onItemClick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			ListView list = (ListView)parent;
			activeBook = (BookEntity)list.getItemAtPosition(position);
			Log.d("BookList", "set activeBook(click):" + activeBook.getTitle());
		}
	};
}