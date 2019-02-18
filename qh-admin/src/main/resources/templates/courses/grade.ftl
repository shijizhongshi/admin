<#import "/layout/header.ftl" as h/>
<#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="课程管理页面"/>
<link rel="stylesheet" href="/styles/admin.css" />
<script src="/scripts/course/grade.js"></script>
<script src="/scripts/admin.js"></script>
<@b.body menu="sidebarmenu-course" submenu="sidebarmenu-course-grade">
<div ng-controller="gradeController">
<div class="classify">
	<ul class="menu">
	
   <li class="list" ng-click="typeList('医师资格',1)" >医师资格 
      <ul class="items" ng-class="{'active':active==1}">
         <li ng-repeat="sub in courseTypeSubclass" ng-click="typeSub('医师资格',sub,$event)" ng-class="{'selected':typeSelected==sub.courseTypeSubclassName}">{{sub.courseTypeSubclassName}}</li>
        
      </ul>
   </li>
   <li class="list" ng-click="typeList('药师资格',2)">药师资格
      <ul class="items" ng-class="{'active':active==2}">
         <li ng-repeat="sub in courseTypeSubclass" ng-click="typeSub('药师资格',sub,$event)" ng-class="{'selected':typeSelected==sub.courseTypeSubclassName}">{{sub.courseTypeSubclassName}}</li>
        
      </ul>
      </li>
       <li class="list" ng-click="typeList('中医基础理论',3)">中医基础理论
      <ul class="items" ng-class="{'active':active==3}">
         <li ng-repeat="sub in courseTypeSubclass" ng-click="typeSub('中医基础理论',sub,$event)" ng-class="{'selected':typeSelected==sub.courseTypeSubclassName}">{{sub.courseTypeSubclassName}}</li>
      </ul>
      </li>
       <li class="list" ng-click="typeList('卫生资格',4)">卫生资格 
      <ul class="items" ng-class="{'active':active==4}">
        <li ng-repeat="sub in courseTypeSubclass" ng-click="typeSub('卫生资格',sub,$event)" ng-class="{'selected':typeSelected==sub.courseTypeSubclassName}">{{sub.courseTypeSubclassName}}</li>
        
      </ul>
      </li>
      <li class="list" ng-click="typeList('健康管理师',5)">健康管理师</a> 
      <ul class="items" ng-class="{'active':active==5}">
         <li ng-repeat="sub in courseTypeSubclass" ng-click="typeSub('健康管理师',sub,$event)" ng-class="{'selected':typeSelected==sub.courseTypeSubclassName}">{{sub.courseTypeSubclassName}}</li>
      </ul>
      </li>
      
      
      
      </ul>
      </div>
<div class="details">
	<div class="details-nav">
		<ul>
			<li><img src="/images/sjk-home.png" style="color: red;"/>我的主页</li>
			<li>/</li>
		<li>班级管理</li>
		</ul>
	</div>
<div class="details-frame" >
	<div class="details-frame-content">

	<div class="select-2" style="width:13%;float:left">
	<span>搜索班级</span>
	<form id="search">
	<input ng-model="className" type="text"  style=" text-indent:2em;"/></form>
	</div>
	<div>
		<input type="button" class="btn-lg im-key" ng-click="classBases()"value="检索"  />
	</div>
	</div>
<div class="manage">
	<ul class="show">

			<li  ng-click="add()" style="background:#9DE879;"><span class="glyphicon glyphicon-plus"></span>&nbsp;班级添加</li>
		<li ng-click="update()" style="background:#F9CD33;"><span class="glyphicon glyphicon-pencil"></span>&nbsp;班级修改</li>
		<li ng-click="deleteClass()" style="background:#F86846;"><span class="glyphicon glyphicon-trash"></span>&nbsp;班级删除</li>
		<li><span class="glyphicon glyphicon-sort" class="move-up"></span>&nbsp;上移</li>
		<li><span class="glyphicon glyphicon-sort-by-attributes" class="move-down"></span>&nbsp;下移</li>
		<!--<li  onclick="showDiv3()"  style="width: 200px;"><span class="glyphicon glyphicon-briefcase"></span>&nbsp;班级课程及赠送管理</li>
		<li ><span class="glyphicon glyphicon-briefcase"></span>&nbsp;关闭城市管理</li>-->
         <li style="float: right;margin-right:20px;background:none;"><img src="/images/sjk-f5.png" name="changyi" ng-click="refresh()"/></li>
	</ul>
	<div class="admin-table">

