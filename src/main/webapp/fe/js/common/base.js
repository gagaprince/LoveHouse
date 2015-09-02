var myUtil = (function(){
	
	
	
	
	var api = (function (){
		var filters = [];
        function addFilter(fiter){
            filters.push(fiter);
        }
        function dofilter(res,callback){
            var filter = filters[0];
            for(var i=0;filter;filter=filters[++i]){
                var flag = filter(res);
                if(!flag){
                    return;
                }
            }
            callback(res);
        }
        //登录filter
        /*addFilter(function(res){
            if(res.bstatus&&res.bstatus.code==NOLOGIN_ERR){
                setHash("/login/");
                return false;
            }
            return true;
        });*/
        /**
         * @param useMock {Boolean} 是否使用测试桩。
         */
        return function(t, data, method ,callback, useMock,noFilter){
            if(!callback){
                callback=$.noop;
            }

            var doneFns=[];
            var stepFns=[callback];

            var e={
                done:function(fn){
                    doneFns.push(fn);
                    return this;
                },
                on:function(fn){
                    stepFns.push(fn);
                    return this;
                },
                resolve:function(){
                    e.status='done';
                    for (var i = 0; i < doneFns.length; i++) {
                        doneFns[i].apply(e,arguments);
                    }
                    return this;
                }
            };
//            var dataSend = JSON.stringify(data);
            var ajaxOption={
                url : t,
                type : method||'GET',
                data :data,
//                contentType:"application/json; charset=UTF-8",
                contentType:"application/x-www-form-urlencoded; charset=UTF-8",
                dataType : 'json',
                timeout : 3e4,
                success : function(res){
                    // 当接口挂了
                    if(typeof res == "string"){
                        res = JSON.parse(res);
                    }
                    e.status='';
                    if(!noFilter){
                        dofilter(res,function(){
                            for (var i = 0; i < stepFns.length; i++) {
                                stepFns[i].call(e,res.bstatus&&res.bstatus.code,res.bstatus&&res.bstatus.desc,res.data,res);
                            }
                        });
                    }else{
                        for (var i = 0; i < stepFns.length; i++) {
                            stepFns[i].call(e,res.bstatus&&res.bstatus.code,res.bstatus&&res.bstatus.desc,res.data,res);
                        }
                    }


                },
                error : function(res){
                    e.status='fail';
                    // time out 的status 也是0
                    for (var i = 0; i < stepFns.length; i++) {
                        stepFns[i].call(e,-res.status||-1,Res.base_network_error,res.responseText);
                    }
                }
            };
            if(useMock){// 使用测试桩数据
                ajaxOption.url = 'js/mock/' + t + '.json' + '?rdm=' + Math.random();
                ajaxOption.type = 'GET';
            }
            e.retry=function(){
                if(e.status==='loading'){
                    return;
                }
                e.status='loading';
                e.ajax=$.ajax(ajaxOption);
                return this;
            };
            if(useMock){
                setTimeout(function(){
                    e.retry();
                },500)
            }else{
                e.retry();
            }
            return e;
        };
	})();
	
	
	function getQueryString(name) { 
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
		var r = window.location.search.substr(1).match(reg); 
		if (r != null) return unescape(r[2]); return null; 
	} 
	
	return {
		api:api,
		getQueryString:getQueryString
	}
})();