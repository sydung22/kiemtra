package com.example.thigrid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    GridView gridView;

    String[] namedh = {"Đồng hồ TwentySeventeen W001Q","Đồng hồ phiên bản G70803.202.212","Đồng hồ thạch anh TwentySeventeen W003Q","Đồng hồ cao cấp TwentySeventeen W004Q","Đồng hồ cơ Xiaomi CIGA mặt vuông","Đồng hồ Longines nam L4.760.2 Automatic"};
    int[] images = {R.drawable.dh1,R.drawable.dh2,R.drawable.dh3,R.drawable.dh4,R.drawable.dh5,R.drawable.dh6};
    String[] giadh = {"450.000 ₫","1.100.000 ₫","1.700.000 ₫","1.100.000 ₫","3.350.000 ₫","2.800.000 ₫"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //finding listview
        gridView = findViewById(R.id.gridview);
        gridView.setOnItemLongClickListener(new ItemLongClickRemove());
        CustomAdapter customAdapter = new CustomAdapter();
        gridView.setAdapter(customAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),namedh[i],Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),GridItemActivity.class);
                intent.putExtra("name",namedh[i]);
                intent.putExtra("image",images[i]);
                intent.putExtra("gia",giadh[i]);
                startActivity(intent);
            }
        });
    }

    private class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return namedh.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1 = getLayoutInflater().inflate(R.layout.row_data,null);
            //getting view in row_data
            TextView name = view1.findViewById(R.id.iddongho);
            ImageView image = view1.findViewById(R.id.imageView);
            TextView gia = view1.findViewById(R.id.idgia);

            name.setText(namedh[i]);
            image.setImageResource(images[i]);
            gia.setText(giadh[i]);
            return view1;
        }
    }

    private class ItemLongClickRemove implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView parent, View view, final int position, long id) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
            alertDialogBuilder.setMessage("Bán có muốn xóa sản phẩm này!");
            alertDialogBuilder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    gridView.setOnItemLongClickListener(new ItemLongClickRemove());
                }
            });
            alertDialogBuilder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //không làm gì
                }
            });
            alertDialogBuilder.show();
            return true;
        }
    }
}
