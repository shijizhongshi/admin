<#import "/layout/header.ftl" as h/> <#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="临时店铺" />
<link rel="stylesheet" href="/styles/admin.css" />
<script src="/scripts/league/short_shop.js"></script>
<script src="/scripts/admin.js"></script>
<@b.body menu="sidebarmenu-league" submenu="sidebarmenu-league-shortshop">
<div ng-controller="fuwushopController">
	<div>
		<div class="details" style="width: 100%">
			<div class="details-nav">
				<ul>
					<li><img src="/images/sjk-home.png" style="color: red;" />我的主页</li>
					<li>/</li>
					<li>加盟商管理</li>
					<li>/</li>
					<li>临时店铺管理</li>
				</ul>
			</div>
			<div class="details-frame">
				
				<div id="guanli">
					<div class="manage">
						
						<ul class="show">
							
							<li style="background:#9DE879;" ng-click="add()">
							<span class="glyphicon glyphicon-plus" ></span>&nbsp;添加店铺
							</li>
							<li style="background:#F9CD33;" ng-click="update()">
							<span class="glyphicon glyphicon-pencil"></span>&nbsp;修改店铺
							</li>
							
							<li style="background: #F86846;" ng-click="deleteshort()">
							<span class="glyphicon glyphicon-trash"></span>&nbsp;删除店铺
							</li>
							<!--<li>
							<span class="glyphicon glyphicon-sort" class="move-up"></span>&nbsp;上移</li>
							<li><span class="glyphicon glyphicon-sort-by-attributes"
								class="move-down"></span>&nbsp;下移</li>
							<li ng-click="guanli()"><span
								class="glyphicon glyphicon-sort-by-attributes" class="move-down"></span>&nbsp;项目管理</li>-->
							<li style="float: right; margin-right: 20px; background: none;"><img
								src="/images/sjk-f5.png" name="changyi" ng-click="refresh()" /></li>
						</ul>
						<div class="admin-table">

							<table>
								<tr>
									<th>店铺名称</th>
									<th>地址</th>
									<th>店铺类型</th>
									<th>门头照片</th>
									<th>店铺标志</th>
									<th>负责人</th>
									<th>联系电话</th>
									<th>创建时间</th>
								</tr>

								<tr ng-repeat="s in shoplist" ng-click="checkshop(s)" ng-class="{'selected':selected==s}">
									<th>{{s.shopName}}</th>
									<th>{{s.address}}</th>
									<th ng-show="{{s.shopType==2}}">商城店铺</th>
									<th ng-show="{{s.shopType==1}}">服务店铺</th>
									<th><img ng-src="{{s.doorHeadUrl}}"/></th>
									<th><img ng-src="{{s.shopLogo}}"/></th>
									<th>{{s.leaderName}}</th>
									<th>{{s.leaderMobile}}</th>
									<th>{{s.addtime | date:'yyyy-MM-dd HH:mm:ss'}}</th>
									
								</tr>

							</table>
							<div class="col-sm-6"></div>
							<div class="col-sm-6">
								<ul uib-pagination boundary-links="true" total-items="total"
									ng-model="current" items-per-page="pageSize" max-size="5"
									class="pagination-sm" previous-text="&lsaquo;"
									next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
									ng-click="shopList()">
								</ul>
							</div>
						</div>


						<!--弹窗-->
						<div class="poop" id="add">
							<form id="myform">
								<h3>添加店铺</h3>
								<div class="template-add">
									<div class="grade-left" style="padding-right: 5%;">
										<div class="select-2">
											<span>请输入店铺名称</span> <input type="text" ng-model="shop.shopName"/>
										</div>

										<div class=" select-2">
											<img src="/images/sjk-xl.png" /> <span>店铺类型</span> 
											<select ng-model="shop.shopType">
												<option ng-selected="shop.shopType==1"  value="1">服务店铺</option>
												<option ng-selected="shop.shopType==2" value="2">商城店铺</option>
											</select>
										</div>

										<div class="select-2">
											<span>负责人</span> <input type="text" ng-model="shop.leaderName" placeholder="请输入负责人姓名" />
										</div>
										<div class="select-2">
											<span>联系电话</span> <input type="text" ng-model="shop.leaderMobile" placeholder="请输入联系电话" />
										</div>
										
										<div class="grade-text">
											<span>店铺地址</span>
											<textarea ng-model="shop.address"></textarea>
										</div>
										

									</div>
									<div class="grade-center">
									
										<div class="costs-uploadfile-div">
											门头照片<input type="file" name="file" accept="image/*"
												value="上传门头照片" onchange="angular.element(this).scope().uploadmainimage(this)" />
											<div class="costs-img">
												<img src="{{doorHeadUrl}}"/>
											</div>
										</div>
										<div class="costs-uploadfile-div">
											店面标志<input type="file" name="file" accept="image/*"
												value="上传店面logo" onchange="angular.element(this).scope().uploadmainimage11(this)" />
											<div class="costs-img">
												<img src="{{shopLogo}}" />
											</div>
										</div>
										
									</div>
								</div>
							</form>
							<div class="end">
								<input name="git" type="submit" value="提交" ng-show="shopId==null" style="background: #5ED8A9;" ng-click="saveUpdateShop()"/> 
								<input name="git" type="submit" value="修改" ng-show="shopId!=null" style="background: #5ED8A9;" ng-click="saveUpdateShop()"/> 
								<input name="esc" type="reset" value="取消" ng-click="reset()" class="esc" />
							</div>
						</div>
					</div>
					
				</div>
			</div>
		</div>
	</div>

	<style type="text/css">
