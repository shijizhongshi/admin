<#import "/layout/header.ftl" as h/>
<!DOCTYPE html>
<html lang="en">
<@h.header title="后台管理页面"/>
<link rel="stylesheet" href="./styles/admin.css" />
<body>
<div class="container-fluid">
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
	
		<ul style="background: black;"><li >网课管理</li></ul>
		<ul style="background: #1B1B22 ;"><li>课程资源管理</li></ul>
		<ul style="background: #272731;"><li>课程章管理</li></ul>
		<ul style="background: #272731;"><li>课程节管理</li></ul>
	
</div>
<div class="details">
	<div class="details-nav">
		<ul>
			<li><img src="./images/sjk-home.png" style="color: red;"/>我的主页</li>
			<li>/</li>
		<li>课程章节管理</li>

		
		</ul>
	</div>
<div class="details-frame">
	<div class="details-frame-content">
		<div class=" select">
			<label >选择专业</label>	
			<br />
			<input type="text" name="majior" class=""/>
			<img src="./images/sjk-xl.png" />
		</div>
		
		<div class="select">
			<label>专业类型</label>
			<br />
			<input type="text" name="majior"/>
			<img src="./images/sjk-xl.png" />
		</div>
		
	<input type="button" class="btn-lg im-key" value="立即检索" /></div>
<div class="manage">
	<ul style="height: 80px;">
		<div class="switch" onclick="showDiv()">
			<li><img src="./images/sjk-add.png"/></li>
			<!--弹窗-->
		<div class="poop" id="poop">
	<h3>添加课程</h3>
	<div class=" select" style="float: left;height:66px;">
			<input type="text" name="majior" class=""placeholder="选择专业"/>
			<img src="./images/sjk-xl.png" />
		</div>
		<div class=" select" style="float: right;height: 66px;">
			<input type="text" name="majior" class=""placeholder="专业类型"/>
			<img src="./images/sjk-xl.png" />
		</div>
		<div class=" select"style="width: 425px;height:53px;border-bottom: 1px solid #F5F6F8;">
			<input type="text" name="majior" class=""placeholder="请输入课程名称"style="width: 230px" />
			<input type="button" value="上传课程图片" style="background: #FDE490;float: right;"/>
		</div>
		<div class=" select"style="width: 425px;height:48px;margin-top: 15px;">
			<input type="text" name="majior" class=""placeholder="输入课程价格" style="width:120px ;"/>
			<input type="text" placeholder="输入折扣价格" style="width:120px ;"/>
		</div>
		<div class=" select"style="width: 425px;height:53px;margin-top: 15px;">
		<input type="text" placeholder="输入课程年份" style="width:120px ;"/></div>
		<div class=" select" style="float: left;height:66px;">
			<input type="text" name="majior" class=""placeholder="是否显示"style="width:120px ;"/>
			<img src="./images/sjk-xl.png" style="position: absolute;left: 87px;" />
		</div>
		<div class=" select" style="float: left;height:66px;">
			<input type="text" name="majior" class=""placeholder="是否精品"style="width:120px ;"/>
			<img src="./images/sjk-xl.png"style="position: absolute;left: 87px;"  />
		</div>
		<p style="float: left;width: 426px;height: 1px;background:#F5F6F8;margin-bottom: ;"></p>
<div class=" select" style="float: left;height:66px;">
			<input type="text" name="majior" class=""placeholder="课程资源类别"/>
			<img src="./images/sjk-xl.png" />
		</div>
		<div class=" select" style="float: right;height: 66px;">
			<input type="text" name="majior" class=""placeholder="课程用途分类"/>
			<img src="./images/sjk-xl.png" />
		</div>
		<div class="end">
			<input name="git" type="submit" value="提交" style="background:#5ED8A9;"/>
			<input name="esc" type="submit" value="取消" id="esc" />
		</div>
		
		</div>
		</div>
		<li><img src="./images/sjk-xiugai.png" name="xiugai"/></li>
		<li><img src="./images/sjk-delete.png" name="shanchu"/></li>
		<li><img src="./images/sjk-up.png" name="changyi"/></li>
		<li><img src="./images/sjk-down.png" name="changyi"/></li>
		<li><img src="./images/guanli.png" name="guanli"/></li>
         <li style="float: right;margin-right: 100px;"><img src="./images/sjk-f5.png" name="changyi"/></li>
	</ul>
	<div class="admin-table">

	<ol style="background: #CBD2D8;font-weight: bold;">
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

</div>
</div>
</div>
</div>

</body>
 <script type="text/javascript">  
        function showDiv(){
        document.getElementById('poop').style.display="block"; 
        }

    </script>

</html>