package yy.androidgeneralmodule.baseUi.dialog;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import yy.androidgeneralmodule.R;

/**
 * Created by YY on 2019/5/16.
 * dialog 控制器
 */

public class DialogController {

    private int layoutRes;
    private int dialogWidth;
    private int dialogHeight;
    private float dimAmount = 0.2f;
    private int gravity = Gravity.CENTER;
    private boolean isCancelableOutside = true;
    private boolean cancelable;
    private int animRes;
    private View dialogView;

    private IDialog.OnClickListener mPositiveBtnListener;
    private IDialog.OnClickListener mNegativeBtnListener;
    private WeakReference<IDialog> mDialog;
    //默认标题
    private String titleStr;
    //默认内容
    private String contentStr;
    //默认左边按钮文字
    private String leftStr;
    //默认右边按钮文字
    private String rightStr;
    //显示左右两边的按钮
    private boolean showBtnLeft, showBtnRight;

    private Button btnLeft, btnRight;

    public DialogController(IDialog dialog) {
        mDialog = new WeakReference<IDialog>(dialog);
    }

    int getLayoutRes() {
        return layoutRes;
    }

    void setLayoutRes(int layoutRes) {
        this.layoutRes = layoutRes;
    }

    int getDialogWidth() {
        return dialogWidth;
    }

    int getDialogHeight() {
        return dialogHeight;
    }

    float getDimAmount() {
        return dimAmount;
    }

    int getGravity() {
        return gravity;
    }

    boolean isCancelableOutside() {
        return isCancelableOutside;
    }

    boolean isCancelable() {
        return cancelable;
    }

    int getAnimRes() {
        return animRes;
    }

    View getDialogView() {
        return dialogView;
    }

    void setDialogView(View dialogView) {
        this.dialogView = dialogView;
    }

    public void setChildView(View view) {
        setDialogView(view);

    }

    void dealDefuaultDialog(IDialog.OnClickListener positiveBtnListener, IDialog.OnClickListener negativeBtnListener,
                            String titleStr, String contentStr, boolean showBtnLeft, String leftStr, boolean showBtnRight, String rightStr) {

        if (dialogView == null) return;
        this.mPositiveBtnListener = positiveBtnListener;
        this.mNegativeBtnListener = negativeBtnListener;
        this.titleStr = titleStr;
        this.contentStr = contentStr;
        this.showBtnLeft = showBtnLeft;
        this.leftStr = leftStr;
        this.showBtnRight = showBtnRight;
        this.rightStr = rightStr;

        btnRight = dialogView.findViewById(R.id.btnRight);
        btnLeft = dialogView.findViewById(R.id.btnLeft);

        if (btnRight != null && !TextUtils.isEmpty(rightStr)) {
            btnRight.setVisibility(showBtnRight ? View.VISIBLE : View.GONE);
            btnRight.setText(rightStr);
            btnRight.setOnClickListener(mButtonHandler);
        }
        if (btnLeft != null && !TextUtils.isEmpty(leftStr)) {
            btnLeft.setVisibility(showBtnLeft ? View.VISIBLE : View.GONE);
            btnLeft.setText(leftStr);
            btnLeft.setOnClickListener(mButtonHandler);
        }

        TextView tv_title = dialogView.findViewById(R.id.dialog_title);
        TextView tv_content = dialogView.findViewById(R.id.dialog_content);
        if (tv_title != null) {
            tv_title.setVisibility(TextUtils.isEmpty(titleStr) ? View.GONE : View.VISIBLE);
            tv_title.setText(titleStr);
        }
        if (tv_content != null) {
            tv_content.setVisibility(TextUtils.isEmpty(contentStr) ? View.GONE : View.VISIBLE);
            tv_content.setText(contentStr);
        }

    }

    private final View.OnClickListener mButtonHandler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == btnLeft) {
                if (mDialog.get() == null) return;
                if (mNegativeBtnListener != null) {
                    mNegativeBtnListener.onClick(mDialog.get());
                }
            } else if (view == btnRight) {
                if (mDialog.get() == null) return;
                if (mPositiveBtnListener != null) {
                    mPositiveBtnListener.onClick(mDialog.get());
                }
            }
        }
    };

    public static class SYParams {
        FragmentManager fragmentManager;
        int layoutRes;
        int dialogWidth;
        int dialogHeight;
        float dimAmount = 0.2f;
        public int gravity = Gravity.CENTER;
        boolean isCancelableOutside = true;
        boolean cancelable = false;
        View dialogView;
        Context context;
        IDialog.OnClickListener positiveBtnListener;
        IDialog.OnClickListener negativeBtnListener;
        String titleStr;//默认标题
        String contentStr;//默认内容
        String positiveStr;//右边按钮文字
        String negativeStr;//左边按钮文字
        boolean showBtnLeft, showBtnRight;
        int animRes;//Dialog动画style

        void apply(DialogController controller) {
            controller.dimAmount = dimAmount;
            controller.gravity = gravity;
            controller.isCancelableOutside = isCancelableOutside;
            controller.cancelable = cancelable;
            controller.animRes = animRes;
            controller.titleStr = titleStr;
            controller.contentStr = contentStr;
            controller.rightStr = positiveStr;
            controller.leftStr = negativeStr;
            controller.showBtnLeft = showBtnLeft;
            controller.showBtnRight = showBtnRight;
            controller.mPositiveBtnListener = positiveBtnListener;
            controller.mNegativeBtnListener = negativeBtnListener;
            if (layoutRes > 0) {
                controller.setLayoutRes(layoutRes);
            } else if (dialogView != null) {
                controller.dialogView = dialogView;
            } else {
                throw new IllegalArgumentException("Dialog View can't be null");
            }
            if (dialogWidth > 0) {
                controller.dialogWidth = dialogWidth;
            }
            if (dialogHeight > 0) {
                controller.dialogHeight = dialogHeight;
            }
        }

    }
}
