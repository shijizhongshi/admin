<#import "/layout/header.ftl" as h/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="课程管理页面"/>
<link rel="stylesheet" href="./styles/admin.css" />
<script src="./scripts/course.js"></script>
<script src="./scripts/admin.js"></script>
<body>
 <#include "/menu.ftl"/>
<div class="details">
	<div class="details-nav">
		<ul>
			<li><img src="./images/sjk-home.png" style="color: red;"/>我的主页</li>
			<li>/</li>
		<li>课程章节管理</li>

		
		</ul>
	</div>
<div class="details-frame" >
	<div class="details-frame-content">
		<div class=" select" ng-model="title">
			专业选择<br />
					<img src="./images/sjk-xl.png"/>
			<select>
				<option></option>
				<option></option>
				<option></option>
			</select>
	
		</div>
	
		<div class="select">
				专业类型<br />
				<img src="./images/sjk-xl.png"/>
			<select>
				<option></option>
				<option></option>
				<option></option>
			</select>
		</div>
		
		
	<div class="select" style="float:left;" ><input type="button" class="btn-lg im-key" value="立即检索"  style="background:#E9484D"/></div>
	<div class="select" style="float: right;margin-right:15px;">搜索课程<br />
	<form id="search">
	<input type="text" name="search" style=" text-indent:2em;"/></form>
	</div>
	</div>
