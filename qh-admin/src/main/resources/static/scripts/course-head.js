document.writeln("<div class=\'top\'>");
document.writeln("	<div class=\'col-md-7\'>");
document.writeln("		<img src=\'./images/sjk-gd.png\' />");
document.writeln("		<img src=\'./images/sjk-.png\' />");
document.writeln("	</div>");
document.writeln("	<div class=\'col-md-4 top-right\'>");
document.writeln("		<p class=\'col-md-6\'>{{name}}你好，欢迎登录中师系统</p>");
document.writeln("		<div class=\'col-md-2 admin-logo\'><img src=\'./images/sjk-adm.png\' /></div>");
document.writeln("		<div  class=\'col-md-2\'></div>");
document.writeln("		<p class=\'col-md-2 exit\'>退出</p>");
document.writeln("		");
document.writeln("	</div>");
document.writeln("	</div>");
document.writeln("<div class=\'classify\'>");
document.writeln("	<ul class=\'menu\'>");
document.writeln("   <li class=\'list\'><a href=\'#\'>网课资源管理 </a> ");
document.writeln("      <ul class=\'items\'>");
document.writeln("         <li><a href=\'#\'> 课程资源管理</a></li>");
document.writeln("         <li><a href=\'#\'>课程章管理</a></li>");
document.writeln("         <li><a href=\'#\'> 课程节管理</a></li>");
document.writeln("      </ul>");
document.writeln("   </li>");
document.writeln("   <li class=\'list\'><a href=\'#\'>List 2</a> ");
document.writeln("      <ul class=\'items\'>");
document.writeln("         <li> <a href=\'#\' > Item 2-1 </a></li>");
document.writeln("         <li> <a href=\'#\' > Item 2-2 </a></li>");
document.writeln("         <li> <a href=\'#\' > Item 2-3 </a></li>");
document.writeln("      </ul>");
document.writeln("      </li>");
document.writeln("       <li class=\'list\'><a href=\'#\'>List 2</a> ");
document.writeln("      <ul class=\'items\'>");
document.writeln("         <li> <a href=\'#\' > Item 2-1 </a></li>");
document.writeln("         <li> <a href=\'#\' > Item 2-2 </a></li>");
document.writeln("         <li> <a href=\'#\' > Item 2-3 </a></li>");
document.writeln("      </ul>");
document.writeln("      </li>");
document.writeln("      </ul>");
document.writeln("</div>");


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