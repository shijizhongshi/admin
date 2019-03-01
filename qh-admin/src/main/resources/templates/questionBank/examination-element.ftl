<#import "/layout/header.ftl" as h/>
<#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="试卷管理"/>
<link rel="stylesheet" href="/styles/admin.css" />
<link rel="stylesheet" href="/styles/management.css" />
<script src="/scripts/admin.js"></script>

<@b.body menu="sidebarmenu-questionBank" submenu="sidebarmenu-questionBank-examination-element">
<div>

	<div class="details" style="width:100%;" >
		<div class="details-nav">
			<ul>
				<li><img src="/images/sjk-home.png" />我的主页</li>
				<li>/</li>
				<li>题库管理</li>
				<li>/</li>
				<li>试卷管理</li>
				<li>/</li>
				<li>试卷单元管理</li>
			</ul>
		</div>
<div class="details-frame">
	<div style="font-size: 1.6rem;font-weight: bold;padding-bottom: 10px;">{章节名称}</div>
	<ul id="guanli">
	<li style="background:white;box-shadow:none;" onclick="go(0)"><p>试卷单元管理</p></li>
	<li onclick="go(1)"><p>试题管理</p></li>
	</ul>
	<!-- 节管理 -->
<div class="manage">
	<ul class="show">

			<li style="background:#9DE879;" onclick="showDiv()"><span class="glyphicon glyphicon-plus" ></span>&nbsp;添加试卷单元</li>
		<li style="background:#F9CD33;"><span class="glyphicon glyphicon-pencil"></span>&nbsp;修改试卷单元</li>
		<li style="background:#F86846;"><span class="glyphicon glyphicon-trash"></span>&nbsp;删除试卷单元</li>
		<li  onclick="showDiv2()"><span class="glyphicon glyphicon-sort"></span>&nbsp;上移</li>
		<li><span class="glyphicon glyphicon-sort"></span>&nbsp;下移</li>
 
	</ul>
	<div class="admin-table">

  <table id="tableExcel">

	<tbody>
	<tr>
		<th>试卷单元名称</th>
<th>试题数量</th>
	<th>是否可见</th>
	<th>用途分类</th>
	
	</tr>
	<tr>
			<th>试卷单元名称</th>
<th>试题数量</th>
	<th>是否可见</th>
	<th>用途分类</th>
	</tr>



	</tbody></table>
	</div>
		<div class="col-sm-6"></div>
					<div class="col-sm-6">
						<ul uib-pagination boundary-links="true" total-items="total"
							ng-model="page" items-per-page="pageSize" max-size="5"
							class="pagination-sm" previous-text="&lsaquo;"
							next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
							ng-click="templateBases()">
						</ul>
					</div>
	<!--添加修改试卷单元-->
		<div class="poop" id="add" >
		<form id="myform" class="ng-pristine ng-valid">
	<h3>{{}}试卷单元</h3>
	<p style="padding:10px 0;">试卷名称：&nbsp;{名称}</p>
<div class="select-2">
		<span>单元名称<i class="bitian">*</i></span>
<input type="text" class="ng-pristine ng-untouched ng-valid ng-empty" placeholder="请输入单元名称" >
	</div>
	<div class="select-2">
		<span>考试时间<i class="bitian">*</i></span>
<input type="text" class="ng-pristine ng-untouched ng-valid ng-empty" placeholder="" >
	</div>
	<div class="select-2">
		<span>用途<i class="bitian">*</i></span>
		<img src="/images/sjk-xl.png">
		<select  class="ng-pristine ng-untouched ng-valid ng-empty">
		<option value="? undefined:undefined ?"></option>
			<option ></option>
			<option ></option>
		</select>
	</div>
<div class="select-radio ">
		<ul><li>是否可见</li>  
		<li><input type="radio" ng-value="1" class="ng-pristine ng-untouched ng-valid ng-empty" name="1" value="1"> 是</li> 
		<li><input type="radio" ng-value="0" class="ng-pristine ng-untouched ng-valid ng-empty" name="1" value="0">否</li></ul>
		</div>		
		<div class="end">
			<input name="git" type="submit" value="提交" ng-show="courseId==null" ng-click="addCourse()" style="background:#5ED8A9;">
			<input name="git" type="submit" value="修改" ng-show="courseId!=null" ng-click="addCourse()" style="background:#5ED8A9;" class="ng-hide">
			<input name="esc" type="reset" value="取消" onclick="CloseDiv()" class="esc">
		</div>
		</form>
	</div>