<div class="manage">
	<ul style="height: 80px;" class="show">

			<li  onclick="showDiv()" style="margin-left: 70px;background:#9DE879;"><span class="glyphicon glyphicon-plus"></span>&nbsp;添加课程</li>
		<li onclick="showDiv2()" style="background:#F9CD33;"><span class="glyphicon glyphicon-pencil"></span>&nbsp;修改课程</li>
		<li  style="background:#F86846;"><span class="glyphicon glyphicon-trash"></span>&nbsp;删除课程</li>
		<li><span class="glyphicon glyphicon-sort" class="move-up"></span>&nbsp;上移</li>
		<li><span class="glyphicon glyphicon-sort-by-attributes" class="move-down"></span>&nbsp;下移</li>
		<li  onclick="showDiv3()"><span class="glyphicon glyphicon-briefcase"></span>&nbsp;资源章节管理</li>
         <li style="float: right;margin-right: 100px;background:none;"><img src="./images/sjk-f5.png" name="changyi"/></li>
	</ul>
	<div class="admin-table">

	<ol style="background: #CBD2D8;font-weight: bold;">
		<li>课程名称</li>
	<li >课程总数</li>
	<li >课程价格</li>
	<li>课程折扣</li>
	<li>年份</li>
	<li>课时数</li>
	<li>是否显示</li>
	<li>资源类别</li>
	<li>章类别</li>
	</ol>
 <for:each>
 <ol style="box-shadow:0px 1px 1px 0px #B1B1B1 inset;" >
		<li class="col-md-2">章名称</li>
	<li class="col-md-1">章总数</li>
	<li class="col-md-1">课程价格</li>
	<li  class="col-md-1">课程折扣</li>
	<li class="col-md-1">年份</li>
	<li  class="col-md-1">课时数</li>
	<li  class="col-md-1">是否显示</li>
	<li  class="col-md-1">资源类别</li>
	<li  class="col-md-1">章类别</li>
	</ol>
	
	<ol >
		<li class="col-md-2">章名称</li>
	<li class="col-md-1">章总数</li>
	<li class="col-md-1">课程价格</li>
	<li  class="col-md-1">课程折扣</li>
	<li class="col-md-1">年份</li>
	<li  class="col-md-1">课时数</li>
	<li  class="col-md-1">是否显示</li>
	<li  class="col-md-1">资源类别</li>
	<li  class="col-md-1">章类别</li>
	</ol>
	<ol >
		<li class="col-md-2">章名称</li>
	<li class="col-md-1">章总数</li>
	<li class="col-md-1">课程价格</li>
	<li  class="col-md-1">课程折扣</li>
	<li class="col-md-1">年份</li>
	<li  class="col-md-1">课时数</li>
	<li  class="col-md-1">是否显示</li>
	<li  class="col-md-1">资源类别</li>
	<li  class="col-md-1">章类别</li>
	</ol>
	<ol >
		<li class="col-md-2">章名称</li>
	<li class="col-md-1">章总数</li>
	<li class="col-md-1">课程价格</li>
	<li  class="col-md-1">课程折扣</li>
	<li class="col-md-1">年份</li>
	<li  class="col-md-1">课时数</li>
	<li  class="col-md-1">是否显示</li>
	<li  class="col-md-1">资源类别</li>
	<li  class="col-md-1">章类别</li>
	</ol>
	<ol>
		<li class="col-md-2">章名称</li>
	<li class="col-md-1">章总数</li>
	<li class="col-md-1">课程价格</li>
	<li  class="col-md-1">课程折扣</li>
	<li class="col-md-1">年份</li>
	<li  class="col-md-1">课时数</li>
	<li  class="col-md-1">是否显示</li>
	<li  class="col-md-1">资源类别</li>
	<li  class="col-md-1">章类别</li>
	</ol>
	<ol >
		<li class="col-md-2">章名称</li>
	<li class="col-md-1">章总数</li>
	<li class="col-md-1">课程价格</li>
	<li  class="col-md-1">课程折扣</li>
	<li class="col-md-1">年份</li>
	<li  class="col-md-1">课时数</li>
	<li  class="col-md-1">是否显示</li>
	<li  class="col-md-1">资源类别</li>
	<li  class="col-md-1">章类别</li>
	</ol>
	<ol >
		<li class="col-md-2">章名称</li>
	<li class="col-md-1">章总数</li>
	<li class="col-md-1">课程价格</li>
	<li  class="col-md-1">课程折扣</li>
	<li class="col-md-1">年份</li>
	<li  class="col-md-1">课时数</li>
	<li  class="col-md-1">是否显示</li>
	<li  class="col-md-1">资源类别</li>
	<li  class="col-md-1">章类别</li>
	</ol>
	<ol >
		<li class="col-md-2">章名称</li>
	<li class="col-md-1">章总数</li>
	<li class="col-md-1">课程价格</li>
	<li  class="col-md-1">课程折扣</li>
	<li class="col-md-1">年份</li>
	<li  class="col-md-1">课时数</li>
	<li  class="col-md-1">是否显示</li>
	<li  class="col-md-1">资源类别</li>
	<li  class="col-md-1">章类别</li>
	</ol>
	<ol >
		<li class="col-md-2">章名称</li>
	<li class="col-md-1">章总数</li>
	<li class="col-md-1">课程价格</li>
	<li  class="col-md-1">课程折扣</li>
	<li class="col-md-1">年份</li>
	<li  class="col-md-1">课时数</li>
	<li  class="col-md-1">是否显示</li>
	<li  class="col-md-1">资源类别</li>
	<li  class="col-md-1">章类别</li>
	</ol>
	<ol >
		<li class="col-md-2">章名称</li>
	<li class="col-md-1">章总数</li>
	<li class="col-md-1">课程价格</li>
	<li  class="col-md-1">课程折扣</li>
	<li class="col-md-1">年份</li>
	<li  class="col-md-1">课时数</li>
	<li  class="col-md-1">是否显示</li>
	<li  class="col-md-1">资源类别</li>
	<li  class="col-md-1">章类别</li>
	</ol>
	<ol >
		<li class="col-md-2">章名称</li>
	<li class="col-md-1">章总数</li>
	<li class="col-md-1">课程价格</li>
	<li  class="col-md-1">课程折扣</li>
	<li class="col-md-1">年份</li>
	<li  class="col-md-1">课时数</li>
	<li  class="col-md-1">是否显示</li>
	<li  class="col-md-1">资源类别</li>
	<li  class="col-md-1">章类别</li>
	</ol>
	<ol >
		<li class="col-md-2">章名称</li>
	<li class="col-md-1">章总数</li>
	<li class="col-md-1">课程价格</li>
	<li  class="col-md-1">课程折扣</li>
	<li class="col-md-1">年份</li>
	<li  class="col-md-1">课时数</li>
	<li  class="col-md-1">是否显示</li>
	<li  class="col-md-1">资源类别</li>
	<li  class="col-md-1">章类别</li>
	</ol>
	<ol >
		<li class="col-md-2">章名称</li>
	<li class="col-md-1">章总数</li>
	<li class="col-md-1">课程价格</li>
	<li  class="col-md-1">课程折扣</li>
	<li class="col-md-1">年份</li>
	<li  class="col-md-1">课时数</li>
	<li  class="col-md-1">是否显示</li>
	<li  class="col-md-1">资源类别</li>
	<li  class="col-md-1">章类别</li>
	</ol>
	
	<ol >
		<li class="col-md-2">章名称</li>
	<li class="col-md-1">章总数</li>
	<li class="col-md-1">课程价格</li>
	<li  class="col-md-1">课程折扣</li>
	<li class="col-md-1">年份</li>
	<li  class="col-md-1">课时数</li>
	<li  class="col-md-1">是否显示</li>
	<li  class="col-md-1">资源类别</li>
	<li  class="col-md-1">章类别</li>
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
	<h3>添加课程</h3>
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
			<input type="text" placeholder="请输入课程名称" style="width: 230px;text-indent: 2em;" />
			<input type="button" value="上传课程图片" style="background: #FDE490;float: right;width:122px;"/>
		</div>
		<div class="centre-border">
		<div class="select"style="width: 405px;height:48px;margin-top:5px;">
			<input type="text"  class=""placeholder="输入课程价格" style="width:130px ;text-indent: 2em;margin-right:15px;"/>
			<input type="text" placeholder="输入折扣价格" style="width:130px ;text-indent: 2em;"/>
		</div>
		<div class=" select"style="width: 405px;">
		<input type="text" placeholder="输入课程年份" style="width:130px ;text-indent: 2em;"/></div>
		<div class=" select" style="margin-right:15px;">
			<img src="./images/sjk-xl.png"/>
			<select>
			<option  disabled selected style='display:none;'>是否显示</option>
				<option>是</option>
				<option>否</option>
			</select>
		</div>
		<div class=" select">
			<img src="./images/sjk-xl.png"/>
			<select>
			<option  disabled selected style='display:none;'>是否精品</option>
				<option>是</option>
				<option>否</option>
			</select>
		</div>
		</div>
	
