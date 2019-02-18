<#import "/layout/header.ftl" as h/>
<#import "/layout/body.ftl" as b/>
<!DOCTYPE html>
<html lang="en">
<@h.header title="课程管理页面"/>
<link rel="stylesheet" href="/styles/admin.css" />
<script src="/scripts/course/course.js"></script>
<script src="/scripts/admin.js"></script>
<@b.body menu="sidebarmenu-course" submenu="sidebarmenu-course-course">
<div ng-controller="CourseController">

<div class="classify">
	<ul class="menu">
	
   <li class="list" ng-click="typeList('医师资格',1)" >医师资格 
      <ul class="items" ng-class="{'active':active==1}">
         <li  ng-repeat="sub in courseTypeSubclass" ng-click="courseSub('医师资格',sub,$event)" ng-class="{'selected':typeSelected==sub.courseTypeSubclassName}">{{sub.courseTypeSubclassName}}</li>
        
      </ul>
   </li>
   <li class="list" ng-click="typeList('药师资格',2)">药师资格
      <ul class="items" ng-class="{'active':active==2}">
         <li ng-repeat="sub in courseTypeSubclass" ng-click="courseSub('药师资格',sub,$event)" ng-class="{'selected':typeSelected==sub.courseTypeSubclassName}">{{sub.courseTypeSubclassName}}</li>
        
      </ul>
      </li>
       <li class="list" ng-click="typeList('中医基础理论',3)">中医基础理论
      <ul class="items" ng-class="{'active':active==3}">
         <li ng-repeat="sub in courseTypeSubclass" ng-click="courseSub('中医基础理论',sub,$event)" ng-class="{'selected':typeSelected==sub.courseTypeSubclassName}">{{sub.courseTypeSubclassName}}</li>
      </ul>
      </li>
       <li class="list" ng-click="typeList('卫生资格 ',4)">卫生资格 
      <ul class="items" ng-class="{'active':active==4}">
        <li ng-repeat="sub in courseTypeSubclass" ng-click="courseSub('卫生资格 ',sub,$event)" ng-class="{'selected':typeSelected==sub.courseTypeSubclassName}">{{sub.courseTypeSubclassName}}</li>
        
      </ul>
      </li>
      <li class="list" ng-click="typeList('健康管理师',5)">健康管理师
      <ul class="items" ng-class="{'active':active==5}">
         <li ng-repeat="sub in courseTypeSubclass" ng-click="courseSub('健康管理师',sub,$event)" ng-class="{'selected':typeSelected==sub.courseTypeSubclassName}">{{sub.courseTypeSubclassName}}</li>
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

	<div class="select-2">
	<span>搜索课程</span>
	<form id="search">
	<input type="text" ng-model="courseName"/></form>
	</div>
	<div><input type="button" class="btn-lg im-key" ng-click="courseBases()" value="立即检索"  /></div>	
