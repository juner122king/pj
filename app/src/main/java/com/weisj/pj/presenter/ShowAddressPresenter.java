package com.weisj.pj.presenter;

import com.weisj.pj.base.BaseViewState;
import com.weisj.pj.bean.AdressBean;
import com.weisj.pj.bean.BaseBean;
import com.weisj.pj.manager.IAddressManager;
import com.weisj.pj.manager.impl.AddressManager;
import com.weisj.pj.manager.listener.IOnManagerListener;
import com.weisj.pj.utils.SystemConfig;
import com.weisj.pj.utils.Urls;
import com.weisj.pj.viewinterface.IShowAddressView;

/**
 * Created by Administrator on 2016/7/22 0022.
 */
public class ShowAddressPresenter implements IOnManagerListener {
    private IShowAddressView iShowAddressView;
    private IAddressManager iAddressManager;
    private BaseViewState viewState;

    public ShowAddressPresenter(IShowAddressView iShowAddressView, BaseViewState viewState) {
        this.iShowAddressView = iShowAddressView;
        this.viewState = viewState;
        iAddressManager = new AddressManager();
    }

    public void getAddress() {
        viewState.showInitLoading();
        iAddressManager.getAddress(this);
    }

    public void deleteAddress(String addressId) {
        viewState.showLoading();
        iAddressManager.deleteAddress(addressId, this);
    }

    public void setDefaultAddress(String addressId){
        iAddressManager.setDefaultAddress(addressId,null);
    }

    @Override
    public void onSuccess(Object data, String url) {
        if (url.equals(Urls.getAddress)) {
            AdressBean adressBean = (AdressBean) data;
            if ("1".equals(adressBean.getCode()) && adressBean.getData() != null && adressBean.getData().size() > 0) {
                viewState.showLoadFinish();
                iShowAddressView.showAddress(adressBean);
            } else {
                viewState.showNoData();
            }
        } else {
            if (data instanceof BaseBean) {
                BaseBean baseBean = (BaseBean) data;
                viewState.showLoadFinish();
                if (SystemConfig.isGetNetSuccess(baseBean)) {
                    SystemConfig.showToast(baseBean.getMsg());
                    String[] strs = url.split("&");
                    String[] strs2 = strs[1].split("=");
                    iShowAddressView.successAddress(strs2[1]);
                }
            }
        }
    }

    @Override
    public void onFail(Exception e, String url) {
        if (url.equals(Urls.getAddress)) {
            viewState.showNoNetWork();
        } else {
            SystemConfig.showToast("网络错误");
        }
    }
}
