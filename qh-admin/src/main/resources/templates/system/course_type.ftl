<#import "/layout/header.ftl" as h/> <#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="分类管理"/>
<link rel="stylesheet" href="/styles/admin.css" />
<script src="/scripts/system/course_type.js"></script>
<script src="/scripts/admin.js"></script>

<@b.body menu="sidebarmenu-system"
submenu="sidebarmenu-system-courseType">
<div ng-controller="CourseTypeController">

	<div>
		<div class="details" style="width: 100%">
			<div class="details-nav">
				<ul>
					<li><img src="/images/sjk-home.png" style="color: red;" />我的主页</li>
					<li>/</li>
					<li>公共资源管理</li>
					<li>/</li>
					<li>专业管理</li>
				</ul>
			</div>
			<div class="details-frame">
				<!-- 展示全部类别  -->
				<div class="details-frame-content" id="details-frame-content">
					<ul>
						<li ng-repeat="list in courseTypeList" ng-click="checkedOne(list)"
							ng-class="{'clicked':clicked==list}">
							{{list.courseTypeName}} <i ng-click="deletecate(list)"
							ng-show="selectdelete==list" class="glyphicon glyphicon-remove"
							style="float: right; color: #666; font-size: 1.1rem"></i>
						</li>
						<ul class="add-fenlei" ng-click="addcate(id)" ng-show="id == null">
							<span class="glyphicon glyphicon-plus"></span>&nbsp;添加分类
						</ul>
						<ul class="add-fenlei" ng-click="addcate()" ng-show="id != null">
							<span class="glyphicon glyphicon-plus"></span>&nbsp;修改分类
						</ul>
					</ul>
				</div>
				<!--选中大类别  展示下面所属的全部类别  -->
				<div class="details-frame-content" id="two" style="display: none;">
					<ul>
						<li ng-repeat="clist in courseTypeSubclassList"
							ng-click="checkedTwo(clist)" ng-class="{'clicked':click==clist}">
							{{clist.courseTypeSubclassName}} <i ng-click="deletes(clist)"
							ng-show="selectdeletes == clist"
							class="glyphicon glyphicon-remove"
							style="float: right; color: #666; font-size: 1.1rem"></i>
						</li>
						<ul class="add-fenlei" ng-click="addTwo()"
							ng-show="courseTypeSubclassId == null">
							<span class="glyphicon glyphicon-plus"></span>&nbsp;添加分类
						</ul>
						<ul class="add-fenlei" ng-click="addTwo()"
							ng-show="courseTypeSubclassId != null">
							<span class="glyphicon glyphicon-plus"></span>&nbsp;修改分类
						</ul>
					</ul>
				</div>
				<div class="manage">
					<ul class="show">
						<li style="background: none; color: black;"><b>子类的操作</b></li>
						<li ng-click="addWindows()" style="background: #9DE879;"><span
							class="glyphicon glyphicon-plus"></span>&nbsp;添加子类</li>
						<li ng-click="updateWindows()" style="background: #F9CD33;"><span
							class="glyphicon glyphicon-pencil"></span>&nbsp;修改子类</li>
						<li ng-click="deleteThree()" style="background: #F86846;"><span
							class="glyphicon glyphicon-trash"></span>&nbsp;删除子类</li>

						<li ng-click="refresh()"
							style="float: right; margin-right: 20px; background: none;"><img
							src="/images/sjk-f5.png" name="changyi" /></li>
					</ul>
					<div class="admin-table">

						<table>
							<tr>
								<th>子类名称</th>

							</tr>
							<tr ng-repeat="tl in threeList" ng-click="checkedThree(tl)"
								ng-class="{'selected':selectsThree == tl}">

								<th>{{tl.miniSubclassName}}</th>
							</tr>


						</table>

						<div class="col-sm-6"></div>
						<div class="col-sm-6">
							<ul uib-pagination boundary-links="true" total-items="total"
								ng-model="page" items-per-page="pageSize" max-size="5"
								class="pagination-sm" previous-text="&lsaquo;"
								next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
								ng-click="shopsubBases()">
							</ul>
						</div>
					</div>



					<!--弹窗-->
					<div class="poop" id="add">
						<form id="myform">
							<h3 ng-show="isshow == 1">添加分类</h3>
							<h3 ng-show="isshow == 3">添加二级分类</h3>
							<h3 ng-show="isshow == 2">修改分类</h3>
							<h3 ng-show="isshow == 4">修改二级分类</h3>
							<div class="template-add">

								<div class="select-2" ng-show="isshow == 1 || isshow ==2">
									<span>一级分类名称</span> <input type="text"
										ng-model="courseTypeName" />
								</div>
								<div class="select-2" ng-show="isshow == 3 || isshow == 4">
									<span>二级分类名称</span> <input type="text"
										ng-model="courseTypeSubclassName" />
								</div>
								<!--上传图片 开始  -->
								<div>
									<div style="margin-bottom: 10px">图片:</div>
									<input type="file"
										onchange="angular.element(this).scope().uploadmainimage(this)" />
									<img src="{{imageurl}}" style="width: 50px;" />
								</div>
								<!--上传图片 结束-->
								<!-- <div class="costs-uploadfile-div">
									<input type="file" name="file" ng-show="picture==1"
										onchange="angular.element(this).scope().uploadmainimage(this)"
										accept="image/*" /> <input type="hidden" ng-model="imgUrl" />
									<div ng-show="picture==1"
										style="height: 130px; width: 40%; margin-top: 3px;">
										<img src="{{imgUrl}}" style="height: 130px;" />
									</div>
								</div> -->
							</div>
						</form>
						<div class="end">
							<input name="git" type="submit" value="添加" ng-show="insert==1"
								ng-click="insertOne()" style="background: #5ED8A9;" /> <input
								name="git" type="submit" value="添加" ng-show="isshow == 3"
								ng-click="insertTwo()" style="background: #5ED8A9;" /> <input
								name="git" type="submit" value="修改" ng-show="update==1"
								ng-click="updateOne()" style="background: #5ED8A9;" /> <input
								name="git" type="submit" value="修改" ng-show="isshow == 4"
								ng-click="updateTwo()" style="background: #5ED8A9;" /> <input
								name="esc" type="reset" value="取消" ng-click="resert()"
								class="esc" />
						</div>
					</div>
					<!-- 三级类别弹窗  -->
					<div class="poop" id="three">
						<form id="myform">
							<h3 ng-show="threeId == null">添加分类</h3>
							<h3 ng-show="threeId != null">修改分类</h3>
							<div class="template-add">
								<div class="select-2">
									<span>分类名称</span> <input type="text"
										ng-model="miniSubclassName" />
								</div>
								<div class="costs-uploadfile-div">
									<input type="file" name="file" ng-show="picture==1"
										onchange="angular.element(this).scope().uploadmainimage(this)"
										accept="image/*" /> <input type="hidden" ng-model="imgUrl" />
									<div ng-show="picture==1"
										style="height: 130px; width: 40%; margin-top: 3px;">
										<img src="{{imgUrl}}" style="height: 130px;" />
									</div>
								</div>
							</div>
						</form>
						<div class="end">
							<input name="git" type="submit" value="添加"
								ng-show="threeId == null" ng-click="insertThree()"
								style="background: #5ED8A9;" /> <input name="git" type="submit"
								value="修改" ng-show="threeId != null" ng-click="updateThree()"
								style="background: #5ED8A9;" /> <input name="esc" type="reset"
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
	border: 1px solid #F3F3F3;
	background: #F3F3F3;
	margin-bottom: 8px;
	font-size: 1.5rem;
	cursor: pointer;
}

@media screen and (max-width: 901px) {
	.details-frame-content ul li {
		width: 48%;
	}
}

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
	background: #999 !important;
	color: white !important;
}
</style>

</div>

</@b.body>

</html>
