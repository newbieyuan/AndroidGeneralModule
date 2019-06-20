package yy.androidgeneralmodule.baseUi.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by YY on 2019/5/16.
 */

public abstract class BaseDialog extends DialogFragment {

    private View mView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (getLayoutRes() > 0) {
            //获取外部XML布局资源
            mView = inflater.inflate(getLayoutRes(), container, false);
        } else if (getDialogView() != null) {
            //获取外部传入的View;
            mView = getDialogView();
        }
        return mView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Dialog dialog = getDialog();
        if (dialog != null) {
            //设置 dialog 窗口没有标题
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            // false: 屏蔽物理返回键;
            dialog.setCancelable(isCancelable());
            //false:点击dialog外部区域,dialog不会消失;
            dialog.setCanceledOnTouchOutside(isCancelableOutside());
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    return keyCode == KeyEvent.KEYCODE_BACK
                            && event.getAction() == KeyEvent.ACTION_DOWN
                            && !isCancelable();
                }
            });
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog == null) return;
        Window window = dialog.getWindow();
        if (window == null) return;
        //设置背景色透明
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //设置Dialog动画效果
        if (getAnimRes() > 0) {
            window.setWindowAnimations(getAnimRes());
        }

        WindowManager.LayoutParams params = window.getAttributes();
        //设置Dialog宽度
        if (getDialogWidth() > 0) {
            params.width = getDialogWidth();
        } else {
            params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        }
        //设置Dialog高度
        if (getDialogHeight() > 0) {
            params.height = getDialogHeight();
        } else {
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        }
        //设置屏幕透明度  取值范围: 0.0f~1.0f (完全透明~完全不透明)
        params.dimAmount = getDimAmount();
        params.gravity = getGravity();
        window.setAttributes(params);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        DialogManager.getInstance.over();
    }

    /**
     * 获取根布局
     *
     * @return 返回 view对象
     */
    protected View getBaseView() {
        return mView;
    }

    /**
     * 获取布局资源
     *
     * @return 返回布局资源
     */
    protected abstract int getLayoutRes();

    /**
     * 获取对话框 布局
     *
     * @return 返回对话框布局
     */
    protected abstract View getDialogView();

    /**
     * 是否点击外部区域可取消对话框
     *
     * @return 返回外部区域是否可点击取消
     */
    protected boolean isCancelableOutside() {
        return true;
    }

    /**
     * 获取对话框宽度
     *
     * @return 返回对话框宽度
     */
    protected int getDialogWidth() {
        return WindowManager.LayoutParams.WRAP_CONTENT;
    }

    /**
     * 获取对话框高度
     *
     * @return 返回对话框高度
     */
    protected int getDialogHeight() {
        return WindowManager.LayoutParams.WRAP_CONTENT;
    }

    /**
     * 获取对话框重力
     *
     * @return 返回对话框位置属性
     */
    protected int getGravity() {
        return Gravity.CENTER;
    }

    /**
     * 设置对话框动画
     *
     * @return 返回对话框动画资源
     */
    protected int getAnimRes() {
        return 0;
    }

    /**
     * 设置屏幕透明度
     *
     * @return 返回屏幕透明度
     */
    protected float getDimAmount() {
        return 0.2f;
    }

}
