$(document).ready(function(){
    initPage();
});
function initPage(){
    initShare();
}
function initShare(){
    wxBridge.setImgUrl("http://cgzp.cdn.novorunning.com/second/images/big_icon.jpg");
    wxBridge.setLink("http://gagalulu.wang/lovelulu/game/oneSecond");
    wxBridge.setTitle("超级无敌王子冬，竟然按出了1s，快来试一试！");
    wxBridge.setDesc("你肯定不行！！！！");
}