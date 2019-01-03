<#import "/layout/header.ftl" as h/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="课程管理页面儿"/>
<link rel="stylesheet" href="./styles/admin.css" />
<script src="./scripts/course.js"></script>
<script src="./scripts/admin.js"></script>
<body>

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
<div class="details" id="details">
	<div class="details-nav">
		<ul>
			<li><img src="./images/sjk-home.png" style="color: red;"/>我的主页</li>
			<li>/</li>
		<li>课程章节管理</li>

		
		</ul>
	</div>
<div class="details-frame" >
	<div class="details-frame-content">
<ul style="float:left;padding-top:20px;" class="show">

			<li  onclick="showDiv()" style="margin-left: 70px;background:#5ED8A9;width: 100px;"><span class="glyphicon glyphicon-plus"></span>&nbsp;添加节</li>
		<li onclick="showDiv2()" style="background:#F9CD33;width: 100px;"><span class="glyphicon glyphicon-pencil"></span>&nbsp;修改节</li>
		<li onclick="showDiv2()" style="background:#F9CD33;"><span class="glyphicon glyphicon-pencil"></span>&nbsp;关联题库ID</li>
		<li  style="background:#F86846;width: 100px;"><span class="glyphicon glyphicon-trash"></span>&nbsp;删除节</li>
		<li style="width: 90px;"><span class="glyphicon glyphicon-sort" ></span>&nbsp;上移</li>
		<li style="width: 90px;"><span class="glyphicon glyphicon-sort-by-attributes"  ></span>&nbsp;下移</li>
		<li  onclick="showDiv3()"><span class="glyphicon glyphicon-briefcase"></span>&nbsp;导入节内容</li>
       
	</ul>
	<div class="select" style="float: right;margin-right:15px;">搜索课程<br />
	<form id="search">
	<input type="text" name="search" style=" text-indent:2em;"/></form>
	</div>
	</div>
<div class="manage">
	
	<div class="admin-table">

	<ol style="background: #CBD2D8;font-weight: bold;">
		<li>节名称</li>
	<li style="width: 13%;"> 七牛云链接</li>
	<li style="width: 20%;">阿里云ID</li>
	<li>视频URL</li>
	<li>是否可见</li>
	<li>章节练习</li>
	<li>用途分类</li>
	<li>是否考试</li>
	
	</ol>
 <for:each>
 <ol style="box-shadow:0px 1px 1px 0px #B1B1B1 inset;" >
		<li></li>
	<li style="width: 13%;"></li>
	<li style="width: 20%;"></li>
	<li></li>
	<li ></li>
	<li ></li>
	<li></li>
	<li></li>
	</ol>
	
	<ol >
		<li></li>
	<li style="width: 13%;"></li>
	<li style="width: 20%;"></li>
	<li></li>
	<li ></li>
	<li ></li>
	<li></li>
	<li></li>
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
		<div class="poop" id="add" style="width: 790px;position: absolute;left: 20%;">
			<form id="myform">
			<div class="add-jie-left">
	<h3>添加节</h3>
	<p><span style="float: left;">章名称：</span><span style="float: right;">冲刺课-中医妇科冲刺习题</span></p>
	
		
		<div class=" select">
			<input type="text" placeholder="节名称" style="width: 230px;text-indent: 2em;" />
		</div>
		
		<div class="select">
		
			<input type="text" placeholder="七牛云链接" style="width:130px ;text-indent: 2em;"/>
		</div>
	
		<div class="select">
			<input type="text" placeholder="阿里云ID" style="width:130px ;text-indent: 2em;"/>
		</div>
		<div class="select">
			<input type="text" placeholder="视频3URL3" style="width:130px ;text-indent: 2em;"/>
		</div>
			<div class="add-jie-radio" >
			<span>是否可见</span> <span><input type="radio" name="visible" id="" value="" />是
				&nbsp;<input type="radio" name="visible" id="" value="" />否</span>
		</div>
		<div class="add-jie-radio" >
			<span>是否要求考试</span> <span><input type="radio" name="exam" id="" value="" />是
				&nbsp;<input type="radio" name="exam" id="" value="" />否</span>
		</div>
</div>			

            <div class="add-jie-right">
            	<p>讲义内容</p>
            	<div id="editor" style="height:360px">      <script type="text/javascript" charset="utf-8">      UE.getEditor('editor');      </script>   </div>
            </div>
			<div class="end">
			<input name="git" type="submit" value="提交" style="background:#5ED8A9;"/>
			<input name="esc" type="reset" value="取消"  onclick="CloseDiv();formReset()" class="esc" />
			</div>
			</form>
	</div>
	
	
	
		
		
		<div class="poop-revise" id="revise"  style="width: 790px;position: absolute;left: 20%;" >
			<form id="myform2">
	<h3>关联题库ID</h3>
	<p>关联章节练习</p>
	<div class="add-section">
		<ul id="section-tab" >
			<li onmousedown="setTab(0)">第一部分&nbsp;医学人文</li>
			<li onmousedown="setTab(1)">第二部分&nbsp;医学人文公共卫生</li>
			<li onmousedown="setTab(2)">第三部分&nbsp;中西医（执业）助理医师</li>
		</ul>
		<div style="width: 400px;height: 350px;border-radius: 20px;overflow: hidden;border: 1px solid #B1B1B1;float: right;" id="tab">
		<div class="section-table">
			<ul style="background:#E9EAEE;font-weight: bold;">
				<li>选择</li>
				<li>节名称</li>
			</ul>
			<ul>
				<li><input  type="radio"></li>
				<li>节名称</li>
			</ul>
			<ul>
				<li><input  type="radio"></li>
				<li>节名称</li>
			</ul>
			
		</div>
		<div class="section-table" style="display: none;">
			<ul style="background:#E9EAEE;font-weight: bold;">
				<li>1</li>
				<li>2</li>
			</ul>
			<ul>
				<li><input  type="radio"></li>
				<li>节名称</li>
			</ul>
			<ul>
				<li><input  type="radio"></li>
				<li>节名称</li>
			</ul>
			
		</div>
				<div class="section-table" style="display: none;">
			<ul style="background:#E9EAEE;font-weight: bold;">
				<li>555</li>
				<li>777</li>
			</ul>
			<ul>
				<li><input  type="radio"></li>
				<li>节名称</li>
			</ul>
			<ul>
				<li><input  type="radio"></li>
				<li>节名称</li>
			</ul>
			
		</div>
	</div></div>
	
	
		<div class="end">
			<input name="git" type="submit" value="提交" style="background:#5ED8A9;"/>
			<input name="esc" type="reset" value="取消"  onclick="CloseDiv2();formReset2()" class="esc" />
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
<script type="text/javascript">
	 function setTab(n){
 			var tabs=document.getElementById("section-tab").getElementsByTagName("li");
 			var show=document.getElementById("tab").getElementsByTagName("div");
            for(var i=0;i<tabs.length;i++){
            	show[i].style.display='none';
            	tabs[i].style.color='black';
                if(i==n){
                		tabs[i].style.color='red';
                    show[i].style.display='block';
                }

            }


        }
 
</script>
</body>
 
 </html>
