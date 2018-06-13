package com.example.paul.sql;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public DBManager dbm;
    private Button insertBtn;
    private Button updateBtn;
    private Button deleteBtn;


    private EditText nameEdit;
    private EditText lnameEdit;
    private EditText editId;

    public TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbm =new DBManager(this);
        insertBtn=(Button)findViewById(R.id.insert);
        updateBtn=(Button)findViewById(R.id.update);
        deleteBtn=(Button)findViewById(R.id.delete);

        insertBtn.setOnClickListener(this);
        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);

        nameEdit=(EditText)findViewById(R.id.name);
        lnameEdit=(EditText)findViewById(R.id.lname);
        editId=(EditText)findViewById(R.id.edit_textid);



        textView=(TextView)findViewById(R.id.output);
    }


    @Override
    public void onClick(View v){
        int id=v.getId();

        switch (id){
            case R.id.insert:
                String name=nameEdit.getText().toString();
                String lname=lnameEdit.getText().toString();
                dbm.open(false);
                dbm.insertSchool(name,lname);
                Toast.makeText(this,"1 row updated",Toast.LENGTH_SHORT).show();

                nameEdit.setText("");
                lnameEdit.setText("");

                Cursor cursor=dbm.getSchool();


                StringBuilder sb=new StringBuilder();

                do{
                    String nameData=cursor.getString(cursor.getColumnIndex(DbHelper.NAME));
                    String  lnameData=cursor.getString(cursor.getColumnIndex(DbHelper.LNAME));
                    String idInt = String.valueOf(cursor.getInt(cursor.getColumnIndex(DbHelper._ID)));
                    sb.append("ID: ").append(idInt).append("Name: ").append(nameData).append("LastName: ").append(lnameData).append('\n');
                }while (cursor.moveToNext());

                cursor.close();
                dbm.close();
                textView.setText(sb.toString());
                break;



            case R.id.update:
                dbm.open(true);
                dbm.updateSchool(editId.getText().toString(), nameEdit.getText().toString(), lnameEdit.getText().toString());
                Toast.makeText(this,"updated",Toast.LENGTH_SHORT).show();
                cursor=dbm.getSchool();


                 sb=new StringBuilder();

                do{
                    String idInt = String.valueOf(cursor.getInt(cursor.getColumnIndex(DbHelper._ID)));
                    String nameData=cursor.getString(cursor.getColumnIndex(DbHelper.NAME));
                    String  lnameData=cursor.getString(cursor.getColumnIndex(DbHelper.LNAME));

                    sb.append("ID: ").append(idInt).append("  Name: ").append(nameData).append("  LastName: ").append(lnameData).append('\n');
                }while (cursor.moveToNext());

                cursor.close();
                dbm.close();
                textView.setText(sb.toString());

                nameEdit.setText("");
                lnameEdit.setText("");
                break;

            case R.id.delete:
                dbm.open(true);
              String takeid=editId.getText().toString();

                dbm.deleteData(takeid);
                Toast.makeText(this,"deleted",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}













