<#import "/layout/header.ftl" as h/> <#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="学习记录管理页面"/>
<link rel="stylesheet" href="/styles/admin.css" />
<script src="/scripts/admin.js"></script>
<script src="/scripts/student/videorecord.js"></script>
<style type="text/css">
.selected {
	background-color: #c1ddec
}
</style>
<@b.body menu="sidebarmenu-student" submenu="sidebarmenu-student-videorecord">
<div class="details" ng-controller="videorecordController"
	style="width: 100%">
	<div class="details-nav">
		<ul>
			<li><img src="/images/sjk-home.png" style="color: red;" />我的主页</li>
			<li>/</li>
			<li>学员管理</li>
			<li>/</li>
			<li>学习记录管理</li>


		</ul>
	</div>
	<div class="details-frame">
		<div class="details-frame-content">
			<div class="select-3" style="width: 15%;margin-right:15px" >
				<span>手机号(必填)</span>
				<input type="text" placeholder="请输入学员手机号码" ng-model="mobile" class="ng-pristine ng-untouched ng-valid ng-empty">

			</div>
			<div class="select-3" style="width: 15%;margin-right:15px" >
				<span>课程名称</span>
				<input type="text" placeholder="请输入课程名称" ng-model="courseName" class="ng-pristine ng-untouched ng-valid ng-empty">

			</div>
			<div class="select-3" style="width: 15%;margin-right:15px" >
				<span>章名称</span>
				<input type="text" placeholder="请输入章名称" ng-model="chapterName" class="ng-pristine ng-untouched ng-valid ng-empty">

			</div>
			<div class="select-3" style="width: 15%;margin-right:15px" >
				<span>节名称</span>
				<input type="text" placeholder="请输入节名称" ng-model="sectionName" class="ng-pristine ng-untouched ng-valid ng-empty">

			</div>


			<div style="float: left;">
				<input type="button" class="btn-lg im-key" ng-click="videoRecordListToOne()"
					value="立即检索"  style="background: #E9484D" />
			</div>

		</div>
		<div class="manage">
			<ul class="show">

				<li style="float: right; margin-right: 20px; background: none;"><img
					src="/images/sjk-f5.png" name="changyi" ng-click="refresh()" /></li>
			</ul>
			<div class="admin-table">

				<div class="panel-body" style="padding: 0;">
					<div class="table-responsive">

						<table cellspacing="0"
							class="table table-small-font table-bordered table-striped">

							<tr>
								<th data-priority="1">课程名称</th>
								<th data-priority="1">章名称</th>
								<th data-priority="1">节名称</th>
								<th data-priority="1">开始时间</th>
								<th data-priority="1">结束时间</th>
								<th data-priority="1">观看时长</th>
							</tr>


							<tr ng-repeat="vr in videoRecordlist" ng-click="checkedvideoRecord(vr)"
								ng-class="{'selected':selected==vr}">
								<td>{{vr.courseName}}</td>
								<td>{{vr.chapterName}}</td>
								<td>{{vr.sectionName}}</td>
								<td>{{vr.startTime | date:'yyyy.MM.dd HH:mm:ss'}}</td>
								<td>{{vr.endtime | date:'yyyy.MM.dd HH:mm:ss'}}</td>
								<td>{{vr.howlong}}</td>
							</tr>

						</table>
					</div>
					<div class="col-sm-6"></div>
					<div class="col-sm-6">
						<ul uib-pagination boundary-links="true" total-items="total"
							ng-model="page" items-per-page="pageSize" max-size="5"
							class="pagination-sm" previous-text="&lsaquo;"
							next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
							ng-click="videoRecordList()">
						</ul>
					</div>
				</div>


			</div>


		</div>

	</div>
</div>
</@b.body>    
</html>
