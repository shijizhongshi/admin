<div class="top" ng-app="app">
	<div class="col-md-7">
		<img src="./images/sjk-gd.png" />
		<img src="./images/sjk-.png" />
	</div>
	<div class="col-md-4 top-right">
		<p class="col-md-6"> ${username!''}你好，欢迎登录中师系统</p>
		<div class="col-md-2 admin-logo"><img src="./images/sjk-adm.png" /></div>
		<div  class="col-md-2"></div>
		<p class="col-md-2 exit" ><a href="/web/loginout">退出</a></p>
		
	</div>
	 <!--隐藏菜单-->
    <div id="ensconce">         
芝麻开门
    </div>
  <div class="navH">
芝麻关门
   </div>

	</div>
<div class="classify">
	<ul class="menu">
   <li class="list"><a href="#">网课资源管理 </a> 
      <ul class="items">
         <li><a href="#"> 课程资源管理</a></li>
         <li><a href="#">课程章管理</a></li>
         <li><a href="#"> 课程节管理</a></li>
      </ul>
   </li>
   <li class="list"><a href="#">资源章管理</a> 
      <ul class="items">
         <li> <a href="#" > Item 2-1 </a></li>
         <li> <a href="#" > Item 2-2 </a></li>
         <li> <a href="#" > Item 2-3 </a></li>
      </ul>
      </li>
       <li class="list"><a href="#">资源节管理</a> 
      <ul class="items">
         <li> <a href="#" > Item 2-1 </a></li>
         <li> <a href="#" > Item 2-2 </a></li>
         <li> <a href="#" > Item 2-3 </a></li>
      </ul>
      </li>
      </ul>
      
      
       <script type="text/javascript">  
 
var list = document.querySelectorAll('.list');

function accordion(e) {
    e.stopPropagation();
    if (this.classList.contains('active')) {
        this.classList.remove('active');
    } else
    if (this.parentElement.parentElement.classList.contains('active')) {
        this.classList.add('active');
    } else
    {
        for (i = 0; i < list.length; i++) {
            list[i].classList.remove('active');

        }
        this.classList.add('active');
    }
}
for (i = 0; i < list.length; i++) {
    list[i].addEventListener('click', accordion);
}
</script>
</div>
<div id="menu">
   
    <div id="open">
      
        <div class="navBox">
            <ul>
                <li>
               <h2 class="obtain">北京景点 <i class="w"></i></h2>
                    <div class="secondary">
                        <h3>故宫</h3>
                        <h3>十三陵</h3>
                        <h3>圆明园</h3>
                        <h3>长城</h3>
                        <h3>雍和宫</h3>
                        <h3>天坛公园</h3>
                    </div>
                </li>
                <li>
                    <h2 class="obtain">南京景点 <i class="w"></i></h2>
                    <div class="secondary">
                        <h3>栖霞寺</h3>
                        <h3>夫子庙</h3>
                        <h3>海底世界</h3>
                       
                    </div>
                </li>
                <li>
                    <h2 class="obtain">上海景点<i class="w"></i></h2>
                    <div class="secondary">
                        <h3>东方明珠</h3>
                        <h3>外滩</h3>
                        <h3>豫园</h3>
                        <h3>文庙</h3>
                        <h3>世博园</h3>
                        <h3>田子坊</h3>
                    </div>
                </li>
                <li>
                    <h2 class="obtain">深圳景点<i class="w"></i></h2>
                    <div class="secondary">
                        <h3>华侨城</h3>
                        <h3>观澜湖</h3>
                        <h3>世界之窗</h3>
                        <h3>东门老街</h3>
                        <h3>七娘山</h3>
                        <h3>光明农场</h3>
                    </div>
                </li>
                <li>
                    <h2 class="obtain">广州景点<i class="w"></i></h2>
                    <div class="secondary">
                        <h3>白云山</h3>
                        <h3>长隆</h3>
                        <h3>黄花岗公园</h3>
                        <h3>中山纪念堂</h3>
                        <h3>华南植物园</h3>
                        <h3>南沙湿地公园</h3>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</div>
<script src="./scripts/layout/menu.js"></script> <!--控制js-->
