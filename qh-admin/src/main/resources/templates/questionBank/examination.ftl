<#import "/layout/header.ftl" as h/>
<#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="试卷管理"/>
<link rel="stylesheet" href="/styles/admin.css" />
<link rel="stylesheet" href="/styles/management.css" />
<script src="/scripts/admin.js"></script>
<script src="/scripts/student/management.js"></script>
<script src="/scripts/indent/excle.js"></script>
<@b.body menu="sidebarmenu-questionBank" submenu="sidebarmenu-questionBank-examination">
<div >

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


	<div class="details" >
		<div class="details-nav">
			<ul>
				<li><img src="/images/sjk-home.png" />我的主页</li>
				<li>/</li>
				<li>题库管理</li>
				<li>/</li>
				<li>试卷管理</li>
			</ul>
		</div>
<div class="details-frame">
	
<div class="manage">
	<ul class="show">

			<li style="background:#9DE879;" onclick="showDiv()"><span class="glyphicon glyphicon-plus" ></span>&nbsp;添加试卷</li>
		<li style="background:#F9CD33;"><span class="glyphicon glyphicon-pencil"></span>&nbsp;修改试卷</li>
		<li style="background:#F86846;"><span class="glyphicon glyphicon-trash"></span>&nbsp;删除试卷</li>
		<li  onclick="showDiv2()"><span class="glyphicon glyphicon-sort"></span>&nbsp;上移</li>
		<li><span class="glyphicon glyphicon-sort"></span>&nbsp;下移</li>
		<li ><span class="glyphicon glyphicon-briefcase"></span>&nbsp;试卷单元管理</li>
 
	</ul>
	<div class="admin-table">

  <table id="tableExcel">

	<tbody>
	<tr>
		<th>试卷名称</th>
<th>单元数量</th>
	<th>价格</th>
	<th>折扣价</th>
	<th>是否显示</th>
	</tr>
	<tr>
	<th>试卷名称</th>
<th>单元数量</th>
	<th>价格</th>
	<th>折扣价</th>
	<th>是否显示</th>
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
	<!--添加修改试卷-->
		<div class="poop" id="add" >
		<form id="myform" class="ng-pristine ng-valid">
	<h3>{{}}试卷</h3>
	<p style="padding:10px 0;">专业类型：&nbsp;{左边的专业}</p>
<div class="select-2">
		<span>试卷名称<i class="bitian">*</i></span>
<input type="text" class="ng-pristine ng-untouched ng-valid ng-empty" placeholder="请输入试卷名称" >
	</div>
	<div class="select-2">
		<span>试卷价格<i class="bitian">*</i></span>
<input type="text" class="ng-pristine ng-untouched ng-valid ng-empty" placeholder="请输入试卷价格" >
	</div>
	<div class="select-2">
		<span>折扣价格<i class="bitian">*</i></span>
<input type="text" class="ng-pristine ng-untouched ng-valid ng-empty" placeholder="请输入折扣价格" >
	</div>
	<div class="select-2">
		<span>是否显示<i class="bitian">*</i></span>
		<img src="/images/sjk-xl.png">
		<select  class="ng-pristine ng-untouched ng-valid ng-empty">
		<option value="? undefined:undefined ?"></option>
			<option ></option>
			<option ></option>
		</select>
	</div>
		
		<div class="end">
			<input name="git" type="submit" value="提交" ng-show="courseId==null" ng-click="addCourse()" style="background:#5ED8A9;">
			<input name="git" type="submit" value="修改" ng-show="courseId!=null" ng-click="addCourse()" style="background:#5ED8A9;" class="ng-hide">
			<input name="esc" type="reset" value="取消" onclick="CloseDiv()" class="esc">
		</div>
		</form>
	</div>

</div>

</div>










	</div>
</div>
</div>


</@b.body>

</html>