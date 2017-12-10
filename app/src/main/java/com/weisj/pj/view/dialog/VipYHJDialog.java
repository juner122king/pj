package com.weisj.pj.view.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.weisj.pj.R;
import com.weisj.pj.adapter.ItemVIPYHJAdapter;
import com.weisj.pj.bean.YHJBean;
import com.weisj.pj.utils.SystemConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jun on 2017/12/10.
 */

public class VipYHJDialog extends AlertDialog {

    private RecyclerView recyclerView;
    private Context context;
    private ItemVIPYHJAdapter adapter;

    public VipYHJDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        layout();
    }


    private void init() {
        setContentView(R.layout.dialog_vip_yhj);
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new ItemVIPYHJAdapter(getList());
        recyclerView.setAdapter(adapter);
    }

    private List<YHJBean> getList() {
        List<YHJBean> list = new ArrayList<>();
        YHJBean yhjBean1 = new YHJBean("满99减5优惠劵", "2017/11/01-20017/12/29", 5);
        YHJBean yhjBean2 = new YHJBean("满999减10优惠劵", "2017/11/01-20017/12/29", 10);
        YHJBean yhjBean3 = new YHJBean("满9999减100优惠劵", "2017/11/01-20017/12/29", 100);
        list.add(yhjBean1);
        list.add(yhjBean2);
        list.add(yhjBean3);
        return list;
    }

    private void layout() {
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = (int) (SystemConfig.getScreenWidth() * 0.9);
//        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);//弹出键盘
    }
}