<div class="select">
			<img src="./images/sjk-xl.png"/>
			<select>
				<option  disabled selected style='display:none;'>课程资源类别</option>
				<option></option>
				<option></option>
			</select>
		</div>

			<div class="select">
			<img src="./images/sjk-xl.png"/>
			<select>
				<option  disabled selected style='display:none;'>课程用途分类</option>
				<option></option>
				<option></option>
			</select>
		</div>
		<div class="end">
			<input name="git" type="submit" value="提交" style="background:#5ED8A9;"/>
			<input name="esc" type="reset" value="取消"  onclick="CloseDiv();formReset()" class="esc" />
		</div>
		</form>
	</div>
		<div class="poop-revise" id="revise"  >
		<form id="myform2">
			<div class="revise-left">
	<h3>添加课程</h3>
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
			<input type="text" class=""placeholder="请输入课程名称" style="width: 230px;text-indent: 2em;" />
			<input type="button" value="上传课程图片" style="background: #FDE490;float: right;width:122px;"/>
		</div>
		<div class="centre-border">
		<div class="select"style="width: 405px;height:48px;margin-top:5px;">
			<input type="text"  class=""placeholder="输入课程价格" style="width:130px ;text-indent: 2em;margin-right:15px;"/>
			<input type="text" placeholder="输入折扣价格" style="width:130px ;text-indent: 2em;"/>
		</div>
		<div class=" select"style="width: 405px;">
		<input type="text" placeholder="输入课程年份" style="width:130px ;text-indent: 2em;"/></div>
		<div class=" select" style="margin-right:15px;">
			<img src="./images/sjk-xl.png"/>
			<select>
			<option  disabled selected style='display:none;'>是否显示</option>
				<option>是</option>
				<option>否</option>
			</select>
		</div>
		<div class=" select">
			<img src="./images/sjk-xl.png"/>
			<select>
			<option  disabled selected style='display:none;'>是否精品</option>
				<option>是</option>
				<option>否</option>
			</select>
		</div>
		</div>
	
