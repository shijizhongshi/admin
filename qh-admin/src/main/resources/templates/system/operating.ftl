<#import "/layout/header.ftl" as h/> <#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="banner广告管理页面"/>
<link rel="stylesheet" href="/styles/admin.css" />
<script src="/scripts/admin.js"></script>
<@b.body menu="sidebarmenu-system" submenu="sidebarmenu-system-operating">
<div class="details" style="width: 100%">
	<div class="details-nav">
		<ul>
			<li><img src="/images/sjk-home.png" style="color: red;" />我的主页</li>
			<li>/</li>
			<li>公共资源管理</li>
			<li>/</li>
			<li>操作记录</li>
		</ul>
	</div>
<div class="details-frame" >
<div class="details-frame-content">

				<div class="select-3">
					<span>选择时间</span> <input type="date" name="search" ng-model="fromdate" class="ng-pristine ng-untouched ng-valid ng-empty">
				</div>
				<div class="select-3" style="font-size: 1.6rem; width: 5px; text-align: center;">
&nbsp;<span class="glyphicon glyphicon-hand-right"></span>
				</div>
				<div class="select-3">
					<span>&nbsp;</span> <input type="date" name="search" ng-model="todate" class="ng-pristine ng-untouched ng-valid ng-empty">
				</div>

				<div class="select-3">
					<span>部门</span> <input  type="text" placeholder="请输入部门" ng-model="recommendTeacher" class="ng-pristine ng-untouched ng-valid ng-empty">
				</div>
				<div class="select-3">
					<span>负责人</span> <input style=" type=" text"="" placeholder="请输入姓名" ng-model="mobile" class="ng-pristine ng-untouched ng-valid ng-empty">
				</div>
				
				<div class="select-3">
					<span>多选框</span><img src="/images/sjk-xl.png">  <select ng-model="ordersStatus" class="ng-pristine ng-untouched ng-valid ng-empty">
						<option value=""></option>
						<option value="NEW"></option>
						<option value="RECEIVED"></option>
					</select>
				</div>
				
				<div style="float: left;">
					<input type="button" class="btn-lg im-key"  value="立即检索">
				</div>
			

<div class="admin-table">

						<table>
							<tbody>
							<tr>
								<th>操作人</th>
								<th>内容</th>
                                  <th>IP地址</th>
                                  <th>操作时间</th>
                                  <th>部门</th>
							</tr>
							<th>操作人</th>
								<th>内容</th>
                                  <th>IP地址</th>
                                  <th>操作时间</th>
                                  <th>部门</th>


						</tbody></table>

						<div class="col-sm-6"></div>
		<div class="col-sm-6">
						<ul uib-pagination boundary-links="true" total-items="total"
							ng-model="current" items-per-page="pageSize" max-size="5"
							class="pagination-sm" previous-text="&lsaquo;"
							next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
							ng-click="loaddata()">
						</ul>
					</div>
					</div>
</div>
</div>	
	
</div>
</@b.body> 
<style>
.details-frame-content .select-3{float:left;margin-right: 20px;width: 15%;}
</style>
</html>