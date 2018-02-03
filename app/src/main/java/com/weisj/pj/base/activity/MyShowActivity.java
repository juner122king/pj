package com.weisj.pj.base.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.squareup.okhttp.Request;
import com.weisj.pj.R;
import com.weisj.pj.adapter.ItemMyCardAdapter;
import com.weisj.pj.adapter.ItemMyGoodAdapter;
import com.weisj.pj.base.BaseActivity;
import com.weisj.pj.bean.BaseBean;
import com.weisj.pj.bean.CardTypeBean;
import com.weisj.pj.bean.CommentBean;
import com.weisj.pj.bean.OrderBean;
import com.weisj.pj.utils.BitmapUtil;
import com.weisj.pj.utils.CameraUtil;
import com.weisj.pj.utils.CommenString;
import com.weisj.pj.utils.CropperImageScale;
import com.weisj.pj.utils.OkHttpClientManager;
import com.weisj.pj.utils.PersonMessagePreferencesUtils;
import com.weisj.pj.utils.Urls;
import com.weisj.pj.view.CustomBottomPopWindow;
import com.weisj.pj.view.photocheck.GlideRoundTransform;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jun on 2018/2/2.
 */

public class MyShowActivity extends BaseActivity implements View.OnClickListener, CustomBottomPopWindow.PopupListener {

    OrderBean.DataEntity.OrderInfoDomainListEntity domainListEntity;
    EditText et;
    private CustomBottomPopWindow customPopWindow;//图片选择
    private CameraUtil cameraUtil;
    private Bitmap headBitmap;
    private ImageView image_up, iv_pic;
    private TextView tv1, tv2;


    @Override
    public View initView(Bundle savedInstanceState) {

        View view = mLayoutInflater.inflate(R.layout.activity_myshow, null);
        cameraUtil = new CameraUtil(this);
        initView(view);

        return view;


    }

    @Override
    protected void onResume() {
        super.onResume();
        getOrderData();
    }

    private void initView(View view) {

        view.findViewById(R.id.tv_zf).setOnClickListener(this);
        image_up = (ImageView) view.findViewById(R.id.iv_up);

        image_up.setOnClickListener(this);
        et = (EditText) view.findViewById(R.id.et);


        iv_pic = (ImageView) view.findViewById(R.id.iv);
        tv1 = (TextView) view.findViewById(R.id.tv1);
        tv2 = (TextView) view.findViewById(R.id.tv_sku);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tv_zf:
                //提交晒图
                if (!et.getText().toString().equals(""))

                    addcomment(String.valueOf(domainListEntity.getOrder_info_id()), "5", et.getText().toString());
                else {
                    Toast.makeText(MyShowActivity.this, "请填写评价内容", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.iv_up:
                //选择图片
                clickImage();

                break;


        }

    }

    // 点击用户头像
    private void clickImage() {
        if (customPopWindow == null) {
            customPopWindow = new CustomBottomPopWindow(this, this);
        }
        customPopWindow.show(this);
    }


    //提交晒图
    private void addcomment(String order_info_id, String comment_rank, String content) {

        Map<String, String> params = new HashMap<>();
        params.put("member_id", PersonMessagePreferencesUtils.getUid());
        params.put("order_info_id", order_info_id);
        params.put("comment_rank", comment_rank);//分数
        params.put("content", content);//内容

        OkHttpClientManager.postAsyn(Urls.addcomment, params, new OkHttpClientManager.ResultCallback<CommentBean>() {
            @Override
            public void onError(Request request, Exception e) {
            }

            @Override
            public void onResponse(CommentBean response) {
                if (response != null) {
                    String code = response.getCode();
                    if (code.equals("1")) {
                        Toast.makeText(MyShowActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                        addcommentpic(response.getData().getComment_id());


                    }
                }
            }
        });
    }


    //提交晒图图片
    private void addcommentpic(String comment_id) {
        File file = BitmapUtil.saveBitmapFile(headBitmap);


        if (file != null) {
            OkHttpClientManager.Param params = new OkHttpClientManager.Param("comment_id", comment_id);
            try {
                OkHttpClientManager.postAsyn(Urls.updateUserImage, new OkHttpClientManager.ResultCallback<BaseBean>() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }

                    @Override
                    public void onResponse(BaseBean response) {

                        if (response.getCode().equals("1")) {
                            Toast.makeText(MyShowActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MyShowActivity.this, "上传失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, file, "image", params);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    //我的订单
    public void getOrderData() {

        Map<String, String> params = new HashMap<>();
        params.put("member_id", PersonMessagePreferencesUtils.getUid());
        params.put("order_state", "3");
        params.put("page", "1");
        params.put("page_num", "10");

        OkHttpClientManager.postAsyn(Urls.myorders, params, new OkHttpClientManager.ResultCallback<OrderBean>() {
            @Override
            public void onError(Request request, Exception e) {
            }

            @Override
            public void onResponse(OrderBean response) {
                if (response != null) {

                    if (null != response.getData() && response.getData().size() > 0) {

                        if (response.getData().get(0).getOrder_info_domain_list().size() > 0) {
                            domainListEntity = response.getData().get(0).getOrder_info_domain_list().get(0);
                            initdata();


                        } else {
                            finish();
                            Toast.makeText(MyShowActivity.this, "暂无可以晒图的首饰，请前往首饰盒租用", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        finish();
                        Toast.makeText(MyShowActivity.this, "暂无可以晒图的首饰，请前往首饰盒租用", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

    private void initdata() {

        Glide.with(this)
                .load(domainListEntity.getSpec_pic())
                .crossFade()
                .transform(new GlideRoundTransform(this, 3)).
                into(iv_pic);

        tv1.setText(domainListEntity.getGoods_name());
        tv2.setText(domainListEntity.getSpec_name());

    }


    @Override
    public String setTitleStr() {
        return "晒图";
    }

    @Override
    public void getRefreshData() {

    }


    @Override
    public void onItemClick(View v, int position, int flag) {
        if (flag == 0) {
            if (position == 0) {
                cameraUtil.callCamera();
            } else {
                cameraUtil.callLocalPhotos(1);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CommenString.TAKE_PHOTO) {
                cameraUtil.photoZoomFromTake(CropperImageScale.square);
            } else if (requestCode == CommenString.LOCAL_PHOTO) {
                List<String> selectedImage = data.getStringArrayListExtra("paths");
                cameraUtil.photoZoomFromMapStorage(selectedImage.get(0),
                        CropperImageScale.square);
            } else if (requestCode == CommenString.PHOTO_RESULT) {
                Bundle extras = data.getExtras();
                if (extras != null) {
                    headBitmap = extras.getParcelable("data");
                    image_up.setImageBitmap(headBitmap);
                }
            } else {
            }
        }
    }


}
