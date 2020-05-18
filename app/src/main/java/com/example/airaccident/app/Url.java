package com.example.airaccident.app;

/**
 * 存放所有的Api
 */
public interface Url {
    //String port = "http://cloud.guanweiming.com:10099/api/";
    String port = "http://39.97.219.174:8889/api/";



    //单条件查询
    String singleSelect = port+"airdetail/singleSelect";


    //多条件查询
    String allselect = port+"airdetail/allselect";


    //按事件类型查询
    String airtypeselect = port+"airdetail/airtypeselect";



    //按事件类型查询
    String airwhyselect = port+"airdetail/airwhyselect";



    //详情接口
    String airdetailDetail = port+"airdetail/airdetailDetail";


    //注册接口
    String register = port+"manager/mRegister";

    //登录接口
    String login = port+"manager/mLogin";

    //上传图片
    String upload = port+"manager/upload";

    //添加事故详情接口
    String airdetailadd = port+"airdetail/add";


    //单条件查询原因
    String reasonsingleSelect = port+"reason/singleSelect";


    //删除事故
    String delete = port+"airdetail/delete";


    //修改事故
    String update = port+"airdetail/update";

    //修改密码
    String updateUserInfo = port+"manager/updateManagerInfo";


    //全部原因
    String reasonall = port+"reason/all";

    //普通用户登录
    String userLogin=port+"user/uLogin";
    //普通用户登录
    String userRegister=port+"user/uRegister";
    //普通用户登录
    String userUpdateUserInfo=port+"user/updateUserInfo";

    String reasonadd  = port+"reason/add";
    String newsreasonsingleSelect  = port+"reason/singleSelect";
    String detail  = port+"reason/detail";
    String detaildelete  = port+"reason/delete";
    String detailupdate  = port+"reason/update";

}