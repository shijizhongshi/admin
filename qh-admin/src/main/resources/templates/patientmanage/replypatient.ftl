<#import "/layout/header.ftl" as h/> <#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="发布管理" />
<link rel="stylesheet" href="/styles/admin.css" />
<script src="/scripts/admin.js"></script>
<script src="/scripts/patientmanage/replypatient.js"></script>
<style>
.message{background:#fbfbfd;padding:10px;border:1px solid #E5E5E5;border:1px solid #E5E5E5;}
.user-message{border-right:1px solid #ece8e8;}
.patient-message{height:auto}
.describes{min-height:auto}
.describes span{font-size:1.5rem;}
</style>
<@b.body menu="sidebarmenu-patientmanage" submenu="sidebarmenu-patientmanage-patient">

<div ng-controller="replyPatientController">
	<div class="details" style="width: 100%">
	<input type="hidden" value="${id}" id="id" />
			<div class="details-nav">
				<ul>
					<li><img src="/images/sjk-home.png" style="color: red;" />我的主页</li>
					<li>/</li>
					<li>发布管理</li>
					<li>/</li>
					<li>评论管理</li>
					<li>/</li>
					<li>评论详情</li>
				</ul>
			</div>
			<div class="details-frame">
				
				<div id="guanli">
					<div class="manage" style="width:1200px">
						
						<ul class="show">
						
							<li ng-click="returnpatient()" style="background: #F9CD33;"><span
								class="glyphicon glyphicon-pencil"></span>&nbsp;返回</li>
							<li ng-click="patientdelete()" style="background: #F86846;"><span
									class="glyphicon glyphicon-plus"></span>&nbsp;删除评论</li>
							
							<li style="float: right; margin-right: 20px; background: none;"><img
								src="/images/sjk-f5.png" name="changyi" ng-click="refresh()" /></li>
						</ul>
						<div class="admin-table">

							<div class="message" style="border-bottom: none;">
								<span style="float:left;margin-left: 10px;text-align:center;font-size:24px;position: relative;">{{replypatient.title}}</span>
								<span style="float:right;margin-right: 10px;margin-top: 6px;text-align:center;font-size:16px;position: relative;">{{replypatient.category}}</span>
							</div>
							
							<div class="message">
								<div class="user-message" style="">
									<ul> 
										<li class="head-img" style="text-align:center;">
											<div style="height:auto" >
												<img style="width:80%;margin:0 auto;" src="{{replypatient.publisherHeadImg}}">
											</div>
										</li>
										<li class="name" style="text-align:center;">
										
												<p ng-click="touser(replypatient.publisher)"  style="text-align:center;font-size:1.8rem;font-weight:bold;position: relative;">{{replypatient.publisher}}</p>
									
										</li>
										<li class="position" style="text-align:center;">
												<p style="text-align:center;font-size:1.6rem;;position: relative;color: #69b1ea;">{{replypatient.publisherPosition}}</p>
										</li>
									</ul>
								</div>
								<div class="patient-message">
									<div class="patients">
										<div class="describes">
											<span>{{replypatient.describes}}</span>
										</div>
										<div class="patients-img">
											<div class="img" ng-repeat="il in imglist" style="text-align:center;">
												<i ng-click="deleteimg(il)" ng-show="selectdelete==il" class="glyphicon glyphicon-remove" style="float: right; color: #666; font-size: 1.1rem"></i>
												<img ng-click="checkimg(il)" style="height:100%;width: 100%;" src="{{il.imgUrl}}">
												
											</div>
										</div>
									</div>
									<div  class="timeandlikes" style="text-align:right;">
											<span><b>赞:{{replypatient.likes}}</b>&nbsp;&nbsp;<b>时间:{{replypatient.addtime | date:'yyyy.MM.dd HH:mm:ss'}}</b></span>
									</div>
								</div>
							</div>
							
							<div class="message" style="border-top:none;"ng-repeat="rl in replyList">
								<div class="user-message" style="min-height:auto" >
									<ul>
										<li class="head-img" style="text-align:center;">
											<div style="height:auto">
												<img style="width: 80%;margin:0 auto;" src="{{rl.replyHeadImg}}">
											</div>
										</li>
										<li class="name" style="text-align:center;">
										
												<span ng-click="touser(rl.replyName)" style="text-align:center;font-size:1.5rem;position: relative;">{{rl.replyName}}</span>
									
										</li>
										
									</ul>
								</div>
								<div class="patient-message" style="min-height:auto">
									<div class="patients">
										<div class="describes" style="padding-bottom:10px;">
										<i ng-click="deletereply(rl)" class="glyphicon glyphicon-remove" style="float: right; color: #666; font-size: 1.1rem"></i>
											<span>{{rl.replyContent}}</span>
										</div>
										
									</div>
									<div  class="timeandlikes" style="text-align:right;">
											<span><b>赞:{{rl.likes}}</b>&nbsp;&nbsp;<b>时间:{{rl.addtime | date:'yyyy.MM.dd HH:mm:ss'}}</b></span>
									</div>
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