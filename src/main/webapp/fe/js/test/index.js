$(document).ready(function(){
    initPage();
});
function initPage(){
    initShare();
}
function initShare(){
    wxBridge.setImgUrl("http://cgz");
    wxBridge.setLink("http://www.jiemo.net/smallgame/facegame/?from=groupmessage&isappinstalled=0");
    wxBridge.setTitle("我在10秒内打了京京孟岩267次脸，获得了'你太牛逼'称号，本游戏由《芥末》贡献");
    wxBridge.setDesc("www.jiemo.net");
}