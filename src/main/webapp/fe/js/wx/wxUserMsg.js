var WxUserMsg = (function(){
    var userMsg = wxUserMsg||{};
    function getHeadUrl(){
        return userMsg["headimgurl"]||"";
    }


    return {
        getHeadUrl:getHeadUrl
    }
})();