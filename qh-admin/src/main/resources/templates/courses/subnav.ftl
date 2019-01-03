<#import "/layout/header.ftl" as h/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="后台管理"/>
<style type="text/css">
.classify{width:100%; background:#FFFFFF;}
.classify ul.menu .list{background:#FFFFFF;border-bottom:none;font-size:18px;}
.classify ul.menu .list a:hover{background:#7489A2;color:#FFFFFF;}
.classify ul.menu .active > .items {background:#FFFFFF;border-bottom:1px solid #939090;}
.classify ul.menu .list a{color:black}
*:after{color:#E5B70D;}
*:before{color:#E5B70D;}
ul.menu .active > .items li{border:none;}
</style>
<body>
<div class="classify">
	<ul class="menu">
   <li class="list"><a href="#">医师资格 </a> 
      <ul class="items">
         <li><a href="/course.ftl" target="main"> 临床(执业)助理医师</a></li>
         <li><a>中西医(执业)助理医师</a></li>
         <li><a href="#"> 中西医(执业)助理医师</a></li>
          <li><a href="#"> 乡村全科助理医师</a></li>
           <li><a href="#"> 中医基础理论</a></li>
      </ul>
   </li>
   <li class="list"><a href="#">药师资格</a> 
      <ul class="items">
         <li> <a href="#" > 执业中药师 </a></li>
         <li> <a href="#" > 执业西药师</a></li>
      </ul>
      </li>
       <li class="list"><a href="#">中医基础理论</a> 
      <ul class="items">
         <li> <a href="#" > 中医适宜技术 </a></li>
      </ul>
      </li>
       <li class="list"><a href="#">卫生资格</a> 
      <ul class="items">
         <li> <a href="#" > 主管护师资格 </a></li>
         <li> <a href="#" > 护士执业资格 </a></li>
         <li> <a href="#" > 初级护师 </a></li>
         <li> <a href="#" > 主治医师 </a></li>
      </ul>
      </li>
      <li class="list"><a href="#">健康管理师</a> 
      <ul class="items">
         <li> <a href="#" > 健康管理师 </a></li>
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
</body>

 </html>
