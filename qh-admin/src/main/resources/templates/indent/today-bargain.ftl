<#import "/layout/header.ftl" as h/> 
<#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="课程管理页面"/>
<link rel="stylesheet" href="/styles/admin.css" />
<script src="/scripts/course/grade-template.js"></script>
<script src="/scripts/admin.js"></script>
<script src="/scripts/indent/excle.js"></script>
<@b.body menu="sidebarmenu-indent" submenu="sidebarmenu-indent-today-bargain">
<div >
<div class="details" style="width: 100%">
	<div class="details-nav">
		<ul>
			<li><img src="/images/sjk-home.png" style="color: red;"/>我的主页</li>
			<li>/</li>
		<li>订单管理</li>
		<li>/</li>
		<li>今日成交</li>
		</ul>
	</div>
<div class="details-frame" >
	<div class="details-frame-content">
	<div class="select-3">
		<span>学员姓或手机号</span>
		<input type="text" />
	</div>
	<div class=" select-3">
		<img src="/images/sjk-xl.png" /> <span>交易类型</span> <select>
	<option disabled selected style='display: none;'></option>
		<option></option>
		<option></option>
	</select>
								</div>
			<div style="float:left;">
					<input type="button" class="btn-lg im-key" ng-click="loaddata()"
						value="检索" ng-click="search()" />
				</div>
<div>
<input type="button" class="btn-lg im-key" 
	value="导出excle" onclick="method5('tableExcel')" />
				</div>
	</div>
<div class="manage">
	
				<div class="admin-table">

					<table id="tableExcel">
						<tr>
						<th>交易类型</th>
							<th>学员姓名</th>
							<th>学员手机</th>
							<th>交易金额</th>
							<th>交易时间</th>
							<th>剩余网课币</th>
							<th>剩余直播币</th>
						</tr>

						<tr ng-repeat="" ng-click="" ng-class="">
							<th>交易类型</th>
							<th>学员姓名</th>
							<th>学员手机</th>
							<th>交易金额</th>
							<th>交易时间</th>
							<th>剩余网课币</th>
							<th>剩余直播币</th>

						</tr>
					</table>

				</div>

				<div class="col-sm-6"></div>
                    <div class="col-sm-6">
                        <ul uib-pagination boundary-links="true"
                            total-items="total" ng-model="current"
                            items-per-page="pageSize"
                            max-size="5"
                            class="pagination-sm" previous-text="&lsaquo;"
                            next-text="&rsaquo;"
                            first-text="&laquo;" last-text="&raquo;" ng-change="courseBases()">
                        </ul>
                    </div>

</div>
		
</div>

 </div>  
</div>
<style type="text/css">
.details-frame-content .select-3{width: 10%;margin-right: 1%;}
.details-frame-content .select-3 input{border-radius:0;}

</style>
</@b.body>

</html>
