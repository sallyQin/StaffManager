package cn.studyjams.s1.sj52.staffmanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Apc on 2016/10/8.
 */
public class DatabaseHelper extends SQLiteOpenHelper {


    static String [] deptName = new String[]{"行政部","财务部","技术部","销售部","管理层"};

    static String [] administrationDept = new String[]{"张晓楠","石玉兰","尚文清","杜喜喜","杨海清","孙丽萍"};   //部门
    static String [] administDeptNO = new String[]{"101","102","103","104","105","106"};   //员工号
    static String [] avatar_imageAdmin = new String[]{"staff_101","staff_102","staff_103","staff_104","staff_105","staff_106"};  // 头像
    static String [] positionAdmin = new String[]{"行政文员","行政文员","行政文员","行政主管","行政文员","行政文员"};  //职位名称
    static String [] sexAdmin = new String[]{"女性","女性","女性","女性","女性","女性"};  //性别
    static String [] nativePlaceAdmin = new String[]{"上海市","枣庄市","烟台市","临汾市","济宁市","徐州市"};  //籍贯
    static String [] nationalityAdmin = new String[]{"中国","中国","中国","中国","中国","中国"};  //国籍
    static String [] nationAdmin = new String[]{"汉族","汉族","汉族","汉族","汉族","汉族"};  //民族
    static String [] maternityStatusAdmin = new String[]{"未婚","未婚","已婚","已婚","未婚","已婚"};  //婚姻状况
    static String [] birthDateAdmin = new String[]{"1989-01-02","1991-03-06","1986-04-02","1978-11-03","1989-05-08","1988-11-22"};  //出生年月 （注：填写格式为19xx-xx-xx）
    static String [] phoneNumberAdmin = new String[]{"13187895933","13722787899","13858965736","13985845857","13733322222","13521210155"};  //手机号（注：号码为11位数）
    static String [] officeSeatAdmin = new String[]{"0101","0102","0103","0104","0105","0106"};  //办公座位
    static String [] mailAddressAdmin = new String[]{"zn124578@qq.com","yulan11@qq.com","wenqing22@qq.com","xixi66@gmail.com","haiqing12@qq.com","liping@qq.com"};  //邮箱
    static String [] addressAdmin = new String[]{"上海市普陀区田广花苑2幢202室","上海市静安区常宁苑32号501室","上海市曹家渡鑫康苑3号303室","上海市闵行区春申丽园21号201室","上海市普陀区绿洲公寓2幢1302室","上海市静安区瑶溪小区20幢101室"};  //通讯地址
    static String [] qqAdmin = new String[]{"1452288","525214","1582458","15847596","2848855","854566774"};  //QQ号码
    static String [] weChatAdmin = new String[]{"zn124578","yulan11","wenqing22","xixi66","haiqing12","liping"};  //微信号


    static String [] financialDept = new String[]{"丁冬芹","陈桂平","李开英","张凤霞"}; //部门
    static String [] financialDeptNO = new String[]{"201","202","203","204"};//员工号
    static String [] avatar_imageFin = new String[]{"staff_201","staff_202","staff_203","staff_204"};// 头像
    static String [] positionFin = new String[]{"财务主管","财务科员","财务科员","财务科员"};  //职位名称
    static String [] sexFin = new String[]{"女性","女性","女性","女性"};  //性别
    static String [] nativePlaceFin = new String[]{"青岛市","海阳市","济宁市","巩义市"};  //籍贯
    static String [] nationalityFin = new String[]{"中国","中国","中国","中国"};  //国籍
    static String [] nationFin = new String[]{"汉族","汉族","汉族","汉族"};  //民族
    static String [] maternityStatusFin = new String[]{"已婚","未婚","已婚","未婚"};  //婚姻状况
    static String [] birthDateFin = new String[]{"1983-07-13","1993-01-18","1986-09-05","1988-10-03"};  //出生年月 （注：填写格式为19xx-xx-xx）
    static String [] phoneNumberFin = new String[]{"13662895723","13552787288","13856765736","13185855822"};  //手机号（注：号码为11位数）
    static String [] officeSeatFin = new String[]{"0201","0202","0203","0204"};  //办公座位
    static String [] mailAddressFin = new String[]{"dongqin1@qq.com","guipingyy@qq.com","ky8988@qq.com","fengxia@qq.com"};  //邮箱
    static String [] addressFin = new String[]{"上海市松江区华天公寓33幢602室","上海市静安区静安鑫华苑3号1302室","上海市闵行区古美一村23号403室","上海市闵行区吴中新云城33幢603室"};  //通讯地址
    static String [] qqFin = new String[]{"54847855","1215141221","12569","8584462","99552","57746265554"};  //QQ号码
    static String [] weChatFin = new String[]{"dongqin1","guipingyy","ky8988","fengxia"};  //微信号


