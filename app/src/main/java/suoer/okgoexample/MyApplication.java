package suoer.okgoexample;

import android.app.Application;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;

/**
 * Created by admin on 2017-03-23.
 */

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        OkGo.init(this);
        try {
        OkGo.getInstance()
        .debug("OkGo") //是否打开调试
        .setConnectTimeout(OkGo.DEFAULT_MILLISECONDS) //设置全局连接超时时间
        .setReadTimeOut(OkGo.DEFAULT_MILLISECONDS) //设置全局读取超时时间
        .setWriteTimeOut(OkGo.DEFAULT_MILLISECONDS)//设置全局写入超时时间
        .setCacheMode(CacheMode.NO_CACHE) //可以全局统一设置缓存模式,默认是不使用缓存,可以不传
        .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE) //可以全局统一设置缓存时间,默认永不过期
        .setRetryCount(3)//可以全局统一设置超时重连次数,默认为三次,那么最差的情况会请求4次(一次原始请求,三次重连请求),不需要可以设置为0

                //如果不想让框架管理cookie（或者叫session的保持）,以下不需要
//              .setCookieStore(new MemoryCookieStore())            //cookie使用内存缓存（app退出后，cookie消失）
//              .setCookieStore(new PersistentCookieStore())        //cookie持久化存储，如果cookie不过期，则一直有效

                //可以设置https的证书,以下几种方案根据需要自己设置
                .setCertificates()  ;                           //方法一：信任所有证书,不安全有风险
//              .setCertificates(new SafeTrustManager())            //方法二：自定义信任规则，校验服务端证书
//              .setCertificates(getAssets().open("srca.cer"))      //方法三：使用预埋证书，校验服务端证书（自签名证书）
//              //方法四：使用bks证书和密码管理客户端证书（双向认证），使用预埋证书，校验服务端证书（自签名证书）
//               .setCertificates(getAssets().open("xxx.bks"), "123456", getAssets().open("yyy.cer"))//

                //配置https的域名匹配规则，详细看demo的初始化介绍，不需要就不要加入，使用不当会导致https握手失败
//               .setHostnameVerifier(new SafeHostnameVerifier())

                //可以添加全局拦截器，不需要就不要加入，错误写法直接导致任何回调不执行
//                .addInterceptor(new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        return chain.proceed(chain.request());
//                    }
//                })

                //这两行同上，不需要就不要加入
//                .addCommonHeaders(headers)  //设置全局公共头
//                .addCommonParams(params);   //设置全局公共参数

    } catch (Exception e) {
        e.printStackTrace();
    }




    }

}
