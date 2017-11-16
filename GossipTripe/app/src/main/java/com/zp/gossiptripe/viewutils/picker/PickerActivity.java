//package com.zp.gossiptripe.viewutils.picker;
//
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//
//import com.bigkoo.pickerview.OptionsPickerView;
//import com.bigkoo.pickerview.TimePickerView;
//import com.bigkoo.pickerview.model.IPickerViewData;
//import com.orhanobut.logger.Logger;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//
//import butterknife.BindString;
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//import lk.meterialdesign.R;
//
//public class PickerActivity extends AppCompatActivity {
//
//
//    @BindView(R.id.timepicker)
//    Button timepicker;
//    @BindView(R.id.citypicker)
//    Button citypicker;
//    @BindView(R.id.timeinfo)
//    TextView timeinfo;
//    @BindView(R.id.cityinfo)
//    TextView cityinfo;
//    @BindString(R.string.picker_title)
//    String mTitle;
//
//    TimePickerView mTimePickerView;
//    OptionsPickerView pvOptions;
//    private ArrayList<ProvinceBean> options1Items = new ArrayList<>();
//    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
//    private ArrayList<ArrayList<ArrayList<IPickerViewData>>> options3Items = new ArrayList<>();
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_picker);
//        ButterKnife.bind(this);
//        Logger.init("PengLog");
//        initData();
//
//    }
//
//
//    private void initData() {
//        mTimePickerView =  new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
//        mTimePickerView.setTime(new Date());
//        mTimePickerView.setCyclic(false);
//        mTimePickerView.setCancelable(false);
//        mTimePickerView.setTitle(mTitle);
//        mTimePickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
//            @Override
//            public void onTimeSelect(Date date) {
//                timeinfo.setText(getTime(date));
//            }
//        });
//        pvOptions = new OptionsPickerView(this);
//
//
//
//        options1Items.add(new ProvinceBean(0,"广东","广东省，以岭南东道、广南东路得名","其他数据"));
//        options1Items.add(new ProvinceBean(1,"湖南","湖南省地处中国中部、长江中游，因大部分区域处于洞庭湖以南而得名湖南","芒果TV"));
//        options1Items.add(new ProvinceBean(3,"广西","嗯～～",""));
//
//        ArrayList<String> options2Items_01=new ArrayList<>();
//        options2Items_01.add("广州");
//        options2Items_01.add("佛山");
//        options2Items_01.add("东莞");
//        options2Items_01.add("阳江");
//        options2Items_01.add("珠海");
//        ArrayList<String> options2Items_02=new ArrayList<>();
//        options2Items_02.add("长沙");
//        options2Items_02.add("岳阳");
//        ArrayList<String> options2Items_03=new ArrayList<>();
//        options2Items_03.add("桂林");
//        options2Items.add(options2Items_01);
//        options2Items.add(options2Items_02);
//        options2Items.add(options2Items_03);
//
//        ArrayList<ArrayList<IPickerViewData>> options3Items_01 = new ArrayList<>();
//        ArrayList<ArrayList<IPickerViewData>> options3Items_02 = new ArrayList<>();
//        ArrayList<ArrayList<IPickerViewData>> options3Items_03 = new ArrayList<>();
//
//        ArrayList<IPickerViewData> options3Items_01_01=new ArrayList<>();
//        options3Items_01_01.add(new PickerViewData("天河"));
//        options3Items_01_01.add(new PickerViewData("黄埔"));
//        options3Items_01_01.add(new PickerViewData("海珠"));
//        options3Items_01_01.add(new PickerViewData("越秀"));
//        options3Items_01.add(options3Items_01_01);
//        ArrayList<IPickerViewData> options3Items_01_02=new ArrayList<>();
//        options3Items_01_02.add(new PickerViewData("南海"));
//        options3Items_01_02.add(new PickerViewData("高明"));
//        options3Items_01_02.add(new PickerViewData("禅城"));
//        options3Items_01_02.add(new PickerViewData("桂城"));
//        options3Items_01.add(options3Items_01_02);
//        ArrayList<IPickerViewData> options3Items_01_03=new ArrayList<>();
//        options3Items_01_03.add(new PickerViewData("其他"));
//        options3Items_01_03.add(new PickerViewData("常平"));
//        options3Items_01_03.add(new PickerViewData("虎门"));
//        options3Items_01.add(options3Items_01_03);
//        ArrayList<IPickerViewData> options3Items_01_04=new ArrayList<>();
//        options3Items_01_04.add(new PickerViewData("其他"));
//        options3Items_01_04.add(new PickerViewData("其他"));
//        options3Items_01_04.add(new PickerViewData("其他"));
//        options3Items_01.add(options3Items_01_04);
//        ArrayList<IPickerViewData> options3Items_01_05=new ArrayList<>();
//
//        options3Items_01_05.add(new PickerViewData("其他1"));
//        options3Items_01_05.add(new PickerViewData("其他2"));
//        options3Items_01.add(options3Items_01_05);
//
//
//        ArrayList<IPickerViewData> options3Items_02_01=new ArrayList<>();
//
//        options3Items_02_01.add(new PickerViewData("长沙1"));
//        options3Items_02_01.add(new PickerViewData("长沙2"));
//        options3Items_02_01.add(new PickerViewData("长沙3"));
//        options3Items_02_01.add(new PickerViewData("长沙4"));
//        options3Items_02_01.add(new PickerViewData("长沙5"));
//
//
//
//
//        options3Items_02.add(options3Items_02_01);
//        ArrayList<IPickerViewData> options3Items_02_02=new ArrayList<>();
//
//        options3Items_02_02.add(new PickerViewData("岳阳"));
//        options3Items_02_02.add(new PickerViewData("岳阳1"));
//        options3Items_02_02.add(new PickerViewData("岳阳2"));
//        options3Items_02_02.add(new PickerViewData("岳阳3"));
//        options3Items_02_02.add(new PickerViewData("岳阳4"));
//        options3Items_02_02.add(new PickerViewData("岳阳5"));
//
//        options3Items_02.add(options3Items_02_02);
//
//        ArrayList<IPickerViewData> options3Items_03_01=new ArrayList<>();
//        options3Items_03_01.add(new PickerViewData("好山水"));
//        options3Items_03.add(options3Items_03_01);
//
//        options3Items.add(options3Items_01);
//        options3Items.add(options3Items_02);
//        options3Items.add(options3Items_03);
//
//
//        pvOptions.setPicker(options1Items, options2Items,options3Items, true);
//        //设置选择的三级单位
////        pwOptions.setLabels("省", "市", "区");
//        pvOptions.setTitle("选择城市");
//        pvOptions.setCyclic(false, false, false);
//        //设置默认选中的三级项目
//        //监听确定选择按钮
//        pvOptions.setSelectOptions(1, 1,1);
//        pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
//
//            @Override
//            public void onOptionsSelect(int options1, int option2, int options3) {
//                //返回的分别是三个级别的选中位置
//                String tx = options1Items.get(options1).getPickerViewText()
//                + options2Items.get(options1).get(option2)
//                + options3Items.get(options1).get(option2).get(options3).getPickerViewText();
//                cityinfo.setText(tx);
//            }
//        });
//    }
//
//    @OnClick({R.id.timepicker, R.id.citypicker})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.timepicker:
//                mTimePickerView.show();
//                break;
//            case R.id.citypicker:
//                pvOptions.show();
//                break;
//        }
//    }
//    public static String getTime(Date date) {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        return format.format(date);
//    }
//
//}
