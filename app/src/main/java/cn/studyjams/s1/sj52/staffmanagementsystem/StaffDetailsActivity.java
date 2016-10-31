package cn.studyjams.s1.sj52.staffmanagementsystem;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;

import static android.R.attr.tag;

public class StaffDetailsActivity extends AppCompatActivity {
    EditText details_name_text;
    EditText staffNum_details_text;
    EditText details_dept;
    EditText name_text;
    SimpleDraweeView simpleDraweeView;
    EditText position_text;
    EditText sex_text;
    EditText  nativePlace_text;
    EditText  nationality_text;
    EditText  nation_text;
    EditText maternityStatus_text;
    EditText  birthDate_text;
    EditText  phoneNums_text;
    EditText officeSeat_text;
    EditText mailAddress_text;
    EditText address_text;
    EditText qq_text;
    EditText weChatNum_text;
    String saved_id; //保存的员工号
    final String TAG = "StaffDetailsActivity";
    int REQUEST_CAMERA = 1;
    int REQUEST_ALBUM_OK = 2; //相册requestCode
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
        nationality_text = (EditText) findViewById(R.id.nationality_text );
        nation_text = (EditText) findViewById(R.id.nation_text);
        maternityStatus_text  = (EditText) findViewById(R.id.details_maternityStatus);
        birthDate_text  = (EditText) findViewById(R.id.birthDate_text);
        phoneNums_text  = (EditText) findViewById(R.id.details_phoneNums);
        officeSeat_text = (EditText) findViewById(R.id.details_officeSeat);
        mailAddress_text  = (EditText) findViewById(R.id.details_mailbox);
        address_text = (EditText) findViewById(R.id.details_address);
        qq_text  = (EditText) findViewById(R.id.details_qq);
        weChatNum_text = (EditText) findViewById(R.id.details_weChatNum);

        intent = getIntent();
        bundle = intent.getExtras();