<div class="select">
			<img src="./images/sjk-xl.png"/>
			<select>
				<option  disabled selected style='display:none;'>课程资源类别</option>
				<option></option>
				<option></option>
			</select>
		</div>

			<div class="select">
			<img src="./images/sjk-xl.png"/>
			<select>
				<option  disabled selected style='display:none;'>课程用途分类</option>
				<option></option>
				<option></option>
			</select>
		</div>
		<div class="end">
			<input name="git" type="submit" value="提交" style="background:#5ED8A9;"/>
			<input name="esc" type="reset" value="取消"  onclick="CloseDiv2();formReset2()" class="esc" />
			</div>
</div>			
<div class="revise-right">
				<h3>显示章节</h3>
				<div class="hidden-scoll" >
				<ul class="section"><form>
					<li><label for="chapter">传染病、性传播疾病冲刺课 </label> <input name="chapter" type="checkbox" id="chapter" /></li>
					<li><label for="chapter2">传染病 </label> <input name="chapter" type="checkbox" id="chapter2" /></li>
					<li><label for="chapter3">传染病、性传播疾病冲刺课 </label> <input name="chapter" type="checkbox" id="chapter3" /></li>
					<li><label for="chapter4">传染病、性传播疾病冲刺课 </label> <input name="chapter" type="checkbox" id="chapter4" /></li>
					<li><label for="chapter5">传染病、性传播疾病冲刺课 </label> <input name="chapter" type="checkbox" id="chapter5" /></li>
					<li><label for="chapter6">传染病、性传播疾病冲刺课 </label> <input name="chapter" type="checkbox" id="chapter6" /></li>
					<li><label for="chapter7">传染病、性传播疾病冲刺课 </label> <input name="chapter" type="checkbox" id="chapter7" /></li>
					<li><label for="chapter8">传染病、性传播疾病冲刺课 </label> <input name="chapter" type="checkbox" id="chapter8" /></li>
					<li><label for="chapter9">传染病、性传播疾病冲刺课 </label> <input name="chapter" type="checkbox" id="chapter9" /></li>
					<li><label for="chapter10">传染病、性传播疾病冲刺课 </label> <input name="chapter" type="checkbox" id="chapter10" /></li>
					<li><label for="chapter11">传染病、性传播疾病冲刺课 </label> <input name="chapter" type="checkbox" id="chapter11" /></li>
					<li><label for="chapter12">传染病、性传播疾病冲刺课 </label> <input name="chapter" type="checkbox" id="chapter12" /></li>
					<li><label for="chapter13">传染病、性传播疾病冲刺课 </label> <input name="chapter" type="checkbox" id="chapter13" /></li>
					<li><label for="chapter14">传染病、性传播疾病冲刺课 </label> <input name="chapter" type="checkbox" id="chapter14" /></li>
				</form></ul></div>
			</div>
			</form>
		</div>
