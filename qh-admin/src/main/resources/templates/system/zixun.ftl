<#import "/layout/header.ftl" as h/> <#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="课程管理页面"/>
<link rel="stylesheet" href="/styles/admin.css" />
<script src="/scripts/system/news.js"></script>
<script src="/assets/js/ueditor/ueditor.config.js"></script>
<script src="/assets/js/ueditor/ueditor.all.min.js"></script>
<script src="/assets/js/ueditor/lang/zh-cn/zh-cn.js"></script>
<script src="/scripts/admin.js"></script>

<@b.body menu="sidebarmenu-system" submenu="sidebarmenu-system-zixun">
<div>
	<div class="details" style="width: 100%" ng-controller="newController">
		<div class="details-nav">
			<ul>
				<li><img src="/images/sjk-home.png" style="color: red;" />我的主页</li>
				<li>/</li>
				<li>咨询/话题 管理</li>
			</ul>
		</div>
		<div class="details-frame">
			<div class="details-frame-content">

				<div class="select-2">
					<span>新闻标题</span>
					<form id="search">
						<input type="text" ng-model="title" placeholder="搜索新闻标题" />
					</form>
				</div>
				<div>
					<input type="button" class="btn-lg im-key" ng-click="loaddata()"
						value="检索" />
				</div>


			</div>
			<div class="manage">
				<ul class="show">

					<li ng-click="add()" style="background: #9DE879;"><span
						class="glyphicon glyphicon-plus"></span>&nbsp;添加新闻/话题</li>
					<li ng-click="update()" style="background: #F9CD33;"><span
						class="glyphicon glyphicon-pencil"></span>&nbsp;修改</li>
					<li ng-click="deletenews()" style="background: #F86846;"><span
						class="glyphicon glyphicon-trash"></span>&nbsp;删除</li>
					<li style="float: right; margin-right: 20px; background: none;"><img
						src="/images/sjk-f5.png" name="changyi" ng-click="reset()" /></li>
				</ul>
				<div class="admin-table">

					<table>
						<tr>
							<th>分类</th>
							<th>标题</th>
							<th>封面图</th>

							<th>是否推荐</th>
							<th>是否展示</th>

						</tr>

						<tr ng-repeat="new in newlist" ng-click="checkednews(new)"
							ng-class="{'selected':selected==new}">
							<th>{{new.types}}</th>
							<th>{{new.title}}</th>
							<th><img src="{{new.imgUrl}}"
								style="width: 50px; height: 30px;" /></th>
							<th ng-show="{{new.isrecommend==1}}">推荐</th>
							<th ng-show="{{new.isrecommend==0}}">不推荐</th>

							<th ng-show="{{new.status==0}}">展示</th>
							<th ng-show="{{new.status==1}}">失效</th>

						</tr>
					</table>

					<div class="col-sm-6"></div>
					<div class="col-sm-6">
						<ul uib-pagination boundary-links="true" total-items="total"
							ng-model="current" items-per-page="pageSize" max-size="5"
							class="pagination-sm" previous-text="&lsaquo;"
							next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
							ng-click="loaddata()">
						</ul>
					</div>

				</div>



				<!--弹窗-->
				<div class="poop" id="add"
					style="width: 60%; height: 600px; position: absolute; left: 15%; top: 15%;">
					<form id="myform">
						<h3>添加咨询/话题</h3>
						<div class="template-add">
							<div class="template-left" style="padding-right: 5%;">

								<div class=" select-3">

									<span>标题：</span> <input type="text" ng-model="news.title" />
								</div>
								<div class=" select-3">

									<span>类别：</span> <input type="text" ng-model="news.types" />
								</div>
								<div class=" select-2" style="clear: both;">

									<span>一句话简介</span> <input type="text"
										ng-model="news.simpleIntro" />
								</div>
								<div class=" select-2">
									<img src="/images/sjk-xl.png" /> <span>咨询/话题选择</span> <select
										ng-model="news.contentTypes">
										<option ng-selected="news.contentTypes==1" value="1">新闻</option>
										<option ng-selected="news.contentTypes==2" value="2">话题</option>
									</select>
								</div>
								<div class=" select-2" style="clear: both;">
									<img src="/images/sjk-xl.png" /> <span>选择类型</span> <select
										ng-model="news.typename">
										<option ng-selected="news.typename=='医师资格'" value="医师资格">医师资格</option>
										<option ng-selected="news.typename=='药师资格'" value="药师资格">药师资格</option>
										<option ng-selected="news.typename=='中医适宜技术'" value="中医适宜技术">中医适宜技术</option>
										<option ng-selected="news.typename=='卫生资格'" value="卫生资格">卫生资格</option>
										<option ng-selected="news.typename=='健康管理师'" value="健康管理师">健康管理师</option>
									</select>
								</div>
								<div class="costs-uploadfile-div">
									<div>上传图片</div>
									<input type="file" name="file"
										onchange="angular.element(this).scope().uploadmainimage(this)"
										accept="image/*" />
									<div style="height: 130px; width: 40%; margin-top: 3px;">
										<img style="height: 130px;" src="{{imgUrl}}" />
									</div>
								</div>
								<div class="select-radio ">
									<ul>
										<li>是否推荐</li>
										<li><input type="radio" ng-model="news.isrecommend"
											ng-value="1" /> 是</li>
										<li><input type="radio" ng-model="news.isrecommend"
											ng-value="0" />否</li>
									</ul>
								</div>
								<div class="select-radio ">
									<ul>
										<li>是否展示</li>
										<li><input type="radio" ng-model="news.status"
											ng-value="0" /> 是</li>
										<li><input type="radio" ng-model="news.status"
											ng-value="1" />失效</li>
									</ul>
								</div>
							</div>

							<div class="template-right">
								<p style="height: 40px; font-size: 1.3rem;">班级详情</p>
								<!--<textarea id="editor" ></textarea>
								<script type="text/javascript">var ue = UE.getEditor('editor');</script>-->
								<script id="editor" type="text/plain"
									style="width: 90%; height: auto;"></script>
							</div>
					</form>
					<div class="end">
						<input name="git" type="submit" value="提交" ng-if="newsId==null"
							ng-click="newadd()" style="background: #5ED8A9;" /> <input
							name="git" type="submit" value="修改" ng-if="newsId!=null"
							ng-click="newsupdate()" style="background: #5ED8A9;" /> <input
							name="esc" type="reset" value="取消" ng-click="cancel()"
							class="esc" />
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