@media screen and (max-width:901px) {
	.managr-dianpu, .details-frame-heshen {
		overflow: auto;
	}
}

.poop {
	width: 55%;
	height: 550px;
	position: absolute;
	left: 15%;
	top: 10%;
	display: none;
}

@media screen and (max-width:1401px) {
	.poop {
		width: 560px;
	}
}

.poop span {
	font-size: 1.5rem;
}

.resource .grade-left, .resource .grade-center {
	width: 400px;
	float: left;
	height: auto;
	overflow: auto;
}

.poop .grade-left, .poop .grade-center {
	width: 49%;
	float: left;
	height: 720px;
	overflow: auto;
}

.resource .grade-right {
	width: 200px;
	border-right: none;
	height: 500px;
	float: left;
}

.details-frame-content {
	height: 51px;
	padding: 0 29px;
}

.details-frame-content ul li {
	float: left;
	height: 50px;
	text-align: center;
	line-height: 50px;
	font-size: 1.5rem;
	cursor: pointer;
	margin-right: 15px;
}

.leibie {
	background: #F5F6F8;
	width: 50%;
	text-align: center;
}

.grade-text textarea {
	height: 70px;
	width: 50%;
}

.manage .managr-dianpu .select-3 {
	width: 12%;
	margin-left: 18px;
	margin-right: 0;
}

@media screen and (max-width: 901px) {
	.manage .managr-dianpu .select-3, .details-frame-heshen .select-3,
		.details-frame-heshen .select-2 {
		width: 90% !important;
	}
}

.details-frame-heshen {
	width: 100%;
	padding: 0 30px;
	background: white;
	height: auto;
}

.details-frame-heshen .select-3 {
	width: 15%;
}

.details-frame-heshen .select-2 {
	width: 10%;
	float: left;
}

.details-frame-heshen .select-2 input {
	border-radius: 0;
}

.resource {
	width: auto;
	height: 600px;
	position: absolute;
	left: 10%;
	top: 20%;
	display: none;
}

@media screen and (max-width:1401px) {
	.resource {
		width: 840px;
	}
}

.resource .grade-left ul {
	width: 100%;
	margin: 3px 0;
	height: 50px;
}

.resource .grade-left ul li:nth-child(1) {
	float: left;
}

.resource .grade-left ul li:nth-child(2) {
	float: right;
	color: #999;
}

.end {
	float: none;
	margin-left: auto;
}
</style>
</div>

</@b.body>

</html>