</div>

<!-- 试题管理 -->
<div class="manage" style="display:none;">
	<ul class="show">

			<li style="background:#9DE879;"><span class="glyphicon glyphicon-plus" ></span>&nbsp;导入试题</li>
			<li style="background:#9DE879;" ><span class="glyphicon glyphicon-plus" ></span>&nbsp;导入原有系统试题</li>
			<li style="background:#9DE879;" onclick="showDiv2()"><span class="glyphicon glyphicon-plus" ></span>&nbsp;从文件导入试题</li>
		<li style="background:#F9CD33;" onclick="showDiv3()"> <span class="glyphicon glyphicon-pencil"></span>&nbsp;修改试题</li>
		<li style="background:#F86846;"><span class="glyphicon glyphicon-trash"></span>&nbsp;删除试题</li>
 
	</ul>
	<div style="clear:both;padding-top:20px;padding-left:20px;overflow:hidden;">
<div class="classify" >
		<ul class="menu">

			<li class="list" ng-click="typeList('医师资格',1)">医师资格
				<ul class="items" ng-class="{'active':active==1}">
					<li ng-repeat="sub in courseTypeSubclass"
						ng-click="courseSub('医师资格',sub,$event)"
						ng-class="{'selected':typeSelected==sub.courseTypeSubclassName}">{{sub.courseTypeSubclassName}}</li>

				</ul>
			</li>
			<li class="list" ng-click="typeList('药师资格',2)">药师资格
				<ul class="items" ng-class="{'active':active==2}">
					<li ng-repeat="sub in courseTypeSubclass"
						ng-click="courseSub('药师资格',sub,$event)"
						ng-class="{'selected':typeSelected==sub.courseTypeSubclassName}">{{sub.courseTypeSubclassName}}</li>

				</ul>
			</li>
			<li class="list" ng-click="typeList('中医基础理论',3)">中医基础理论
				<ul class="items" ng-class="{'active':active==3}">
					<li ng-repeat="sub in courseTypeSubclass"
						ng-click="courseSub('中医基础理论',sub,$event)"
						ng-class="{'selected':typeSelected==sub.courseTypeSubclassName}">{{sub.courseTypeSubclassName}}</li>
				</ul>
			</li>
			<li class="list" ng-click="typeList('卫生资格 ',4)">卫生资格
				<ul class="items" ng-class="{'active':active==4}">
					<li ng-repeat="sub in courseTypeSubclass"
						ng-click="courseSub('卫生资格 ',sub,$event)"
						ng-class="{'selected':typeSelected==sub.courseTypeSubclassName}">{{sub.courseTypeSubclassName}}</li>

				</ul>
			</li>
			<li class="list" ng-click="typeList('健康管理师',5)">健康管理师
				<ul class="items" ng-class="{'active':active==5}">
					<li ng-repeat="sub in courseTypeSubclass"
						ng-click="courseSub('健康管理师',sub,$event)"
						ng-class="{'selected':typeSelected==sub.courseTypeSubclassName}">{{sub.courseTypeSubclassName}}</li>
				</ul>
			</li>



		</ul>
	</div>

	
	
	<div class="admin-table" style="width:87%;">

  <table id="tableExcel">

	<tbody>
	<tr>
		<th>试题名称</th>
<th>正确答案项</th>
	<th>试题类型</th>
	<th>答案解析</th>
	
	</tr>
	<tr>
		<th>试题名称</th>
<th>正确答案项</th>
	<th>试题类型</th>
	<th>答案解析</th>
	</tr>



	</tbody></table>
	</div>
		
					</div>
					<div class="col-sm-6"></div>
					<div class="col-sm-6">
						<ul uib-pagination boundary-links="true" total-items="total"
							ng-model="page" items-per-page="pageSize" max-size="5"
							class="pagination-sm" previous-text="&lsaquo;"
							next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
							ng-click="templateBases()">
						</ul>
					</div>
	<!--从文件导入试题-->
		<div class="poop" id="revise" >
		<form id="myform2" class="ng-pristine ng-valid">
	<h3>从文件导入试题</h3>
<div class="costs-uploadfile-div">
							请选择上传的附件<input type="file" name="file"  value="选择文件" onchange="angular.element(this).scope().uploadmainimage(this)">

				
						</div>
		<div class="end">
			<input name="git" type="submit" value="提交" ng-show="courseId==null" ng-click="addCourse()" style="background:#5ED8A9;">
			<input name="esc" type="reset" value="取消" onclick="CloseDiv2()" class="esc">
		</div>
		</form>
	</div>
