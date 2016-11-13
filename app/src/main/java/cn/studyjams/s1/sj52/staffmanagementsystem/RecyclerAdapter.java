package cn.studyjams.s1.sj52.staffmanagementsystem;


import android.content.ContentResolver;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;




/**
 * Created by Apc on 2016/10/9.
 */
class RecyclerAdapter extends RecyclerView.Adapter implements LoaderManager.LoaderCallbacks {

    MainActivity mainActivity;
    private Cursor mCursor;
    private String name_text;
    private String number_text;
    private String deptName_text;
    private String avatar;
    private String positions;
    private String sex;
    private String nativePlace;
    private String nationalities;
    private String nation;
    private String maternityStatus;
    private String birthDate;
    private String phoneNumber;
    private String officeSeat;
    private String mailAddress;
    private String address;
    private String qq;
    private String weChat;

    @Override
    public Loader onCreateLoader(int id, Bundle args) {

        String selection = "";
        if (mainActivity.selected_tab1) {
            selection = "deptName = '行政部'";
        }
        if (mainActivity.selected_tab2) {
            if (!selection.isEmpty()) {
                selection += " OR ";
            }
            selection += "deptName = '财务部'";
        }
        if (mainActivity.selected_tab3) {
            if (!TextUtils.isEmpty(selection)) {
                selection += " OR ";
            }
            selection += "deptName = '技术部'";
        }
        if (mainActivity.selected_tab4) {
            if (!TextUtils.isEmpty(selection)) {
                selection += " OR ";
            }
            selection += "deptName = '销售部'";
        }
        if (mainActivity.selected_tab5) {
            if (!TextUtils.isEmpty(selection)) {
                selection += " OR ";
            }
            selection += "deptName = '管理层'";
        }

        if (mainActivity.selected_tab6) {
            if (!TextUtils.isEmpty(selection)) {
                selection += " OR ";
            }
            selection += "deptName = '其他部'";
        }

        return new CursorLoader(mainActivity, DemoProvider.URI,null, selection, null, null);
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {

        if (mCursor != data) {
            if (mCursor != null) {
                mCursor.close();
            }
            mCursor = (Cursor) data;
        }
        notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader loader) {
        if (mCursor != null) {
            mCursor.close();
        }
        mCursor = null;
    }

    private class ViewHolder extends RecyclerView.ViewHolder{

        ViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recyclerview_content,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        TextView name = (TextView) holder.itemView.findViewById(R.id.name);
        TextView staffNumber = (TextView) holder.itemView.findViewById(R.id.staffNumber);
        TextView dept_title = (TextView) holder.itemView.findViewById(R.id.dept_title);
        ImageView decrement_button = (ImageView) holder.itemView.findViewById(R.id.decrement_button);
        LinearLayout recyclerView = (LinearLayout) holder.itemView.findViewById(R.id.recyclerView);

        if(mCursor.moveToPosition(position)){
            name_text = mCursor.getString(mCursor.getColumnIndex("name"));
            name.setText(name_text);
            number_text = mCursor.getString(mCursor.getColumnIndex("number"));
            staffNumber.setText(number_text);
            deptName_text = mCursor.getString(mCursor.getColumnIndex("deptName"));
            dept_title.setText(deptName_text);
            avatar = mCursor.getString(mCursor.getColumnIndex("avatar"));
            positions = mCursor.getString(mCursor.getColumnIndex("position"));
            sex = mCursor.getString(mCursor.getColumnIndex("sex"));
            nativePlace = mCursor.getString(mCursor.getColumnIndex("nativePlace"));
            nationalities = mCursor.getString(mCursor.getColumnIndex("nationalities"));
            nation = mCursor.getString(mCursor.getColumnIndex("nation"));
            maternityStatus = mCursor.getString(mCursor.getColumnIndex("maternityStatus"));
            birthDate = mCursor.getString(mCursor.getColumnIndex("birthDate"));
            phoneNumber = mCursor.getString(mCursor.getColumnIndex("phoneNumber"));
            officeSeat = mCursor.getString(mCursor.getColumnIndex("officeSeat"));
            mailAddress = mCursor.getString(mCursor.getColumnIndex("mailAddress"));
            address = mCursor.getString(mCursor.getColumnIndex("address"));
            qq = mCursor.getString(mCursor.getColumnIndex("qq"));
            weChat = mCursor.getString(mCursor.getColumnIndex("weChat"));


        }

        final String save_name = name_text;
        final String save_number = number_text;
        final String save_dept = deptName_text;
        final String save_avatar = avatar;
        final String save_positions = positions;
        final String save_sex = sex;
        final String save_nativePlace = nativePlace;
        final String save_nationalities = nationalities;
        final String save_nation = nation;
        final String save_maternityStatus = maternityStatus;
        final String save_birthDate= birthDate;
        final String save_phoneNumber =phoneNumber;
        final String save_officeSeat =officeSeat;
        final  String save_mailAddress =mailAddress;
        final String save_address = address;
        final String save_qq = qq;
        final String save_weChat = weChat;

        decrement_button.setOnClickListener(new View.OnClickListener() {       //设置每个itemView“删除”按钮的监听器
            @Override
            public void onClick(View view) {
                if(mCursor.moveToPosition(position)){
                    ContentResolver cr = mainActivity.getContentResolver(); //外界的程序通过ContentResolver接口可以访问ContentProvider提供的数据，在Activity当中通过getContentResolver()可以得到当前应用的 ContentResolver实例
                    String whereName = "Number = ?";
                    String[] selectionArgsnName = { save_number};
                    cr.delete(DemoProvider.URI, whereName, selectionArgsnName);
                }
            }
        });


        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mainActivity,StaffDetailsActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("save_name",save_name);
                bundle.putString("save_number",save_number);
                bundle.putString("save_dept",save_dept);
                bundle.putString("save_avatar",save_avatar);
                bundle.putString("save_positions",save_positions);
                bundle.putString("save_sex",save_sex);
                bundle.putString("save_nativePlace",save_nativePlace);
                bundle.putString("save_nationalities",save_nationalities);
                bundle.putString("save_nation",save_nation);
                bundle.putString("save_maternityStatus",save_maternityStatus);
                bundle.putString("save_birthDate",save_birthDate);
                bundle.putString("save_phoneNumber",save_phoneNumber);
                bundle.putString("save_officeSeat",save_officeSeat);
                bundle.putString("save_mailAddress",save_mailAddress);
                bundle.putString("save_address",save_address);
                bundle.putString("save_qq",save_qq);
                bundle.putString("save_weChat",save_weChat);

                intent.putExtras(bundle);
                mainActivity.startActivity(intent);

            }
        });

}

    @Override
    public int getItemCount() {
        if (mCursor != null) {  /** normal case */
            return mCursor.getCount();
        } else {                /** mCursor == null, rare case */
            return 0;
        }
    }




    }

