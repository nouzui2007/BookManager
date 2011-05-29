package jp.ccube.bookManager;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class BookListAdapter<T> extends ArrayAdapter<T> {

	private ArrayList<T> items;
	private LayoutInflater inflater;
	
	public BookListAdapter(Context context, int textViewResourceId, ArrayList<T> items) {
		super(context, textViewResourceId, items);
		
		this.items = items;
		this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		
		if (view == null) {
			//viewを生成
			view = inflater.inflate(R.layout.book_row, null);
			//必要に応じてviewの設定
		}
	
		// 表示すべきデータの取得  
		BookEntity item = (BookEntity)this.items.get(position); 
		if (item != null) {  
			TextView title = (TextView) view.findViewById(R.id.BookTitle);  
			// テキストをビューにセット  
			if (title != null) {  
				title.setText(item.getTitle());  
			}  
		}  
		return view;
	}
}
