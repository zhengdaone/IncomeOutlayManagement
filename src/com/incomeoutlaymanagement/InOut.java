package com.incomeoutlaymanagement;

        import com.data.Database;

        import android.app.Fragment;
        import android.content.ContentValues;
        import android.content.Intent;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.view.ViewGroup;
        import android.widget.TextView;
        import android.widget.Toast;

public class InOut extends Fragment {

    private int width;
    private int height;
    private String baseContext;
    private Database database;
    private SQLiteDatabase sdr, sdw;
    private View credit;
    private View cash;
    private View deposit;
    private View ebank;

    public InOut(Database database) {
        this.database = database;
    }
    public InOut() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View cFragment = inflater.inflate(R.layout.inout, container, false);
        inoutData(cFragment);
        bindView(cFragment);
        return cFragment;
    }

    public View inoutData(View data) {
        sdr = database.getReadableDatabase();
        int ass=0;
        Cursor cursor = sdr.query("msort", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String asset = cursor.getString(cursor.getColumnIndex("sort"));
            float money = cursor.getFloat(cursor.getColumnIndex("allmoney"));
            if (money != 0) {
                if (asset.equals( "信用卡")) {
                    TextView assets = (TextView) data.findViewById(R.id.credit_text);
                    CharSequence mon = String.valueOf(money);
                    assets.setText(mon);
                }
                if (asset.equals( "现金")) {
                    TextView assets = (TextView) data.findViewById(R.id.xianjin_text);
                    CharSequence mon = String.valueOf(money);
                    assets.setText(mon);
                }
                if (asset.equals("储蓄卡")) {
                    TextView assets = (TextView) data.findViewById(R.id.deposit_text);
                    CharSequence mon = String.valueOf(money);
                    assets.setText(mon);
                }
                if (asset.equals("网银")) {
                    TextView assets = (TextView) data.findViewById(R.id.ebank_text);
                    CharSequence mon = String.valueOf(money);
                    assets.setText(mon);
                }
                ass+=money;
            }
        }
        TextView assets = (TextView) data.findViewById(R.id.asset_text);
        CharSequence mon = String.valueOf(ass);
        assets.setText(mon);
        return data;
    }

    public void bindView(View lay) {
        credit = lay.findViewById(R.id.credit);
        cash= lay.findViewById(R.id.cash);
        deposit= lay.findViewById(R.id.deposit);
        ebank= lay.findViewById(R.id.ebank);

        credit.setOnClickListener(listener);
        cash.setOnClickListener(listener);
        ebank.setOnClickListener(listener);
        deposit.setOnClickListener(listener);
    }

    public OnClickListener listener=new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            Intent intent = new Intent();
            switch (v.getId()) {
                case R.id.credit:
                    intent.putExtra("payway", "信用卡");
                    break;
                case R.id.deposit:
                    intent.putExtra("payway", "储蓄卡");
                    break;
                case R.id.cash:
                    intent.putExtra("payway", "现金");
                    break;
                case R.id.ebank:
                    intent.putExtra("payway", "网银");
                    break;
            }
            intent.setClass(getActivity(), InOutDis.class);
           // Toast.makeText(getActivity(), v.getId()+" "+R.id.credit, Toast.LENGTH_LONG).show();
            startActivity(intent);
        }
    };
}
