package cn.studyjams.s1.sj52.staffmanagementsystem;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by 1 on 2016/11/8.
 */
public class SearchAdapter extends RecyclerView.Adapter implements LoaderManager.LoaderCallbacks{  //recyclerView + 异步信息查询
    SearchActivity searchActivity;

    private Cursor sCursor;
    private String search_data;//搜索的文本字符

    static ArrayList<Map<String,String>> mData = new ArrayList<Map<String, String>>();

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return new CursorLoader(searchActivity,DemoProvider.URI,null,null,null, null);
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {
        if (sCursor != data) {
            if (sCursor != null) {
                sCursor.close();
            }
            sCursor = (Cursor) data;
        }

        mData.clear();//清空List数据
        getmDataSub(mData, search_data);
        notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader loader) {
        if (sCursor != null) {
            sCursor.close();
        }
        sCursor = null;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(searchActivity).inflate(R.layout.recyclerview_search,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        TextView search_name = (TextView) holder.itemView.findViewById(R.id.search_name);
        TextView search_staffNumber = (TextView) holder.itemView.findViewById(R.id.search_dept_title);
        TextView search_dept_title = (TextView) holder.itemView.findViewById(R.id.search_staffNumber);
        final Map<String, String> map = mData.get(position);

        search_name.setText( map.get("姓名："));
        search_staffNumber.setText( map.get("员工号："));
        search_dept_title.setText(  map.get("部门："));
        LinearLayout recyclerView_search = (LinearLayout) holder.itemView.findViewById(R.id.recyclerview_search);
        recyclerView_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(searchActivity,StaffDetailsActivity.class);
                //传值
                Bundle bundle = new Bundle();
                bundle.putString("save_name",map.get("姓名："));
                bundle.putString("save_number", map.get("员工号："));
                bundle.putString("save_dept", map.get("部门："));
                bundle.putString("save_avatar",map.get("头像："));
                bundle.putString("save_positions",map.get("职位名称："));
                bundle.putString("save_sex",map.get("性别："));
                bundle.putString("save_nativePlace",map.get("籍贯："));
                bundle.putString("save_nationalities",map.get("国籍："));
                bundle.putString("save_nation",map.get("民族："));
                bundle.putString("save_maternityStatus",map.get("婚姻状况："));
                bundle.putString("save_birthDate",map.get("出生年月："));
                bundle.putString("save_phoneNumber",map.get("手机号："));
                bundle.putString("save_officeSeat",map.get("办公座位号："));
                bundle.putString("save_mailAddress",map.get("邮箱："));
                bundle.putString("save_address",map.get("通讯地址："));
                bundle.putString("save_qq",map.get("QQ："));
                bundle.putString("save_weChat",map.get("微信号："));
                bundle.putString("save_eduExperience",map.get("教育经历："));
                bundle.putString("save_workExperience",map.get("工作经历："));
                intent.putExtras(bundle);
                searchActivity.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        if (null != mData) {  /** normal case */
            return mData.size();
        } else {                /** mCursor == null, rare case */
            return 0;
        }
    }



    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            search_data = searchActivity.etSearch.getText().toString();
            mData.clear();//清空List数据
            getmDataSub(mData, search_data);
            notifyDataSetChanged();//数据发生改变，通知刷新。
        }
    };


    void getmDataSub(ArrayList<Map<String, String>> mData, String data){
        if(sCursor != null && sCursor.getCount()> 0 && !TextUtils.isEmpty(search_data)){
                for(int m = 0; m<sCursor.getCount();m++){
                    if(sCursor.moveToPosition(m)){
                        String search_name_text = sCursor.getString(sCursor.getColumnIndex("name"));
                        String search_num_text = sCursor.getString(sCursor.getColumnIndex("number"));
                        String search_dept_text = sCursor.getString(sCursor.getColumnIndex("deptName"));
                        if(search_name_text.contains(search_data) || search_num_text.contains(search_data)|| search_dept_text.contains(search_data)){
                            Map<String,String> item = new HashMap<String,String>();
                            item.put("姓名：", search_name_text);
                            item.put("员工号：",search_num_text);
                            item.put("部门：", search_dept_text);
                            item.put("职位名称：", sCursor.getString(sCursor.getColumnIndex("position")));
                            item.put("头像：", sCursor.getString(sCursor.getColumnIndex("avatar")));
                            item.put("性别：", sCursor.getString(sCursor.getColumnIndex("sex")));
                            item.put("籍贯：", sCursor.getString(sCursor.getColumnIndex("nativePlace")));
                            item.put("民族：", sCursor.getString(sCursor.getColumnIndex("nation")));
                            item.put("国籍：", sCursor.getString(sCursor.getColumnIndex("nationalities")));
                            item.put("婚姻状况：", sCursor.getString(sCursor.getColumnIndex("maternityStatus")));
                            item.put("出生年月：", sCursor.getString(sCursor.getColumnIndex("birthDate")));
                            item.put("手机号：", sCursor.getString(sCursor.getColumnIndex("phoneNumber")));
                            item.put("办公座位号：", sCursor.getString(sCursor.getColumnIndex("officeSeat")));
                            item.put("邮箱：", sCursor.getString(sCursor.getColumnIndex("mailAddress")));
                            item.put("通讯地址：", sCursor.getString(sCursor.getColumnIndex("address")));
                            item.put("QQ：",sCursor.getString(sCursor.getColumnIndex("qq")));
                            item.put("微信号：", sCursor.getString(sCursor.getColumnIndex("weChat")));
                            item.put("教育经历：",sCursor.getString(sCursor.getColumnIndex("eduExperience")));
                            item.put("工作经历：", sCursor.getString(sCursor.getColumnIndex("workExperience")));
                            mData.add(item);
                        }
                    }
                }

        }

    }

}
