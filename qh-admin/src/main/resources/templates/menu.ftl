<div class="top">
	<div class="col-md-7">
		<img src="./images/sjk-gd.png" />
		<img src="./images/sjk-.png" />
	</div>
	<div class="col-md-4 top-right">
		<p class="col-md-6">{{name}}你好，欢迎登录中师系统</p>
		<div class="col-md-2 admin-logo"><img src="./images/sjk-adm.png" /></div>
		<div  class="col-md-2"></div>
		<p class="col-md-2 exit">退出</p>
		
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


