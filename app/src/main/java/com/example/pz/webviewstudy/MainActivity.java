package com.example.pz.webviewstudy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.pz.webviewstudy.config.UrlCantent;
import com.example.pz.webviewstudy.tencentx5.X5WebViewActivity;
import com.example.pz.webviewstudy.utils.StatusBarUtil;
import com.example.pz.webviewstudy.widgets.GridViewAdapter;
import com.example.pz.webviewstudy.widgets.MyGridView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {


    int[] imgs = {R.drawable.img_tentv, R.drawable.img_iqiyi, R.drawable.img_youku, R.drawable.img_manggtv,
            R.drawable.img_souhutv, R.drawable.img_pptv, R.drawable.img_hantv, R.drawable.img_miguvideo,
            R.drawable.img_letv, R.drawable.img_bilibili, R.drawable.img_ku6, R.drawable.img_v1,
            R.drawable.img_feng, R.drawable.img_gusi, R.drawable.img_80s, R.drawable.img_360,
            R.drawable.img_7060, R.drawable.img_sina};
    int[] names = {R.string.url_tenqq, R.string.url_iqiyi, R.string.url_youku, R.string.url_mgtv,
            R.string.url_sohu, R.string.url_pptv, R.string.url_hantv, R.string.url_migutv,
            R.string.url_letv, R.string.url_bilibili, R.string.url_ku6, R.string.url_v1,
            R.string.url_fengxing, R.string.url_sigu, R.string.url_80s, R.string.url_360kan,
            R.string.url_7060, R.string.url_sina};
    String[] urls = {UrlCantent.TENQQ, UrlCantent.IQIYI, UrlCantent.YOUKU, UrlCantent.MGTV,
            UrlCantent.SOHU, UrlCantent.PPTV, UrlCantent.HANJUTV, UrlCantent.MIGUVIDEO,
            UrlCantent.LETV, UrlCantent.BILIBILI, UrlCantent.KU6, UrlCantent.V1,
            UrlCantent.FUN, UrlCantent.SIGU, UrlCantent.Y80S, UrlCantent.KAN360,
            UrlCantent.i7060, UrlCantent.SINA};


    // 是否开启了主页，没有开启则会返回主页
    public static boolean isLaunch = false;
    private AutoCompleteTextView etSearch;
    private RadioButton rbSystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.colorPrimary), 0);
        initView();
        isLaunch = true;
    }

    private void initView() {
        findViewById(R.id.bt_openUrl).setOnClickListener(this);
        MyGridView gv = findViewById(R.id.gv);
        GridViewAdapter gridViewAdapter = new GridViewAdapter(this, imgs, names);
        gv.setAdapter(gridViewAdapter);
        gv.setOnItemClickListener(this);
        rbSystem = findViewById(R.id.rb_system);
        etSearch = findViewById(R.id.et_search);
        rbSystem.setChecked(true);
        /** 处理键盘搜索键 */
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    openUrl();
                }
                return false;
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        loadUrl(urls[position], getString(names[position]));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_openUrl) {
            openUrl();
        }
    }

    /**
     * 打开网页
     */
    private void openUrl() {
        String url = etSearch.getText().toString().trim();
        if (TextUtils.isEmpty(url)) {
            // 空url
            url = "http://www.baidu.com";

        } else if (!url.startsWith("http") && url.contains("http")) {
            // 有http且不在头部
            url = url.substring(url.indexOf("http"));

        } else if (url.startsWith("www")) {
            // 以"www"开头
            url = "http://" + url;

        } else if (!url.startsWith("http") && (url.contains(".me") || url.contains(".com") || url.contains(".cn"))) {
            // 不以"http"开头且有后缀
            url = "http://www." + url;

        } else if (!url.startsWith("http") && !url.contains("www")) {
            // 输入纯文字 或 汉字的情况
            url = "http://m5.baidu.com/s?from=124n&word=" + url;
        }
        loadUrl(url, "详情");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionbar_update:
                loadUrl("https://github.com/", "网页浏览器 - fir.im");
                break;
            case R.id.actionbar_about:
                loadUrl("https://github.com/", "github.com");
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadUrl(String mUrl, String mTitle) {
        if (rbSystem.isChecked()) {
            WebViewActivity.loadUrl(this, mUrl, mTitle);
        } else {
            X5WebViewActivity.loadUrl(this, mUrl, mTitle);
        }
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isLaunch = false;
    }
}
