package cn.studyjams.s1.sj52.staffmanagementsystem;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import static cn.studyjams.s1.sj52.staffmanagementsystem.SearchAdapter.mData;


public class SearchActivity extends AppCompatActivity {  //“搜索框”界面
    ImageView returnArrow;//返回箭头
    ImageView ivDeleteText; //搜索框中的删除按钮
    EditText etSearch;  //搜索框中的文本框
    RecyclerView recycler_searchData;
    SearchAdapter searchAdapter;
    static Handler handler = new Handler();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        returnArrow = (ImageView) findViewById(R.id.return_arrow);

        recycler_searchData = (RecyclerView) findViewById(R.id.recycler_searchData);
        searchAdapter = new SearchAdapter();
        searchAdapter.searchActivity = this;
        assert recycler_searchData != null;
        recycler_searchData.setAdapter(searchAdapter);

/**
 * 设置点击 返回箭头的监听器
 **/
        returnArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });

        set_eSearch_TextChanged();//设置点击 搜索文本框的监听器
        set_ivDeleteText_OnClick();//设置点击 搜索文本框中的“删除”按钮的监听器

        getSupportLoaderManager().restartLoader(0, null, searchAdapter);//开启Loader.
    }


/**
 * 设置点击 搜索文本框的监听器
 **/
    private void set_eSearch_TextChanged() {
        ivDeleteText = (ImageView) findViewById(R.id.ivDeleteText);
        etSearch = (EditText) findViewById(R.id.etSearch);


        etSearch.addTextChangedListener(new TextWatcher() {  //实时监控文本框变化
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {  //在输入数据时监听
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) { //输入数据之前的监听
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {   //输入数据之后监听,表示最终内容
                if (s.length() == 0) {
                    ivDeleteText.setVisibility(View.GONE);
                } else {
                    ivDeleteText.setVisibility(View.VISIBLE);
                }
                handler.post(searchAdapter.runnable); //文本框数据发生变化时，传送message给子线程让进行数据更新。
            }
        });
    }


    /**
     * 设置点击 搜索文本框中的“删除”按钮的监听器
     **/
    void set_ivDeleteText_OnClick(){
    ivDeleteText.setOnClickListener(new View.OnClickListener() {

        public void onClick(View v) {
            etSearch.setText("");
        }
    });}

        }


