package com.weisj.pj.view.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;
import com.weisj.pj.R;
import com.weisj.pj.bean.ShareData;
import com.weisj.pj.utils.KeyboardUtil;
import com.weisj.pj.utils.QRCodeUtil;
import com.weisj.pj.utils.SystemConfig;
import com.weisj.pj.utils.TextViewUtils;

import java.io.File;

/**
 * Created by Administrator on 2016/7/27 0027.
 */
public class ImageShareDialog extends AlertDialog implements View.OnClickListener {
    private ImageView image;
    private Bitmap bitmap;
    private Context context;
    private ShareData data;

    public ImageShareDialog(Context context, ShareData data) {
        super(context, R.style.dialog);
        this.context = context;
        this.data = data;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_image_share);
        image = (ImageView) findViewById(R.id.share_image);
        if (data.isShareHome()){
            bitmap = QRCodeUtil.getShareImage(data.getUrl(),data.getTitle());
        }else {
            bitmap = QRCodeUtil.getShareImage(data.getBitmap(), data.getUrl(),data.getTitle(),data.getPrice(), TextViewUtils.sprStringMoney(data.getDelMoney()));
        }
        if (bitmap == null) {
            Toast.makeText(context, "分享失败", Toast.LENGTH_SHORT).show();
        } else {
            image.setImageBitmap(bitmap);
            findViewById(R.id.click_wx).setOnClickListener(this);
            findViewById(R.id.click_wechat_py).setOnClickListener(this);
            findViewById(R.id.click_link).setOnClickListener(this);
            findViewById(R.id.click_qq).setOnClickListener(this);
            findViewById(R.id.click_download).setOnClickListener(this);
            findViewById(R.id.click_sina).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.click_download:
//                File file = BitmapUtil.saveBitmapFile(bitmap, String.format("%s%d", "share", System.currentTimeMillis()));
//                if (file != null) {
//                    SystemConfig.showToast("保存到" + file.getParent().toString());
//                } else {
//                    SystemConfig.showToast("保存失败");
//                }
                String filePath = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, String.format("%s%d", "share", System.currentTimeMillis()), "");
                if (filePath != null) {
                    SystemConfig.showToast("保存到相册");
                } else {
                    SystemConfig.showToast("保存失败");
                }
                Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri uri = Uri.fromFile(new File(filePath));
                intent.setData(uri);
                context.sendBroadcast(intent);
                break;
            case R.id.click_link:
                KeyboardUtil.copy(data.getUrl(), getContext());
                break;
        }
        dismiss();
    }
}