        if (bundle != null) {
            saved_id = bundle.getString("save_number");
            details_name_text.setText(bundle.getString("save_name"));
            details_name_text.setEnabled(false);

            staffNum_details_text.setText(bundle.getString("save_number"));
            details_dept.setText(bundle.getString("save_dept"));
            name_text.setText(bundle.getString("save_name"));

            String avatar = bundle.getString("save_avatar");
            if (!TextUtils.isEmpty(avatar)) {
                if (avatar.contains("://")) {
                    simpleDraweeView.setImageURI(Uri.parse(avatar));//头像
                } else {
                    simpleDraweeView.setImageURI(Uri.parse("res://x/" + getResources().getIdentifier(avatar, "drawable", getPackageName())));//头像
                }
            }
            position_text.setText(bundle.getString("save_positions"));
            sex_text.setText(bundle.getString("save_sex"));
            nativePlace_text.setText(bundle.getString("save_nativePlace"));
            nationality_text.setText(bundle.getString("save_nationalities"));
            nation_text.setText(bundle.getString("save_nation"));
            maternityStatus_text.setText(bundle.getString("save_maternityStatus"));
            birthDate_text.setText(bundle.getString("save_birthDate"));
            phoneNums_text.setText(bundle.getString("save_phoneNumber"));
            officeSeat_text.setText(bundle.getString("save_officeSeat"));
            mailAddress_text.setText(bundle.getString("save_mailAddress"));
            address_text.setText(bundle.getString("save_address"));
            qq_text.setText(bundle.getString("save_qq"));
            weChatNum_text.setText(bundle.getString("save_weChat"));

        }
        else{
            details_name_text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    details_name_text.setEnabled(true);


                }
            });
        }


        simpleDraweeView.setOnClickListener(new View.OnClickListener() {   //点击换头像
            @Override
            public void onClick(View view) {
                showGallery();
            }
        });

   }


    /** 打开相册 **/
    private void showGallery() {
        Intent albumIntent = new Intent(Intent.ACTION_PICK, null);
        albumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(albumIntent, REQUEST_ALBUM_OK);

    }

    /** 处理相册事件,返回的相册路径传入数据库保存。 **/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(TAG, "onActivityResult:相册  intent=" + data);
        if (requestCode == REQUEST_ALBUM_OK) {
            if (data != null) {
                Log.d(TAG, "onActivityResult:相册 " + data.getData().toString());

                simpleDraweeView.setImageURI(data.getData());
                ContentValues contentValues = new ContentValues();
                contentValues.put("avatar",data.getData().toString());
                getContentResolver().update(DemoProvider.URI, contentValues,"number = ?",new String[]{saved_id});

            }
        }
    }

    public void modifyBtnClicked(View view){  //点击修改时，处于可修改状况。
        switch(view.getId()){
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

    public void confirmedBtnClicked(View view){   //点击“√”按钮，确定并保存刷新数据库
        switch(view.getId()){
            case R.id.maternityStatus_correct_btn:
                maternityStatus_text.setEnabled(false);
                String saved_maternityStatus = maternityStatus_text.getText().toString();
                ContentValues contentValuesMaternityStatus = new ContentValues();
                contentValuesMaternityStatus.put("maternityStatus",saved_maternityStatus);
                getContentResolver().update(DemoProvider.URI,contentValuesMaternityStatus, "number = ?",new String[]{saved_id});//更新数据库
                break;
            case R.id.phoneNum_correct_btn:
                phoneNums_text.setEnabled(false);
                String save_phoneNums = phoneNums_text.getText().toString();
                ContentValues contentValuesPhoneNums = new ContentValues();
                contentValuesPhoneNums.put("phoneNumber",save_phoneNums);
                getContentResolver().update(DemoProvider.URI,contentValuesPhoneNums, "number = ?",new String[]{saved_id});//更新数据库
                break;
            case R.id.fixlinePhone_correct_btn:
                officeSeat_text.setEnabled(false);
                String save_officeSeat = officeSeat_text.getText().toString();
                ContentValues contentValuesOfficeSeat = new ContentValues();
                contentValuesOfficeSeat.put("officeSeat",save_officeSeat);
                getContentResolver().update(DemoProvider.URI,contentValuesOfficeSeat, "number = ?",new String[]{saved_id});//更新数据库
                break;
            case R.id.mailbox_correct_btn:
                mailAddress_text.setEnabled(false);
                String save_mailAddress = mailAddress_text.getText().toString();
                ContentValues contentValuesMailAddress = new ContentValues();
                contentValuesMailAddress.put("mailAddress",save_mailAddress);
                getContentResolver().update(DemoProvider.URI,contentValuesMailAddress, "number = ?",new String[]{saved_id});//更新数据库
                break;
            case R.id.address_correct_btn:
                address_text.setEnabled(false);
                String save_address = address_text.getText().toString();
                ContentValues contentValuesAddress = new ContentValues();
                contentValuesAddress.put("mailAddress",save_address);
                getContentResolver().update(DemoProvider.URI,contentValuesAddress, "number = ?",new String[]{saved_id});//更新数据库
                break;
            case R.id.qq_correct_btn:
                qq_text.setEnabled(false);
                String save_qq = qq_text.getText().toString();
                ContentValues contentValuesQQ = new ContentValues();
                contentValuesQQ.put("qq",save_qq);
                getContentResolver().update(DemoProvider.URI,contentValuesQQ, "number = ?",new String[]{saved_id});//更新数据库
                break;
            case R.id.weChatNum_correct_btn:
                weChatNum_text.setEnabled(true);
                String save_weChatNum = weChatNum_text.getText().toString();
                ContentValues contentValuesWeChatNum = new ContentValues();
                contentValuesWeChatNum.put("weChat",save_weChatNum);
                getContentResolver().update(DemoProvider.URI,contentValuesWeChatNum, "number = ?",new String[]{saved_id});//更新数据库
                break;
        }
    }


}
