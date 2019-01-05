<#import "/layout/header.ftl" as h/>
<#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="课程管理页面"/>
<link rel="stylesheet" href="/styles/admin.css" />
<script src="/scripts/course/course.js"></script>
<script src="/scripts/admin.js"></script>
<@b.body menu="sidebarmenu-system-teacher" submenu="/web/system/teacher">

<div class="details" style="width: 100%;">
	<div class="details-nav">
		<ul>
			<li><img src="/images/sjk-home.png" style="color: red;"/>我的主页</li>
			<li>/</li>
		<li>教师管理</li>
		</ul>
	</div>
<div class="details-frame" >
	<div class="details-frame-content">

	<div class="select-2" style="float:left;margin-right:15px;width: 15%;">
	<span>教师名称</span>
	<form id="search">
	<input type="text" placeholder="请输入教师名称"/></form>
	</div>
<div><input type="button" class="btn-lg im-key" ng-click="loaddata()" value="立即检索" ng-click="search()"  style="background:#E9484D"/></div>	
	</div>
<div class="manage">
	<ul style="height: 80px;" class="show">

			<li  onclick="showDiv()" style="margin-left: 70px;background:#9DE879;"><span class="glyphicon glyphicon-plus"></span>&nbsp;添加教师</li>
		<li  style="background:#F9CD33;"><span class="glyphicon glyphicon-pencil"></span>&nbsp;修改教师</li>
		<li  style="background:#F86846;"><span class="glyphicon glyphicon-trash"></span>&nbsp;删除</li>
         <li><span class="glyphicon glyphicon-sort" class="move-up"></span>&nbsp;上移</li>
		<li><span class="glyphicon glyphicon-sort-by-attributes" class="move-down"></span>&nbsp;下移</li>
         <li style="float: right;margin-right: 100px;background:none;"><img src="/images/sjk-f5.png" name="changyi"/></li>
	</ul>
	<div class="admin-table">

<table>
	<tr>
	<th>教师图片</th>
	<th >教师名称</th>
	<th >讲授课程</th >
	<th >是否显示</th >
	</tr>
 <for:each>
 <tr >
	<th>教师图片</th>
	<th >教师名称</th>
	<th >讲授课程</th >
	<th >是</th >
	</tr>
	</table>

	</div>
<div class="fanye">
	<ul class="pagination">
		<li ><a href="#">&laquo;</a></li>
		<li class="active"><a href="#">1</a></li>
		<li><a href="#">2</a></li>
		<li><a href="#">3</a></li>
		<li><a href="#">4</a></li>
		<li><a href="#">5</a></li>
		<li><a href="#">&raquo;</a></li>
	</ul>
</div>

	<!--弹窗-->
			<div class="poop" id="add" style="width:60%;height: 600px;position: absolute;left: 15%;top: 5%;" >
				<form id="myform">
	<h3>添加教师</h3>
		<div class="template-add">
			<div class="template-left" style="padding-right: 5%;">
						<div class=" select-2">
						<span>教师姓名：</span>
			<input type="text" class=""placeholder="请输入教师名称" style="width: 230px;" />
		</div>
<div  class="select-radio ">
		<ul><li>是否显示</li>  <li><input type="radio"/> 显示</li> 
		<li><input type="radio" />不显示</li></ul>
		</div>
		<div  class="select-radio ">
		<ul><li>是否推荐</li>  <li><input type="radio"/> 是</li> 
		<li><input type="radio" />否</li></ul>
		</div>
			<div class="costs-uploadfile-div">   
				 <input type="file" name="file" id="fileField"  onchange="document.getElementById('textfield').value=this.value"  accept="image/*" /> 
    <input type='text' id="textfield" style="border: solid 1px #B1B1B1;" /> 
    <button class="allBtn costs-marl15">老师图片</button>
    <div style="height:130px;width:35%;border: solid 1px #B1B1B1;margin-top:3px;">
    <img  /></div>
        </div>
<p>讲授课程</p>




	</div>
		
		
		<div class="template-right">
			<div  class="grade-text">
				<span>适宜人群</span>
			    <textarea></textarea>
			</div>
			<div  class="grade-text">
				<span>班级承诺</span>
		    <textarea></textarea>
			</div>
			<div  class="grade-text">
				<span>班级特色</span>
		    <textarea></textarea>
			</div>
			<div  class="grade-text">
				<span>班级介绍</span>
				    <textarea></textarea>
			</div>
		</div>


		</form>
	
		<div class="end">
			<input name="git" type="submit" value="提交" style="background:#5ED8A9;"/>
			<input name="esc" type="reset" value="取消"  onclick="CloseDiv();formReset()" class="esc" />
		</div>
		





		
	
	 
</div>
	
</div>

</div>

</body>
 <style type="text/css">
	
		.poop{overflow-y: scroll;}
	.poop span{font-size: 1.5rem;}
	.template-add{width: 100%;border-top:1px solid #F5F6F8;height: 80%;padding-top: 10px;margin-top:10px ;}
	.template-left,.template-right{width: 50%;float: left;height: 600px;overflow:auto;}
	.template-left ul{height: 50px;line-height: 50px; font-size: 2rem;}
	.template-left ul li{float: left;margin-right: 5px;}
	.template-left ul li:nth-child(1){margin-right: 10px;}
	.template-right .grade-text{width: 80%;}
	.template-right .grade-text textarea{width: 100%;height: 110px;font-size:1.5rem}
    position:relative;
}
div.costs-uploadfile-div #textfield{
    width:40%;
    height:30px;    
}
div.costs-uploadfile-div #fileField{
    width:100%;
    height:30px;    
    position: absolute;
    top: 0;
    left:0;
    filter: alpha(opacity:0);
    opacity: 0;
}
div.costs-uploadfile-div .allBtn{
    padding:0;
    margin:0;
    height: 30px;
    line-height: 30px;
    width: 35%;
    background-color: #18b3cf;
    border: none;
    color: #fff;
    }
	
</style>
</@b.body>
 </html>
