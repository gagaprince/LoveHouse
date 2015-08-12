$(document).ready(function(){
    initPage();
});
function initPage(){
    initShare();
}
function initShare(){
    wxBridge.setImgUrl("http://cgzp.cdn.novorunning.com/second/images/big_icon.jpg");
    wxBridge.setLink("http://second.shenzhankai.com/second/index.html");
    wxBridge.setTitle("我按出了10个1秒，一个终极大奖都木有，这个是骗人滴！")
}