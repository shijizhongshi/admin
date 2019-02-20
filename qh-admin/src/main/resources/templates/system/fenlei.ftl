<#import "/layout/header.ftl" as h/> <#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="分类管理"/>
<link rel="stylesheet" href="/styles/admin.css" />
<script src="/scripts/system/fenlei.js"></script>
<script src="/scripts/admin.js"></script>

<@b.body menu="sidebarmenu-system" submenu="sidebarmenu-system-fenlei">
<div ng-controller="ShopDrugCategoryController">

<div>
	<div class="details" style="width: 100%">
		<div class="details-nav">
			<ul>
				<li><img src="/images/sjk-home.png" style="color: red;" />我的主页</li>
				<li>/</li>
				<li>公共资源管理</li>
				<li>/</li>
				<li>分类管理</li>
			</ul>
		</div>
		<div class="details-frame">
			<div class="details-frame-content" id="details-frame-content">
				<ul   >
					<li ng-repeat="s in shopDrugCategory"  ng-click="checkedShopcate(s)"  ng-class="{'clicked':clicked==s}">
					{{s.categoryName}} 
					<i ng-click="deletecate(s)"  ng-show="selectdelete==s" class="glyphicon glyphicon-remove" style="float: right;color:#666;font-size:1.1rem"></i>
					</li>
					
					

					<ul class="add-fenlei" ng-click="addcate()">
						<span class="glyphicon glyphicon-plus"></span>&nbsp;添加分类
					</ul>
				</ul>


			</div>
			<div class="manage">
				<ul class="show">
					<li style="background: none; color: black;" ><b>子类的操作</b></li>
					<li  ng-click="addsub()" style="background: #9DE879;"><span
						class="glyphicon glyphicon-plus"></span>&nbsp;添加</li>
					<li ng-click="updateed()" style="background: #F9CD33;"><span
						class="glyphicon glyphicon-pencil"></span>&nbsp;修改</li>
					<li ng-click="deletesub()" style="background: #F86846;"><span
						class="glyphicon glyphicon-trash"></span>&nbsp;删除</li>
					<li><span class="glyphicon glyphicon-sort" class="move-up"></span>&nbsp;上移</li>
					<li><span class="glyphicon glyphicon-sort-by-attributes"
						class="move-down"></span>&nbsp;下移</li>
					<li ng-click="refresh()" style="float: right; margin-right: 100px; background: none;"><img
						src="/images/sjk-f5.png" name="changyi" /></li>
				</ul>
				<div class="admin-table">

					<table>
						<tr>
							<th>子类名称</th>
							<th>图片</th>
							
						</tr>
						<tr ng-repeat="ss in shopDrugSubcategory" ng-click="checkedShopsub(ss)" ng-class="{'selected':selected==ss}">
							
							<th>{{ss.subName}}</th>
							<th><img  src="{{ss.imgUrl}}"></th>
						</tr>

						
					</table>

				</div>
				<div class="col-sm-6"></div>
				<div class="col-sm-6">
					<ul uib-pagination boundary-links="true" total-items="total"
						ng-model="current" items-per-page="pageSize" max-size="5"
						class="pagination-sm" previous-text="&lsaquo;"
						next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
						ng-click="shopsubBases()">
					</ul>
				</div>


				<!--弹窗-->
				<div class="poop" id="add">
					<form id="myform">
						<h3>添加或修改分类</h3>
						<div class="template-add">
						
							<div class="select-2">
								<span>分类名称</span> 
								<input type="text" ng-model="name"/>
								
							</div>
							<div class="costs-uploadfile-div" >
								<input type="file" name="file" ng-show="picture==1"
									onchange="angular.element(this).scope().uploadmainimage(this)"
									accept="image/*" />
								
								<input type="hidden" ng-model="imgUrl" />
								<div ng-show="picture==1" style="height: 130px; width: 40%; margin-top: 3px;">
									<img src="{{imgUrl}}" style="height: 130px;" />
								</div>
							</div>
						</div>
					</form>
					<div class="end">
						<input  name="git" type="submit" value="提交"  ng-show="insert==1"  ng-click="insertcatesub()"
							style="background: #5ED8A9;" /> 
							<input  name="git" type="submit" value="修改"  ng-show="update==1"  ng-click="updatesub()"
							style="background: #5ED8A9;" />
							<input name="esc" type="reset"
							value="取消" ng-click="resert()" class="esc" />
					</div>
				</div>
			</div>
		</div>
	</div>

</div>


<style type="text/css">
.poop {
	overflow-y: auto;
	width: 25%;
	height: 400px;
	position: absolute;
	left: 25%;
	top: 10%;
	display: none;
}

.poop span {
	font-size: 1.5rem;
}

.details-frame-content {
	height: auto;

	padding-bottom: 0;
}

.details-frame-content ul li {
	float: left;
	width: 10%;
	height: 45px;
	text-align: center;
	line-height: 45px;
	margin-right: 2%;
	border:1px solid  #F3F3F3;
	background: #F3F3F3; 
	margin-bottom: 8px;
	font-size: 1.5rem;
	cursor: pointer;
}
@media screen and (max-width: 901px) {.details-frame-content ul li{	width: 48%; }}
.details-frame-content .add-fenlei {
	float: left;
	border-radius: 20px;
	background: #F3F3F3;
	height: 30px;
	line-height: 30px;
	margin-top: 10px;
	font-size: 1.2rem;
	padding: 0 15px;
	cursor: pointer;
}

.admin-table tr th {
	cursor: pointer;
}
.clicked {
background:#999 !important;
color:white !important;
}

</style>

</div>

</@b.body>

</html>