<table>
	<tr>
		<th>班级名称</th>

	<th>班级图片</th>
	<th>班级价格</th>
	<th>班级折扣价</th>
	<th>主讲老师</th>
	<th>班级年限</th>
	<th>是否可见</th>
	</tr>
 <tr ng-repeat="c in classlist" ng-click="checkedclass(c)" ng-class="{'selected':selected==c}">
	<th>{{c.className}}</th>

	<th><img ng-src="{{c.imgUrl}}" style="width:50px;height:30px;"/></th>
	<th>{{c.classPrice}}</th>
	<th>{{c.classDiscountPrice}}</th>
	<th>{{c.courseLecturer}}</th>
	<th>{{c.classYear}}</th>
	<th ng-show="{{c.isshow==1}}">是</th>
	<th ng-show="{{c.isshow==0}}">否</th>
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
	<h3>班级添加</h3>
	<!--<span style="margin-right:20px ;">已选专业类型:</span> <span>lin</span>-->
		<div class="grade-add">
			<div class="grade-left" style="padding-right: 5%;">
		
				<div class=" select-2" style="width:100%;">
						<span>班级名称：</span>
			<input type="text" ng-model="classes.className" class=""placeholder="请输入班级名称" />
		</div>
	
	<div style="width: 100%;height:90px;clear: both;">	<div class=" select-2" style="float: left;">
		<img src="/images/sjk-xl.png" ng-click="openTemplate()"/>
						<span>班级模板：</span>
						
			<select ng-model="classes"  ng-change="checkedTemplate(classes)"  ng-options="classes as classes.templateName for classes in templatelist" ng-selected="selected==classes" >
			</select>
			<!--<ul ng-show="tems==1">
			<li style="float:none;" ng-repeat="classes in templatelist" ng-click="checkedTemplate(classes)" ng-model="classes.templateId">{{classes.templateName}}</li>
			</ul>-->
		</div>
		<div class=" select-2" style="float:right;">
			<img src="/images/sjk-xl.png"/>
						<span>主讲老师：</span>
			<select ng-model="classes.courseLecturer" ng-options="t.name as t.name for t in teacherlist" ng-selected="selected==t.name">
			</select>
		</div></div>
			<div style="width: 100%;height:90px;clear: both;">	<div class=" select-2" style="float: left;">
	
						<span>班级价格：</span>
			<input ng-model="classes.classPrice" type="text" class=""placeholder="请输入班级名称"  />
		</div>
		<div class=" select-2" style="float: right;">
	
						<span>班级折扣价：</span>
			<input type="text"  ng-model="classes.classDiscountPrice" class=""placeholder="请输入班级名称"  />
		</div></div>
		
		
		<div class=" select-2"style="width:100%;">
						<span>最多折扣豆：</span>
			<input type="number" ng-model="classes.maxdoudou" class=""placeholder="请输入最多使用的豆豆数" />
		</div>
		<div style="width: 100%;height:90px;clear: both;">		
		
		
		
		<div class=" select-2" style="float: left;">
	
	
	
	
						<span>班级年份：</span>
			<input type="text"  ng-model="classes.classYear" class=""placeholder="请输入班级年份"  />
		</div>
		<div class=" select-2" style="float: right;">
	
						<span>总课课时数：</span>
			<input type="text" ng-model="classes.allTime" class=""placeholder="请输入课时数"  />
		</div></div>
<!--<div class=" select-2">
	<img src="/images/sjk-xl.png"/>
		<span>显示级别：</span>
			<select>
				<option disabled selected style='display:none;'>查找</option>
				<option></option>
				<option></option>
			</select>
		</div>-->

<ul>
<li>是否推荐</li>  
<li><input type="radio" name="tuijian"  ng-model="classes.isremmend" ng-value="1" /> 是</li>
<li><input type="radio" name="tuijian" ng-model="classes.isremmend" ng-value="0" />否</li>
 </ul>
