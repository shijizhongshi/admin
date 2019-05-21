<#import "/layout/header.ftl" as h/> <#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="课程管理页面"/>
<link rel="stylesheet" href="/styles/admin.css" />
<script src="/scripts/salesman/salesmans.js"></script>
<script src="/scripts/admin.js"></script>

<@b.body menu="sidebarmenu-salesman" submenu="sidebarmenu-salesman-salesmans">
<div>
	<div class="details" style="width: 100%" ng-controller="salesmansController">
		<div class="details-nav">
			<ul>
				<li><img src="/images/sjk-home.png" style="color: red;" />我的主页</li>
				<li>/</li>
				<li>销售管理</li>
				<li>/</li>
				<li>销售人员管理</li>
			</ul>
		</div>
		<div class="details-frame">
			<div class="details-frame-content">
			
				<div class="select-3" style="width: 10%;margin-right:15px">
					<span>姓名</span>
					<input type="text" ng-model="name" placeholder="请输入姓名" />
				</div>
				<div class="select-3" style="width: 10%;margin-right:15px">
					<span>手机号码</span>
					<input type="text" ng-model="mobile" placeholder="请输入手机号码" />
				</div>
				<div class="select-3" style="width: 10%;margin-right:15px">
					<span>地区</span>
					<input type="text" ng-model="address" placeholder="请输入地区" />
				</div>
				<div>
					<input type="button" class="btn-lg im-key" ng-click="SalesmanList()"
						value="立即检索" />
				</div>


			</div>
			<div class="manage">
				<ul class="show">

					<li ng-click="add()" style="background: #9DE879;"><span
						class="glyphicon glyphicon-plus"></span>&nbsp;添加销售人员</li>
					<li ng-click="update()" style="background: #F9CD33;"><span
						class="glyphicon glyphicon-pencil"></span>&nbsp;修改销售人员</li>
					<li ng-click="deletesalesman()" style="background: #F86846;"><span
						class="glyphicon glyphicon-trash"></span>&nbsp;删除销售人员</li>
					<li ng-click="showQrcode()" style="background: #F9CD33;"><span
						class="xiangqing"></span>&nbsp;查看二维码</li>
					<li style="float: right; margin-right: 20px; background: none;"><img
						src="/images/sjk-f5.png" name="changyi" ng-click="reset()" /></li>
				</ul>
				<div class="admin-table">

					<table>
						<tr>
							<th>姓名</th>
							<th>销售人员照片</th>
							<th>手机号码</th>
							<th>所在地区</th>
							<th>客户数量</th>
							<th>操作</th>
						</tr>

						<tr ng-repeat="sl in salesmanlist" ng-click="checkedsalesman(sl)"
							ng-class="{'selected':selected==sl}">
							<th>{{sl.name}}</th>
							<th><img src="{{sl.imgUrl}}"
								style="width: 50px; height: 30px;" /></th>
							<th>{{sl.mobile}}</th>
							<th>{{sl.address}}</th>
							<th>{{sl.secondCount}}</th>
							<th ><span class="xiangqing" ng-click="toSecond(sl.id)">查看客户</span></th>
							

						</tr>
					</table>

					<div class="col-sm-6"></div>
					<div class="col-sm-6">
						<ul uib-pagination boundary-links="true" total-items="total"
							ng-model="page" items-per-page="pageSize" max-size="5"
							class="pagination-sm" previous-text="&lsaquo;"
							next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
							ng-click="SalesmanList()">
						</ul>
					</div>

				</div>



				<!--弹窗-->
				<div class="poop" id="add">
					
						<h3>{{html}}销售人员</h3>
						<div class="template-add">

							<div class="select-2">
								<span>名称<i class="bitian">*</i></span> <input type="text" placeholder="输入名称"
									ng-model="salesman.name"
									class="ng-pristine ng-untouched ng-valid ng-empty">
							</div>
							
							<div class="select-2" >
								<span>手机号码<i class="bitian">*</i></span> <input type="text" placeholder="输入手机号码"
									ng-model="salesman.mobile"
									class="ng-pristine ng-untouched ng-valid ng-empty">
							</div>
							<div class="select-2" >
								<span>地址<i class="bitian">*</i></span> <input type="text" placeholder="输入地址"
									ng-model="salesman.address"
									class="ng-pristine ng-untouched ng-valid ng-empty">
							</div>
							<span>上传销售人员照片<i class="bitian">*</i></span>
							<div class="costs-uploadfile-div">
									<input type="file" value="上传销售人员照片"
										onchange="angular.element(this).scope().uploadmainimage(this)">
									<div style="height: 130px; margin-top: 3px;">
										<img src="{{imgUrl}}" style="height: 130px;" />
									</div>
								</div>
								 <!--<div class="costs-uploadfile-div">
									<span class="xiangqing" ng-click="ShowQrcode()">生成二维码</span>
									<div style="height: 130px; margin-top: 3px;">
										<img src="{{qrcode}}" style="height: 130px;" />
									</div>
								</div> -->
								
								<div class="costs-uploadfile-div" ng-show="id!=null">
								<span>生成二维码</span>
									<input type="file" value="shengchenger"
										onchange="angular.element(this).scope().uploadmainimage2(this)">
									<div style="height: 130px; margin-top: 3px;">
										<img src="{{qrcode}}" style="height: 130px;" />
									</div>
								</div>
							
							
						</div>
					<div class="end" >
						<input name="git" type="submit" value="提交" ng-click="salesmanAdd()"ng-show="id==null"
							style="background: #5ED8A9;" /> 
							<input name="git" type="submit"
							value="修改" style="background: #5ED8A9;" ng-show="id!=null"
							ng-click="salesmanupdate()"><input name="esc" type="reset"
							value="取消" ng-click="cancel()" class="esc" />
					</div>
					
			
				
		</div>
				<!--二维码-->
			<div class="poop" id="addQrCode" style="width: auto; height: auto;left:30%;">
			<p class="close" ng-click="cancel()" style="font-size:2.0rem;">X</p>
				
					<div>
						
						<div class="template-add">

								<div class="costs-uploadfile-div">
									<div style="height: 130px; margin-top: 3px;">
										<img src="{{qrcode}}" style="height: 130px;" />
									</div>
								</div>
						</div>
						
				</div>

				
			</div>
			
			
			<div id="revise" class="resource" style="width: 720px;">
			
					<div class="select-2" style="width: 100%;">
							<span>输入转入手机号</span> <input type="text" ng-model="mobile" />
							<p ng-click="SalesmanList()"
								style="position: absolute; right: 10px; top: 40px; cursor: pointer;">
								<i class="glyphicon glyphicon-search"></i>搜索
							</p>
						</div>

						<div class="admin-table">
							<table>
						<tr>
							<th>姓名</th>
							<th>销售人员照片</th>
							<th>手机号码</th>
							<th>所在地区</th>
						</tr>

						<tr ng-repeat="sln in salesmanlist" ng-click="checkedsalesmanNew(sln)"
							ng-class="{'selected':selected1==sln}">
							<th>{{sln.name}}</th>
							<th><img src="{{sln.imgUrl}}"
								style="width: 50px; height: 30px;" /></th>
							<th>{{sln.mobile}}</th>
							<th>{{sln.address}}</th>
							</tr>
					</table>
						</div>
						

					<div class="end" style="clear: both;">
						<input name="git" type="submit" value="转入" ng-click="inputNewSalesman()"
							style="background: #5ED8A9;" /> <input name="esc" type="reset"
							value="取消" ng-click="resets()" class="esc" />
					</div>

				</div>
	</div>
	 
</div>
 
<style type="text/css">
.poop {
	overflow-y: scroll;
}

.poop span {
	font-size: 1.5rem;
}

.template-add {
	width: 100%;
	border-top: 1px solid #F5F6F8;
	height: 80%;
	padding-top: 10px;
	margin-top: 10px;
}

.template-left, .template-right {
	width: 50%;
	float: left;
	height: auto;
}

.template-left ul {
	height: 50px;
	line-height: 50px;
	font-size: 2rem;
}

.template-left ul li {
	float: left;
	margin-right: 5px;
}

.template-left ul li:nth-child(1) {
	margin-right: 10px;
}

.template-right .grade-text {
	width: 80%;
}

.template-right .grade-text textarea {
	width: 100%;
	height: 110px;
	font-size: 1.5rem
}

.grade-add-bottom {
	width: 100%;
	clear: both;
}

.grade-add-bottom textarea {
	width: 100%;
	height: 250px;
	background: #EDEEF0;
	border-radius: 20px;
	text-indent: 2em;
}

.details-frame-content .select-2 {
	float: left;
	margin-right: 15px;
	width: 18%;
}

@media screen and (max-width: 901px) {
	.details-frame-content .select-2 {
		width: 90%;
	}
}
</style>
</@b.body>

</html>
