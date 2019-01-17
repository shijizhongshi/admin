<#import "/layout/header.ftl" as h/> 
<#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="直播管理"/>
<link rel="stylesheet" href="/styles/admin.css" />
<script src="/scripts/course/grade-template.js"></script>
<script src="/scripts/admin.js"></script>

<@b.body menu="sidebarmenu-system" submenu="sidebarmenu-system-live">
<div ng-controller="CourseClassTemplateController">
<div class="details" style="width: 100%">
	<div class="details-nav">
		<ul>
			<li><img src="/images/sjk-home.png" style="color: red;"/>我的主页</li>
			<li>/</li>
		<li>直播管理</li>
		</ul>
	</div>
<div class="details-frame" >
	<div class="details-frame-content" style="height:110px;padding-bottom:0;">

	<div class="select-3" style="width:15%;">
	<span>直播名称</span>
	<form id="">
	<input type="text"/></form>
	</div>
		<div>
					<input type="button" class="btn-lg im-key" ng-click="loaddata()"
						value="检索" ng-click="search()" />
				</div>
	</div>
<div class="manage">
	<ul class="show">

					<li ng-click="add()"
						style="background: #9DE879;"><span
						class="glyphicon glyphicon-plus"></span>&nbsp;添加</li>
					<li ng-click="update()" style="background: #F9CD33;"><span
						class="glyphicon glyphicon-pencil"></span>&nbsp;修改</li>
					<li ng-click="deletetemplate()" style="background: #F86846;"><span
						class="glyphicon glyphicon-trash"></span>&nbsp;删除</li>
					<li ng-click="refresh()" style="float: right; margin-right:20px; background: none;"><img
						src="/images/sjk-f5.png" name="changyi" /></li>
				</ul>
				<div class="admin-table">

					<table>
						<tr>
							<th>直播名称</th>
							<th>直播图片</th>
							<th>显示级别</th>
							<th>创建时间</th>

						</tr>

						<tr>
							<th></th>
							<th></th>
							<th></th>
							<th></th>

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
				<div class="poop" id="add">
					<form id="myform">
						<h3>直播管理</h3>
						<div class="template-add">
						
							<div class="select-2">
								<span>直播名称<i class="bitian">*</i></span> 
								<input type="text" ng-model="name" class="ng-pristine ng-untouched ng-valid ng-empty">
							</div>
							<div class=" select-2">
									<img src="/images/sjk-xl.png" /> <span>显示级别<i class="bitian">*</i></span> <select>
										<option disabled selected style='display: none;'></option>
										<option></option>
										<option></option>
									</select>
								</div>
								<div class="select-2">
								<span>直播链接</span> 
								<input type="text">
							</div>
							<div class="costs-uploadfile-div">
										课程图片<input type="file" name="file" value="上传课程图片" onchange="">
										<input type="hidden">
										<div class="costs-img">
											<img src="">
										</div>
									</div>
						</div>
					</form>
					<div class="end">
						<input name="git" type="submit" value="提交" style="background: #5ED8A9;" class=""> 
							<input name="git" type="submit" value="修改" style="background: #5ED8A9;" class="ng-hide">
							<input name="esc" type="reset" value="取消" onclick="CloseDiv();formReset()" class="esc">
					</div>
				</div>
</div>
					</div>
		</div>

 
<style type="text/css">
.poop{width: 400px;height:490px;}
.poop .select-2{width:90%;}
 .poop .select-2 input ,  .poop .select-2  select {border-radius:0;}

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