<!--<ul>
<li>是否关闭  </li>
<li><input type="radio" name="tuijian" /> 是</li> 
<li><input type="radio" name="tuijian" />否</li>
</ul>-->
<ul>
<li>是否显示 </li>
<li><input type="radio"  ng-model="classes.isshow" ng-value="1"/> 是</li> 
<li><input type="radio"  ng-model="classes.isshow" ng-value="0"/>否</li>
</ul>
			
			<div class="costs-uploadfile-div">   
				 <input type="file" name="file" id="fileField"  onchange="angular.element(this).scope().uploadmainimage(this)"  accept="image/*" /> 
    <input type='text' id="textfield" style="border: solid 1px #B1B1B1;"/> 
    <button class="allBtn costs-marl15">班级图片</button>
     <div class="costs-img"> <img ng-src="{{imgUrl}}"/></div></div>
  
	</div>
			</div>
		
		<div class="grade-center">
			<div  class="grade-text">
				<span>适宜人群</span>
			    <textarea ng-model="classes.properPeople"></textarea>
			</div>
			<div  class="grade-text">
				<span>班级承诺</span>
		    <textarea ng-model="classes.promises"></textarea>
			</div>
			<div  class="grade-text">
				<span>班级特色</span>
		    <textarea ng-model="classes.features"></textarea>
			</div>
			<div  class="grade-text">
				<span>班级介绍</span>
				    <textarea ng-model="classes.introduce"></textarea>
			</div>
		</div>
		<div class="grade-right">
<div>
<span>课程资源</span>
<label ng-repeat="course in courselist">
<p><input type="checkbox" ng-checked="isSelected(course)" ng-click="updateSelection($event,course)" />{{course.courseName}}</p>
</label>
</div>
<div>
	<span>班级教师</span>
	<label ng-repeat="teacher in teacherlist">
  <p><input type="checkbox" ng-checked="isTeacherSelected(teacher)" ng-click="updateTeacherSelection($event,teacher)" />{{teacher.name}}</p>
</label>
		</div>
		</div>
<div class="grade-add-bottom">
	<span>班级详情</span>
    <textarea ng-model="classes.detail"></textarea>
</div>
		</form>
		<div class="end">
			<input name="git" type="submit" ng-show="classId==null" ng-click="addClass()" value="提交" style="background:#5ED8A9;"/>
			<input name="git" type="submit" ng-show="classId!=null" ng-click="addClass()" value="修改" style="background:#5ED8A9;"/>
			<input name="esc" type="reset" value="取消"  ng-click="rest()" class="esc" />
		</div>
	</div>
</div>
	
</div>

</div>
</div>
</body>
 <style type="text/css">
	
	.poop{overflow-y: scroll;width: 80%;height: 600px;position: absolute;left: 5%;top: 5%;}
	@media screen and (max-width: 1401px){.poop{width:850px;left:0%;top: 5%;}}
	.poop span{font-size: 1.5rem;}
	.grade-add{width: 100%;border-top:1px solid #F5F6F8;height: 80%;padding-top: 10px;margin-top:10px ;}
	.grade-left,.grade-center,.grade-right{width: 33%;float: left;height:auto;overflow:auto;}
	.grade-left ul{height: 50px;line-height: 50px; font-size: 2rem;}
	.grade-left ul li{float: left;margin-right: 5px;}
	.grade-left ul li:nth-child(1){margin-right: 10px;}
	.grade-center .grade-text{width: 80%;}
	.grade-center .grade-text textarea{width: 100%;height:100px;font-size:1.5rem}
	.grade-right label{width: 80%;height: auto;background:#EDEEF0;border-radius:20px;text-indent: 2em;font-size: 1.6rem;}
	.grade-right label input{margin-right: 5px;width:17px;height: 17px;}
	.grade-add-bottom{width: 100%;clear:both;}
	.grade-add-bottom textarea{width: 100%;height: 250px;background:#EDEEF0;border-radius:20px;text-indent: 2em;}
	div.costs-uploadfile-div{
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
