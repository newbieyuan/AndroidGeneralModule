package yy.androidgeneralmodule.baseUi;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import yy.androidgeneralmodule.R;

/**
 */
public abstract class BaseFragment extends Fragment {
    protected View mRootView;


    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mRootView == null) {
            int layoutId = getContentLayoutId();
            //初始化当前的根布局,但是不在创建时就添加到container里面
            View root = inflater.inflate(layoutId, container, false);
            initWidget(root);
            mRootView = root;
        } else {
            if (mRootView.getParent() != null) {
                //把当前的根布局从其父控件中移除
                ((ViewGroup) mRootView.getParent()).removeView(mRootView);
            }
        }
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //当View创建完成后初始化数据
        initData();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initArgs(getArguments());
    }

    /**
     * 初始化参数
     *
     * @param bundle 需要初始化的参数
     */
    protected void initArgs(Bundle bundle) {
    }

    /**
     * 获得当前界面的资源文件Id
     *
     * @return 资源文件Id
     */
    protected abstract int getContentLayoutId();

    /**
     * 初始化控件
     */
    protected void initWidget(View root) {
//         mRootUnbinder = ButterKnife.bind(this, root);
    }

    /**
     * 初始化数据
     */
    protected void initData() {
    }


    /**
     * 返回按键触发时调用
     *
     * @return 返回true代表自己处理返回逻辑, Activity不用处理
     * 返回false代表没有处理逻辑,交由Activity处理
     */
    public boolean onBackPressed() {
        return false;
    }
}
