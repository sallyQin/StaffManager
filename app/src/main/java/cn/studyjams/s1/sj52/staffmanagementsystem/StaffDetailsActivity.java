package cn.studyjams.s1.sj52.staffmanagementsystem;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.drawee.view.SimpleDraweeView;


public class StaffDetailsActivity extends AppCompatActivity {
    EditText details_name_text;
    EditText staffNum_details_text;
    EditText details_dept;
    EditText name_text;
    SimpleDraweeView simpleDraweeView;
    EditText position_text;
    EditText sex_text;
    EditText nativePlace_text;
    EditText nationality_text;
    EditText nation_text;
    EditText maternityStatus_text;
    EditText birthDate_text;
    EditText phoneNums_text;
    EditText officeSeat_text;
    EditText mailAddress_text;
    EditText address_text;
    EditText qq_text;
    EditText weChatNum_text;
    String saved_id; //保存的员工号
    TextView saveDetails;//保存按钮
    final String TAG = "StaffDetailsActivity";
    private static final int SELECTED_PERMISSION_STORAGE = 1;
    private static final int REQUEST_ALBUM_OK = 2; //相册requestCode
    Intent intent;
    Bundle bundle;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_details);
        details_name_text = (EditText) findViewById(R.id.details_name);
        staffNum_details_text = (EditText) findViewById(R.id.staffNum_details);
        details_dept = (EditText) findViewById(R.id.details_dept);
        name_text = (EditText) findViewById(R.id.name_text);
        simpleDraweeView = (SimpleDraweeView) findViewById(R.id.simpleDraweeView);
        position_text = (EditText) findViewById(R.id.details_position);
        sex_text = (EditText) findViewById(R.id.sex_text);
        nativePlace_text = (EditText) findViewById(R.id.nativePlace_text);
        nationality_text = (EditText) findViewById(R.id.nationality_text);
        nation_text = (EditText) findViewById(R.id.nation_text);
        maternityStatus_text = (EditText) findViewById(R.id.details_maternityStatus);
        birthDate_text = (EditText) findViewById(R.id.birthDate_text);
        phoneNums_text = (EditText) findViewById(R.id.details_phoneNums);
        officeSeat_text = (EditText) findViewById(R.id.details_officeSeat);
        mailAddress_text = (EditText) findViewById(R.id.details_mailbox);
        address_text = (EditText) findViewById(R.id.details_address);
        qq_text = (EditText) findViewById(R.id.details_qq);
        weChatNum_text = (EditText) findViewById(R.id.details_weChatNum);
        saveDetails = (TextView) findViewById(R.id.save_icon);

        intent = getIntent();
        bundle = intent.getExtras();

        if (bundle != null) {

            saved_id = bundle.getString("save_number");

            if (TextUtils.isEmpty(bundle.getString("save_name"))) {  //其中有几项是“非空则不可修改”(即：右边无修改和√ 按钮)的：但若其是处于“未填”状态，则editText需处于可编辑状态。
                details_name_text.setEnabled(true);
            } else {
                details_name_text.setText(bundle.getString("save_name"));
                details_name_text.setEnabled(false);
            }

            if (TextUtils.isEmpty(bundle.getString("save_number"))) {
                staffNum_details_text.setEnabled(true);
            } else {
                staffNum_details_text.setText(bundle.getString("save_number"));
                staffNum_details_text.setEnabled(false);
            }

            details_dept.setText(bundle.getString("save_dept")); //任职部门
            details_dept.setEnabled(false);

            if (TextUtils.isEmpty(bundle.getString("save_name"))) {
                name_text.setEnabled(true);
            } else {
                name_text.setText(bundle.getString("save_name"));
                name_text.setEnabled(false);
            }

            String avatar = bundle.getString("save_avatar");
            if (!TextUtils.isEmpty(avatar)) {
                if (avatar.contains("://")) {
                    simpleDraweeView.setImageURI(Uri.parse(avatar));//头像
                } else {
                    simpleDraweeView.setImageURI(Uri.parse("res://x/" + getResources().getIdentifier(avatar, "drawable", getPackageName())));//头像
                }
            }


            if (TextUtils.isEmpty(bundle.getString("save_positions"))) {
                position_text.setEnabled(true);
            } else {
                position_text.setText(bundle.getString("save_positions"));
                position_text.setEnabled(false);
            }

            if (TextUtils.isEmpty(bundle.getString("save_sex"))) {
                sex_text.setEnabled(true);
            } else {
                sex_text.setText(bundle.getString("save_sex"));
                sex_text.setEnabled(false);
            }

            if (TextUtils.isEmpty(bundle.getString("save_nativePlace"))) {
                nativePlace_text.setEnabled(true);
            } else {
                nativePlace_text.setText(bundle.getString("save_nativePlace"));
                nativePlace_text.setEnabled(false);
            }

            if (TextUtils.isEmpty(bundle.getString("save_nationalities"))) {
                nationality_text.setEnabled(true);
            } else {
                nationality_text.setText(bundle.getString("save_nationalities"));
                nationality_text.setEnabled(false);
            }

            if (TextUtils.isEmpty(bundle.getString("save_nation"))) {
                nation_text.setEnabled(true);
            } else {
                nation_text.setText(bundle.getString("save_nation"));
                nation_text.setEnabled(false);
            }

            if (TextUtils.isEmpty(bundle.getString("save_maternityStatus"))) {
                maternityStatus_text.setEnabled(true);
            } else {
                maternityStatus_text.setText(bundle.getString("save_maternityStatus"));
                maternityStatus_text.setEnabled(false);
            }

            if (TextUtils.isEmpty(bundle.getString("save_birthDate"))) {
                birthDate_text.setEnabled(true);
            } else {
                birthDate_text.setText(bundle.getString("save_birthDate"));
                birthDate_text.setEnabled(false);
            }

            if (TextUtils.isEmpty(bundle.getString("save_phoneNumber"))) {
                phoneNums_text.setEnabled(true);
            } else {
                phoneNums_text.setText(bundle.getString("save_phoneNumber"));
                phoneNums_text.setEnabled(false);
            }

            if (TextUtils.isEmpty(bundle.getString("save_officeSeat"))) {
                officeSeat_text.setEnabled(true);
            } else {
                officeSeat_text.setText(bundle.getString("save_officeSeat"));
                officeSeat_text.setEnabled(false);
            }

            if (TextUtils.isEmpty(bundle.getString("save_mailAddress"))) {
                mailAddress_text.setEnabled(true);
            } else {
                mailAddress_text.setText(bundle.getString("save_mailAddress"));
                mailAddress_text.setEnabled(false);
            }

            if (TextUtils.isEmpty(bundle.getString("save_address"))) {
                address_text.setEnabled(true);
            } else {
                address_text.setText(bundle.getString("save_address"));
                address_text.setEnabled(false);
            }

            if (TextUtils.isEmpty(bundle.getString("save_qq"))) {
                qq_text.setEnabled(true);
            } else {
                qq_text.setText(bundle.getString("save_qq"));
                qq_text.setEnabled(false);
            }

            if (TextUtils.isEmpty(bundle.getString("save_weChat"))) {
                weChatNum_text.setEnabled(true);
            } else {
                weChatNum_text.setText(bundle.getString("save_weChat"));
                weChatNum_text.setEnabled(false);
            }

        }


        simpleDraweeView.setOnClickListener(new View.OnClickListener() {   //点击换头像
            @Override
            public void onClick(View view) {
                showAlbum();
            }
        });


        /** 设置点击"保存"按钮的监听事件**/
        saveDetails.setOnClickListener(new View.OnClickListener() {  //存盘
            @Override
            public void onClick(View view) {

                Toast.makeText(StaffDetailsActivity.this, "信息已保存成功！", Toast.LENGTH_LONG).show();
                if (!TextUtils.isEmpty(staffNum_details_text.getText().toString())) {

                    Cursor dCursor = getContentResolver().query(DemoProvider.URI, null, "number = ?", new String[]{staffNum_details_text.getText().toString()}, null);
                    if (dCursor != null) {
                        int num = dCursor.getCount();
                        if (num == 0 && staffNum_details_text.getText().toString().startsWith("1")) {  //1开头的，部门为"行政部"。
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("name", details_name_text.getText().toString());
                            contentValues.put("number", staffNum_details_text.getText().toString());
                            contentValues.put("deptName", "行政部");
                            contentValues.put("position", position_text.getText().toString());
                            contentValues.put("sex", sex_text.getText().toString());
                            contentValues.put("nativePlace", nativePlace_text.getText().toString());
                            contentValues.put("nationalities", nationality_text.getText().toString());
                            contentValues.put("nation", nation_text.getText().toString());
                            contentValues.put("maternityStatus", maternityStatus_text.getText().toString());
                            contentValues.put("birthDate", birthDate_text.getText().toString());
                            contentValues.put("phoneNumber", phoneNums_text.getText().toString());
                            contentValues.put("officeSeat", officeSeat_text.getText().toString());
                            contentValues.put("mailAddress", mailAddress_text.getText().toString());
                            contentValues.put("address", address_text.getText().toString());
                            contentValues.put("qq", qq_text.getText().toString());
                            contentValues.put("weChat", weChatNum_text.getText().toString());

                            getContentResolver().insert(DemoProvider.URI, contentValues);
                        } else if (num == 0 && staffNum_details_text.getText().toString().startsWith("2")) { //2开头的，部门为"行政部"。
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("name", details_name_text.getText().toString());
                            contentValues.put("number", staffNum_details_text.getText().toString());
                            contentValues.put("deptName", "财务部");
                            contentValues.put("position", position_text.getText().toString());
                            contentValues.put("sex", sex_text.getText().toString());
                            contentValues.put("nativePlace", nativePlace_text.getText().toString());
                            contentValues.put("nationalities", nationality_text.getText().toString());
                            contentValues.put("nation", nation_text.getText().toString());
                            contentValues.put("maternityStatus", maternityStatus_text.getText().toString());
                            contentValues.put("birthDate", birthDate_text.getText().toString());
                            contentValues.put("phoneNumber", phoneNums_text.getText().toString());
                            contentValues.put("officeSeat", officeSeat_text.getText().toString());
                            contentValues.put("mailAddress", mailAddress_text.getText().toString());
                            contentValues.put("address", address_text.getText().toString());
                            contentValues.put("qq", qq_text.getText().toString());
                            contentValues.put("weChat", weChatNum_text.getText().toString());

                            getContentResolver().insert(DemoProvider.URI, contentValues);
                        } else if (num == 0 && staffNum_details_text.getText().toString().startsWith("3")) { //3开头的，部门为"技术部"。
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("name", details_name_text.getText().toString());
                            contentValues.put("number", staffNum_details_text.getText().toString());
                            contentValues.put("deptName", "技术部");
                            contentValues.put("position", position_text.getText().toString());
                            contentValues.put("sex", sex_text.getText().toString());
                            contentValues.put("nativePlace", nativePlace_text.getText().toString());
                            contentValues.put("nationalities", nationality_text.getText().toString());
                            contentValues.put("nation", nation_text.getText().toString());
                            contentValues.put("maternityStatus", maternityStatus_text.getText().toString());
                            contentValues.put("birthDate", birthDate_text.getText().toString());
                            contentValues.put("phoneNumber", phoneNums_text.getText().toString());
                            contentValues.put("officeSeat", officeSeat_text.getText().toString());
                            contentValues.put("mailAddress", mailAddress_text.getText().toString());
                            contentValues.put("address", address_text.getText().toString());
                            contentValues.put("qq", qq_text.getText().toString());
                            contentValues.put("weChat", weChatNum_text.getText().toString());

                            getContentResolver().insert(DemoProvider.URI, contentValues);
                        } else if (num == 0 && staffNum_details_text.getText().toString().startsWith("4")) { //4开头的，部门为"销售部"。
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("name", details_name_text.getText().toString());
                            contentValues.put("number", staffNum_details_text.getText().toString());
                            contentValues.put("deptName", "销售部");
                            contentValues.put("position", position_text.getText().toString());
                            contentValues.put("sex", sex_text.getText().toString());
                            contentValues.put("nativePlace", nativePlace_text.getText().toString());
                            contentValues.put("nationalities", nationality_text.getText().toString());
                            contentValues.put("nation", nation_text.getText().toString());
                            contentValues.put("maternityStatus", maternityStatus_text.getText().toString());
                            contentValues.put("birthDate", birthDate_text.getText().toString());
                            contentValues.put("phoneNumber", phoneNums_text.getText().toString());
                            contentValues.put("officeSeat", officeSeat_text.getText().toString());
                            contentValues.put("mailAddress", mailAddress_text.getText().toString());
                            contentValues.put("address", address_text.getText().toString());
                            contentValues.put("qq", qq_text.getText().toString());
                            contentValues.put("weChat", weChatNum_text.getText().toString());

                            getContentResolver().insert(DemoProvider.URI, contentValues);
                        } else if (num == 0 && staffNum_details_text.getText().toString().startsWith("8")) { //8开头的，部门为"管理层"。
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("name", details_name_text.getText().toString());
                            contentValues.put("number", staffNum_details_text.getText().toString());
                            contentValues.put("deptName", "管理层");
                            contentValues.put("position", position_text.getText().toString());
                            contentValues.put("sex", sex_text.getText().toString());
                            contentValues.put("nativePlace", nativePlace_text.getText().toString());
                            contentValues.put("nationalities", nationality_text.getText().toString());
                            contentValues.put("nation", nation_text.getText().toString());
                            contentValues.put("maternityStatus", maternityStatus_text.getText().toString());
                            contentValues.put("birthDate", birthDate_text.getText().toString());
                            contentValues.put("phoneNumber", phoneNums_text.getText().toString());
                            contentValues.put("officeSeat", officeSeat_text.getText().toString());
                            contentValues.put("mailAddress", mailAddress_text.getText().toString());
                            contentValues.put("address", address_text.getText().toString());
                            contentValues.put("qq", qq_text.getText().toString());
                            contentValues.put("weChat", weChatNum_text.getText().toString());

                            getContentResolver().insert(DemoProvider.URI, contentValues);
                        } else if (num == 0) { //员工已其它数字开头的，部门为"其他部"。
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("name", details_name_text.getText().toString());
                            contentValues.put("number", staffNum_details_text.getText().toString());
                            contentValues.put("deptName", "其他部");
                            contentValues.put("position", position_text.getText().toString());
                            contentValues.put("sex", sex_text.getText().toString());
                            contentValues.put("nativePlace", nativePlace_text.getText().toString());
                            contentValues.put("nationalities", nationality_text.getText().toString());
                            contentValues.put("nation", nation_text.getText().toString());
                            contentValues.put("maternityStatus", maternityStatus_text.getText().toString());
                            contentValues.put("birthDate", birthDate_text.getText().toString());
                            contentValues.put("phoneNumber", phoneNums_text.getText().toString());
                            contentValues.put("officeSeat", officeSeat_text.getText().toString());
                            contentValues.put("mailAddress", mailAddress_text.getText().toString());
                            contentValues.put("address", address_text.getText().toString());
                            contentValues.put("qq", qq_text.getText().toString());
                            contentValues.put("weChat", weChatNum_text.getText().toString());

                            getContentResolver().insert(DemoProvider.URI, contentValues);
                        } else if (num == 1) {  //当此员工号已存在时，点击保存更新的内容。
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("name", details_name_text.getText().toString());
                            contentValues.put("position", position_text.getText().toString());
                            contentValues.put("sex", sex_text.getText().toString());
                            contentValues.put("nativePlace", nativePlace_text.getText().toString());
                            contentValues.put("nationalities", nationality_text.getText().toString());
                            contentValues.put("nation", nation_text.getText().toString());
                            contentValues.put("maternityStatus", maternityStatus_text.getText().toString());
                            contentValues.put("birthDate", birthDate_text.getText().toString());
                            contentValues.put("phoneNumber", phoneNums_text.getText().toString());
                            contentValues.put("officeSeat", officeSeat_text.getText().toString());
                            contentValues.put("mailAddress", mailAddress_text.getText().toString());
                            contentValues.put("address", address_text.getText().toString());
                            contentValues.put("qq", qq_text.getText().toString());
                            contentValues.put("weChat", weChatNum_text.getText().toString());

                            String where = "number = ?";
                            String[] whereArgs = new String[]{staffNum_details_text.getText().toString()};
                            getContentResolver().update(DemoProvider.URI, contentValues, where, whereArgs);
                        }

                    }
                }

            }
        });
    }

    /**  动态获取相册读写权限（安卓6.0，版本23以上时），则需要在开启相册前多加2步： **/

    // 1. 检查并申请权限判断是否已拥有读写的权限，如有直接显示相册，如无则要求权限。
    private void showAlbum() {
        int checkWritePermission = ContextCompat.checkSelfPermission(StaffDetailsActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        //1.权限还未授予，申请权限
        //1.1向用户解释,为何要申请该权限.
        if(checkWritePermission != PackageManager.PERMISSION_GRANTED){
            if (!ActivityCompat.shouldShowRequestPermissionRationale(StaffDetailsActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                //显示解释用户权限的对话框，若选择”确定” ，将不再出现该解释对话框。“取消”则会再次出现 解释对话框。
                showMessageOKCancel("请允许授权，否则会导致无法查阅你相册中的图片！",
                        new DialogInterface.OnClickListener() {
                            @TargetApi(Build.VERSION_CODES.M)
                            @Override
                            public void onClick(DialogInterface dialog, int which) { //点“确定”，就会被请求授予权限。
                                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                        SELECTED_PERMISSION_STORAGE);
                            }
                        });
                return;
            }      //1.2 用户选择  “确定”，直接跳出请给予权限的要求.
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}; //1.2-1 需要申请哪些些权限，可申请一个或多个。
            ActivityCompat.requestPermissions(StaffDetailsActivity.this, mPermissionList, SELECTED_PERMISSION_STORAGE);//1.2-2申请权限
        }
        else {//权限已经被授予(如：6.0以下版本会直接授予权限)，直接跳到给予权限的请求.
            showAlbumDirectly();
        }
    }

    //2. 请求授权后的回调进行操作。
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //请求相册的回调：
        switch (requestCode){
            case SELECTED_PERMISSION_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "现在你已开启相机了权限", Toast.LENGTH_SHORT).show();// Permission Granted
                    showAlbumDirectly();  //权限已经被授予，可 开启相册了。
                } else {
                    // Permission Denied
                    Toast.makeText(StaffDetailsActivity.this, "你拒绝了授权,导致相册调用失败！", Toast.LENGTH_LONG)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }}


    //解释为何要授权的  对话框
    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(StaffDetailsActivity.this)
                .setMessage(message)
                .setPositiveButton("确定", okListener)
                .setNegativeButton("取消", null)
                .create()
                .show();
    }

    /**.
     * 开启相册（这里写的是一个调起相册）
     * **/
    private void showAlbumDirectly() {
        Intent albumIntent = new Intent(Intent.ACTION_PICK, null);
        albumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(albumIntent, REQUEST_ALBUM_OK);
    }

    /**
     * 处理相册事件,返回的相册路径传入数据库保存。
     **/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(TAG, "onActivityResult:相册  intent=" + data);
        if (requestCode == REQUEST_ALBUM_OK) {
            if (data != null) {
                Log.d(TAG, "onActivityResult:相册 " + data.getData().toString());

                simpleDraweeView.setImageURI(data.getData());
                ContentValues contentValues = new ContentValues();
                contentValues.put("avatar", data.getData().toString());
                getContentResolver().update(DemoProvider.URI, contentValues, "number = ?", new String[]{saved_id});

            }
        }
    }

    public void modifyBtnClicked(View view) {  //点击修改时，处于可修改状况。
        switch (view.getId()) {
            case R.id.maternityStatus_modify_btn:
                maternityStatus_text.setEnabled(true);
                break;
            case R.id.phoneNum_modify_btn:
                phoneNums_text.setEnabled(true);
                break;
            case R.id.fixlinePhone_modify_btn:
                officeSeat_text.setEnabled(true);
                break;
            case R.id.mailbox_modify_btn:
                mailAddress_text.setEnabled(true);
                break;
            case R.id.address_modify_btn:
                address_text.setEnabled(true);
                break;
            case R.id.qq_modify_btn:
                qq_text.setEnabled(true);
                break;
            case R.id.weChatNum_modify_btn:
                weChatNum_text.setEnabled(true);
                break;
        }
    }

    public void confirmedBtnClicked(View view) {   //点击“√”按钮，确定并保存刷新数据库
        switch (view.getId()) {
            case R.id.maternityStatus_correct_btn:
                maternityStatus_text.setEnabled(false);
                String saved_maternityStatus = maternityStatus_text.getText().toString();
                ContentValues contentValuesMaternityStatus = new ContentValues();
                contentValuesMaternityStatus.put("maternityStatus", saved_maternityStatus);
                getContentResolver().update(DemoProvider.URI, contentValuesMaternityStatus, "number = ?", new String[]{saved_id});//更新数据库
                 break;
            case R.id.phoneNum_correct_btn:
                phoneNums_text.setEnabled(false);
                String save_phoneNums = phoneNums_text.getText().toString();
                ContentValues contentValuesPhoneNums = new ContentValues();
                contentValuesPhoneNums.put("phoneNumber", save_phoneNums);
                getContentResolver().update(DemoProvider.URI, contentValuesPhoneNums, "number = ?", new String[]{saved_id});//更新数据库
                break;
            case R.id.fixlinePhone_correct_btn:
                officeSeat_text.setEnabled(false);
                String save_officeSeat = officeSeat_text.getText().toString();
                ContentValues contentValuesOfficeSeat = new ContentValues();
                contentValuesOfficeSeat.put("officeSeat", save_officeSeat);
                getContentResolver().update(DemoProvider.URI, contentValuesOfficeSeat, "number = ?", new String[]{saved_id});//更新数据库
                break;
            case R.id.mailbox_correct_btn:
                mailAddress_text.setEnabled(false);
                String save_mailAddress = mailAddress_text.getText().toString();
                ContentValues contentValuesMailAddress = new ContentValues();
                contentValuesMailAddress.put("mailAddress", save_mailAddress);
                getContentResolver().update(DemoProvider.URI, contentValuesMailAddress, "number = ?", new String[]{saved_id});//更新数据库
                break;
            case R.id.address_correct_btn:
                address_text.setEnabled(false);
                String save_address = address_text.getText().toString();
                ContentValues contentValuesAddress = new ContentValues();
                contentValuesAddress.put("mailAddress", save_address);
                getContentResolver().update(DemoProvider.URI, contentValuesAddress, "number = ?", new String[]{saved_id});//更新数据库
                break;
            case R.id.qq_correct_btn:
                qq_text.setEnabled(false);
                String save_qq = qq_text.getText().toString();
                ContentValues contentValuesQQ = new ContentValues();
                contentValuesQQ.put("qq", save_qq);
                getContentResolver().update(DemoProvider.URI, contentValuesQQ, "number = ?", new String[]{saved_id});//更新数据库
                break;
            case R.id.weChatNum_correct_btn:
                weChatNum_text.setEnabled(false);
                String save_weChatNum = weChatNum_text.getText().toString();
                ContentValues contentValuesWeChatNum = new ContentValues();
                contentValuesWeChatNum.put("weChat", save_weChatNum);
                getContentResolver().update(DemoProvider.URI, contentValuesWeChatNum, "number = ?", new String[]{saved_id});//更新数据库
                break;
        }
    }


}
