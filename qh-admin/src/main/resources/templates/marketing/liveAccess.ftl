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
					<span>用户手机号</span> <input type="text" ng-model="mobile"
						placeholder="请输入手机号">
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
							<tr>
								<th>用户名</th>
								<th>视频名称</th>
								<th>专业</th>
								<th>观看时长</th>
								<th>视频总长</th>
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