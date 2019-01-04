<#import "/layout/header.ftl" as h/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="班级管理"/>
<link rel="stylesheet" href="./styles/admin.css" />
<script src="./scripts/course.js"></script>
<script src="./scripts/admin.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
<body>

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

	<div class="select" style="float:left;margin-right:15px;">搜索课程<br />
	<form id="search">
	<input type="text" name="search" style=" text-indent:2em;"/></form>
	</div>
	</div>
<div class="manage">
	<ul style="height: 80px;" class="show">

			<li  onclick="showDiv()" style="margin-left: 70px;background:#9DE879;"><span class="glyphicon glyphicon-plus"></span>&nbsp;班级添加</li>
		<li onclick="showDiv2()" style="background:#F9CD33;"><span class="glyphicon glyphicon-pencil"></span>&nbsp;班级修改</li>
		<li  style="background:#F86846;"><span class="glyphicon glyphicon-trash"></span>&nbsp;班级删除</li>
		<li><span class="glyphicon glyphicon-sort" class="move-up"></span>&nbsp;上移</li>
		<li><span class="glyphicon glyphicon-sort-by-attributes" class="move-down"></span>&nbsp;下移</li>
		<li  onclick="showDiv3()"  style="width: 200px;"><span class="glyphicon glyphicon-briefcase"></span>&nbsp;班级课程及赠送管理</li>
		<li ><span class="glyphicon glyphicon-briefcase"></span>&nbsp;关闭城市管理</li>
         <li style="float: right;margin-right: 100px;background:none;"><img src="./images/sjk-f5.png" name="changyi"/></li>
	</ul>
	<div class="admin-table">

	<ol style="background: #CBD2D8;font-weight: bold;">
		<li>班级名称</li>
	<li >班级图片</li>
	<li >班级价格</li>
	<li>班级折扣价</li>
	<li>赠送班级网课</li>
	<li>增送直播班</li>
	<li>主讲老师</li>
	<li>班级年限</li>
	<li>显示级别</li>
	<li>是否推荐</li>
	<li>是否关闭</li>
	</ol>
 <for:each>
 <ol style="box-shadow:0px 1px 1px 0px #B1B1B1 inset;" >
		<li>班级名称</li>
	<li ><div style="width: 80%;height:90%; margin: 0 auto;">
		<img src="#" style="width: 100%;height: 100%;" />
	</div></li>
	<li >班级价格</li>
	<li>班级折扣价</li>
	<li>赠送班级网课</li>
	<li>增送直播班</li>
	<li>主讲老师</li>
	<li>班级年限</li>
	<li>显示级别</li>
	<li>是否推荐</li>
	<li>是否关闭</li>
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
		<div class="poop" id="add" style="width: 80%;height: 800px;position: absolute;left: 5%;top: 5%;">
		<form id="myform">
	<h3>班级添加</h3>
	<span style="margin-right:20px ;">已选专业类型:</span> <span>lin</span>
		<div class="grade-add">
			<div class="grade-left" style="padding-right: 5%;">
		
				<div class=" select-2">
						<span>班级名称：</span>
			<input type="text" class=""placeholder="请输入班级名称" style="width: 230px;" />
		</div>
	
	<div style="width: 100%;height:90px;clear: both;">	<div class=" select-2" style="float: left;">
		<img src="/images/sjk-xl.png"/>
						<span>班级模板：</span>
			<select>
				<option disabled selected style='display:none;'>选择模板</option>
				<option></option>
				<option></option>
			</select>
		</div>
		<div class=" select-2" style="float:right;">
			<img src="/images/sjk-xl.png"/>
						<span>主讲老师：</span>
			<select>
				<option disabled selected style='display:none;'>查找</option>
				<option></option>
				<option></option>
			</select>
		</div></div>
			<div style="width: 100%;height:90px;clear: both;">	<div class=" select-2" style="float: left;">
	
						<span>班级价格：</span>
			<input type="text" class=""placeholder="请输入班级名称"  />
		</div>
		<div class=" select-2" style="float: right;">
	
						<span>班级折扣价：</span>
			<input type="text" class=""placeholder="请输入班级名称"  />
		</div></div>
		<div style="width: 100%;height:90px;clear: both;">		<div class=" select-2" style="float: left;">
	
						<span>班级年份：</span>
			<input type="text" class=""placeholder="请输入班级年份"  />
		</div>
		<div class=" select-2" style="float: right;">
	
						<span>总课课时数：</span>
			<input type="text" class=""placeholder="请输入课时数"  />
		</div></div>
