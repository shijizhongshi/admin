<#import "/layout/header.ftl" as h/> <#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="直播验证数据"/>
<link rel="stylesheet" href="/styles/admin.css" />
<link rel="stylesheet" href="/styles/management.css" />
<script src="/scripts/admin.js"></script>
<script src="/scripts/indent/excle.js"></script>
<script src="/scripts/marketing/liveVerify.js"></script>

<!--测试  -->
<style type="text/css">
.dd {
	border: solid 1px gray;
	width: 180px;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
}
</style>
<@b.body menu="sidebarmenu-marketing"
submenu="sidebarmenu-marketing-liveVerify">
<div ng-controller="liveVerifyController">
	<div class="details" style="width: 100%">
		<div class="details-nav">
			<ul>
				<li><img src="/images/sjk-home.png" />我的主页</li>
				<li>/</li>
				<li>营销管理</li>
				<li>/</li>
				<li>直播验证数据</li>
			</ul>
		</div>
		<div class="details-frame">
			<div class="details-frame-content">
			<div class="select-3">
					<span>学员注册时间</span> <input type="date" name="search"
						ng-model="fromdate"
						class="ng-pristine ng-untouched ng-valid ng-empty">
				</div>
				<div class="select-3"
					style="font-size: 1.6rem; width: 1%; text-align: center;">

					&nbsp;<span class="glyphicon glyphicon-hand-right"></span>
				</div>
				<div class="select-3">
					<span>&nbsp;</span> <input type="date" name="search"
						ng-model="todate"
						class="ng-pristine ng-untouched ng-valid ng-empty">
				</div>
				<div class="select-3">
					<span>手机号</span> <input type="text" ng-model="mobile"
						placeholder="请输入手机号">
				</div>
				<div class="select-3">
					<span>直播房间号</span> <input type="text" ng-model="roomId"
						placeholder="请输入房间号">
				</div>
				<div class="select-3">
					<!-- ng-options="a for a in nameList"  -->
					<span>专业</span> <img src="/images/sjk-xl.png"> <select
						ng-model="courseTypeSubclassName">
						<option value="">无专业</option>
						<option ng-repeat="name in nameList">{{name}}</option>
					</select>
				</div>
				<div style="float: left;">
					<input type="button" class="btn-lg im-key" ng-click="loaddata()"
						value="检索">
				</div>
				<div>
					<input type="button" class="btn-lg im-key" value="导出excle"
						ng-click="ExcelExport()">
				</div>
			</div>
			<div class="manage">
				<div class="admin-table">

					<table id="tableExcel">
						<tbody>
							<tr>
								<th>手机号</th>
								<th>用户属性</th>
								<th>校验码</th>
								<th>专业名称</th>
								<th>房间号</th>
								<th>最后一次操作时间</th>
							</tr>
							<tr ng-repeat="liveVerify in liveVerifyList" ng-click="checkLiveVerify(liveVerify)"
								ng-class="{'selected':selected == liveVerify}">
								<th>{{liveVerify.mobile}}</th>
								<th ng-show="{{liveVerify.isRegister==1}}">注册用户</th>
								<th ng-show="{{liveVerify.isRegister==0}}">游客用户</th>
								<th>{{liveVerify.token}}</th>
								<th style="max-width:20% ">{{liveVerify.courseTypeSubclassName}}</th>
								<th>{{liveVerify.roomId}}</th>
								<th ng-show="{{liveVerify.updatetime == null}}">{{liveVerify.addtime
									| date:'yyyy.MM.dd HH:mm:ss'}}</th>
								<th ng-show="{{liveVerify.updatetime != null}}">{{liveVerify.updatetime
									| date:'yyyy.MM.dd HH:mm:ss'}}</th>
							</tr>



						</tbody>
					</table>
					
					
				</div>
				<div class="col-sm-6"></div>
				<div class="col-sm-6">
					<ul uib-pagination boundary-links="true" total-items="total"
						ng-model="page" items-per-page="pageSize" max-size="5"
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