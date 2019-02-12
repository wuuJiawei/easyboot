package com.easyboot.common.easemob.api.impl;

import com.easyboot.common.easemob.api.AuthTokenAPI;
import com.easyboot.common.easemob.comm.TokenUtil;


public class EasemobAuthToken implements AuthTokenAPI{

	@Override
	public Object getAuthToken(){
		return TokenUtil.getAccessToken();
	}
}
