package com.jay.word_app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MyFragment_setting extends Fragment {
    private Context mContext;
    private int ModeCheckedItem = -1;//记忆模式 -1：空  0：汉背英  1：英背汉


    private String[] province = new String[] { "上海", "北京", "湖南", "湖北", "海南" };
    private ButtonOnClick buttonOnClick = new ButtonOnClick(ModeCheckedItem);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        this.mContext = getActivity();

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting_layout,container,false);
        TextView txt_ChangeMode=view.findViewById(R.id.txt_ChangeMode);
        txt_ChangeMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSingleChoiceButton();
            }
        });

       /* txt_ChangeMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setIcon(R.drawable.right);
                builder.setTitle("请选择记忆模式");
                final String[] mode = {"汉背英", "英背汉"};
                //    设置一个单项选择下拉框
                *//**
                 * 第一个参数指定我们要显示的一组下拉单选框的数据集合
                 * 第二个参数代表索引，指定默认哪一个单选框被勾选上，1表示默认‘汉背英‘ 会被勾选上
                 * 第三个参数给每一个单选项绑定一个监听器
                 *//*
                builder.setSingleChoiceItems(mode, ModeCheckedItem, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(mContext, "所选模式为：" + mode[which], Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        ModeCheckedItem=which;
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                });
                builder.show();
            }
        });*/


        return view;
    }

    // 在单选选项中显示 确定和取消按钮
    //buttonOnClickg变量的数据类型是ButtonOnClick,一个单击事件类
    private void showSingleChoiceButton()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("请选择省份");
        builder.setSingleChoiceItems(province, 0, buttonOnClick);
        builder.setPositiveButton("确定", buttonOnClick);
        builder.setNegativeButton("取消", buttonOnClick);
        builder.show();
    }

    private class ButtonOnClick implements DialogInterface.OnClickListener
    {

        private int index; // 表示选项的索引

        public ButtonOnClick(int index)
        {
            this.index = index;
        }

        @Override
        public void onClick(DialogInterface dialog, int which)
        {
            // which表示单击的按钮索引，所有的选项索引都是大于0，按钮索引都是小于0的。
            if (which >= 0)
            {
                //如果单击的是列表项，将当前列表项的索引保存在index中。
                //如果想单击列表项后关闭对话框，可在此处调用dialog.cancel()
                //或是用dialog.dismiss()方法。
                index = which;
            }
            else
            {
                //用户单击的是【确定】按钮
                if (which == DialogInterface.BUTTON_POSITIVE)
                {
                    ModeCheckedItem=index;
                    //显示用户选择的是第几个列表项。
                    final AlertDialog ad = new AlertDialog.Builder(
                            mContext).setMessage(
                            "你选择的地区是：" + index + ":" + province[index]).show();
                    //五秒钟后自动关闭。
                    Handler hander = new Handler();
                    Runnable runnable = new Runnable()
                    {

                        @Override
                        public void run()
                        {
                            ad.dismiss();
                        }
                    };
                    hander.postDelayed(runnable, 5 * 1000);
                }
                //用户单击的是【取消】按钮
                else if (which == DialogInterface.BUTTON_NEGATIVE)
                {
                    Toast.makeText(mContext, "你没有选择任何东西",
                            Toast.LENGTH_LONG);
                }
            }
        }
    }


}