    static String [] technicalDept = new String[]{"杜茂仁","李金云","石俊梅","戴双宝","杜元晓","刘文明","王焕锁","高文利","张海宝","孙瑞军","" +
            "高文军","张飞雄","石胜厚","刘御","高志新","尚文忠","刘双正","郭山彤","康志强","马仁毅","尤帅齐","孙彩武","邬肖任","赵单羽","陈自萤"}; //部门
    static String [] technicalDeptNO = new String[]{"301","302","303","304","305","306","307","308","309","310","311","312","313","314","315",
            "316","317","318","319","320","321","322","323","324","325"};//员工号
    static String [] avatar_imageTech = new String[]{"staff_301","staff_302","staff_303","staff_304","staff_305","staff_306","staff_307","staff_308",
            "staff_309","staff_310","staff_311","staff_312","staff_313","staff_314","staff_315","staff_316","staff_317","staff_317","staff_318","staff_319",
            "staff_320","staff_321","staff_322","staff_323","staff_324","staff_325"};// 头像
    static String [] positionTech = new String[]{"技术员","技术员","技术员","技术员","技术组长","技术员","技术员","技术员","技术员","技术员","技术员",
            "技术员","技术员","技术员","技术员","技术员","技术员","技术员","技术员","技术员","技术员","技术员","技术员","技术副组长","技术主管"};  //职位名称
    static String [] sexTech = new String[]{"男性","男性","男性","男性","男性","男性","男性","男性","男性","男性","男性","男性","男性","男性","男性",
            "男性","男性","男性","男性","男性","男性","男性","男性","男性","男性"};  //性别
    static String [] nativePlaceTech = new String[]{"长春市","无锡市","常州市","烟台市","济宁市","上海市","海阳市","常熟市","天津市","墨尔本","苏州市","东台市",
            "北京市","宿迁市","重庆市","泰兴市","泰安市","日照市","聊城市","开封市","上海市","武汉市","上海市","丹阳市","无锡市"};  //籍贯
    static String [] nationalityTech = new String[]{"中国","中国","中国","中国","中国","中国","中国","中国","中国","澳大利亚","中国","中国","中国",
            "中国","中国","中国","中国","中国","中国","中国","中国","中国","中国","中国","中国"};  //国籍
    static String [] nationTech = new String[]{"汉族","汉族","汉族","汉族","汉族","汉族","汉族","汉族","汉族","/","汉族","汉族","汉族","汉族","汉族"
            ,"汉族","汉族","汉族","汉族","汉族","汉族","汉族","汉族","汉族","汉族"};  //民族
    static String [] maternityStatusTech = new String[]{"未婚","未婚","已婚","未婚","未婚","未婚","未婚","已婚","未婚","未婚","未婚","未婚","未婚",
            "未婚","未婚","未婚","未婚","未婚","未婚","未婚","未婚","未婚","未婚","未婚","未婚"};  //婚姻状况
    static String [] birthDateTech = new String[]{"1992-04-22","1993-09-08","1990-07-04","1989-10-23","1989-05-28","1990-10-02","1990-01-05",
            "1993-02-01","1990-06-28","1987-11-23","1989-03-24","1990-09-14","1991-04-05","1991-02-09","1990-03-19","1990-06-07",
            "1991-07-21","1991-08-08","1990-01-14","1991-11-23","1991-02-05","1990-05-22","1991-12-23","1989-09-20","1990-01-15"};  //出生年月 （注：填写格式为19xx-xx-xx）
    static String [] phoneNumberTech = new String[]{"13187195933","13722487899","13158922736","13711145857","13965657777","13521266155",
            "13116895933","13433487888","13835358974","13611245858","13392295933","13927487897","13265932736","13188145855","13977657777","13568266155",
            "13777552121","13455487499","13958622733","13882145850","135000487899","13495922736","13965645857","13387196953","13566487811"};  //手机号（注：号码为11位数）
    static String [] officeSeatTech = new String[]{"0301","0302","0303","0304","0305","0306","0307","0308","0309","0310","0311","0312",
            "0313","0314","0315","0316","0317","0318","0319","0320","0321","0322","0323","0324","0325"};  //办公座位
    static String [] mailAddressTech = new String[]{"maoren1@qq.com","jinyun@qq.com","junmei1@qq.com","sbao121@gmail.com","yunxiao@qq.com","wenming@qq.com",
            "huansuo@qq.com","wenli1212@qq.com","haibaoyy@gmail.com","mikegaoa@qq.com","ruijun11@qq.com","wenjun1@qq.com","feixiong2@qq.com","liujyu1@gmail.com",
            "zhixin1@qq.com","wenzhong@qq.com","shuangz11@qq.com","shand2@qq.com","zhiqiang2@qq.com","renyi1@gmail.com","shuaqi@qq.com","caiwu@gmail.com",
            "xiaoren2@qq.com","danyuyy@qq.com","ziying22@qq.com"};  //邮箱
    static String [] addressTech = new String[]{"上海市闵行区绿恒花苑12幢203室","上海市青浦区春色苑30号503室","上海市松江区鑫添苑3号303室","上海市闵行区古美八村2号301室",
            "上海市普陀区祥和公寓208幢102室","上海市静安区春田小区21幢701室","上海市崇明区绿春苑10号703室","上海市松江区金堂苑13号303室","上海市闵行区古美新村12号501室",
            "上海市松江区田玲独院3号703室","上海市静安区暨南花苑121幢203室","上海市青浦区光新苑130号1203室","上海市松江区光听风苑33号403室","上海市闵行区古美三村212号201室",
            "上海市普陀区祥和公寓20幢902室","上海市静安区石泉小区2幢601室","上海市闵行区沙田苑100号1703室","上海市松江区星河苑10号703室","上海市闵行区双山公寓13号701室",
            "上海市松江区甘泉小区103号203室","上海市闵行区吴江花苑12幢1203室","上海市青浦区浒苔苑230号502室","上海市松江区吴廷花园23号403室","上海市闵行区古美一村2号301室",
            "上海市普陀区风凌天公寓20幢102室"};  //通讯地址
    static String [] qqTech = new String[]{"245221","788465","85132458","164483165546","6485461","4468484465","24848151","818321666","958992699","5586568886",
            "878686466","87777878564","98935326","455156","45856533","225525","585466","66565569","996599666", "555556988","8788456",
            "88566562","8448524844","565215484","8584666"};  //QQ号码
    static String [] weChatTech = new String[]{"maoren1","jinyun","junmei1","sbao121","yunxiao","wenming","huansuo","wenli1212","haibaoyy","mikegaoa","ruijun11"
            ,"wenjun1","feixiong2","liujyu1","zhixin1","wenzhong","shuangz11","shand2","zhiqiang2","renyi1","shuaqi12","caiwu23","xiaoren2","danyuyy","ziying22"};  //微信号


