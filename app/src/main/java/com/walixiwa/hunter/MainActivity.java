package com.walixiwa.hunter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.walixiwa.webhunter.WebHunter;
import com.walixiwa.webhunter.WebParser;
import com.walixiwa.webhunter.WebTool;
import com.walixiwa.webhunter.model.BaseParseUrlModel;
import com.walixiwa.webhunter.model.BaseWebHunterModel;
import com.walixiwa.webhunter.model.BaseWebResultModel;
import com.walixiwa.webhunter.model.ParseResultModel;
import com.walixiwa.webhunter.model.TabModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    private TextView mTextViewJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextViewJson = findViewById(R.id.json);
        mTextViewJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                if (cm != null) {
                    cm.setText(mTextViewJson.getText());
                }
                Toast.makeText(MainActivity.this, "复制成功!", Toast.LENGTH_LONG).show();
            }
        });
        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTextViewJson.setText(getModel3().toJson());
                Log.e(TAG, "onClick: " + getModel3().toJson());

                BaseWebHunterModel hunterModel = new BaseWebHunterModel(getModel3().toJson());
                new WebHunter().init(hunterModel).setCallBack(new WebHunter.CallBack() {
                    @Override
                    public void callBack(BaseWebHunterModel h, List<BaseWebResultModel> resultModels) {
                        for (int i = 0; i < resultModels.size(); i++) {
                            Log.e(TAG, "callBack: " + resultModels.get(i).getResultCover().toString());
                        }
                    }
                }).with(2, 0).start();
            }
        });
    }

     /*new WebParser().init(resultModels.get(i).getWebHunterModel().getBaseParseUrlModel()).withParamUrl(testUrl).setCallBack(new WebParser.CallBack() {
        @Override
        public void callBack(List<ParseResultModel> resultModels) {
            for (int i = 0; i < resultModels.size(); i++) {
                Log.e(TAG, "callBack: " + resultModels.get(i).getUrl());
            }
        }
    }).start();*/

    private BaseWebHunterModel getModel() {
        BaseWebHunterModel model = new BaseWebHunterModel();
        model.setModelName("AV淘宝");
        model.setModelUrl("http://www.avtb0055.com");
        model.setModelVersion("19.10.15");
        model.setRequestCharset("utf-8");
        model.setResultCharset("utf-8");
        model.setRuleResult("<li id=\"video-.*?\">[\\s\\S]*?</div>\n\t\t\t\t\t</li>");
        model.setRuleResultCover("<img src=\"(.*?)\"");
        model.setRuleResultLinkHeader("parent");
        model.setRuleResultLink("<a href=\"(.*?)\"");
        model.setRuleResultTitle("title=\"(.*?)\"");
        model.setRuleResultDate("<span class=\"pull-left\">(.*?)</span>");
        model.setHasDetailPage(true);

        BaseParseUrlModel baseParseUrlModel = new BaseParseUrlModel();
        baseParseUrlModel.setRuleFull("<source src=\".*?\" type=\"video/mp4\" label=\".*?\" res=\".*?\" />");
        baseParseUrlModel.setRuleUrl("<source src=\"(.*?)\"");
        baseParseUrlModel.setRuleTitle("label=\"(.*?)\"");
        model.setBaseParseUrlModel(baseParseUrlModel);

        model.setTabModels(Arrays.asList(
                new TabModel("亚洲", "/asian/%first/%page/", "recent/"),
                new TabModel("重口", "/awesome/%first/%page/", "recent/"),
                new TabModel("少女", "/babe/%first/%page/", "recent/"),
                new TabModel("潮吹", "/chaochui/%first/%page/", "recent/"),
                new TabModel("大屌", "/dadiao/%first/%page/", "recent/"),
                new TabModel("长视频", "/duration/%first/%page/", "recent/"),
                new TabModel("口爆颜射", "/facial/%first/%page/", "recent/"),
                new TabModel("肛交", "/gangjiao/%first/%page/", "recent/"),
                new TabModel("国产", "/guochan/%first/%page/", "recent/"),
                new TabModel("HD高清", "/hd/%first/%page/", "recent/"),
                new TabModel("户外", "/huwai/%first/%page/", "recent/"),
                new TabModel("巨乳", "/juru/%first/%page/", "recent/"),
                new TabModel("口交  ", "/koujiao/%first/%page/", "recent/"),
                new TabModel("乱伦", "/luanlun/%first/%page/", "recent/"),
                new TabModel("美女", "/meinv/%first/%page/", "recent/"),
                new TabModel("女同", "/nvtong/%first/%page/", "recent/"),
                new TabModel("欧美", "/oumei/%first/%page/", "recent/"),
                new TabModel("性派对", "/party/%first/%page/", "recent/"),
                new TabModel("器具及自慰", "/qiju/%first/%page/", "recent/"),
                new TabModel("群交", "/qunjiao/%first/%page/", "recent/"),
                new TabModel("强奸", "/rapping/%first/%page/", "recent/"),
                new TabModel("人妻熟女", "/renqi/%first/%page/", "recent/"),
                new TabModel("日本无码", "/ribenwuma/%first/%page/", "recent/"),
                new TabModel("日本有码", "/ribenyouma/%first/%page/", "recent/"),
                new TabModel("学生妹 校园", "/schoolgirl/%first/%page/", "recent/"),
                new TabModel("性爱", "/sex/%first/%page/", "recent/"),
                new TabModel("丝袜", "/siwa/%first/%page/", "recent/"),
                new TabModel("SM调教", "/sm/%first/%page/", "recent/"),
                new TabModel("中文字幕", "/subtitle/%first/%page/", "recent/"),
                new TabModel("素人", "/suren/%first/%page/", "recent/"),
                new TabModel("3P", "/threesome/%first/%page/", "recent/"),
                new TabModel("动漫无码", "/wuma/%first/%page/", "recent/"),
                new TabModel("动漫有码", "/youma/%first/%page/", "recent/"),
                new TabModel("制服诱惑", "/zhifu/%first/%page/", "recent/"),
                new TabModel("主播", "/zhubo/%first/%page/", "recent/"),
                new TabModel("足交", "/zujiao/%first/%page/", "recent/"))
        );
        return model;
    }


    private BaseWebHunterModel getModel2() {
        BaseWebHunterModel model = new BaseWebHunterModel();
        model.setModelName("天噜啦");
        model.setModelUrl("http://www.tlula209.com");
        model.setModelVersion("19.10.18");
        model.setRequestCharset("utf-8");
        model.setResultCharset("utf-8");
        model.setRuleResult("<li>[\\s\\S]*?</li>");
        model.setRuleResultCover("<img src=\"(.*?)\" />");
        model.setRuleResultLinkHeader("parent");
        model.setRuleResultLink("href=\"(.*?)\"");
        model.setRuleResultTitle("<h3>(.*?)</h3>");
        model.setRuleResultDate("(\\d{4}-\\d{2}-\\d{2})");
        model.setHasDetailPage(true);

        BaseParseUrlModel baseParseUrlModel = new BaseParseUrlModel();
        baseParseUrlModel.setRuleFull("var url = '.*?'");
        baseParseUrlModel.setRuleUrl("var url = '(.*?)'");
        model.setBaseParseUrlModel(baseParseUrlModel);

        model.setTabModels(Arrays.asList(
                new TabModel("日韩女优", "/vodlist/5%first/%page.html", "-"),
                new TabModel("欧美激情", "/vodlist/6%first/%page.html", "-"),
                new TabModel("偷拍自拍", "/vodlist/4%first/%page.html", "-"),
                new TabModel("成人动漫", "/vodlist/8%first/%page.html", "-"),
                new TabModel("经典三级", "/vodlist/7%first/%page.html", "-"),
                new TabModel("强奸乱伦", "/vodlist/9%first/%page.html", "-"),
                new TabModel("变态另类", "/vodlist/10%first/%page.html", "-"),
                new TabModel("制服丝袜", "/vodlist/11%first/%page.html", "-"),
                new TabModel("激情3P", "/vodlist/12%first/%page.html", "-"),
                new TabModel("中文字幕", "/vodlist/31%first/%page.html", "-")
                )
        );
        return model;
    }

    private BaseWebHunterModel getModel3() {
        BaseWebHunterModel model = new BaseWebHunterModel();
        model.setModelName("环亚无码");
        model.setModelUrl("https://api.gtvbb0x.com");
        model.setModelVersion("19.10.18");
        model.setRequestCharset("utf-8");
        model.setResultCharset("utf-8");
        model.setRuleResult("\\{\"id\":[\\s\\S]*?content\":\"\"\\}");
        model.setRuleResultCover("pic\":\"(.*?)\"");
        model.setRuleResultLinkHeader("https://api.gtvbb0x.com/Videos/Get?vid=");
        model.setRuleResultLink("\"id\":\"(.*?)\"");
        model.setRuleResultTitle("title\":\"(.*?)\"");
        model.setRuleResultDate("hits\":\"(.*?)\"");
        model.setHasDetailPage(true);

        BaseParseUrlModel baseParseUrlModel = new BaseParseUrlModel();
        baseParseUrlModel.setRuleFull("vurl\":\".*?\"");
        baseParseUrlModel.setRuleUrl("vurl\":\"(.*?)\"");
        model.setBaseParseUrlModel(baseParseUrlModel);

        model.setTabModels(Arrays.asList(
                new TabModel("乱伦无码", "/videos/GetVideoList?cvid=2&page=%page&pagesize=20"),
                new TabModel("强奸无码", "/videos/GetVideoList?cvid=3&page=%page&pagesize=20"),
                new TabModel("人妻无码", "/videos/GetVideoList?cvid=4&page=%page&pagesize=20"),
                new TabModel("制服无码", "/videos/GetVideoList?cvid=5&page=%page&pagesize=20"),
                new TabModel("巨乳无码", "/videos/GetVideoList?cvid=12&page=%page&pagesize=20")
        ));
        return model;
    }

    public static String readAssetsTxt(Context context, String fileName) {
        try {
            //Return an AssetManager instance for your application's package
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            // Convert the buffer into a string.
            String text = new String(buffer, "utf-8");
            // Finally stick the string into the text view.
            return text;
        } catch (IOException e) {
            // Should never happen!
//            throw new RuntimeException(e);
            e.printStackTrace();
        }
        return "读取错误，请检查文件名";
    }

}
