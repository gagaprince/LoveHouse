$(document).ready(function(){
    initPage();
});
function initPage(){
    initShare();
}
function initShare(){
    var shareImg = "${contextBean.shareImg}";
    var shareTitle = "${contextBean.shareTitle}";
    var shareDesc = "${contextBean.shareDesc}";
    wxBridge.setImgUrl(shareImg);
    wxBridge.setTitle(shareTitle);
    wxBridge.setDesc(shareDesc);
}