<#import "/layout/header.ftl" as h/>
<#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="学员管理"/>
<link rel="stylesheet" href="/styles/admin.css" />
<link rel="stylesheet" href="/styles/management.css" />
<script src="/scripts/indent/excle.js"></script>
<@b.body menu="sidebarmenu-student" submenu="sidebarmenu-student-Online-course">
<div >
	<div class="details" style="width: 100%">
		<div class="details-nav">
			<ul>
				<li><img src="/images/sjk-home.png" />我的主页</li>
				<li>/</li>
				<li>学员管理</li>
				<li>/</li>
				<li>学员课程订购记录</li>
			</ul>
		</div>
<div class="details-frame">
	<div class="details-frame-content">
		<div class="select-3">
							<span>学员注册时间</span>
								<input type="date" name="search" class="ng-pristine ng-untouched ng-valid ng-empty">
						</div>
						<div class="select-3" style="font-size: 1.6rem;width: 1%;text-align: center;">
							
								&nbsp;<span class="glyphicon glyphicon-hand-right"></span>
						</div>
						<div class="select-3">
							<span>&nbsp;</span>
								<input type="date" name="search" class="ng-pristine ng-untouched ng-valid ng-empty">
						</div>
					
	<div class="select-3">
		<span>学员姓名或电话</span>
		
		<input type="text" class="ng-pristine ng-untouched ng-valid ng-empty">
	</div>
			<div class="select-3">
		<span>子账户</span>
		
		<input type="text" class="ng-pristine ng-untouched ng-valid ng-empty">
	</div>
	
			<div style="float:left;">
					<input type="button" class="btn-lg im-key" ng-click="loaddata()" value="检索">
				</div>
<div>
<input type="button" class="btn-lg im-key" value="导出excle" onclick="method5('tableExcel')">
				</div>
	</div>
<div class="manage">
	<div class="admin-table">

  <table id="tableExcel">

	<tbody>
	<tr>
		<th>学员名</th>

	<th>学员电话</th>
	<th>真实姓名</th>
	<th>销售人员</th>
	<th>课程名称</th>
	<th>课程类型</th>
	<th>是否关闭</th>
	<th>课程价格</th>
	<th>操作账户</th>
	<th>是否重学</th>
	<th>订购时间</th>
	</tr>
	<tr>
			<th>学员名</th>

	<th>学员电话</th>
	<th>真实姓名</th>
	<th>销售人员</th>
	<th>课程名称</th>
	<th>课程类型</th>
	<th>是否关闭</th>
	<th>课程价格</th>
	<th>操作账户</th>
	<th>是否重学</th>
	<th>订购时间</th>
	
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

</div>

</div>










	</div>
</div>
</div>


</@b.body>

</html>