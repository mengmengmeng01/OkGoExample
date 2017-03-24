package suoer.okgoexample;

import android.graphics.Bitmap;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.BitmapCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.convert.StringConvert;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tv;
    private ImageView qr,photo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button btn1 = (Button) findViewById(R.id.up1);
        Button btn2 = (Button) findViewById(R.id.up2);
        Button btn3 = (Button) findViewById(R.id.up3);
        tv= (TextView)findViewById(R.id.tv_msg);
        qr = (ImageView)findViewById(R.id.qr);
        photo = (ImageView)findViewById(R.id.photo);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
//        InputStream stream = getClass().getClassLoader().getResourceAsStream("assets/" + "aa.jpg");
//        file:///android_asset/input.html

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.up1:
                test1();
                break;
            case R.id.up2:
                test2();
                break;
            case R.id.up3:
                test3();
                break;
        }
    }
//http://192.168.0.229:8087/api/V1.0/Patient/PatientInfoSearch?searchId=15620934278
    //"PatientImagePath":"http:\/\/192.168.0.229:8087\/Files\/Avatar\/20170224\/fc628856-a14f-4f0f-aef7-22cb1fa75ae1.jpg",
//"PatientQRCodePath":"http:\/\/192.168.0.229:8087\/Files\/QRCode\/20170308\/bbefedbd-1dae-4fa2-9a90-51166da15eac.jpeg"
    //
    private void test1() {
        OkGo.post("http://192.168.0.229:8087/api/V1.0/Patient/PatientInfoSearch?searchId=15620934278").execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                tv.setText(s);
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                tv.setText(response.toString()+e.getMessage());
            }
        });
        OkGo.get("http://192.168.0.229:8087//Files//Avatar//20170224//fc628856-a14f-4f0f-aef7-22cb1fa75ae1.jpg").execute(new BitmapCallback() {
            @Override
            public void onSuccess(Bitmap bitmap, Call call, Response response) {
                photo.setImageBitmap(bitmap);
//                tv.setText(response.toString());
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                tv.setText(response.toString());
            }
        });
        OkGo.get("http://192.168.0.229:8087//Files//QRCode//20170308//bbefedbd-1dae-4fa2-9a90-51166da15eac.jpeg").execute(new BitmapCallback() {
            @Override
            public void onSuccess(Bitmap bitmap, Call call, Response response) {
                qr.setImageBitmap(bitmap);
//                tv.setText(response.toString());
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                tv.setText(response.toString());
            }
        });

    }
    private void test2() {

        File file = new File("file:///android_assets/a.png");
        InputStream stream = getClass().getClassLoader().getResourceAsStream("assets/" + "a.png");
//        InputStream is = getAssets().open(fileName);
//        bitmap = BitmapFactory.decodeStream(is);
//        ivImg.setImageBitmap(bitmap);

        OkGo.post("http://192.168.0.229:8087/api/V1.0/Patient/PatientHeadImgUpload").params("file","").execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                tv.setText(s);
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                tv.setText(response.toString()+e.getMessage());
            }
        });
    }
    private void test3() {

        //本地读取数据库里的数据，一个个往服务器里写入，使用OKGo框架，要用for循环么，执行结果不对呀


//        for (int i=0;i<1000;i++){
//        RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=gb2312"),)

//        http://192.168.0.229:8087/api/V1.0/Patient/PatientInfoSearch?searchId=15620934278
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=unicode"), "searchId=15620934278");
//        System.out.print(requestBody.toString());
//        OkGo.post("http://120.25.226.186:32812/login").params("pwd","b").execute(new StringCallback() {
//                @Override
//                public void onSuccess(String s, Call call, Response response) {
//                    tv.append(s);
//                }
//                @Override
//                public void onError(Call call, Response response, Exception e) {
//                    super.onError(call, response, e);
//                    tv.setText(response.toString()+e.getMessage());
//                }
//            });
//        }
//        OkGo.post("http://192.168.0.229:8087/api/V1.0/Patient/PatientInfoSearch?searchId=15620934278").params("pwd","b").execute(new StringCallback() {
//                @Override
//                public void onSuccess(String s, Call call, Response response) {
//                    tv.append(s);
//                }
//                @Override
//                public void onError(Call call, Response response, Exception e) {
//                    super.onError(call, response, e);
//                    tv.setText(response.toString()+e.getMessage());
//                }
//            });
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("searchId","15620934278");
        } catch (JSONException e) {
            e.printStackTrace();
        }
//
        OkGo.post("http://192.168.0.229:8087/api/V1.0/Patient/PatientInfoSearch?searchId=15620934278").execute(new StringCallback() {
//      OkGo.post("http://192.168.0.229:8087/api/V1.0/Patient/PatientInfoSearch").params("searchId","15620934278").execute(new StringCallback() {
//      OkGo.post("http://192.168.0.229:8087/api/V1.0/Patient/PatientInfoSearch").upJson(jsonObject).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                tv.append(s);
            }
            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                tv.setText(response.toString()+e.getMessage());
            }
        });
        }
}
