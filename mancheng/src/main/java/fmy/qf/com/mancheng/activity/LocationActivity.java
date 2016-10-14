package fmy.qf.com.mancheng.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;

import fmy.qf.com.mancheng.R;

public class LocationActivity extends AppCompatActivity {
    public LocationClient mLocationClient = null;
    private MapView mapView;
    private BaiduMap mBaiduMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_location);
        mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
        mapView = ((MapView) findViewById(R.id.mapView));
        mLocationClient.registerLocationListener(myListener);    //注册监听函数
        mBaiduMap = mapView.getMap();
        MapStatusUpdate  mapStatusUpdate2 = MapStatusUpdateFactory.zoomBy(8f);
        mBaiduMap.setMapStatus(mapStatusUpdate2);
    }


    @Override
    protected void onResume() {
        super.onResume();
        initLocation();
    }

    BDLocationListener myListener =  new BDLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation location) {

            if (null!= location) {

                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                LatLng latLng = new LatLng(latitude,longitude);

                MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng(latLng);
                mBaiduMap.animateMapStatus(mapStatusUpdate);
                mBaiduMap.clear();
                BitmapDescriptor bitmapDescriptor =null;
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.huaji);
                bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(bitmap);
                OverlayOptions options = new MarkerOptions().position(latLng).icon(bitmapDescriptor);
                mBaiduMap.addOverlay(options);
//                mBaiduMap.hideInfoWindow();


            }
        }
    };
    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span=0;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        mLocationClient.setLocOption(option);
        mLocationClient.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mLocationClient.stop();
    }
}