<!-- 修改试题 -->
<div id="resource" class="resource" style="width:600px;min-width:600px;left:20%;">
	<form id="myform3" class="ng-pristine ng-valid">
	<h3 style="margin-bottom:0;">修改试题</h3>
	<p style="border-bottom:1px solid #999;padding-bottom:5px;"><span>试题所属节：{}</span><span style="margin-left:10%;">试题类型：{单选题}</span></p>
	<div>
	<div style="float:left;width:49%;">
	<div class="grade-text">
	<span>试题标题</span>
	<textarea ng-model="courseNofree.describes" class="ng-pristine ng-untouched ng-valid ng-empty"></textarea>
	</div>
	<div class="grade-text">
	<span>答案解析</span>
	<textarea ng-model="courseNofree.describes" class="ng-pristine ng-untouched ng-valid ng-empty"></textarea>
	</div>
	<div class="select-2">
		<span>解析视频ID<i class="bitian">*</i></span>
<input type="text" class="ng-pristine ng-untouched ng-valid ng-empty" placeholder="视频ID" >
	</div>
	<div class="select-2">
		<span>试题考点ID<i class="bitian">*</i></span>
<input type="text" class="ng-pristine ng-untouched ng-valid ng-empty" placeholder="考点ID" >
	</div></div>
	<div style="width:49%;float:right;">
	<div class="shiti">
	<ul><li style="background:#F5F6F8">试题1</li><li>试题2</li><li>试题3</li></ul>
	<p>标题</p>
	<div class="xiugaibt">
	<textarea></textarea>
	</div>
	</div>


<!-- 答案项  。试题类型是公共选项的时候调用，单选题不调用 -->
<div class="daan">
<table>
<tbody>
<tr>
<td>正确选项</td>
<td>选项</td>
</tr>
<tr>
<td><input type="checkbox"/></td>
<td>答案答案答案答案答案答案答案答案</td>
</tr>
</tbody>
</table>
	</div>
</div>
</div>

	<div class="end" style="margin-top:10px;">
			<input name="git" type="submit" value="修改" ng-show="courseId==null" ng-click="addCourse()" style="background:#5ED8A9;">
			<input name="esc" type="reset" value="取消" onclick="CloseDiv3()" class="esc">
		</div>
</form>

</idv>


</div>







</div>










	</div>
</div>
</div>
<style>
.media screen and (max-width: 1201px){width:100%;float:none;}
#guanli{height:40px;margin-bottom:0;}
#guanli li{float:left;padding:0 20px;background:#F4F4F4;font-size:1.3rem;font-weight: bold;height:40px;line-height: 40px;box-shadow:inset 0px -3px 7px -3px #c2c0c0;}
#resource .grade-text , #resource .select-2 {width:auto;}
#resource .grade-text textarea{height:50px;}
.shiti{}
.shiti ul {display:flex;justify-content: left;margin-bottom: 0;}
.shiti ul li{margin-right: 3px;border-radius: 7px 7px 0px 0;background:#CBD2D8;text-align: center;padding: 0 5px;}
.shiti p{padding-top: 7px;width:100%;padding-left: 2%;background: #F5F6F8;margin: 0;font-size: 1.3rem;padding-bottom: 5px;}
.xiugaibt{width:100%;background: #F5F6F8;padding: 0 2%;height: 80px;}
.xiugaibt textarea{width:100%;background:#FFFFFF;height: 73px;}
.daan table{margin-top:10px;overflow:hidden;border-radius:10px; border-collapse: collapse;text-align: center;}
.daan table tr{background:#F7F8FC;font-size:1.3rem;}
.daan table tr:nth-child(1){background:#dddedf;color:black;}
.daan table tr td:nth-child(1){border-right:1px solid #999;}
</style>
<script>
function go(n){
		var tabs=document.getElementById("guanli").getElementsByTagName("li");
		var show=document.getElementsByClassName("manage");
    for(var i=0;i<tabs.length;i++){
    	show[i].style.display='none';
    	tabs[i].style.background='#F4F4F4'
    		tabs[i].style.boxShadow='inset 0px -3px 7px -3px #c2c0c0'
        if(i==n){
            show[i].style.display='block';
            tabs[i].style.background='white'
            	tabs[i].style.boxShadow='none'
        }

    }


}

</script>
</@b.body>

</html>