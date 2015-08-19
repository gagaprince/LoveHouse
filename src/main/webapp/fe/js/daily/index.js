$(document).ready(function(){
    initPage();
});
function initPage(){
    initShare();
}
function initShare(){
    wxBridge.setImgUrl("http://gagaprince.oss-cn-beijing.aliyuncs.com/daily/IMG_1259.JPG");
    wxBridge.setTitle("给我最爱的小璐璐");
    wxBridge.setDesc("有了你，我的世界才有了白天黑夜，有了你，我的天空才能逃离阴霾");
}