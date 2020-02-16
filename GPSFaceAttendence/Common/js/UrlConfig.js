const IP = "http://oaapi.yingfeng365.com/jsyf-oa";
export const URL = {
    //登录
    loginIn: IP + "/user/login.json",

    //签到
    signIn: IP + "/signIn/save.json",

    //签到撤销
    revertIn: IP + "/signIn/revertIn.json",

    //签退撤销
    revertOut: IP + "/signIn/revert.json",

    //百度逆地址解析
    getAddress: "http://api.map.baidu.com/geocoder/v2/?callback=&output=json&pois=0&ak=18eZsB5LvSoIf1HZOCUBDXKWHMbvhfpa&location=",

    //查询签到
    getSign: IP + "/signIn/getSignInByDay.json",

    //上传文件
    uploadFile: IP + "/attach/uploadFile.json",

    //获取推荐
    getRecommend: "https://www.apiopen.top/satinApi",

    //获取美图
    getPic: "https://www.apiopen.top/meituApi",


}