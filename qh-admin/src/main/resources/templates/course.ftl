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
			<select>
				<option></option>
				<option></option>
				<option></option>
			</select>
		</div>
		
		<div class="select">
		
			专业类型<br />
			<select>
				<option></option>
				<option></option>
				<option></option>
			</select>
		</div>
		
		
	<div class="select" style="float:left;" ><input type="button" class="btn-lg im-key" value="立即检索"  style="background:#E9484D"/></div>
	<div class="select" style="float: right;margin-right:15px;">搜索课程<br />
	<input type="text" name="sousuo" style=" text-indent:2em;"/>
	</div>
	</div>
<div class="manage">
	<ul style="height: 80px;">

			<li  onclick="showDiv()"><img src="./images/sjk-add.png"/></li>
	
		<li onclick="showDiv2()" ><img src="./images/sjk-xiugai.png" /></li>
		<li><img src="./images/sjk-delete.png" name="shanchu"/></li>
		<li><img src="./images/sjk-up.png" name="changyi"/></li>
		<li><img src="./images/sjk-down.png" name="changyi"/></li>
		<li><img src="./images/guanli.png" name="guanli"/></li>
         <li style="float: right;margin-right: 100px;"><img src="./images/sjk-f5.png" name="changyi"/></li>
	</ul>
	<div class="admin-table">

	<ol style="background: #CBD2D8;font-weight: bold;">
		<li class="col-md-2">课程名称</li>
	<li class="col-md-1">课程总数</li>
	<li class="col-md-1">课程价格</li>
	<li  class="col-md-1">课程折扣</li>
	<li class="col-md-1">年份</li>
	<li  class="col-md-1">课时数</li>
	<li  class="col-md-1">是否显示</li>
	<li  class="col-md-1">资源类别</li>
	<li  class="col-md-1">章类别</li>
	</ol>
 <for:each>
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
		
			专业类型<br />
			<select>
				<option></option>
				<option></option>
				<option></option>
			</select>
		</div>
		<div class="select">
		
			专业类型<br />
			<select>
				<option></option>
				<option></option>
				<option></option>
			</select>
		</div>
		<div class=" select"style="width: 425px;height:53px;border-bottom: 1px solid #F5F6F8;">
			<input type="text" name="majior" class=""placeholder="请输入课程名称" style="width: 230px;text-indent: 2em;" />
			<input type="button" value="上传课程图片" style="background: #FDE490;float: right;"/>
		</div>
		<div class=" select"style="width: 425px;height:48px;margin-top: 10px;">
			<input type="text" name="majior" class=""placeholder="输入课程价格" style="width:120px ;text-indent: 2em;"/>
			<input type="text" placeholder="输入折扣价格" style="width:120px ;text-indent: 2em;"/>
		</div>
		<div class=" select"style="width: 425px;height:43px;margin-top: 10px;">
		<input type="text" placeholder="输入课程年份" style="width:120px ;text-indent: 2em;"/></div>
		<div class=" select" style="height:66px;width:150px">
			是否显示<br />
			<select style="width:120px ;" >
			<option></option>
				<option>是</option>
				<option>否</option>
			</select>
		</div>
		<div class=" select" style="height:66px;">
			是否精品<br />
			<select style="width:120px ;">
			<option></option>
				<option>是</option>
				<option>否</option>
			</select>
		</div>
		<p style="float: left;width: 426px;height: 1px;background:#F5F6F8;"></p>
