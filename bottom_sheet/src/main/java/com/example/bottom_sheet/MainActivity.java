package com.example.bottom_sheet;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bottom_sheet.dialog.CustomBottomSheetDialog;

/**
 * 自定义BottomSheetDialog
 */
public class MainActivity extends AppCompatActivity {
    private static final String shareStr[] = {"微信","QQ","空间","微博","GitHub","CJJ测试\nRecyclerView自适应","微信朋友圈","短信","推特","遇见"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindEvent();
        initData();
    }

    private void bindEvent() {
        findViewById(R.id.tv_center).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSheetDialog();
            }
        });
    }

    private void initData() {
        showSheetDialog();
    }

    private void showSheetDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_layout, null);
        CustomBottomSheetDialog dialog = new CustomBottomSheetDialog(this, 600, 1000);

        RecyclerView recyclerView = view.findViewById(R.id.bs_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SimpleStringRecyclerViewAdapter adapter = new SimpleStringRecyclerViewAdapter(this);
        adapter.setItemClickListener(pos -> {
            dialog.dismiss();
            Toast.makeText(MainActivity.this, "pos--->" + pos, Toast.LENGTH_LONG).show();
        });
        recyclerView.setAdapter(adapter);
        dialog.setContentView(view);
        dialog.show();
    }

    public static class SimpleStringRecyclerViewAdapter extends RecyclerView.Adapter<SimpleStringRecyclerViewAdapter.ViewHolder> {
        public ItemClickListener mItemClickListener;
        private Context mContext;

        public void setItemClickListener(ItemClickListener listener) {
            mItemClickListener = listener;
        }

        public interface ItemClickListener {
            void onItemClick(int pos);
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public final ImageView mImageView;
            public final TextView mTextView;

            public ViewHolder(View view) {
                super(view);
                mImageView = view.findViewById(R.id.avatar);
                mTextView = view.findViewById(R.id.tv);
            }
        }

        public SimpleStringRecyclerViewAdapter(Context context) {
            super();
            mContext = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.mTextView.setText(shareStr[position]);
            holder.mTextView.setOnClickListener(v -> mItemClickListener.onItemClick(position));
        }

        @Override
        public int getItemCount() {
            return shareStr.length;
        }
    }
}