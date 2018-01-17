package com.weisj.pj.base.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.WindowManager;

import com.weisj.pj.MainActivity;
import com.weisj.pj.R;
import com.weisj.pj.utils.LocationUtils;
import com.weisj.pj.utils.PersonMessagePreferencesUtils;
import com.weisj.pj.utils.PreferencesUtils;

/**
 * Created by Administrator on 2016/7/20 0020.
 */
public class SplashActivity extends Activity {


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                if (PreferencesUtils.getBoolean("initFinish") || true) {
                    if (PersonMessagePreferencesUtils.getUid() != null) {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    } else {
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    }
                } else {
                    Intent intent = new Intent(SplashActivity.this, AboutActivity.class);
                    intent.putExtra("type", 1);
                    startActivity(intent);
                }
                finish();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        setContentView(R.layout.activity_splash);
////        PersonMessagePreferencesUtils.storeInfo();
//        handler.sendEmptyMessageDelayed(1, 2000);
//        new NavPresenter().getNavData();
//
//        LocationUtils locationUtils = new LocationUtils(this);
//        mLocationClient = new LocationClient(getApplicationContext());
//        //声明LocationClient类
//        mLocationClient.registerLocationListener(myListener);
//        mLocationClient.requestOfflineLocation();
//        initLocation();
//        mLocationClient.start();
    }
//
//    private void initLocation() {
//        LocationClientOption option = new LocationClientOption();
//        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
//        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
//        option.setCoorType("bd09ll");
//        //可选，默认gcj02，设置返回的定位结果坐标系
//        int span = 1000;
//        option.setScanSpan(span);
//        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
//        option.setIsNeedAddress(true);
//        //可选，设置是否需要地址信息，默认不需要
//        option.setOpenGps(true);
//        //可选，默认false,设置是否使用gps
//        option.setLocationNotify(true);
//        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
//        option.setIsNeedLocationDescribe(true);
//        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
//        option.setIsNeedLocationPoiList(true);
//        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
//        option.setIgnoreKillProcess(false);
//        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
//        option.SetIgnoreCacheException(false);
//        //可选，默认false，设置是否收集CRASH信息，默认收集
//        option.setEnableSimulateGps(false);
//        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
//        mLocationClient.setLocOption(option);
//    }
//
//    private class MyLocationListener implements BDLocationListener {
//        @Override
//        public void onReceiveLocation(BDLocation location) {
//            //获取定位结果
//            StringBuffer sb = new StringBuffer(256);
//
//            sb.append("time : ");
//            sb.append(location.getTime());    //获取定位时间
//
//            sb.append("\nerror code : ");
//            sb.append(location.getLocType());    //获取类型类型
//
//            sb.append("\nlatitude : ");
//            sb.append(location.getLatitude());    //获取纬度信息
//
//            sb.append("\nlontitude : ");
//            sb.append(location.getLongitude());    //获取经度信息
//
//            sb.append("\nradius : ");
//            sb.append(location.getRadius());    //获取定位精准度
//
//            if (location.getLocType() == BDLocation.TypeGpsLocation) {
//
//                // GPS定位结果
//                sb.append("\nspeed : ");
//                sb.append(location.getSpeed());    // 单位：公里每小时
//
//                sb.append("\nsatellite : ");
//                sb.append(location.getSatelliteNumber());    //获取卫星数
//
//                sb.append("\nheight : ");
//                sb.append(location.getAltitude());    //获取海拔高度信息，单位米
//
//                sb.append("\ndirection : ");
//                sb.append(location.getDirection());    //获取方向信息，单位度
//
//                sb.append("\naddr : " + location.getAddress().city);
//                sb.append(location.getAddrStr());    //获取地址信息
//
//                sb.append("\ndescribe : ");
//                sb.append("gps定位成功");
//
//            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
//
//                // 网络定位结果
//                sb.append("\naddr : ");
//                sb.append(location.getAddrStr() + "==" + location.getAddress().city);    //获取地址信息
//
//                sb.append("\noperationers : ");
//                sb.append(location.getOperators());    //获取运营商信息
//                sb.append("\ndescribe : ");
//                sb.append("网络定位成功");
//
//            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {
//
//                // 离线定位结果
//                sb.append("\ndescribe : ");
//
//                sb.append("离线定位成功，离线定位结果也是有效的" + location.getAddress().city);
//
//            } else if (location.getLocType() == BDLocation.TypeServerError) {
//
//                sb.append("\ndescribe : ");
//                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
//
//            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
//
//                sb.append("\ndescribe : ");
//                sb.append("网络不同导致定位失败，请检查网络是否通畅");
//
//            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
//
//                sb.append("\ndescribe : ");
//                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
//
//            }
//
//            sb.append("\nlocationdescribe : ");
//            sb.append(location.getLocationDescribe());    //位置语义化信息
//
//
//            Log.i("BaiduLocationApiDem", sb.toString());
//        }
//
//        @Override
//        public void onConnectHotSpotMessage(String s, int i) {
//
//        }
//
//
//    }
//

}
