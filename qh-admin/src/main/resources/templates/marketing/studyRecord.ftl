<#import "/layout/header.ftl" as h/> <#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="学习记录"/>
<link rel="stylesheet" href="/styles/admin.css" />
<link rel="stylesheet" href="/styles/management.css" />
<script src="/scripts/admin.js"></script>
<script src="/scripts/indent/excle.js"></script>
<script src="/scripts/marketing/studyRecord.js"></script>

</style>
<@b.body menu="sidebarmenu-marketing"
submenu="sidebarmenu-marketing-studyRecord">
<div ng-controller="studyRecordController">
	<div class="details" style="width: 100%">
		<div class="details-nav">
			<ul>
				<li><img src="/images/sjk-home.png" />我的主页</li>
				<li>/</li>
				<li>营销管理</li>
				<li>/</li>
				<li>学习记录</li>
			</ul>
		</div>
		<div class="details-frame">
			<div class="details-frame-content">
				<div class="select-3">
					<span>请选择日期</span> <input type="date" name="search"
						ng-model="date"
						class="ng-pristine ng-untouched ng-valid ng-empty">
				</div>
				<div class="select-3">
					<span>用户手机号</span> <input type="text" ng-model="mobile"
						placeholder="请输入手机号">
				</div>
				<!-- onkeyup="value=value.replace(/[^\w\.\/]/ig,'') ： 约束 不能输入特殊字符和空格？  -->
				<div class="select-3">
					<span>视频id</span> <input type="text" ng-model="videoId"
						placeholder="请输入视频id" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')">
				</div>
				<div style="float: left;">
					<input type="button" class="btn-lg im-key" ng-click="loaddata()"
						value="检索">
				</div>
				<div>
					<input type="button" class="btn-lg im-key" value="导出excle"
						onclick="method5('tableExcel')">
				</div>
			</div>
			<div class="manage">
				<div class="admin-table">

					<table id="tableExcel" >
						<tbody>
							<tr>
								<th>用户名</th>
								<th>视频名称</th>
								<th>专业</th>
								<th>观看时长</th>
								<th>视频总长</th>
							</tr>
							<tr ng-repeat="list in list" ng-click="checkList(list)"
								ng-class="{'selected':selected == list}">
								<th>{{list.userName}}</th>
								<th>{{list.sectionName}}</th>
								<th>{{list.courseTypeSubclassName}}</th>
								<th>{{list.play_duration/60 | number:1}}分钟</th>
								<th>{{list.video_duration/60 | number:1}}分钟</th>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="col-sm-6"></div>
				<div class="col-sm-6">
					<ul uib-pagination boundary-links="true" total-items="total"
						ng-model="page" items-per-page="numPerPage" max-size="5"
						class="pagination-sm" previous-text="&lsaquo;"
						next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
						ng-click="loaddata()">
					</ul>
				</div>

			</div>
		</div>
	</div>
</div>
</div>


</@b.body>

</html>