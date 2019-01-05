<#import "/layout/header.ftl" as h/> 
<#import "/layout/body.ftl" as b/>

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
			<li><img src="/images/sjk-home.png" style="color: red;"/>我的主页</li>
			<li>/</li>
		<li>班级模板</li>
		</ul>
	</div>
<div class="details-frame" >
	<div class="details-frame-content">

	<div class="select" style="float:left;margin-right:15px;">搜索课程<br />
	<form id="search">
	<input type="text" name="search" style=" text-indent:2em;"/></form>
	</div>
	</div>
<div class="manage">
	<ul style="height: 80px;" class="show">

					<li onclick="showDiv()"
						style="margin-left: 70px; background: #9DE879;"><span
						class="glyphicon glyphicon-plus"></span>&nbsp;添加模板</li>
					<li onclick="showDiv2()" style="background: #F9CD33;"><span
						class="glyphicon glyphicon-pencil"></span>&nbsp;修改模板</li>
					<li style="background: #F86846;"><span
						class="glyphicon glyphicon-trash"></span>&nbsp;删除模板</li>
					<li style="float: right; margin-right: 100px; background: none;"><img
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

						<tr ng-repeat="t in templatelist">
							<th>{{t.templateName}}</th>
							<th>{{t.className}}</th>
							<th>{{t.classPrice}}</th>
							<th>{{t.classDiscountPrice}}</th>

						</tr>
					</table>

				</div>

				<div class="col-sm-6">
					<ul uib-pagination boundary-links="true" total-items="total"
						ng-model="page" items-per-page="pageSize" max-size="5"
						class="pagination-sm" previous-text="&lsaquo;"
						next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
						ng-change="courseBases()">
					</ul>
				</div>


				<!--弹窗-->
				<div class="poop" id="add"style="width: 60%; height: 600px; position: absolute; left: 15%; top: 15%;">
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
									<input type="hidden" ng-model="courseClassTemplate.classUrl"
										id="textfield" style="border: solid 1px #B1B1B1;" />
									<button class="allBtn costs-marl15">班级图片</button>
									<div
										style="height: 130px; width: 40%; border: solid 1px #B1B1B1; margin-top: 3px;">
										<img ng-src="{{classUrl}}" />
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
						<input name="git" type="submit" value="提交"
							ng-show="{{courseId==null}}" ng-click="addTemplate()"
							style="background: #5ED8A9;" /> <input name="esc" type="reset"
							value="取消" onclick="CloseDiv();formReset()" class="esc" />
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
	height: 600px;
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