<div class="select">
		
			课程资源类别<br />
			<select>
				<option></option>
				<option></option>
				<option></option>
			</select>
		</div>

			<div class="select">
		
			课程用途分类<br />
			<select>
				<option></option>
				<option></option>
				<option></option>
			</select>
		</div>
		<div class="end">
			<input name="git" type="submit" value="提交" style="background:#5ED8A9;"/>
			<input name="esc" type="reset" value="取消"  onclick="CloseDiv();formReset()" class="esc" />
			</form>
		</div>
	
		<div class="poop-revise" id="revise"  >
		<form id="myform2">
			<div class="revise-left">
	<h3>添加课程</h3>
	<div class="select">
		
			专业类型<br />
			<select>
				<option></option>
				<option></option>
				<option></option>
			</select>
		</div>
		<div class="select">
		
			专业类型<br />
			<select>
				<option></option>
				<option></option>
				<option></option>
			</select>
		</div>
		<div class=" select"style="width: 405px;height:53px;border-bottom: 1px solid #F5F6F8;">
			<input type="text" name="majior" class=""placeholder="请输入课程名称" style="width: 210px;text-indent: 2em;" />
			<input type="button" value="上传课程图片" style="background: #FDE490;float: right;"/>
		</div>
		<div class=" select"style="width: 405px;height:48px;margin-top: 10px;">
			<input type="text" name="majior" class=""placeholder="输入课程价格" style="width:120px ;text-indent: 2em;"/>
			<input type="text" placeholder="输入折扣价格" style="width:120px ;text-indent: 2em;"/>
		</div>
		<div class=" select"style="width: 405px;height:43px;margin-top: 10px;">
		<input type="text" placeholder="输入课程年份" style="width:120px ;text-indent: 2em;"/></div>
		<div class=" select" style="height:66px;">
			是否显示<br />
			<select style="width:120px ;" >
			<option></option>
				<option>是</option>
				<option>否</option>
			</select>
		</div>
		<div class=" select" style="height:66px;">
			是否精品<br />
			<select style="width:120px ;">
			<option></option>
				<option>是</option>
				<option>否</option>
			</select>
		</div>
		<p style="float: left;width: 426px;height: 1px;background:#F5F6F8;"></p>
<div class="select">
		
			课程资源类别<br />
			<select>
				<option></option>
				<option></option>
				<option></option>
			</select>
		</div>

			<div class="select">
		
			课程用途分类<br />
			<select>
				<option></option>
				<option></option>
				<option></option>
			</select>
		</div>
		<div class="end">
			<input name="git" type="submit" value="提交" style="background:#5ED8A9;"/>
			<input name="esc" type="reset" value="取消"  onclick="CloseDiv2();formReset()" class="esc" />
			</div>
</div>			
<div class="revise-right">
				<h3>显示章节</h3>
				<ul class="section"><form>
					<li><label for="chapter">传染病、性传播疾病冲刺课 </label> <input name="chapter" type="checkbox" id="chapter" /></li>
					<li><label for="chapter2">传染病、性传播疾病冲刺课 </label> <input name="chapter" type="checkbox" id="chapter2" /></li>
					<li><label for="chapter3">传染病、性传播疾病冲刺课 </label> <input name="chapter" type="checkbox" id="chapter3" /></li>
					<li><label for="chapter4">传染病、性传播疾病冲刺课 </label> <input name="chapter" type="checkbox" id="chapter4" /></li>
					<li><label for="chapter5">传染病、性传播疾病冲刺课 </label> <input name="chapter" type="checkbox" id="chapter5" /></li>
					<li><label for="chapter6">传染病、性传播疾病冲刺课 </label> <input name="chapter" type="checkbox" id="chapter6" /></li>
					<li><label for="chapter7">传染病、性传播疾病冲刺课 </label> <input name="chapter" type="checkbox" id="chapter7" /></li>
					<li><label for="chapter8">传染病、性传播疾病冲刺课 </label> <input name="chapter" type="checkbox" id="chapter8" /></li>
					<li><label for="chapter9">传染病、性传播疾病冲刺课 </label> <input name="chapter" type="checkbox" id="chapter9" /></li>
					<li><label for="chapter10">传染病、性传播疾病冲刺课 </label> <input name="chapter" type="checkbox" id="chapter10" /></li>
					<li><label for="chapter9">传染病、性传播疾病冲刺课 </label> <input name="chapter" type="checkbox" id="chapter9" /></li>
					<li><label for="chapter10">传染病、性传播疾病冲刺课 </label> <input name="chapter" type="checkbox" id="chapter10" /></li>
					<li><label for="chapter9">传染病、性传播疾病冲刺课 </label> <input name="chapter" type="checkbox" id="chapter9" /></li>
					<li><label for="chapter10">传染病、性传播疾病冲刺课 </label> <input name="chapter" type="checkbox" id="chapter10" /></li>
				</form></ul>
			</div>
			</form>
		</div>

	
</div>

</div>
</div>
</body>
 
 </html>