    static String [] salesDept = new String[]{"文解军","顾西风","苏普","马力峰","李正","李小帆","王丽峰","柳辰飞","余少虹","胡丹丹"};//部门
    static String [] salesDeptNP = new String[]{"401","402","403","404","405","406","407","408","409","410"};//员工号
    static String [] avatar_imageSales = new String[]{"staff_401","staff_402","staff_403","staff_404","staff_405","staff_406","staff_407","staff_408","staff_409","staff_410"};// 头像
    static String [] positionSales = new String[]{"销售经理","销售员","销售员","销售员","销售员","销售员","销售员","销售主管","销售员","销售员"};  //职位名称
    static String [] sexSales = new String[]{"男性","女性","女性","女性","男性","男性","男性","男性","男性","男性"};  //性别
    static String [] nativePlaceSales = new String[]{"天津市","武汉市","烟台市","重庆市","海口市","青岛市","北京市","太原市","河津市","古交市"};  //籍贯
    static String [] nationalitySales = new String[]{"中国","中国","中国","中国","中国","中国"};  //国籍
    static String [] nationSales = new String[]{"汉族","汉族","汉族","汉族","汉族","汉族","汉族","汉族","汉族","汉族"};  //民族
    static String [] maternityStatusSales = new String[]{"已婚","未婚","未婚","未婚","未婚","未婚","未婚","未婚","未婚","未婚"};  //婚姻状况
    static String [] birthDateSales = new String[]{"1987-08-01","1991-03-16","1992-08-22","1991-10-15","1990-07-17","1990-10-12","1992-04-12","1991-03-11","1991-12-05","1992-08-14"};  //出生年月 （注：填写格式为19xx-xx-xx）
    static String [] phoneNumberSales = new String[]{"13217895933","13399987899","13578978736","13085645867","13731155222","13831310152","13622667899","13458565735","13762645852","13032399922"};  //手机号（注：号码为11位数）
    static String [] officeSeatSales = new String[]{"0401","0402","0403","0404","0405","0406","0407","0408","0409","0410"};  //办公座位
    static String [] mailAddressSales = new String[]{"xiejun22@qq.com","xifeng33@qq.com","supu7@qq.com","lifeng616@gmail.com","lizheng8@qq.com","xiaofan12@qq.com","lifeng22@qq.com"
            ,"chenfei2@qq.com","shaohong3@gmail.com","dandan99@qq.com"};  //邮箱
    static String [] addressSales = new String[]{"上海市长宁区泰山一苑29幢702室","上海市卢湾区长华苑503号501室","上海市静安区普天新城55号103室","上海市闵行区天华园321号202室","上海市普陀区君悦宫苑22幢102室",
            "上海市静安区高荣花苑120幢101室","上海市卢湾区景安花苑32号901室","上海市青浦区达安大厦51号303室","上海市闵行区蓝朝庭院21号203室","上海市普陀区桂花公寓45幢1802室"};  //通讯地址
    static String [] qqSales= new String[]{"464648","4548477","7778854","8925558862","52111","8747516611","443222333","749565","82154","858486222"};  //QQ号码
    static String [] weChatSales = new String[]{"xiejun22","xifeng33","supu7","lifeng616","lizheng8","xiaofan12","lifeng22","chenfei2","shaohong3","dandan99"};  //微信号


