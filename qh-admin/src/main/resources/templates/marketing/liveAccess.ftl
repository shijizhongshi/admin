<#import "/layout/header.ftl" as h/> <#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="直播访问记录"/>
<link rel="stylesheet" href="/styles/admin.css" />
<link rel="stylesheet" href="/styles/management.css" />
<script src="/scripts/admin.js"></script>
<script src="/scripts/indent/excle.js"></script>
<script src="/scripts/marketing/liveAccess.js"></script>

</style>
<@b.body menu="sidebarmenu-marketing"
submenu="sidebarmenu-marketing-liveAccess">
<div ng-controller="liveAccessController">
	<div class="details" style="width: 100%">
		<div class="details-nav">
			<ul>
				<li><img src="/images/sjk-home.png" />我的主页</li>
				<li>/</li>
				<li>营销管理</li>
				<li>/</li>
				<li>直播访问记录</li>
			</ul>
		</div>
		<div class="details-frame">
			<div class="details-frame-content">
				<div class="select-3">
					<span>学员状态</span> <img src="/images/sjk-xl.png"> <select
						ng-model="notToEnter">
						<option value="">已进入直播间</option>
						<option value="1">未进入直播间</option>
					</select>
				</div>
				<div class="select-3">
					<span>直播Id</span> <input type="text" ng-model="liveId"
						placeholder="请输入直播Id">
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
								<th>用户昵称</th>
								<th>用户地域</th>
								<th>进入时间</th>
								<th>离开时间</th>
								<th>直播观看时长</th>
								<th>终端类型</th>
							</tr>
							<tr ng-repeat="list in list">
								<th>{{list.viewerName}}</th>
								<th>{{list.city}}</th>
								<th>{{list.enterTime}}</th>
								<th>{{list.leaveTime}}</th>
								<th ng-show="{{list.watchTime != null}}">{{list.watchTime/60}}</th>
								<th ng-show="{{list.watchTime == null}}">未观看直播</th>
								<th ng-show="{{list.terminal == 0}}">电脑端</th>
								<th ng-show="{{list.terminal == 1}}">移动端</th>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="col-sm-6"></div>
				<div class="col-sm-6">
					<ul uib-pagination boundary-links="true" total-items="total"
						ng-model="pageindex" items-per-page="pagenum" max-size="5"
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