<div class=" select-2">
	<img src="/images/sjk-xl.png"/>
		<span>显示级别：</span>
			<select>
				<option disabled selected style='display:none;'>查找</option>
				<option></option>
				<option></option>
			</select>
		</div>

<ul><li>是否推荐</li>  <li><input type="radio" name="tuijian"  /> 是</li> <li><input type="radio" name="tuijian" />否</li></ul>
<ul><li>是否关闭  </li><li><input type="radio" name="tuijian" /> 是</li> <li><input type="radio" name="tuijian" />否</li></ul>
<ul><li>是否试听 </li><li><input type="radio" name="tuijian" /> 是</li> <li><input type="radio" name="tuijian" />否</li></ul>
			<div >
				<form name="form"  method="post" enctype="multipart/form-data">
     <input type="file" name="upload" id="file_upload"style="display: none;"
         onchange="document.form.path.value=this.value" multiple="multiple" accept="image/*" />
     <input name="path" id="path" readonly>
     <input type="button" value="班级图片" onclick="document.form.file_upload.click()">
     <div class="image_container" >
            <img id="preview" style="height:130px;width:117px;border-width:0px;" />
        </div>
</form>
	</div>
			</div>
		
		<div class="grade-center">
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
		<div class="grade-right">
<div>
<span>课程资源</span>
<form >
<p><input type="checkbox" name="ziyuan" /> 2018临床-冲刺习题课程</p>
<p><input type="checkbox" name="ziyuan" />2018临床-冲刺习题课程</p>
</form>
</div>
<div>
			<span>班级教师</span>
			<form >
  <p><input type="checkbox" name="teacher" value="Bike" /> 2018临床-冲刺习题课程</p>
  <p><input type="checkbox" name="teacher" value="Car" checked="checked" />2018临床-冲刺习题课程</p>
</form>
		</div>
		</div>
<div class="grade-add-bottom">
	<span>班级详情</span>
    <textarea></textarea>
</div>
		</form>
		<div class="end">
			<input name="git" type="submit" value="提交" style="background:#5ED8A9;"/>
			<input name="esc" type="reset" value="取消"  onclick="CloseDiv();formReset()" class="esc" />
		</div>
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
 <style type="text/css">
	.admin-table ol li{width: 9%;}
	.admin-table ol li:nth-child(1){width: 10%;}
	.poop{overflow-y: scroll;}
	.poop span{font-size: 2rem;}
	.grade-add{width: 100%;border-top:1px solid #F5F6F8;height: 80%;padding-top: 10px;margin-top:10px ;}
	.grade-left,.grade-center,.grade-right{width: 33%;float: left;height: 800px;}
	.grade-left ul{height: 50px;line-height: 50px; font-size: 2rem;}
	.grade-left ul li{float: left;margin-right: 5px;}
	.grade-left ul li:nth-child(1){margin-right: 10px;}
	.grade-center .grade-text{width: 80%;}
	.grade-center .grade-text textarea{width: 100%;height: 160px;font-size:1.5rem}
	.grade-right form{width: 80%;height: auto;background:#EDEEF0;border-radius:20px;text-indent: 2em;font-size: 1.6rem;}
	.grade-right form input{margin-right: 5px;width:17px;height: 17px;}
	.grade-add-bottom{width: 100%;clear:both;}
	.grade-add-bottom textarea{width: 100%;height: 250px;background:#EDEEF0;border-radius:20px;text-indent: 2em;}
</style>

   <script type="text/javascript">
             $(function () {
            $("#file_upload").change(function () {
                var $file = $(this);
                var fileObj = $file[0];
                var windowURL = window.URL || window.webkitURL;
                var dataURL;
                var $img = $("#preview");

                if (fileObj && fileObj.files && fileObj.files[0]) {
                    dataURL = windowURL.createObjectURL(fileObj.files[0]);
                    $img.attr('src', dataURL);
                } else {
                    dataURL = $file.val();
                    var imgObj = document.getElementById("preview");
                    // 两个坑:
                    // 1、在设置filter属性时，元素必须已经存在在DOM树中，动态创建的Node，也需要在设置属性前加入到DOM中，先设置属性在加入，无效；
                    // 2、src属性需要像下面的方式添加，上面的两种方式添加，无效；
                    imgObj.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
                    imgObj.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = dataURL;

                }
            });
        });
        </script>
 </html>