<div class="resource" id="resource">
	<h3>资源管理器</h3>
	<p>所属专业：临床执业助理医师</p>
	<p>课程资源名：2018临床-基础课程</p>
	<div style="width:700px;height: 340px;border-radius: 20px;overflow: hidden;border: 1px solid #B1B1B1;">
	<form id="myform3">
	<div class="resource-table">
		<ul style="background: #CBD2D8;font-weight: bold;">
			<li>章名称</li>
			<li>主讲老师</li>
			<li>小结数量</li>
			<li>用途分类</li>
			<li>是否显示</li>
		</ul>
		<ul>
	<li>章节名称</li>	
	<li>主讲老师</li>
	<li>5</li>
	<li>自用课程</li>
	<li><input  type="radio"> </li>
		</ul>
		<ul>
	<li>章节名称</li>	
	<li>主讲老师</li>
	<li>5</li>
	<li>自用课程</li>
	<li><input type="radio"> </li>
		</ul>
		<ul>
	<li>章节名称</li>	
	<li>主讲老师</li>
	<li>5</li>
	<li>自用课程</li>
	<li><input type="radio"> </li>
		</ul>
		<ul>
	<li>章节名称</li>	
	<li>主讲老师</li>
	<li>5</li>
	<li>自用课程</li>
	<li><input  type="radio"> </li>
		</ul>
		<ul>
	<li>章节名称</li>	
	<li>主讲老师</li>
	<li>5</li>
	<li>自用课程</li>
	<li><input  type="radio"> </li>
		</ul>
			<ul>
	<li>章节名称</li>	
	<li>主讲老师</li>
	<li>5</li>
	<li>自用课程</li>
	<li><input  type="radio"> </li>
		</ul>
				<ul>
	<li>章节名称</li>	
	<li>主讲老师</li>
	<li>5</li>
	<li>自用课程</li>
	<li><input type="radio"> </li>
		</ul>
				<ul>
	<li>章节名称</li>	
	<li>主讲老师</li>
	<li>5</li>
	<li>自用课程</li>
	<li><input type="radio"> </li>
		</ul>
				<ul>
	<li>章节名称</li>	
	<li>主讲老师</li>
	<li>5</li>
	<li>自用课程</li>
	<li><input  type="radio"> </li>
		</ul>
				<ul>
	<li>章节名称</li>	
	<li>主讲老师</li>
	<li>5</li>
	<li>自用课程</li>
	<li><input  type="radio"> </li>
		</ul>
				<ul>
	<li>章节名称</li>	
	<li>主讲老师</li>
	<li>5</li>
	<li>自用课程</li>
	<li><input  type="radio"> </li>
		</ul>
				<ul>
	<li>章节名称</li>	
	<li>主讲老师</li>
	<li>5</li>
	<li>自用课程</li>
	<li><input  type="radio"> </li>
		</ul>
				<ul>
	<li>章节名称</li>	
	<li>主讲老师</li>
	<li>5</li>
	<li>自用课程</li>
	<li><input  type="radio"> </li>
		</ul>		<ul>
	<li>章节名称</li>	
	<li>主讲老师</li>
	<li>5</li>
	<li>自用课程</li>
	<li><input  type="radio"> </li>
		</ul>
				<ul>
	<li>章节名称</li>	
	<li>主讲老师</li>
	<li>5</li>
	<li>自用课程</li>
	<li><input  type="radio"> </li>
		</ul>
				<ul>
	<li>章节名称</li>	
	<li>主讲老师</li>
	<li>5</li>
	<li>自用课程</li>
	<li><input  type="radio"> </li>
		</ul>
				<ul>
	<li>章节名称</li>	
	<li>主讲老师</li>
	<li>5</li>
	<li>自用课程</li>
	<li><input type="radio"> </li>
		</ul>	
	</div>
	</form>
	</div>
	<div class="end">
			<input name="git" type="submit" value="提交" style="background:#5ED8A9;"/>
			<input name="esc" type="reset" value="取消"  onclick="CloseDiv3();formReset3()" class="esc" />
		</div>
</div>
	
</div>

</div>
</div>
</body>
 
 </html>
