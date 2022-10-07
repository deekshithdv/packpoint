package com.example.myapplication;

import android.graphics.Paint;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.switchmaterial.SwitchMaterial;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class carryList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carry_list);
        String[] International = {"パスポート", "ビザ", "Itinerary", "チケット"};
        String[] Formal = {"シャツ", "ズボン", "靴", "靴下", "ネクタイ" };
        String[] Working = {"パサコン", "チャージャー", "ディスク"};
        String[] Dinner = {"シャッツ", "ジャケット", "靴", "靴下"};
        String[] Swimming = {"Swimming suit", "メガネ"};
        String[] Running = {"靴", "靴下","ボトル", "食べ物"};
        String[] Gym = {"Gym　Suit", "靴", "靴下", "ボトル"};
        String[] Walking = {"靴", "メガネ"};
        String[] Baby = {"食べ物", "飲み物", "パンチーズ", "ボトル"};
        String[] Snow = {"帽子", "Gloves", "セーター", "ジャケット"};
        String[] Beach = {"Swimming suit", "メガネ"};
        String[] Photography = {"カメラ", "チャージャー", "バッテリー", "メモリーカード"};
        String[] Hiking = {"Stick", "ハイキング靴"};

        ListView listView = (ListView) findViewById(R.id.itemlist);
        Bundle extra = getIntent().getExtras();
        ArrayList<String> activities = extra.getStringArrayList("LIST_ITEMS");
        TextView destinationtv = (TextView)findViewById(R.id.destination);
        TextView datetv = (TextView) findViewById(R.id.date);
        String destination = extra.getString("Destination");
        String date = extra.getString("Date");
        destinationtv.setText(destination);
        datetv.setText(date);

        ArrayList<Object> list = new ArrayList<>();
        for(int i=0; i<activities.size(); i++){
            switch (activities.get(i)){
                case "外国":
                    list.add(new String("外国"));
                    list = funcAddList(list, International);
                    break;
                case "仕事":
                    list.add(new String("仕事"));
                    list = funcAddList(list, Working);
                    break;
                case "食事":
                    list.add(new String("食事"));
                    list = funcAddList(list, Dinner);
                    break;
                case "ブシネス":
                    list.add(new String("ブシネス"));
                    list = funcAddList(list, Formal);
                    break;
                case "ウォーキング":
                    list.add(new String("ウォーキング"));
                    list = funcAddList(list, Walking);
                    break;
                case "水泳":
                    list.add(new String("水泳"));
                    list = funcAddList(list, Swimming);
                    break;
                case "ビーチ":
                    list.add(new String("ビーチ"));
                    list = funcAddList(list, Beach);
                    break;
                case "ランニング":
                    list.add(new String("ビーチ"));
                    list = funcAddList(list, Running);
                    break;
                case "Baby":
                    list.add(new String("Baby"));
                    list = funcAddList(list, Baby);
                    break;
                case "雪":
                    list.add(new String("雪"));
                    list = funcAddList(list, Snow);
                    break;
                case "ジム":
                    list.add(new String("ジム"));
                    list = funcAddList(list, Gym);
                    break;
                case "写真撮影":
                    list.add(new String("写真撮影"));
                    list = funcAddList(list, Photography);
                    break;
                case "ハイキング":
                    list.add(new String("ハイキング"));
                    list = funcAddList(list, Hiking);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + activities.get(i));
            }
        }


        listView.setAdapter(new childAdapter(this, list));
        TextView childname = (TextView)findViewById(R.id.childName);
    }
    public ArrayList<Object> funcAddList(ArrayList<Object> arraylist, String[] list){
        for(int i=0; i< list.length; i++) {
            arraylist.add(new childModel(list[i]));
        }
        return arraylist;

    }

}