$(document).ready(function(){
    initPage();
});
function initPage(){
    initShare();
}
function initShare(){
    wxBridge.setImgUrl(shareImg||"");
    wxBridge.setTitle(shareTitle||"");
    wxBridge.setDesc(shareDesc||"");
}