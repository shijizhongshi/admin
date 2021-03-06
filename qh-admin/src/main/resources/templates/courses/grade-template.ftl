<#import "/layout/header.ftl" as h/> <#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="课程管理页面"/>
<link rel="stylesheet" href="/styles/admin.css" />
<script src="/scripts/course/grade-template.js"></script>
<script src="/scripts/admin.js"></script>

<@b.body menu="sidebarmenu-course" submenu="sidebarmenu-grade-template">
<div ng-controller="CourseClassTemplateController">
	<div class="details" style="width: 100%">
		<div class="details-nav">
			<ul>
				<li><img src="/images/sjk-home.png" style="color: red;" />我的主页</li>
				<li>/</li>
				<li>网课资源管理</li>
				<li>/</li>
				<li>班级模板</li>
			</ul>
		</div>
		<div class="details-frame">
			<div class="details-frame-content">

				<div class="select-3" style="width: 15%;margin-right:0%">
					<span>模板名称</span>
					<form id="search">
						<input type="text" placeholder="请输入模板名称" ng-model="templateName" />
					</form>
				</div>
				<div>
					<input type="button" class="btn-lg im-key" value="立即检索"
						ng-click="templateBases()" />
				</div>
			</div>
			<div class="manage">
				<ul class="show">

					<li ng-click="add()" style="background: #9DE879;"><span
						class="glyphicon glyphicon-plus"></span>&nbsp;添加模板</li>
					<li ng-click="update()" style="background: #F9CD33;"><span
						class="glyphicon glyphicon-pencil"></span>&nbsp;修改模板</li>
					<li ng-click="deletetemplate()" style="background: #F86846;"><span
						class="glyphicon glyphicon-trash"></span>&nbsp;删除模板</li>
					<li ng-click="refresh()"
						style="float: right; margin-right: 20px; background: none;"><img
						src="/images/sjk-f5.png" name="changyi" /></li>
				</ul>
				<div class="admin-table">

					<table>
						<tr>
							<th>模板名称</th>
							<th>班级名称</th>
							<th>班级价格</th>
							<th>课程折扣价</th>

						</tr>

						<tr ng-repeat="t in templatelist" ng-click="checkedtemplate(t)"
							ng-class="{'selected':selected==t}">
							<th>{{t.templateName}}</th>
							<th>{{t.className}}</th>
							<th>{{t.classPrice}}</th>
							<th>{{t.classDiscountPrice}}</th>

						</tr>
					</table>
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




				<!--弹窗-->
				<div class="poop" id="add">
					<form id="myform">
						<h3>添加模板</h3>
						<div class="template-add">
							<div class="template-left" style="padding-right: 5%;">
								<div class=" select-2">
									<span>模板名称：</span> <input type="text"
										ng-model="courseClassTemplate.templateName"
										placeholder="请输入模板名称" style="width: 230px;" />
								</div>

								<div class=" select-2">
									<span>班级名称：</span> <input type="text"
										ng-model="courseClassTemplate.className" placeholder="请输入班级名称"
										style="width: 230px;" />
								</div>

								<div style="width: 100%; height: 90px; clear: both;">
									<div class=" select-2" style="float: left;">

										<span>班级价格：</span> <input type="text"
											ng-model="courseClassTemplate.classPrice" />
									</div>
									<div class=" select-2" style="float: right;">

										<span>班级折扣价：</span> <input type="text"
											ng-model="courseClassTemplate.classDiscountPrice" />
									</div>
								</div>
								<div class="costs-uploadfile-div">
									<input type="file" name="file"
										onchange="angular.element(this).scope().uploadmainimage(this)" />
									<div style="height: 130px; margin-top: 3px;">
										<img src="{{classUrl}}" />
									</div>
								</div>

							</div>


							<div class="template-right">
								<div class="grade-text">
									<span>适宜人群</span>
									<textarea ng-model="courseClassTemplate.properPeople"></textarea>
								</div>
								<div class="grade-text">
									<span>班级承诺</span>
									<textarea ng-model="courseClassTemplate.promises"></textarea>
								</div>
								<div class="grade-text">
									<span>班级特色</span>
									<textarea ng-model="courseClassTemplate.features"></textarea>
								</div>
								<div class="grade-text">
									<span>班级介绍</span>
									<textarea ng-model="courseClassTemplate.introduce"></textarea>
								</div>
							</div>

							<div class="grade-add-bottom">
								<span>班级详情</span>
								<textarea ng-model="courseClassTemplate.detail"></textarea>
							</div>
					</form>
					<div class="end">
						<input name="git" type="submit" value="提交" ng-if="id==null"
							ng-click="addTemplate()" style="background: #5ED8A9;" /> <input
							name="git" type="submit" value="修改" ng-if="id!=null"
							ng-click="updateTemplate()" style="background: #5ED8A9;" /> <input
							name="esc" type="reset" value="取消" ng-click="reset()" class="esc" />
					</div>
				</div>
			</div>
		</div>

		 
		<style type="text/css">
.poop {
	overflow-y: scroll;
	width: 60%;
	height: 600px;
	position: absolute;
	left: 15%;
	top: 15%;
}

@media screen and (max-width: 1401px) {
	.poop {
		width: 580px;
		left: 0%;
		top: 5%
	}
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
	overflow: auto;
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
	height: 80px;
	font-size: 1.5rem
}

.grade-add-bottom {
	width: 100%;
	clear: both;
}

.grade-add-bottom textarea {
	width: 100%;
	height: 250px;
	background:#F7F8FC;
	border-radius: 20px;
	text-indent: 2em;
}

div.costs-uploadfile-div {
	position: relative;
}

div.costs-uploadfile-div #textfield {
	width: 40%;
	height: 30px;
}

div.costs-uploadfile-div #fileField {
	width: 100%;
	height: 30px;
	position: absolute;
	top: 0;
	left: 0;
	filter: alpha(opacity : 0);
	opacity: 0;
}

div.costs-uploadfile-div .allBtn {
	padding: 0;
	margin: 0;
	height: 30px;
	line-height: 30px;
	width: 35%;
	background-color: #18b3cf;
	border: none;
	color: #fff;
}
</style>
		</@b.body>
	</div>
	 
</div>
</html>
