<#import "/layout/header.ftl" as h/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="课程章管理"/>
<link rel="stylesheet" href="./styles/admin.css" />
<script src="./scripts/course.js"></script>
<script src="./scripts/admin.js"></script>
<body>

<#include "/courses/subnav.ftl"/>
<div class="details">
<input type="hidden" value="${courseTypeName}" id="courseTypeName"/>
<input type="hidden" value="${courseSubTypeName}" id="courseSubTypeName"/>
	<div class="details-nav">
		<ul>
			<li><img src="./images/sjk-home.png" style="color: red;"/>我的主页</li>
			<li>/</li>
		<li>课程章节管理</li>

		
		</ul>
	</div>
<div class="details-frame" >
	<div class="details-frame-content">

	<div class="select" style="float:left;margin-right:15px;">搜索课程<br />
	<form id="search">
	<input type="text" name="search" style=" text-indent:2em;"/></form>
	</div>
	</div>
<div class="manage">
	<ul style="height: 80px;" class="show">

			<li  onclick="showDiv()" style="margin-left: 70px;background:#9DE879;"><span class="glyphicon glyphicon-plus"></span>&nbsp;添加章</li>
		<li onclick="showDiv2()" style="background:#F9CD33;"><span class="glyphicon glyphicon-pencil"></span>&nbsp;修改章</li>
		<li  style="background:#F86846;"><span class="glyphicon glyphicon-trash"></span>&nbsp;删除章</li>
		<li><span class="glyphicon glyphicon-sort" class="move-up"></span>&nbsp;上移</li>
		<li><span class="glyphicon glyphicon-sort-by-attributes" class="move-down"></span>&nbsp;下移</li>
         <li style="float: right;margin-right: 100px;background:none;"><img src="./images/sjk-f5.png" name="changyi"/></li>
	</ul>
	<div class="admin-table">

	<ol style="background: #CBD2D8;font-weight: bold;">
		<li>章名称</li>
	<li >主讲老师</li>
	<li >小结数量</li>
	<li>分类用途</li>
	<li>分类类别</li>
	<li>章类别</li>

	</ol>
 <for:each>
 <ol style="box-shadow:0px 1px 1px 0px #B1B1B1 inset;" >
		<li>章名称</li>
	<li >主讲老师</li>
	<li >小结数量</li>
	<li>分类用途</li>
	<li>分类类别</li>
	<li>章类别</li>

	</ol>
	

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
		<div class="poop" id="add">
		<form id="myform">
	<h3>添加章</h3>
	<div class="select">
			<img src="./images/sjk-xl.png"/>
			<select>
				<option disabled selected style='display:none;'>选择专业</option>
				<option></option>
				<option></option>
			</select>
		</div>
		<div class="select">
			<img src="./images/sjk-xl.png"/>
			<select>
				<option  disabled="disabled"  selected  style='display:none;'>专业类型</option>
				<option></option>
				<option></option>
			<img src="./images/sjk-xl.png" />
			</select>
		</div>
		<div class=" select"style="width: 370px;height:53px;border-bottom: 1px solid #F5F6F8;margin-top:3px;">
			<input type="text" placeholder="请输入章名称" style="width: 230px;text-indent: 2em;" />
			
		</div>
		<div class="select"style="width: 405px;height:48px;margin-top:5px;">
			<input type="text"  class=""placeholder="主讲老师" style="text-indent: 2em;margin-right:15px;"/>
		
		</div>
		<p style="clear: both;">
		<div class=" select">
			<img src="./images/sjk-xl.png"/>
			<select>
			<option  disabled selected style='display:none;'>用途分类</option>
				<option></option>
				<option></option>
			</select>
		</div>
		</p>
			<p style="clear: both;">
<div class="select">
			<img src="./images/sjk-xl.png"/>
			<select>
				<option  disabled selected style='display:none;'>课程类别</option>
				<option></option>
				<option></option>
			</select>
		</div></p>
		<p style="clear: both;">
<div class="select">
			<img src="./images/sjk-xl.png"/>
			<select>
				<option  disabled selected style='display:none;'>章类别</option>
				<option></option>
				<option></option>
			</select>
		</div>
		</p>
		<p style="clear: both;">
		<div class="end">
			<input name="git" type="submit" value="提交" style="background:#5ED8A9;"/>
			<input name="esc" type="reset" value="取消"  onclick="CloseDiv();formReset()" class="esc" />
		</div>
			</p>
		</form>
	</div>
		<div class="poop-revise" id="revise" style="width: 460px; position: absolute;left: 30%;" >
		<form id="myform2">
			<h3>修改章</h3>
	<div class="select">
			<img src="./images/sjk-xl.png"/>
			<select>
				<option disabled selected style='display:none;'>选择专业</option>
				<option></option>
				<option></option>
			</select>
		</div>
		<div class="select">
			<img src="./images/sjk-xl.png"/>
			<select>
				<option  disabled="disabled"  selected  style='display:none;'>专业类型</option>
				<option></option>
				<option></option>
			<img src="./images/sjk-xl.png" />
			</select>
		</div>
		<div class=" select"style="width: 370px;height:53px;border-bottom: 1px solid #F5F6F8;margin-top:3px;">
			<input type="text" placeholder="请输入章名称" style="width: 230px;text-indent: 2em;" />
			
		</div>
		<div class="select"style="width: 405px;height:48px;margin-top:5px;">
			<input type="text"  class=""placeholder="主讲老师" style="text-indent: 2em;margin-right:15px;"/>
		
		</div>
		<p style="clear: both;">
		<div class=" select">
			<img src="./images/sjk-xl.png"/>
			<select>
			<option  disabled selected style='display:none;'>自用课程</option>
				<option></option>
				<option></option>
			</select>
		</div>
		</p>
			<p style="clear: both;">
<div class="select">
			<img src="./images/sjk-xl.png"/>
			<select>
				<option  disabled selected style='display:none;'>基础课</option>
				<option></option>
				<option></option>
			</select>
		</div></p>
		<p style="clear: both;">
<div class="select">
			<img src="./images/sjk-xl.png"/>
			<select>
				<option  disabled selected style='display:none;'>第一部分</option>
				<option></option>
				<option></option>
			</select>
		</div>
		</p>
		<p style="clear: both;">
		<div class="end">
			<input name="git" type="submit" value="提交" style="background:#5ED8A9;"/>
			<input name="esc" type="reset" value="取消"  onclick="CloseDiv2();formReset2()" class="esc" />
		</div>
			</p>
			</form>
		</div>

</div>

</div>
</div>
</body>
<style type="text/css">
	.admin-table ol li{width: 16% }
	.admin-table ol li:nth-child(1){width: 20%;}
</style>
 </html>
