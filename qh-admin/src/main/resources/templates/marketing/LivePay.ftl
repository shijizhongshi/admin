<#import "/layout/header.ftl" as h/> <#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="直播访问记录"/>
<link rel="stylesheet" href="/styles/admin.css" />
<link rel="stylesheet" href="/styles/management.css" />
<script src="/scripts/admin.js"></script>
<script src="/scripts/marketing/LivePay.js"></script>

</style>
<@b.body menu="sidebarmenu-marketing"
submenu="sidebarmenu-marketing-LivePay">
<div ng-controller="livePayController">
	<div class="details" style="width: 100%">
		<div class="details-nav">
			<ul>
				<li><img src="/images/sjk-home.png" />我的主页</li>
				<li>/</li>
				<li>营销管理</li>
				<li>/</li>
				<li>直播购买记录</li>
			</ul>
		</div>
		<div class="details-frame">
			<div class="details-frame-content">
				<div class="select-3">
					<span>学员状态</span> <img src="/images/sjk-xl.png"> <select
						ng-model="status">
						<option value="">查看全部</option>
						<option value="1">已支付成功</option>
						<option value="0">已生成订单</option>
					</select>
				</div>
				<div class="select-3">
					<span>直播名称</span> <input ng-model="livename"
						placeholder="请输入直播名称">
				</div>
				<div class="select-3" style="width:15%">
								<span>直播时间<i class="bitian">*</i></span> 
								<input type="datetime-local" ng-model="startTime" >
							</div>
				<div style="float: left;">
					<input type="button" class="btn-lg im-key" ng-click="LivePayList()"
						value="检索">
				</div>
				<div>
					<input type="button" class="btn-lg im-key" value="导出excle"
						ng-click="ExcelExport()">
				</div>
			</div>
			<div class="manage">
				<div class="admin-table">

					<table id="tableExcel" >
						<tbody>
							<tr>
								<th>直播名称</th>
								<th>手机号码</th>
								<th>真实姓名</th>
								<th>价格</th>
								<th>折扣价格</th>
								<th>订单情况</th>
								<th>订单生成时间</th>
							</tr>
							<tr ng-repeat="livepay in LivePaylist" ng-click="checkList(livepay)"
								ng-class="{'selected':selected == livepay}">
								<th>{{livepay.livename}}</th>
								<th>{{livepay.mobile}}</th>
								<th>{{livepay.realname}}</th>
								<th>{{livepay.price}}</th>
								<th>{{livepay.discount}}</th>
								<th>{{livepay.status}}</th>
								<th>{{livepay.addtime | date:'yyyy.MM.dd HH:mm:ss'}}</th>
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
						ng-click="LivePayList()">
					</ul>
				</div>

			</div>
		</div>
	</div>
</div>
</div>


</@b.body>

</html>