<#import "/layout/header.ftl" as h/> 
<#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="课程订单管理"/>
<link rel="stylesheet" href="/styles/admin.css" />
<script src="/scripts/admin.js"></script>
<script src="/scripts/orders/course_orders.js"></script>
<script src="/scripts/indent/excle.js"></script>
<@b.body menu="sidebarmenu-orders" submenu="sidebarmenu-orders-course">
<div ng-controller="courseOrdersController">
<div class="details" style="width: 100%">
	<div class="details-nav">
		<ul>
			<li><img src="/images/sjk-home.png" style="color: red;"/>我的主页</li>
			<li>/</li>
		<li>订单管理</li>
		<li>/</li>
		<li>课程订单</li>
		</ul>
	</div>
<div class="details-frame" >
	<div class="details-frame-content">
		<div class="select-3">
							<span>选择时间</span>
								<input type="date" name="search" ng-model="fromdate"/>
						</div>
						<div class="select-3" style="font-size: 1.6rem;width: 1%;text-align: center;">
							
								&nbsp;<span class="glyphicon glyphicon-hand-right"></span>
						</div>
						<div class="select-3">
							<span>&nbsp;</span>
								<input type="date" name="search" ng-model="todate"/>
						</div>
					
	<div class="select-3">
		<span>推荐老师</span>
		<input type="text" ng-model="recommendTeacher"/>
	</div>
		<div class="select-3">
		<span>用户手机号</span>
		<input type="text" ng-model="mobile"/>
	</div>
		<div class="select-3">
		<span>支付订单号</span>
		<input type="text" ng-model="orderno"/>
	</div>
		<div class="select-3">
		<span>订单状态</span>
		<select ng-model="ordersStatus">
			<option value="NEW">待付款</option>
			<option value="RECEIVED">已完成</option>
		</select>
	</div>
	<div class="select-3">
		<span>推荐老师</span>
		<input type="text" />
	</div>
			<div style="float:left;">
					<input type="button" class="btn-lg im-key" ng-click="loaddata()"
						value="检索"  />
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
							<th>用户手机号</th>
							<th>学员姓名</th>
							<th>学员电话</th>
							<th>支付订单号</th>
							<th>支付方式</th>
							<th>支付途径</th>
							
							<th>订单状态</th>
							<th>实际费用</th>
							
							<th>订单创建时间</th>
							<th>订单支付时间</th>
							<th>推荐老师</th>
							
						<th>操作</th>
						</tr>

						<tr ng-repeat="o in list">
							<th>{{o.userMobile}}</th>
							<th>{{o.receiver}}</th>
							<th>{{o.mobile}}</th>
							<th>{{o.orderno}}</th>
							<th>{{o.payname}}</th>
							<th>APP</th>
							<th>{{o.statusName}}</th>
							<th>{{o.payaccount}}</th>
							<th>{{o.showtime}}</th>
							<th>{{o.paidtimes}}</th>
							<th>{{o.recommendTeacher}}</th>
							
						
                           <th><span class="xiangqing" ng-click="detail(o)">查看详情</span></th>
						</tr>
					</table>

				</div>

				<div class="col-sm-6">
					<ul uib-pagination boundary-links="true" total-items="total"
						ng-model="page" items-per-page="pageSize" max-size="5"
						class="pagination-sm" previous-text="&lsaquo;"
						next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
						ng-change="loaddata()">
					</ul>
				</div>

</div>
		<div id="revise" class="resource" style="display:none;">
								<form id="myform2" class="ng-pristine ng-valid">
									<h4>详细信息</h4>
						<div class="template-add">
										<div class="grade-left" style="border-top:1px solid #dddddd;border-bottom:1px solid #dddddd;padding: 10px 0;">
                                       
                                        <ul>
                                        	<li>订单编号：</li>
                                        	<li>{{orders.orderno}}</li>
                                        </ul>
                                         <ul>
                                        	<li>姓名:</li>
                                        	<li>{{orders.receiver}}</li>
                                        </ul>
                                        
                                         <ul>
                                        	<li>手机号：</li>
                                        	<li>{{orders.mobile}}</li>
                                        </ul>
                                         <ul>
                                        	<li>订单状态：</li>
                                        	<li>{{orders.statusName}}</li>
                                        </ul>
                                         <ul>
                                        	<li>实际费用：</li>
                                        	<li>{{orders.payaccount}}</li>
                                        </ul>
                                        <ul>
                                        	<li>下单时间：</li>
                                        	<li>{{orders.showtime}}</li>
                                        </ul>
                                        
	                                       </div>
								</div>
								<div>
								<p style="padding-top: 15px;">购买清单</p>
								<ul style="width: 100%;height: 80px;margin: 15px 0;" ng-repeat="p in productList">
								<div style="width:20%;height:100%;float: left;">
								<img ng-src="{{p.productImg}}" style="height:100%;width:100%;">
								</div>
								<div style="width:75%;height:100%;float: right;">
								<p>名称:{{p.productName}}</p>
								<p><span style="color: #B1B1B1;">原价：{{p.productPrice}}</span></p>
								<p><span>现价：{{p.productPrice}}</span></p>
								</div>
								</ul>
		
								</div>
								</form>
								<div class="end" style="width: 100px;">
									<input name="esc" type="reset" value="关闭" onclick="CloseDiv2();formReset2()" class="esc">
								</div>
				
					
					
					
					</div>
</div>

 </div>  
</div>
<style type="text/css">
.details-frame-content{height:219px;}
.details-frame-content .select-3{width: 10%;margin-right: 1%;}
@media screen and (min-width: 1601px) {.details-frame-content .select-3{width: 12%;}}
@media screen and (max-width: 1600px)  {.details-frame-content .select-3{width: 14%;}}
@media screen and (max-width: 1400px)  {.details-frame-content .select-3{width: 20%;}}
@media screen and (max-width: 901px) {.details-frame-content .select-3{width: 90%;}}
.details-frame-content .select-3 input{border-radius:0;}
.resource{width:400px;
				height: 490px;
				position: absolute;
				left: 25%;
				top:20%;
				display: none;padding:20px;}

.resource .grade-left ul{width:100%;margin:3px 0;height:30px;line-height: 30px;}
.resource .grade-left ul li:nth-child(1){float:left;font-size: 1.5rem;}
.resource .grade-left ul li:nth-child(2){float:right;color:#999;font-size: 1.5rem;}
 .admin-table table tr form span{font-size:1.4rem;}
</style>
</@b.body>

</html>
