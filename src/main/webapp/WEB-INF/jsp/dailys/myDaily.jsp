<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <base href="<%=basePath%>">

        <title><c:out value="${title}"></c:out></title>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no,minimal-ui" name="viewport">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta content="telephone=no" name="format-detection">
        <!-- UC默认竖屏 ，UC强制全屏 -->
        <meta name="full-screen" content="yes">
        <meta name="browsermode" content="application">
        <!-- QQ强制竖屏 QQ强制全屏 -->
        <meta name="x5-orientation" content="portrait">
        <meta name="x5-fullscreen" content="true">
        <meta name="x5-page-mode" content="app">
        <meta name="keywords" content="">
        <meta name="description" content="">
        <meta charset="UTF-8">

        <link href="/fe/css/daily/daily.css" rel="stylesheet" />

    </head>

    <body>
        <div class="daily-frame v-c">
            <div class="daily-title font-yahei">
                我深爱的女孩儿
            </div>
            <div class="daily-time-frame">
                <div class="daily-time">2015-08-19</div>
            </div>
            <div class="daily-img-frame h-c">
                <img src="http://gagaprince.oss-cn-beijing.aliyuncs.com/daily/0.jpg">
            </div>
            <div class="daily-content-frame">
                <div class="daily-content">
                    <div class="daily-bg v-c">
                        <div class="daily-duan-frame black-tou">
                            她很纯真，她很美腻，<br>她很聪明，她很专一，<br>她很霸道，她很文艺，<br>她很厉害，她很神秘，<br>她很孝顺，她很贤惠，<br>她很温柔，也很逗逼，<br>她~是我的唯一！<br>她有数不尽的优点，<br>也有改不掉的强迫症。<br>我们的故事很长很复杂，<br>其实也很短很简单，<br>只是我喜欢她，<br>刚好她也喜欢我，
                            <br>这不就是传说中的幸福嘛！
                        </div>
                        <div class="daily-img mt30">
                            <img src="http://gagaprince.oss-cn-beijing.aliyuncs.com/daily/20150819223328.jpg">
                        </div>
                        <div class="daily-duan-frame white-tou mt30">
                            她是十足的游戏控，也是cf战队的核心成员，反正我是不太懂，但每次听她讲怎么打怎么打，我总是听的津津有味！仿佛身临其境，和她并肩作战。
                            我不止一次的想变身成为cf高手，这样就可以在枪法上超过她，因为她说过，她的男票起码在枪法上刚过她~至今我也没有实现这个愿望~也不知道她介意不？
                        </div>
                        <div class="daily-img mt30">
                            <img src="http://gagaprince.oss-cn-beijing.aliyuncs.com/daily/20150819223359.jpg">
                        </div>
                        <div class="daily-duan-frame">
                            她有的时候会文艺范爆棚，写出一些有点做作，又带点煽情，或者有点伤感的东东，再或者信手拈来一段文绉绉的格言，让我实在觉得我TMD就是一个文盲。
                            而我也会对她说过的一些话印象格外深刻！<br>
                            她说：曾经那些让我们委屈到哭泣，难过到无法呼吸的事情，都会随着时间的流逝与增长，变成了心脏壁上最美的花纹，变成了别人嘴里随意可以讲的故事，
                            变成了可以随意刮起的风。<br>
                            她说：理想中的爱情就是这个样子，枯藤老树昏鸦，晚饭有鱼有虾，空调wifi西瓜，夕阳西下，你丑，没事，我瞎！（是不是应验了呢？）<br>
                            她说：我想做你的小太阳，心情好，就暖着你，心情不好，就晒死你！（我好害怕~~~快来暖我哦！）<br>
                            她说：老娘这么好的一个姑娘，可直可弯能屈能伸，卖的了萌，耍的了二，扮得了少女，演的了女王，晒的了下限，红的了脸颊，玩的了小清新，咽得下重口味
                            ，斥退过死皮赖脸的无知少年，躲过了不怀好意的搭讪大叔，你讲笑话可以趴桌大笑，你要玩文艺我仰望天空，得之你幸，失之你命！（我可以说，我是幸运的么？）<br>
                        </div>
                        <div class="daily-img mt30">
                            <img src="http://gagaprince.oss-cn-beijing.aliyuncs.com/daily/20150819223421.jpg">
                        </div>
                        <!--<div class="daily-line mt30"></div>-->

                        <div class="daily-duan-frame">
                            我喜欢她的声音，很萌！她经常说，她没有在卖萌，她是真萌~而我只想对她讲：“宝宝啊，你的萌多少钱一斤，我全包了行不行？今生只为我萌好不的？”<br>
                        </div>
                        <div class="daily-duan-frame">
                            我喜欢听她讲各种有趣的事情，经过她的嘴巴，什么事情都变得那么有意思~可她却老不愿意听我讲我的事情，比如，我给她讲数学方程怎么解，编程服务器挂了怎么办，电脑连不上网点哪里
                            她说：这些事情辣么复杂，你帮我搞定，不然要你何用啊？（宝宝，你不是一般的懒哟！但我就愿意让你这么懒！）
                        </div>
                        <div class="daily-img mt30">
                            <img src="http://gagaprince.oss-cn-beijing.aliyuncs.com/daily/20150819223434.jpg">
                        </div>
                        <div class="daily-duan-frame">
                            我喜欢她在朋友圈提到我，虽然不会直接出现我的名字，但我依然会美美的享受她传递给我的心意~然后默默的发一条朋友圈回复她，我知道她也懂我的心意！自从有了她，我的每一条朋友圈都是她！
                            她说在我之前，她的朋友圈都是发给奶奶的。我要说：今后的朋友圈，你是唯一的女主角！这辈子都是了！
                        </div>

                        <div class="daily-duan-frame">
                            我喜欢她工作的样子，带着玩命的感觉去工作，一个对工作认真的女孩，对什么事情都会很认真。而她认真的样子，很美！
                        </div>
                        <div class="daily-img mt30">
                            <img src="http://gagaprince.oss-cn-beijing.aliyuncs.com/daily/20150819223444.jpg">
                        </div>
                        <div class="daily-duan-frame">
                            她问过我，什么时候开始喜欢上她的，后来又问过我，什么时候喜欢变成了爱！她说，喜欢和爱是不一样的，喜欢是不离开，爱是离不开！喜欢更随意，爱却很深沉！<br>
                            我自己都不知道从什么时候开始喜欢上她的，<br>也许从第一次知道她叫什么名字的时候~<br>也许是第一次在微信中骂她不会自己照顾自己的时候~<br>也许是在她第一次打电话给我的时候！~<br>
                            也许是第一次她在电话中陪我从五道口走回上地的时候~<br>也许是她第一次说那句，奶奶在哪儿，哪儿就是她的全部的时候！<br>我对喜欢的感觉这么迟钝，她一定很嫌弃我！<br>
                            <br>什么时候变成爱的？<br>从那么一天，我不再觉得她高不可攀开始，<br>从那么一天，看到她难过我很心塞开始，<br>从那么一天，她坐别人的车回家，我难过了一晚上开始，<br>从那么一天，
                            她住院了而我却不在她身边开始！
                        </div>
                        <div class="daily-img mt30">
                            <img src="http://gagaprince.oss-cn-beijing.aliyuncs.com/daily/IMG_0517.JPG">
                        </div>
                        <div class="daily-duan-frame">
                            亲爱的，这是我们第一个情人节，我依然不能飞到你身边呵护你，照顾你~不能帮你揉一揉被针扎过的伤痕累累的手，不能将温好的药拿到你身旁，不能亲手为你做爱心便当，
                            不能帮你拿着吊瓶陪你上厕所。<br>
                            亲爱的，我能想到最浪漫的事，每天早晨一睁开眼就看到你，每一顿饭都有你陪伴，在你身后聚精会神的看你打穿越火线，稍微发出点动静，就会被你狠揍一通~教你玩比较费脑子的三国杀
                            我们一起去开黑吧。拿着单反，拉着你到每一个我没去过地方拍照片，把你最美的样子记录下来。还有最好能穿上中学生的校服，陪我补个初恋！<br>
                            亲爱的，不久的将来，我要让你坐在小路虎里面笑，你笑起来的样子最美了~我们要有大大的房子，房子里要有你，那样才是我们的家~我要和你一起孝敬奶奶，你要和我一起孝敬爸爸妈妈，我们要有可爱的孩子，
                            我教他数学，你教她英语！<br>
                            亲爱的，我爱你，我爱你，我爱你！
                        </div>
                        <div class="daily-img mt30">
                            <img src="http://gagaprince.oss-cn-beijing.aliyuncs.com/daily/IMG_1259.JPG">
                        </div>

                        <div class="daily-duan-frame" style="font-size:18px;">
                            愿得你心，白首不离~
                        </div>
                   </div>
                </div>
            </div>


            <div class="daily-img-frame h-c pb30">
                <img src="http://gagaprince.oss-cn-beijing.aliyuncs.com/daily/1.jpg">
            </div>
        </div>
    </body>
    <script type="text/javascript" src="/fe/js/lib/zepto-1.1.4.js"></script>
    <%@include file="../wx/wxsharePage.jsp" %>
    <script src="/fe/js/daily/index.js"></script>
</html>
































