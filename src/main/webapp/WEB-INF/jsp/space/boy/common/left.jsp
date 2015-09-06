<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    <c:forEach var="dailyItem" items="${LatestDailys}" varStatus="status">
    <li><a href="detail?id=${dailyItem.id}">${dailyItem.title}</a></li>
    </c:forEach>
    </ul>
    </div>
    <div class="viny">
    <ul>
    <dl>
    <dt class="art"><img src="http://gagaprince.oss-cn-beijing.aliyuncs.com/lovespace/boy/artwork.png" alt="专辑"></dt>
    <dd class="icon-song"><span></span>${song.name}</dd>
    <dd class="icon-artist"><span></span>歌手：${song.singer}</dd>
    <dd class="icon-album"><span></span>专辑：${song.album}</dd>
    <dd class="music">
    <audio src="${song.src}" autoplay="autoplay" loop="loop" controls></audio>
    </dd>
    <!--也可以添加loop属性 音频加载到末尾时，会重新播放-->
    </dl>
    </ul>
    </div>
    </div>


