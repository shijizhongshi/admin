<#import "/layout/header.ftl" as h/> <#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="考官提问管理"/>
<link rel="stylesheet" href="/styles/admin.css" />
<link rel="stylesheet" href="/styles/management.css" />
<script src="/scripts/admin.js"></script>
<script src="/scripts/student/management.js"></script>
<script src="/scripts/questionBank/examinerQuestion.js"></script>
<style>
		.product-buyer-name {

			max-width: 110px;

			overflow: hidden;

			text-overflow: ellipsis;

			white-space: nowrap;

		}
	</style>
<@b.body menu="sidebarmenu-questionBank"
submenu="sidebarmenu-questionBank-examinerQuestion">
<div ng-controller="examinerQuestionController">

	<div class="classify">
		<ul class="menu">

			<li ng-repeat="ctl in courseTypeList" class="list" ng-click="typeList(ctl.courseTypeName,ctl.id)">{{ctl.courseTypeName}}
				<ul class="items" ng-class="{'active':active==ctl.id}">
					<li ng-repeat="sub in courseTypeSubclass"
						ng-click="questionSub(ctl.courseTypeName,sub,$event)"
						ng-class="{'selected':typeSelected==sub.courseTypeSubclassName}">{{sub.courseTypeSubclassName}}</li>

				</ul>
				</li>

</ul>
	</div>


	<div class="details">
		<div class="details-nav">
			<ul>
				<li><img src="/images/sjk-home.png" />我的主页</li>
				<li>/</li>
				<li>题库管理</li>
				<li>/</li>
				<li>考官提问管理</li>
			</ul>
		</div>
		<div class="details-frame">

			<div class="manage">
				<ul class="show">

					<li style="background: #9DE879;" ng-click="add()"><span
						class="glyphicon glyphicon-plus"></span>&nbsp;添加问题</li>
					<li style="background: #F9CD33;" ng-click="update()"><span
						class="glyphicon glyphicon-pencil"></span>&nbsp;修改问题</li>
					<li style="background: #F86846;" ng-click="deletequestion()"><span
						class="glyphicon glyphicon-trash"></span>&nbsp;删除问题</li>
					<li style="background: #9DE879;" ng-click="batch()"><span
						class="glyphicon glyphicon-plus"></span>&nbsp;Excel 批量上传</li>
					<!-- <li ng-click="tojie()"><span
						class="glyphicon glyphicon-briefcase"></span>&nbsp;节内容管理</li> -->

				</ul>
				<div class="admin-table">

					<table id="tableExcel">

						<tbody>
							<tr>
								<th>问题</th>
								<th>答案</th>

								<th>创建时间</th>
								<th>修改时间</th>
							</tr>

							<tr ng-repeat="qc in list"
								ng-click="checkquestioncate(qc)"
								ng-class="{'selected':selected==qc}">
									
								<th class = "product-buyer-name">{{qc.questionAsk}}</th>
								<th class = "product-buyer-name">{{qc.questionAnswer}}</th>
								<th>{{qc.addtime | date:'yyyy.MM.dd HH:mm:ss'}}</th>
								<th>{{qc.updatetime | date:'yyyy.MM.dd HH:mm:ss'}}</th>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="col-sm-6"></div>
				<div class="col-sm-6">
					<ul uib-pagination boundary-links="true" total-items="total"
						ng-model="page" items-per-page="pageSize" max-size="5"
						class="pagination-sm" previous-text="&lsaquo;"
						next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
						ng-click="questionList()()">
					</ul>
				</div>
				
				<!--添加修改问题-->
				<div class="poop" id="add">
					<form id="myform" class="ng-pristine ng-valid">
						<h3>{{}}问题内容</h3>
						<p style="padding: 10px 0;">专业类型：&nbsp;{{courseTypeSubclassName}}</p>
						<div class="select-2" ng-show = "batch">
							<span>问题内容<i class="bitian">*</i></span> <input type="text" ng-model="qc.questionAsk"
								class="ng-pristine ng-untouched ng-valid ng-empty"
								placeholder="请输入问题内容">
						</div>
						<div class="select-2" ng-show = "batch">
							<span>答案内容<i class="bitian">*</i></span> <input type="text" ng-model="qc.questionAnswer"
								class="ng-pristine ng-untouched ng-valid ng-empty"
								placeholder="请输入答案内容">
						</div>
						<!-- <div class="costs-uploadfile-div" ng-show = "!batch">
							使用Excel批量上传<input type="file" name="file" value="选择文件" id="file">
						</div>	 -->
					
						<div class="end">
							<input name="git" type="submit" value="提交" ng-show="id==null" ng-click="addQuestion()"
								style="background: #5ED8A9;"> 
							<input name="git" type="submit" value="修改" ng-show="id!=null"
								ng-click="updateQuestion()" style="background: #5ED8A9;"
								class="ng-hide"> 
							<input name="esc" type="reset" value="取消" ng-click="reset()" class="esc">
						</div>
					</form>
				</div>
				<!--批量上传弹窗-->
				<div class="poop" id="batch">
					<form id="myform" class="ng-pristine ng-valid">
						<h3>{{}}问题内容</h3>
						<p style="padding: 10px 0;">专业类型：&nbsp;{{courseTypeSubclassName}}</p>
						
						<div class="costs-uploadfile-div">
							使用Excel批量上传<input type="file" name="file" value="选择文件" id="file">
						</div>
						
						<div class="end">
							<input name="git" type="submit" value="提交" ng-click="addBantch()"
								style="background: #5ED8A9;"> 
							<input name="esc" type="reset" value="取消" ng-click="reset()" class="esc">
						</div>
					</form>
				</div>

			</div>

		</div>

	</div>
</div>
</div>


</@b.body>

</html>