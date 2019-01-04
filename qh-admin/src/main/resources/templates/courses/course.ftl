<#import "/layout/header.ftl" as h/>
<#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">

<@h.header title="课程管理页面"/>
<link rel="stylesheet" href="/styles/admin.css" />
<script src="/scripts/course/course.js"></script>
<script src="/scripts/admin.js"></script>
<@b.body menu="sidebarmenu-system" submenu="sidebarmenu-system-banner">
<style type="text/css">
.classify{width:100%; background:#FFFFFF;}
.classify ul.menu .list{background:#FFFFFF;border-bottom:none;font-size:18px;}
.classify ul.menu .list a:hover{background:#7489A2;color:#FFFFFF;}
.classify ul.menu .list a{color:black}
*:after{color:#E5B70D;}
*:before{color:#E5B70D;}
.items{display:none;}
.active{display:block;}
</style>
<div ng-controller="CourseController">
<div class="classify">
	<ul class="menu">
	
   <li class="list" ng-click="typeList(1)" >医师资格 
      <ul class="items" ng-class="{'active':active==1}">
         <li ng-repeat="sub in courseTypeSubclass" ng-click="courseSub('医师资格',sub)">{{sub.courseTypeSubclassName}}</li>
        
      </ul>
   </li>
   <li class="list" ng-click="typeList(2)">药师资格
      <ul class="items" ng-class="{'active':active==2}">
         <li ng-repeat="sub in courseTypeSubclass">{{sub.courseTypeSubclassName}}</li>
        
      </ul>
      </li>
       <li class="list" ng-click="typeList(3)">中医基础理论
      <ul class="items" ng-class="{'active':active==3}">
         <li ng-repeat="sub in courseTypeSubclass">{{sub.courseTypeSubclassName}}</li>
      </ul>
      </li>
       <li class="list" ng-click="typeList(4)">卫生资格 
      <ul class="items" ng-class="{'active':active==4}">
        <li ng-repeat="sub in courseTypeSubclass">{{sub.courseTypeSubclassName}}</li>
        
      </ul>
      </li>
      <li class="list"><a href="#">健康管理师</a> 
      <ul class="items" ng-class="{'active':active==5}">
         <li ng-repeat="sub in courseTypeSubclass">{{sub.courseTypeSubclassName}}</li>
      </ul>
      </li>
      
      
      
      </ul>
      </div>
 <div class="details">
	<div class="details-nav">
		<ul>
			<li><img src="/images/sjk-home.png" style="color: red;"/>我的主页</li>
			<li>/</li>
		<li>课程管理</li>

		
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

			<li  onclick="showDiv()" style="margin-left: 70px;background:#9DE879;"><span class="glyphicon glyphicon-plus"></span>&nbsp;添加课程</li>
		<li onclick="showDiv2()" style="background:#F9CD33;"><span class="glyphicon glyphicon-pencil"></span>&nbsp;修改课程</li>
		<li  style="background:#F86846;"><span class="glyphicon glyphicon-trash"></span>&nbsp;删除课程</li>
		<li><span class="glyphicon glyphicon-sort" class="move-up"></span>&nbsp;上移</li>
		<li><span class="glyphicon glyphicon-sort-by-attributes" class="move-down"></span>&nbsp;下移</li>
		<li  onclick="showDiv3()"><span class="glyphicon glyphicon-briefcase"></span>&nbsp;资源章节管理</li>
         <li style="float: right;margin-right: 100px;background:none;"><img src="/images/sjk-f5.png" name="changyi"/></li>
	</ul>
	<div class="admin-table">

	<ol style="background: #CBD2D8;font-weight: bold;">
		<li>课程名称</li>
	<li >课程总数</li>
	<li >课程价格</li>
	<li>课程折扣</li>
	<li>是否显示</li>
	</ol>
 <ol style="box-shadow:0px 1px 1px 0px #B1B1B1 inset;"  ng-repeat="c in courselist">
	<li class="col-md-2">{{c.courseName}}</li>
	<li class="col-md-1">{{c.courseChapterSize}}</li>
	<li class="col-md-1">{{c.coursePrice}}</li>
	<li  class="col-md-1">{{c.courseDiscountPrice}}</li>
	<li  class="col-md-1" ng-show="{{c.courseShow==1}}">是</li>
	<li  class="col-md-1" ng-show="{{c.courseShow==0}}">否</li>
	</ol>
	</div>
	<div class="col-sm-6"></div>
                    <div class="col-sm-6">
                        <ul uib-pagination boundary-links="true"
                            total-items="total" ng-model="current"
                            items-per-page="pageSize"
                            max-size="5"
                            class="pagination-sm" previous-text="&lsaquo;"
                            next-text="&rsaquo;"
                            first-text="&laquo;" last-text="&raquo;" ng-change="courseBases()">
                        </ul>
                    </div>
	<!--弹窗-->
		<div class="poop" id="add">
		<form id="myform">
	<h3>添加课程</h3>
	<div class=" select"style="width: 370px;height:53px;border-bottom: 1px solid #F5F6F8;margin-top:3px;">
			<input type="text" ng-model="course.courseName" placeholder="请输入课程名称" style="width: 230px;text-indent: 2em;" />
			<input type="file" value="上传课程图片" onchange="angular.element(this).scope().uploadmainimage(this)" style="float: right;width:122px;"/>
			<input type="hidden" ng-model="course.courseImg"/>
			<img src="{{course.courseImg}}" ng-show="{{course.courseImg!=null}}">
		</div>
		<div class="centre-border">
		<div class="select"style="width: 405px;height:48px;margin-top:5px;">
			<input type="text"  ng-model="course.coursePrice" placeholder="输入课程价格" style="text-indent: 2em;margin-right:15px;"/>
			<input type="text" ng-model="course.courseDiscountPrice" placeholder="输入折扣价格" style="text-indent: 2em;"/>
		</div>
		<div class=" select"style="width: 405px;">
		<input type="text" placeholder="输入课程年份"  ng-model="course.courseYear" style="text-indent: 2em;"/></div>
		<div class=" select" style="margin-right:15px;">
			<img src="/images/sjk-xl.png"/>
			是否展示
			<select ng-model="course.courseShow">
				<option ng-selected="course.courseShow==1" value="1">是</option>
				<option ng-selected="course.courseShow==0" value="0">否</option>
			</select>
		</div>
		<div class="select">
			<img src="/images/sjk-xl.png"/>
			是否精品
			<select ng-model="course.courseExcellent">
				<option ng-selected="course.courseExcellent==1" value="1">是</option>
				<option ng-selected="course.courseExcellent==0" value="0">否</option>
			</select>
		</div>
		</div>
	
<div class="select">
			<img src="/images/sjk-xl.png"/>
			选择类别
			<select ng-model="course.courseResourceType">
			
				<option ng-selected="course.courseResourceType=='基础课' " value="基础课">基础课</option>
				<option ng-selected="course.courseResourceType=='实践技能' " value="实践技能">实践技能</option>
				<option ng-selected="course.courseResourceType=='应试技巧' " value="应试技巧">应试技巧</option>
				<option ng-selected="course.courseResourceType=='冲刺课' " value="冲刺课">冲刺课</option>
				<option ng-selected="course.courseResourceType=='习题精讲' " value="习题精讲">习题精讲</option>
				<option ng-selected="course.courseResourceType=='包过套餐' " value="包过套餐">包过套餐</option>
			</select>
		</div>

			<div class="select">
			<img src="/images/sjk-xl.png"/>
			用途
			<select ng-model="course.courseUseDifference">
				
				<option ng-selected="course.courseUseDifference=='自用课程' " value="自用课程">自用课程</option>
				<option ng-selected="course.courseUseDifference=='部门共用' " value="部门共用"> 部门共用</option>
				<option ng-selected="course.courseUseDifference=='公司共用' " value="公司共用">公司共用</option>
				<option ng-selected="course.courseUseDifference=='他人共用' " value="他人共用">他人共用</option>
			</select>
		</div>
		<div class="end">
			<input name="git" type="submit" value="提交" style="background:#5ED8A9;"/>
			<input name="esc" type="reset" value="取消"  onclick="CloseDiv();formReset()" class="esc" />
		</div>
		</form>
	</div>

</div>

</div>
</div>
</div>
</@b.body>
 
 </html>
