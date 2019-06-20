package yy.androidgeneralmodule;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private ListView mListView;
    private List<String> mData;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mContext = this;
        mData = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mData.add(Integer.toString(i));
        }
        mListView = findViewById(R.id.lv);
        MyAdapter adapter = new MyAdapter();

        mListView.setAdapter(adapter);
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext, "ListView -- 长按了" + position + "条图片", Toast.LENGTH_SHORT).show();
                Log.w("TAG", "长按了" + position + "条图片");
                return false;
            }
        });
    }

    private OnImageLongClickListener mListener = new OnImageLongClickListener() {
        @Override
        public void onImageLong(View finalConvertView, int position) {
            Toast.makeText(mContext, "ImageView --- 长按了" + position + "条图片", Toast.LENGTH_SHORT).show();
            Log.w("TAG", "长按了" + position + "条图片");
        }
    };

    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mData == null ? 0 : mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData == null ? null : mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list, null);
                holder = new ViewHolder();
                holder.ivIcon = convertView.findViewById(R.id.iv);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.ivIcon.setImageResource(R.mipmap.ic_launcher);
            holder.ivIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "点击了" + position + "条图片", Toast.LENGTH_SHORT).show();
                    Log.w("TAG", "点击了" + position + "条图片");
                }
            });

            final View finalConvertView = convertView;
            holder.ivIcon.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mListener.onImageLong(finalConvertView, position);
                    return true;
                }
            });
            return convertView;
        }

        class ViewHolder {
            ImageView ivIcon;
        }


    }

    interface OnImageLongClickListener {
        void onImageLong(View finalConvertView, int position);
    }
}
