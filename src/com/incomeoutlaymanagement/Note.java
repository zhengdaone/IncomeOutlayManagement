package com.incomeoutlaymanagement;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

public class Note extends Fragment {
	SharedPreferences sh;
	View cfragment;
	EditText mEditText;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		cfragment=inflater.inflate(R.layout.note, container, false);
		sh=getActivity().getSharedPreferences("note", Context.MODE_PRIVATE);
		EditText editText=(EditText) cfragment.findViewById(R.id.note);
		editText.setText(sh.getString("note", ""));
		editText.addTextChangedListener(textWatcher);
		mEditText=(EditText) cfragment.findViewById(R.id.note);
		//editText.setOnFocusChangeListener(textWatcher);
		return cfragment;
	}
	
	public TextWatcher textWatcher=
            new TextWatcher() {
				private CharSequence temp;
				private int editStart;
				private int editEnd;
				String text;
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				/*SharedPreferences sh=getActivity().getSharedPreferences("note", Context.MODE_PRIVATE);
				Editor editor=sh.edit();
				EditText editText = (EditText) cfragment.findViewById(R.id.note);
				editor.putString("note", editText.getText().toString());
				editor.commit();
				Toast.makeText(getActivity(), "aaa", Toast.LENGTH_LONG).show();*/

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
				temp = s;
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				SharedPreferences sh=getActivity().getSharedPreferences("note", Context.MODE_PRIVATE);
				Editor editor=sh.edit();
				EditText editText = (EditText) cfragment.findViewById(R.id.note);
				editor.putString("note", editText.getText().toString());
				editor.commit();
				//Toast.makeText(getActivity(), "aaa", Toast.LENGTH_LONG).show();
				editStart = mEditText.getSelectionStart();
				editEnd = mEditText.getSelectionEnd();
				if (temp.length() > 300) {//限制长度
					Toast.makeText(getActivity(),
							"输入的字数不超过300！", Toast.LENGTH_SHORT)
							.show();
					s.delete(editStart - 1, editEnd);
					int tempSelection = editStart;
					mEditText.setText(s);
					mEditText.setSelection(tempSelection);
				}
			}
		};
	/*public View.OnFocusChangeListener focusChangeListener=new View.OnFocusChangeListener() {
		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			SharedPreferences sh=getActivity().getSharedPreferences("note", Context.MODE_PRIVATE);
			Editor editor=sh.edit();
			EditText editText = (EditText) cfragment.findViewById(R.id.note);
			editor.putString("note", editText.getText().toString());
			editor.commit();
		}
	};*/
}
