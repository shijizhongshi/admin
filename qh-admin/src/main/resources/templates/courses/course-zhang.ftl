<#import "/layout/header.ftl" as h/> <#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="课程管理页面"/>
<link rel="stylesheet" href="/styles/admin.css" />
<script src="/scripts/course/chapter.js"></script>
<script src="/scripts/admin.js"></script>

<@b.body menu="sidebarmenu-course" submenu="sidebarmenu-course-chapter">
<div ng-controller="ChapterController">
	<input type="hidden" value="${typeId}" id="typeId"> <input
		type="hidden" value="${courseId}" id="courseId"> <input
		type="hidden" value="${courseTypeName}" id="courseTypeName">
	<input type="hidden" value="${courseTypeSubclassName}"
		id="courseTypeSubclassName">
	<div class="classify">
		<ul class="menu">

			<li ng-repeat="ctl in courseTypeList" class="list" ng-click="typeList(ctl.courseTypeName,ctl.id)">{{ctl.courseTypeName}}
				<ul class="items" ng-class="{'active':active==ctl.id}">
					<li ng-repeat="sub in courseTypeSubclass"
						ng-click="typeSub(ctl.courseTypeName,sub,$event)"
						ng-class="{'selected':typeSelected==sub.courseTypeSubclassName}">{{sub.courseTypeSubclassName}}</li>

				</ul>
			</li>

		</ul>
	</div>
	<div class="details">

		<div class="details-nav">
			<ul>
				<li><img src="/images/sjk-home.png" style="color: red;" />我的主页</li>
				<li>/</li>
				<li>网课资源管理</li>
				<li>/</li>
				<li>课程章管理</li>


			</ul>
		</div>
		<div class="details-frame">
			<div class="details-frame-content">

				<div class="select-3" style="width: 15%;margin-right:0%">
					<span>搜索课程</span>
					<form id="search">
						<input type="text" placeholder="请输入章名称" ng-model="courseChapterName" />
					</form>
				</div>
				<div>
					<input type="button" class="btn-lg im-key"
						ng-click="chapterBases()" value="立即检索" />
				</div>
			</div>
			<div class="manage">
				<ul class="show">

					<li ng-click="saveChapter()" style="background: #9DE879;"><span
						class="glyphicon glyphicon-plus"></span>&nbsp;添加章</li>
					<li ng-click="updateChapter()" style="background: #F9CD33;"><span
						class="glyphicon glyphicon-pencil"></span>&nbsp;修改章</li>
					<li ng-click="deleteChapter()" style="background: #F86846;"><span
						class="glyphicon glyphicon-trash"></span>&nbsp;删除章</li>
					<li style="width: 90px;" ng-click="chaptermove(1)"><span class="glyphicon glyphicon-sort"></span>&nbsp;上移</li>
				<li style="width: 90px;" ng-click="chaptermove(2)"><span
					class="glyphicon glyphicon-sort-by-attributes"></span>&nbsp;下移</li>
					<li ng-click="section()"><span
						class="glyphicon glyphicon-sort-by-attributes" class="move-down"></span>&nbsp;节管理</li>
					<li style="float: right; margin-right: 20px; background: none;"><img
						src="/images/sjk-f5.png" ng-click="reset()" name="changyi" /></li>

				</ul>
				<div class="admin-table">
					<table>
						<tr>
							<th>章名称</th>
							<th>主讲老师</th>
							<th>小节数量</th>
						</tr>

						<tr ng-repeat="c in chapterlist" ng-click="checkedChapter(c)"
							ng-class="{'selected':selected==c}">
							<th>{{c.courseChapterName}}</th>
							<th>{{c.courseLecturer}}</th>
							<th>{{c.courseSectionSize}}</th>
						</tr>
					</table>

					<div class="col-sm-6"></div>
					<div class="col-sm-6">
						<ul uib-pagination boundary-links="true" total-items="total"
							ng-model="page" items-per-page="pageSize" max-size="5"
							class="pagination-sm" previous-text="&lsaquo;"
							next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
							ng-change="chapterBases()">
						</ul>
					</div>
				</div>

				<!--弹窗-->
				<div class="poop" id="add" style="width: auto">
					<form id="myform">
						<h3>添加章</h3>

						<div class="select">
							<input type="text" ng-model="chapter.courseChapterName"
								placeholder="请输入章名称" />

						</div>
						<div class="select">
							<input type="text" ng-model="chapter.courseLecturer" class=""
								placeholder="主讲老师" />

						</div>
						<div class="select">
							<span>选择课程</span> <select ng-model="chapter.courseId"
								ng-options="item.id as item.courseName for item in courselist"
								ng-selected="selected==item.courseName">
							</select>
						</div>
						<p style="clear: both;">
						<div class="end">
							<input name="git" type="submit" value="提交"
								ng-show="chapterId==null" ng-click="addChapter()"
								style="background: #5ED8A9;" /> <input name="git" type="submit"
								value="修改" ng-show="chapterId!=null" ng-click="addChapter()"
								style="background: #5ED8A9;" /> <input name="esc" type="reset"
								value="取消" ng-click="cancel()" class="esc" />
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
.admin-table ol li {
	width: 16%
}

.admin-table ol li:nth-child(1) {
	width: 20%;
}

.poop .select {
	clear: both !important;
}

.poop {
	height: auto;
}
</style>
</@b.body>  
</html>
