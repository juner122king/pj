package com.weisj.pj.base.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mcxtzhang.layoutmanager.flow.FlowLayoutManager;
import com.weisj.pj.R;
import com.weisj.pj.adapter.ItemSearchAdapter;
import com.weisj.pj.base.BaseActivity;
import com.weisj.pj.utils.DBUtil;
import com.weisj.pj.utils.KeyboardUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/4 0004.
 */
public class SearchActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener, View.OnClickListener {
    private DBUtil dbUtil;
    private List<String> list = new ArrayList<>();
    //    private ListView listView;
    private RecyclerView recyclerView;
    private EditText searchEdit;
    private TextView searchbt, delete;
    private ItemSearchAdapter adapter;

    @Override
    public View initView(Bundle savedInstanceState) {
        View view = mLayoutInflater.inflate(R.layout.activity_search, null);
        rootView.isHintHeadBar(true);
        initView(view);
        dbUtil = new DBUtil(this);
        initData();
        return view;
    }

    public void setTitle(String str) {
        searchEdit.setText(str);
        searchEdit.setSelection(str.length());
    }

    private void initData() {
        list = dbUtil.select();
        adapter = new ItemSearchAdapter(list);
        adapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(new FlowLayoutManager());
        recyclerView.setAdapter(adapter);


    }

    private void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        searchEdit = (EditText) view.findViewById(R.id.search_edit);
        searchbt = (TextView) view.findViewById(R.id.search_bt);
        delete = (TextView) view.findViewById(R.id.tv_delete);
        view.findViewById(R.id.search_image).setOnClickListener(this);
        searchbt.setOnClickListener(this);
        delete.setOnClickListener(this);
        searchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (searchEdit.getText().length() > 0 && !searchEdit.getText().toString().trim().equals("")) {
                    searchbt.setText("搜索");
                } else {
                    searchbt.setText("取消");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public String setTitleStr() {
        return "搜索";
    }

    @Override
    public void getRefreshData() {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(this, SearchListActivity.class);
        intent.putExtra("goodName", list.get(position));
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_bt:
                if (searchbt.getText().toString().equals("取消")) {
                    finish();
                } else {
                    Intent intent = new Intent(this, SearchListActivity.class);
                    intent.putExtra("goodName", searchEdit.getText().toString());
                    dbUtil.insert(searchEdit.getText().toString());
                    list.add(0, searchEdit.getText().toString());
                    startActivity(intent);
                }
                KeyboardUtil.closeKeyBoard(this);
                break;
            case R.id.search_image:
                if (!searchbt.getText().toString().equals("取消")) {
                    Intent intent = new Intent(this, SearchListActivity.class);
                    intent.putExtra("goodName", searchEdit.getText().toString());
                    dbUtil.insert(searchEdit.getText().toString());
                    list.add(0, searchEdit.getText().toString());
                    startActivity(intent);
                    KeyboardUtil.closeKeyBoard(this);
                }
                break;

            case R.id.tv_delete:
                dbUtil.clean();
                list.clear();
                adapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (null != adapter)
            adapter.notifyDataSetChanged();
    }
}
