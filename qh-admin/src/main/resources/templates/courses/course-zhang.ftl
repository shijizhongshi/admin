<#import "/layout/header.ftl" as h/>
<#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="课程管理页面"/>
<link rel="stylesheet" href="/styles/admin.css" />
<script src="/scripts/course/chapter.js"></script>
<script src="/scripts/admin.js"></script>

<@b.body menu="sidebarmenu-course" submenu="sidebarmenu-course-chapter">
<div ng-controller="ChapterController">
<input type="hidden" value="${typeId}" id="typeId" >
<input type="hidden" value="${courseId}" id="courseId">
<div class="classify">
	<ul class="menu">
	
   <li class="list" ng-click="typeList(1)" >医师资格 
      <ul class="items" ng-class="{'active':active==1}">
         <li ng-repeat="sub in courseTypeSubclass" ng-click="typeSub('医师资格',sub)">{{sub.courseTypeSubclassName}}</li>
        
      </ul>
   </li>
   <li class="list" ng-click="typeList(2)">药师资格
      <ul class="items" ng-class="{'active':active==2}">
         <li ng-repeat="sub in courseTypeSubclass" ng-click="typeSub('药师资格',sub)">{{sub.courseTypeSubclassName}}</li>
        
      </ul>
      </li>
       <li class="list" ng-click="typeList(3)" >中医基础理论
      <ul class="items" ng-class="{'active':active==3}">
         <li ng-repeat="sub in courseTypeSubclass" ng-click="typeSub('中医基础理论',sub)">{{sub.courseTypeSubclassName}}</li>
      </ul>
      </li>
       <li class="list" ng-click="typeList(4)">卫生资格 
      <ul class="items" ng-class="{'active':active==4}">
        <li ng-repeat="sub in courseTypeSubclass" ng-click="typeSub('卫生资格',sub)">{{sub.courseTypeSubclassName}}</li>
        
      </ul>
      </li>
      <li class="list"><a href="#">健康管理师</a> 
      <ul class="items" ng-class="{'active':active==5}">
         <li ng-repeat="sub in courseTypeSubclass" ng-click="typeSub('健康管理师',sub)">{{sub.courseTypeSubclassName}}</li>
      </ul>
      </li>
      
      
      
      </ul>
      </div>
<div class="details">

	<div class="details-nav">
		<ul>
			<li><img src="/images/sjk-home.png" style="color: red;"/>我的主页</li>
			<li>/</li>
		<li>课程章节管理</li>

		
		</ul>
	</div>
<div class="details-frame" >
	<div class="details-frame-content">

	<div class="select" style="float:left;margin-right:15px;">搜索课程<br />
	<form id="search">
	<input type="text" name="search" style="text-indent:2em;"/></form>
	</div>
	</div>
<div class="manage">
	<ul style="height: 80px;" class="show">

			<li  ng-click="saveChapter()" style="margin-left: 70px;background:#9DE879;"><span class="glyphicon glyphicon-plus"></span>&nbsp;添加章</li>
		<li ng-click="updateChapter()" style="background:#F9CD33;"><span class="glyphicon glyphicon-pencil"></span>&nbsp;修改章</li>
		<li ng-click="deleteChapter()" style="background:#F86846;"><span class="glyphicon glyphicon-trash"></span>&nbsp;删除章</li>
		<li><span class="glyphicon glyphicon-sort" class="move-up"></span>&nbsp;上移</li>
		<li><span class="glyphicon glyphicon-sort-by-attributes" class="move-down"></span>&nbsp;下移</li>
		<li ng-click="section()"><span class="glyphicon glyphicon-sort-by-attributes" class="move-down"></span>&nbsp;资源节管理</li>
         <li style="float: right;margin-right: 100px;background:none;"><img src="/images/sjk-f5.png" ng-click="chapterBases()" name="changyi"/></li>

	</ul>
	<div class="admin-table">
 <table>
	<tr>
	<th>章名称</th>
	<th >主讲老师</th>
	<th >小节数量</th >
	</tr>

 <tr ng-repeat="c in chapterlist" ng-click="checkedChapter(c)" ng-class="{'selected':selected==c}">
		<th>{{c.courseChapterName}}</th>
	<th>{{c.courseLecturer}}</th>
	<th>{{c.courseSectionSize}}</th>
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
	<h3>添加章</h3>
	
		<div class=" select"  style="width: 370px;height:53px;border-bottom: 1px solid #F5F6F8;margin-top:3px;">
			<input type="text" ng-model="chapter.courseChapterName" placeholder="请输入章名称" style="width: 230px;text-indent: 2em;" />
			
		</div>
		<div class="select"  style="width: 405px;height:48px;margin-top:5px;">
			<input type="text" ng-model="chapter.courseLecturer"  class=""placeholder="主讲老师" style="text-indent: 2em;margin-right:15px;"/>
		
		</div>
		<div class="select"  style="width: 405px;height:48px;margin-top:5px;">
			<input type="text" ng-model="chapter.courseSectionSize"  class="" placeholder="小节数" style="text-indent: 2em;margin-right:15px;"/>
		
		</div>
		<div>
		选择课程
		<select ng-model="chapter.courseId"  ng-options="item.id as item.courseName for item in courselist" ng-selected="selected==item.courseName">
		</select>
		</div>
		<p style="clear: both;">
		<div class="end">
			<input name="git" type="submit" value="提交" ng-show="chapterId==null" ng-click="addChapter()" style="background:#5ED8A9;"/>
			<input name="git" type="submit" value="修改" ng-show="chapterId!=null" ng-click="addChapter()" style="background:#5ED8A9;"/>
			<input name="esc" type="reset" value="取消"  onclick="CloseDiv();formReset()" class="esc" />
		</div>
			</p>
		</form>
	</div>

</div>

</div>
</div>
</div>
</body>
<style type="text/css">
	.admin-table ol li{width: 16% }
	.admin-table ol li:nth-child(1){width: 20%;}
</style>
</@b.body>
 </html>
