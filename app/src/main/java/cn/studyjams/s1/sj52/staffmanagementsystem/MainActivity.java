package cn.studyjams.s1.sj52.staffmanagementsystem;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {  //主界面

    TextView admin;//行政部tab
    TextView financial;//财务部tab
    TextView technical;//技术部tab
    TextView sales; //销售部tab
    TextView management;//管理层tab
    TextView otherDept; //其他部tab
    View admin_line;
    View financial_line;
    View technical_line;
    View sales_line;
    View management_line;
    View otherDept_line;
    ImageView incrementBtn;

    RecyclerView recyclerview;
    RecyclerAdapter adapter;
    boolean selected_tab1;
    boolean selected_tab2;
    boolean selected_tab3;
    boolean selected_tab4;
    boolean selected_tab5;
    boolean selected_tab6;
    RelativeLayout search_bar; //搜索框

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        admin = (TextView) findViewById(R.id.admin);
        financial = (TextView) findViewById(R.id.financial);
        technical = (TextView) findViewById(R.id.technical);
        sales = (TextView) findViewById(R.id.sales);
        management = (TextView) findViewById(R.id.management);
        otherDept = (TextView) findViewById(R.id.otherDept);
        admin_line = findViewById(R.id.admin_line);
        financial_line = findViewById(R.id.financial_line);
        technical_line = findViewById(R.id.technical_line);
        sales_line = findViewById(R.id.sales_line);
        management_line = findViewById(R.id.management_line);
        otherDept_line = findViewById(R.id.other_line);
        incrementBtn = (ImageView) findViewById(R.id.incrementBtn);
        search_bar = (RelativeLayout) findViewById(R.id.search_bar);

        /**设置“搜索框”监听器**/
        search_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });


        /**设置点击“增加”按钮的监听器**/
        incrementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,StaffDetailsActivity.class);
                startActivity(intent);
            }
        });

        //设置tab_textView下划线
        admin.setText("行政部");
        admin.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG); //下划线
        admin.getPaint().setAntiAlias(true);//抗锯齿
        admin.getPaint().setColor(0x4F94CD);
        financial.setText("财务部");
        financial.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG ); //下划线
        financial.getPaint().setAntiAlias(true);//抗锯齿
        financial.getPaint().setColor(0x4F94CD);
        technical.setText("技术部");
        technical.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG ); //下划线
        technical.getPaint().setAntiAlias(true);//抗锯齿
        technical.getPaint().setColor(0x4F94CD);
        sales.setText("销售部");
        sales.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG ); //下划线
        sales.getPaint().setAntiAlias(true);//抗锯齿
        sales.getPaint().setColor(0x4F94CD);
        management.setText("管理层");
        management.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG ); //下划线
        management.getPaint().setAntiAlias(true);//抗锯齿
        management.getPaint().setColor(0x4F94CD);
        otherDept.setText("其他部");
        otherDept.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG ); //下划线
        otherDept.getPaint().setAntiAlias(true);//抗锯齿
        otherDept.getPaint().setColor(0x4F94CD);


        adapter = new RecyclerAdapter();
        recyclerview = (RecyclerView) findViewById(R.id.recycler);
        assert recyclerview != null;
        recyclerview.setAdapter(adapter);
        adapter.mainActivity = MainActivity.this;

        getSupportLoaderManager().restartLoader(1, null, adapter);//*LoaderManager就是加载器的管理器，一个LoaderManager可管理一个或多个Loader，
        // 一个Activity只能有一个LoadManager. LoaderManager管理Loader的初始化，重启和销毁操作。
      }

    /**设置点击每个tab栏的监听事件。**/
    public void tabClicked(View view){

        switch (view.getId()){
            case R.id.admin:
                if(selected_tab1){
                    selected_tab1 = false;
                    admin_line.setVisibility(View.INVISIBLE);
                } else {
                    selected_tab1 = true;
                    admin_line.setVisibility(View.VISIBLE);
                }
                getSupportLoaderManager().restartLoader(1, null, adapter);
                break;
            case R.id.financial:
                if (selected_tab2) {
                    selected_tab2 = false;
                    financial_line.setVisibility(View.INVISIBLE);
                } else {
                    selected_tab2 = true;
                    financial_line.setVisibility(View.VISIBLE);
                }
                getSupportLoaderManager().restartLoader(1, null, adapter);
                break;
            case R.id.technical:
                if(selected_tab3){
                    selected_tab3 = false;
                    technical_line.setVisibility(View.INVISIBLE);
                }else {
                    selected_tab3 = true;
                    technical_line.setVisibility(View.VISIBLE);
                }
                getSupportLoaderManager().restartLoader(1, null, adapter);
                break;
            case R.id.sales:
                if(selected_tab4){
                    selected_tab4 = false;
                    sales_line.setVisibility(View.INVISIBLE);
                }else {
                    selected_tab4 = true;
                    sales_line.setVisibility(View.VISIBLE);
                }
                getSupportLoaderManager().restartLoader(1, null, adapter);
                break;
            case R.id.management:
                if(selected_tab5){
                    selected_tab5 = false;
                    management_line.setVisibility(View.INVISIBLE);
                }else {
                    selected_tab5 = true;
                    management_line.setVisibility(View.VISIBLE);
                }
                getSupportLoaderManager().restartLoader(1, null, adapter);
                break;
            case R.id.otherDept:
                if(selected_tab6){
                    selected_tab6 = false;
                    otherDept_line.setVisibility(View.INVISIBLE);
                }else {
                    selected_tab6 = true;
                    otherDept_line.setVisibility(View.VISIBLE);
                }
                getSupportLoaderManager().restartLoader(1, null, adapter);
                break;

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
