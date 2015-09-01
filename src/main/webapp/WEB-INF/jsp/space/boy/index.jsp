<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>gaga的技术小屋</title>
<meta name="Keywords" content="" >
<meta name="Description" content="" >
<link href="/fe/css/space/index.css" rel="stylesheet">
<!--[if lt IE 9]>
<script src="/fe/js/space/modernizr.js"></script>
<![endif]-->
</head>
<body>
<header>
  <h1><a>青涩の初夏</a></h1>
  <p>趁我们都还年轻,多欣赏下沿途的风景，不要错过了流年里温暖的人和物....</p>
  <p>什么？这不像一个技术博客？对，我本就没想把它做成技术博客....</p>
</header>
<!--nav begin-->
<div id="nav">
  <ul>
    <li><a href="/">首页</a></li>
    <li><a href="#">关于我</a></li>
    <li><a href="#">慢生活</a></li>
    <li><a href="#">技术人生</a></li>
    <!-- <li><a href="#">留言板</a></li> -->
  </ul>
</div>
<!--nav end-->
<div class="blank"></div>
<div class="banner">
  <ul class="boy_girl">
    <li class="boyimg"><a href="#aboutMe"><span>关于我</span></a></li>
    <li class="girlimg"><a href="#aboutHer"><span>关于她</span></a></li>
  </ul>
  <ul class="texts">
    <p><img src="http://gagaprince.oss-cn-beijing.aliyuncs.com/lovespace/boy/t-1.png" alt="人生，是一场盛大的遇见"></p>
    <p><img src="http://gagaprince.oss-cn-beijing.aliyuncs.com/lovespace/boy/t-2.png" alt="若你懂得，就请珍惜。"></p>
    <p><img src="http://gagaprince.oss-cn-beijing.aliyuncs.com/lovespace/boy/t-3.png" alt="无论下多久的雨，最后都会有彩虹；无论你多么悲伤，要相信幸福在前方等候"></p>
  </ul>
</div>
<div class="blank"></div>
<div class="memorial_day">
  <div class="time_axis"></div>
  <ul>
    <li class="n1"><a href="/">一只瓶子</a>
      <div class="dateview">2013-xx-xx</div>
    </li>
    <li class="n2"><a href="/">真心话与大冒险</a>
      <div class="dateview">2015-05-30</div>
    </li>
    <!-- <li class="n3"><a href="/"></a>
      <div class="dateview">2012-10-01</div>
    </li>
    <li class="n4"><a href="/">相恋</a>
      <div class="dateview">2013-02-14</div>
    </li>
    <li class="n5"><a href="/">相爱</a>
      <div class="dateview">2014-10-05</div>
    </li> -->
  </ul>
</div>
<div class="blank"></div>
<article>
  <div class="l_box">
    <div class="about_me" id="aboutMe">
      <h2>关于我</h2>
      <ul>
        <img src="http://gagaprince.oss-cn-beijing.aliyuncs.com/lovespace/boy/IMG_1237.JPG">
        <p>网名：gagaprince | 小路虎</p>
        <p>主页：www.gagalulu.wang</p>
        <p>职业：去哪儿网前端工程师</p>
        <p>未来：与爱的人去哪儿都可以</p>
      </ul>
    </div>
    <div class="about_he" id="aboutHer">
      <h2>关于她</h2><ul>
        <img src="http://gagaprince.oss-cn-beijing.aliyuncs.com/lovespace/boy/IMG_0517.JPG">
        <p>网名：拾光独白 | AY</p>
        <p>主页：人家又不是程序媛</p>
        <p>职业：反正不在去哪儿网</p>
        <p>未来：老娘要在北京买房</p>
      </ul>
      
    </div>
    <div class="newslist">
      <h2>最新日志</h2>
      <ul>
        <li><a href="#"> 一个简单的游戏</a></li>
        <li><a href="#"> 做个保卫萝卜玩一玩</a></li>
        <li><a href="#"> 教你使用游戏引擎</a></li>
        <li><a href="#"> 第一个电子书软件</a></li>
      </ul>
    </div>
    <div class="viny">
      <ul>
        <dl>
          <dt class="art"><img src="http://gagaprince.oss-cn-beijing.aliyuncs.com/lovespace/boy/artwork.png" alt="专辑"></dt>
          <dd class="icon-song"><span></span>给我的快乐</dd>
          <dd class="icon-artist"><span></span>歌手：胡夏</dd>
          <dd class="icon-album"><span></span>专辑：《神犬小七》</dd>
          <dd class="music">
            <audio src="http://cc2.qq190.com/Musics/2015/2015-08/33/qq190.com_1303_11592412.mp3" autoplay="autoplay" loop="loop" controls></audio>
          </dd>
          <!--也可以添加loop属性 音频加载到末尾时，会重新播放-->
        </dl>
      </ul>
    </div>
  </div>
  <!--l_box end -->
  <div class="r_box">
    <li> <a href="#"><img src="http://gagaprince.oss-cn-beijing.aliyuncs.com/lovespace/boy/01.jpg"></a>
      <h3><a href="#">你是什么人便会遇上什么人</a></h3>
      <p>有时就为了一句狠话，像心头一口毒钉，永远麻痺着亲密感情交流。恶言，真要慎出，平日多誠心爱语，乃最简易之佈施。</p>
    </li>
    <li> <a href="#"><img src="http://gagaprince.oss-cn-beijing.aliyuncs.com/lovespace/boy/02.jpg"></a>
      <h3><a href="#">爱情没有永远，地老天荒也走不完</a></h3>
      <p>也许，爱情没有永远，地老天荒也走不完，生命终结的末端，苦短情长。站在岁月的边端，那些美丽的定格，心伤的绝恋，都被四季的掩埋，一去不返。徒剩下这荒芜的花好月圆，一路相随，流离天涯背负了谁的思念？</p>
    </li>
    <li> <a href="#"><img src="http://gagaprince.oss-cn-beijing.aliyuncs.com/lovespace/boy/03.jpg"></a>
      <h3><a href="#">女孩都有浪漫的小情怀――浪漫的求婚词</a></h3>
      <p>还在为浪漫的求婚词而烦恼不知道该怎么说吗？女孩子都有着浪漫的小情怀，对于求婚更是抱着满满的浪漫期待，也希望在求婚那一天对方可以给自己一个最浪漫的求婚词。</p>
    </li>
    <li> <a href="#"><img src="http://gagaprince.oss-cn-beijing.aliyuncs.com/lovespace/boy/04.jpg"></a>
      <h3><a href="#">擦肩而过</a></h3>
      <p>《擦肩而过》文/清河鱼 编绘/天朝羽打开一扇窗，我不曾把你想得平常。看季节一一过往。你停留的那个地方，是否依然花儿开放？在夜里守靠着梦中的，想那仿佛前世铭刻进心肠的</p>
    </li>
    <li> <a href="#"><img src="http://gagaprince.oss-cn-beijing.aliyuncs.com/lovespace/boy/01.jpg"></a>
      <h3><a href="#">擦肩而过</a></h3>
      <p>《擦肩而过》文/清河鱼 编绘/天朝羽打开一扇窗，我不曾把你想得平常。看季节一一过往。你停留的那个地方，是否依然花儿开放？在夜里守靠着梦中的，想那仿佛前世铭刻进心肠的</p>
    </li>
  </div>
</article>
</body>
</html>