</div>
<div class="manage">
	<ul class="show">

			<li  ng-click="add()" style="background:#9DE879;"><span class="glyphicon glyphicon-plus"></span>&nbsp;添加课程</li>
		<li ng-click="updateCourse()" style="background:#F9CD33;"><span class="glyphicon glyphicon-pencil"></span>&nbsp;修改课程</li>
		<li ng-click="deleteCourse()" style="background:#F86846;"><span class="glyphicon glyphicon-trash"></span>&nbsp;删除课程</li>
		<li><span class="glyphicon glyphicon-sort" class="move-up"></span>&nbsp;上移</li>
		<li><span class="glyphicon glyphicon-sort-by-attributes" class="move-down"></span>&nbsp;下移</li>
		<li  ng-click="chapter()"><span class="glyphicon glyphicon-briefcase"></span>&nbsp;资源章节管理</li>
         <li style="float: right;margin-right: 20px;background:none;"><img src="/images/sjk-f5.png" name="changyi" ng-click="reset()"/></li>
	</ul>
	<div class="admin-table">

  <table>

	<tr>
		<th>课程名称</th>

	<th>课程总数</th>
	<th>课程价格</th>
	<th>课程折扣</th>
	<th>是否显示</th>
	</tr>


 <tr ng-repeat="c in courselist" ng-click="checkedCourse(c)" ng-class="{'selected':selected==c}">
	<th>{{c.courseName}}</th>
	<th >{{c.courseChapterSize}}</th>
	<th >{{c.coursePrice}}</th >
	<th >{{c.courseDiscountPrice}}</th >
	<th ng-show="{{c.courseShow==1}}">是</th >
	<th ng-show="{{c.courseShow==0}}">否</th >

	</tr>
	</table>
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

	<div class=" select" style="width: 100%;height:48px;">
			<input type="text" ng-model="course.courseName" placeholder="请输入课程名称" style="margin-right:5%;" />
				<input type="text" placeholder="输入课程年份"  ng-model="course.courseYear"/>
		</div>
		<div>
		<div class="select"style="width: 100%;height:48px;">
			<input type="text"  ng-model="course.coursePrice" placeholder="输入课程价格" style="margin-right:5%;"/>
			<input type="text" ng-model="course.courseDiscountPrice" placeholder="输入折扣价格" />
		</div>
		
		<div class=" select-2" style="float: left;margin-right:5%;width:35%; ">
			<img src="/images/sjk-xl.png"/>
			<span>是否展示</span>
			<select ng-model="course.courseShow">
				<option ng-selected="course.courseShow==1" value="1">是</option>
				<option ng-selected="course.courseShow==0" value="0">否</option>
			</select>
		</div>
		<div class="select-2" style="float: left;width:35%;">
			<img src="/images/sjk-xl.png"/>
			<span>是否精品</span>
			<select ng-model="course.courseExcellent">
				<option ng-selected="course.courseExcellent==1" value="1">是</option>
				<option ng-selected="course.courseExcellent==0" value="0">否</option>
			</select>
		</div>
		</div>
	
<div class="select-2" style="float: left;margin-right:5%;width:35%; ">
			<img src="/images/sjk-xl.png"/>
			<span>选择类别</span>
			<select ng-model="course.courseResourceType">
			
				<option ng-selected="course.courseResourceType=='基础课' " value="基础课">基础课</option>
				<option ng-selected="course.courseResourceType=='实践技能' " value="实践技能">实践技能</option>
				<option ng-selected="course.courseResourceType=='应试技巧' " value="应试技巧">应试技巧</option>
				<option ng-selected="course.courseResourceType=='冲刺课' " value="冲刺课">冲刺课</option>
				<option ng-selected="course.courseResourceType=='习题精讲' " value="习题精讲">习题精讲</option>
				<option ng-selected="course.courseResourceType=='包过套餐' " value="包过套餐">包过套餐</option>
			</select>
		</div>

			<div class="select-2" style="float: left;width:35%; ">
			<img src="/images/sjk-xl.png"/>
			<span>用途</span>
			<select ng-model="course.courseUseDifference">
				
				<option ng-selected="course.courseUseDifference=='自用课程' " value="自用课程">自用课程</option>
				<option ng-selected="course.courseUseDifference=='部门共用' " value="部门共用"> 部门共用</option>
				<option ng-selected="course.courseUseDifference=='公司共用' " value="公司共用">公司共用</option>
				<option ng-selected="course.courseUseDifference=='他人共用' " value="他人共用">他人共用</option>
			</select>
		</div>
	
		<div class="costs-uploadfile-div">   
				 上传课程图片<input type="file" name="file"   accept="image/*"  value="上传课程图片" onchange="angular.element(this).scope().uploadmainimage(this)" /> 
 
  <div class="costs-img"> <img  ng-src="{{course.courseImg}}" /></div></div>

		
		<div class="end">
			<input name="git" type="submit" value="提交" ng-show="courseId==null" ng-click="addCourse()" style="background:#5ED8A9;"/>
			<input name="git" type="submit" value="修改" ng-show="courseId!=null" ng-click="addCourse()" style="background:#5ED8A9;"/>
			<input name="esc" type="reset" value="取消"  ng-click="cancel()" class="esc" />
		</div>
		</form>
	</div>

</div>

</div>
</div>
</div>
<style type="text/css">
.details-frame-content .select-2{float: left; margin-right: 15px; width: 18%;}
@media screen and (max-width: 901px) {.details-frame-content .select-2{width: 90%;}}

</style>

</@b.body>
 
 </html>
