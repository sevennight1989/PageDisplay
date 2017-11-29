package com.zp.gossiptripe.main.personal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.SDCardUtils;
import com.squareup.picasso.Picasso;
import com.zp.gossiptripe.R;
import com.zp.gossiptripe.viewutils.ScreenDialogUtils;

import org.w3c.dom.Text;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.zp.gossiptripe.main.personal.PersonalFragment.TOPERSON_INFO;

public class PersonInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout mHeadRoot;
    private RelativeLayout mNameRoot;
    private RelativeLayout mAgeRoot;
    private RelativeLayout mLocationRoot;
    private RelativeLayout mOrganizationRoot;
    private TextView mNameValue;
    private TextView mBirthdayValue;
    private TextView mAddressValue;
    private TextView mOrganizationValue;
    CircleImageView mHeadImage;
    Bitmap uploadBp;
    PersonBean mPersonBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getResources().getString(R.string.personalInfo));
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        mPersonBean = (PersonBean) getIntent().getSerializableExtra(TOPERSON_INFO);
        initViews();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initViews() {
        mHeadRoot = (RelativeLayout) findViewById(R.id.headRoot);
        mNameRoot = (RelativeLayout) findViewById(R.id.nameRoot);
        mAgeRoot = (RelativeLayout) findViewById(R.id.birthdayRoot);
        mLocationRoot = (RelativeLayout) findViewById(R.id.addressRoot);
        mOrganizationRoot = (RelativeLayout) findViewById(R.id.organizationRoot);
        mHeadRoot.setOnClickListener(this);
        mNameRoot.setOnClickListener(this);
        mAgeRoot.setOnClickListener(this);
        mLocationRoot.setOnClickListener(this);
        mOrganizationRoot.setOnClickListener(this);
        mHeadImage = (CircleImageView) findViewById(R.id.head);
        mNameValue = (TextView) findViewById(R.id.nameValue);
        mBirthdayValue = (TextView) findViewById(R.id.birthdayValue);
        mAddressValue = (TextView) findViewById(R.id.addressValue);
        mOrganizationValue = (TextView) findViewById(R.id.organizationValue);
        loadData();
    }


    private void loadData() {
        PersonBean pBean = mPersonBean;
        mNameValue.setText(pBean.getmName());
        mAddressValue.setText(pBean.getmLocation());
        mBirthdayValue.setText(pBean.getmBirthday());
        mOrganizationValue.setText(pBean.getmOrganization());
        Picasso.with(this).load(pBean.getmIconPath())
        .placeholder(R.mipmap.touxiang).into(mHeadImage);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case PersonConstants.IMAGE_REQUEST_CODE:
                    ScreenDialogUtils.startPhotoZoom(data.getData(), this);
                    break;

                case PersonConstants.CAMERA_REQUEST_CODE:
                    File file = new File(Environment.getExternalStorageDirectory(), "/" +
                    PersonConstants.IMAGE_FILE_NAME);
                    ScreenDialogUtils.startPhotoZoom(Uri.fromFile(file), this);
                    break;

                case PersonConstants.RESULT_REQUEST_CODE:
                    if (data != null) {
                        getImageToView(data);
                    }
                    break;

                case PersonConstants.NAME_REQUEST_CODE:
                    String mModifyName = data.getStringExtra(PersonNameEditActivity.MODITY_NAME);
                    if (mModifyName != null && !mModifyName.equals(""))
                        mNameValue.setText(mModifyName);
                    break;

                case PersonConstants.AGE_REQUEST_CODE:
                    String mModifyBirthday = data.getStringExtra(PersonAgeEditActivity.MODITY_BIRTHDAY);
                    if (mModifyBirthday != null && !mModifyBirthday.equals(""))
                        mBirthdayValue.setText(mModifyBirthday);
                    break;

                case PersonConstants.ADDRESS_REQUEST_CODE:
                    String mModifyAddress = data.getStringExtra(PersonAddressEditActivity.MODIFY_ADDRESS);
                    if (mModifyAddress != null && !mModifyAddress.equals(""))
                        mAddressValue.setText(mModifyAddress);
                    break;

                case PersonConstants.ORGANIZATION_REQUEST_CODE:
                    String mModifyOrganization = data.getStringExtra(PersonOrganizationActivity.MODIFY_ORGANIZATION);
                    if (mModifyOrganization != null && !mModifyOrganization.equals(""))
                        mOrganizationValue.setText(mModifyOrganization);
                    break;
            }
        }
    }

    private void getImageToView(Intent data) {
        Bundle bundle = data.getExtras();
        if (bundle != null) {
            String path = SDCardUtils.getSDCardPaths(false).get(0);
            uploadBp = bundle.getParcelable("data");
            if (SDCardUtils.isSDCardEnable()) {
                ImageUtils.save(uploadBp, path + File.separator + PersonConstants.HEAD_UPDATE_NAME, Bitmap.CompressFormat.JPEG);
            }
            mHeadImage.setImageBitmap(uploadBp);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.headRoot:
                ScreenDialogUtils.showDialog(this);
                break;

            case R.id.nameRoot:
                Intent nameEditIntent = new Intent(this, PersonNameEditActivity.class);
                startActivityForResult(nameEditIntent, PersonConstants.NAME_REQUEST_CODE);
                break;

            case R.id.birthdayRoot:
                Intent ageEditIntent = new Intent(this, PersonAgeEditActivity.class);
                startActivityForResult(ageEditIntent, PersonConstants.AGE_REQUEST_CODE);
                break;

            case R.id.addressRoot:
                Intent addressEditIntent = new Intent(this, PersonAddressEditActivity.class);
                startActivityForResult(addressEditIntent, PersonConstants.ADDRESS_REQUEST_CODE);
                break;

            case R.id.organizationRoot:
                Intent organizationRootIntent = new Intent(this, PersonOrganizationActivity.class);
                startActivityForResult(organizationRootIntent, PersonConstants.ORGANIZATION_REQUEST_CODE);
                break;

            default:
                break;
        }
    }
}