    static String [] managementDept = new String[]{"彭玉林","卢兰凤","刘彬彬","任青" };//部门
    static String [] managementDeptNO = new String[]{"801","802","803","804" };//员工号
    static String [] avatar_imageMage = new String[]{"staff_801","staff_802","staff_803","staff_804"};// 头像
    static String [] positionMage = new String[]{"董事长","总经理","副总经理","副总经理"};  //职位名称
    static String [] sexMage = new String[]{"女性","男性","女性","男性"};  //性别
    static String [] nativePlaceMage = new String[]{"天津市","海口市","济宁市","常熟市"};  //籍贯
    static String [] nationalityMage = new String[]{"中国","中国","中国","中国"};  //国籍
    static String [] nationMage = new String[]{"汉族","汉族","汉族","汉族"};  //民族
    static String [] maternityStatusMage = new String[]{"已婚","已婚","已婚","已婚"};  //婚姻状况
    static String [] birthDateMage = new String[]{"1970-07-16","1980-01-14","1972-04-23","1979-10-22"};  //出生年月 （注：填写格式为19xx-xx-xx）
    static String [] phoneNumberMage = new String[]{"13755566622","13052788288","13226765737","13085655862"};  //手机号（注：号码为11位数）
    static String [] officeSeatMage = new String[]{"0801","0802","0803","0804"};  //办公座位
    static String [] mailAddressMage = new String[]{"yulingp1@qq.com","lanfenglu2@qq.com","bingbingliu@qq.com","renqingtt@qq.com"};  //邮箱
    static String [] addressMage = new String[]{"上海市长宁区天富公寓3幢802室","上海市静安区菲戈花苑13号102室","上海市闵行区望亭新都223号903室","上海市闵行区华都鑫城202幢1603室"};  //通讯地址
    static String [] qqMage = new String[]{"958846","41222122","5585755","6595336","84487","513188559"};  //QQ号码
    static String [] weChatMage = new String[]{"yulingp1","lanfenglu2","bingbingliu","renqingtt"};  //微信号


    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "DatabaseHelper", null,1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql_table = "create table staffsInfos(number INTEGER PRIMARY KEY,deptName TEXT,name TEXT,avatar TEXT,position TEXT,sex TEXT,nationalities TEXT,nativePlace TEXT,nation TEXT," +
                "maternityStatus TEXT,birthDate TEXT,phoneNumber TEXT,officeSeat TEXT,mailAddress TEXT,address TEXT,qq TEXT,weChat TEXT)";
        db.execSQL(sql_table);

        ContentValues cv = new ContentValues();
        DataBase dataBase = new DataBase();
        for(int i = 0; i< dataBase.staffsName.length; i++){
            cv.put("name",dataBase.staffsName[i]);
            cv.put("number",dataBase.staffNumbers[i]);
            cv.put("deptName",dataBase.deptNames[i]);
            cv.put("avatar",dataBase.avatar[i]);
            cv.put("position",dataBase.position[i]);
            cv.put("sex",dataBase.sex[i]);
            cv.put("nativePlace",dataBase.nativePlace[i]);
            cv.put("nationalities",dataBase.nationalities[i]);
            cv.put("nation",dataBase.nation[i]);
            cv.put("maternityStatus",dataBase.maternityStatus[i]);
            cv.put("birthDate",dataBase.birthDate[i]);
            cv.put("phoneNumber",dataBase.phoneNumber[i]);
            cv.put("officeSeat",dataBase.officeSeat[i]);
            cv.put("mailAddress",dataBase.mailAddress[i]);
            cv.put("address",dataBase.address[i]);
            cv.put("qq",dataBase.qq[i]);
            cv.put("weChat",dataBase.weChat[i]);
            db.insert("staffsInfos", null, cv);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
