package com.wenhuaijun.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.wenhuaijun.easytagdragview.EasyTipDragView;
import com.wenhuaijun.easytagdragview.R;
import com.wenhuaijun.easytagdragview.bean.TitleTip;
import com.wenhuaijun.easytagdragview.widget.TipItemView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
 /*implements AbsTipAdapter.DragDropListener, TipItemView.OnSelectedListener, TipItemView.OnDeleteClickListener */ {
    private EasyTipDragView easyTipDragView;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        easyTipDragView = (EasyTipDragView) findViewById(R.id.easy_tip_drag_view);
        btn = (Button) findViewById(R.id.btn);

        initData();
    }

    private void initData() {
        //设置已包含的标签数据
        easyTipDragView.setAddData(TipDataModel.getAddTips());
        //设置可以添加的标签数据
        easyTipDragView.setDragData(TipDataModel.getDragTips());


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                easyTipDragView.open();
                btn.setVisibility(View.GONE);
            }
        });
        //在easyTipDragView处于非编辑模式下点击item的回调（编辑模式下点击item作用为删除item）
        easyTipDragView.setSelectedListener(new TipItemView.OnSelectedListener() {
            @Override
            public void onTileSelected(TitleTip entity, int position, View view) {
                toast(entity.getTip());
            }
        });
        //设置每次数据改变后的回调（例如每次拖拽排序了标签或者增删了标签都会回调）
        easyTipDragView.setDataResultCallback(new EasyTipDragView.OnDataChangeResultCallback() {
            @Override
            public void onDataChangeResult(ArrayList<TitleTip> tips) {
                Log.i("heheda", tips.toString());
            }
        });
        //设置点击“确定”按钮后最终数据的回调
        easyTipDragView.setOnCompleteCallback(new EasyTipDragView.OnCompleteCallback() {
            @Override
            public void onComplete(ArrayList<TitleTip> tips) {
                toast("最终数据：" + tips.toString());
                btn.setVisibility(View.VISIBLE);
            }
        });

    }


    public void toast(String str) {
        Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            //点击返回键
            case KeyEvent.KEYCODE_BACK:
                //判断easyTipDragView是否已经显示出来
                if (easyTipDragView.isOpen()) {
                    if (!easyTipDragView.onKeyBackDown()) {
                        btn.setVisibility(View.VISIBLE);
                    }
                    return true;
                }
                //....自己的业务逻辑

                break;
        }
        return super.onKeyDown(keyCode, event);
    }
}
