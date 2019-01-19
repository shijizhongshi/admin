<#import "/layout/header.ftl" as h/> <#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="专题活动"/>
<link rel="stylesheet" href="/styles/admin.css" />
<script src="/scripts/course/grade-template.js"></script>
<script src="/scripts/admin.js"></script>

<@b.body menu="sidebarmenu-system-zhuanti"
submenu="/web/system/zhuanti">
<div >
	<div class="details" style="width: 100%">
		<div class="details-nav">
			<ul>
				<li><img src="/images/sjk-home.png" style="color: red;" />我的主页</li>
				<li>/</li>
				<li>公共资源管理</li>
				<li>/</li>
				<li>专题活动</li>
			</ul>
		</div>
		<div class="details-frame">
			<div class="details-frame-content">

				<div class="select-2">
					<span>商品名称</span>
					<form id="search">
						<input type="text" placeholder="" />
					</form>
				</div>
				<div>
					<input type="button" class="btn-lg im-key" ng-click="loaddata()"
						value="检索" ng-click="search()" />
				</div>

			</div>
			<div class="manage">
				<ul class="show">

					<li onclick="showDiv()"
						style="background: #9DE879;"><span
						class="glyphicon glyphicon-plus"></span>&nbsp;添加</li>
					<li ng-click="update()" style="background: #F9CD33;"><span
						class="glyphicon glyphicon-pencil"></span>&nbsp;修改</li>
					<li ng-click="deletetemplate()" style="background: #F86846;"><span
						class="glyphicon glyphicon-trash"></span>&nbsp;删除</li>
					<li><span class="glyphicon glyphicon-sort" class="move-up"></span>&nbsp;上移</li>
					<li><span class="glyphicon glyphicon-sort-by-attributes"
						class="move-down"></span>&nbsp;下移</li>
					<li style="float: right; margin-right: 100px; background: none;"><img
						src="/images/sjk-f5.png" name="changyi" /></li>
				</ul>
				<div class="admin-table">

					<table>
						<tr>
							<th>专题名称</th>
							<th>专题banner</th>
							<th>连接产品</th>
							<th>专题链接</th>
							<th>展示时间</th>
							<th>创建时间</th>

						</tr>

						<tr>
							<th>专题名称</th>
							<th>专题banner</th>
							<th>连接产品</th>
							<th>专题链接</th>
							<th>展示时间</th>
							<th>创建时间</th>

						</tr>
					</table>

				</div>
				<div class="col-sm-6"></div>
				<div class="col-sm-6">
					<ul uib-pagination boundary-links="true" total-items="total"
						ng-model="current" items-per-page="pageSize" max-size="5"
						class="pagination-sm" previous-text="&lsaquo;"
						next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
						ng-change="courseBases()">
					</ul>
				</div>


				<!--弹窗-->
				<div class="poop" id="add"
					style="width: 60%; height: 600px; position: absolute; left: 15%; top: 15%;">
					<form id="myform">
						<h3>添加推荐商品</h3>
						<div class="template-add">
							<div class="template-left" style="padding-right: 5%;">
								<p>选择店铺</p>
								<div class=" select-3">
									<img src="/images/sjk-xl.png" /> <span>商品店铺：</span> <select>
										<option disabled selected style='display: none;'></option>
										<option></option>
										<option></option>
									</select>
								</div>
								<div class=" select-2" style="float: left;">
									<span>&nbsp;</span> <input type="text" class=""
										placeholder="按名称搜索" /> <i
										style="position: absolute; right: 10px; top: 42px; display: inherit; cursor: pointer; font-size: 1.9rem;"
										class="glyphicon glyphicon-search"></i>
								</div>
								<div class="dianpu">
									<table>
										<tr>
											<th><input type="checkbox" /></th>
											<th>药店</th>
											<th>商品店铺</th>
										</tr>
										<tr>
											<th><input type="checkbox" /></th>
											<th>药店</th>
											<th>商品店铺</th>
										</tr>
									</table>
								</div>
							</div>

							<div class="template-right">
								<p style="height: 40px; font-size: 1.3rem;">班级详情</p>
								<textarea id="editor" style="width: 90%; height: auto;"></textarea>
								<script type="text/javascript">var ue = UE.getEditor('editor');</script>
							</div>
					</form>
					<div class="end">
						<input name="git" type="submit" value="提交"
							style="background: #5ED8A9;" /> <input name="esc" type="reset"
							value="取消" onclick="CloseDiv();formReset()" class="esc" />
					</div>
				</div>
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
	height: 500px;
	overflow: auto;
}

.dianpu table {
	width: 90%;
	height: auto;
	border: 1px solid #B1B1B1;
	margin: 0 auto;
	border-radius: 10px;
}

.dianpu table tr th {
	text-align: center;
	height: 42px;
	line-height: 42px;
	border-right: 1px solid #F2F2F2;
	margin: 0;
}

.dianpu table {
	width: 100%;
	border-collapse: collapse;
	border: 1px solid #dbdbdb;
}

.dianpu table tr {
	width: 100%;
	height: 42px;
	margin-bottom: 0;
	border-bottom: 1px solid #dbdbdb;
}

.dianpu table tr:nth-child(2n) {
	background: #F3F4F6;
}

.dianpu table input {
	width: 18px;
	height: 18px;
}
.details-frame-content .select-2{float: left; margin-right: 15px; width: 18%;}
</style>
</@b.body>

</html>
