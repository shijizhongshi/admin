<#import "/layout/header.ftl" as h/> <#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="banner广告管理页面"/>
<link rel="stylesheet" href="/styles/admin.css" />
<script src="/scripts/admin.js"></script>
<@b.body menu="sidebarmenu-system" submenu="sidebarmenu-system-index">
<div class="details" style="width: 100%">
	<div class="details-nav">
		<ul>
			<li><img src="/images/sjk-home.png" style="color: red;" />我的主页</li>
			<li>/</li>
			<li>欢迎您~</li>
		</ul>
	</div>
<div  style="background: #EDEEF2;width:100%;padding-bottom:20px;">
<img  src="/images/inder-bg.png" style="width:100%;"/>
<div style="border-radius:5px;width: 98%;margin:0 auto;background: white;padding: 15px;margin-top: -110px;position: relative;">
<ul class="tag">
<li>单位名称：<span>北京中视传承中医药研究院</span></li>
<li>部门：<span>新媒体部</span></li>
<li>登录人员：<span>某某</span></li>
<li style="float:right;margin:0;"><span>2019年3月28日星期三</span></li>
</ul>
<div class="unresolved">
<div class="unresolvedtit">
<p class="tit-left">待办事项-&nbsp;<span>2项</span>&nbsp;&nbsp;<i>更多</i></p>
<ul class="tit-right">
<li>批量删除</li>
<li style="background:#5693FE;color: white;">批量处理</li>
</ul>
<table>
<tr>
<td><input type="checkbox"/>任务详情</td>
<td>状态</td>
<td>负责人</td>
<td>时间</td>
<td>操作</td>
</tr>
<tr>
<td><input type="checkbox"/>协同办公登录地址</td>
<td>未完成</td>
<td>负责人</td>
<td>3-9 3：00</td>
<td><span>执行</span><span>完成</span></td>
</tr>
</table>
</div>
</div>

</div>
</div>	
	
</div>
</@b.body>    
<style >
.tag{padding:10px 0;border-bottom:1px solid #EFEFEF;overflow: hidden;padding-bottom:20px;}
.tag li{float:left;color:#999;font-size: 1.4rem;margin-right:8%;}
.tag li span{color:#333;text-indent: 1em;font-size: 1.4rem;display: inline-block;}
.unresolved{padding-top:20px;}
.unresolvedtit{}
.tit-left{font-size:2.0rem;font-weight: bold;float:left;}
.tit-left span{color:red;font-size:2.0rem;font-weight: bold;}
.tit-left i{font-style:normal;color:#999;font-weight:normal;}
.tit-right{float:right;}
.tit-right li{float:left;padding:3px 10px;border-radius:10px;background:#F2F7FD; margin-left:15px;}
.unresolved table{width:100%;clear: both;border: 1px solid #F2F2F2;border-radius:5px;}
.unresolved table tr td{padding:10px 0;text-align: center;font-size：1.4rem;}
.unresolved table tr td input{margin-right:5px;}
.unresolved table tr td span{display: inline-block;padding: 0 5px;border-right:1px solid #4C96F9;height: 1.2rem;line-height: 1rem;color:#4C96F9;}
.unresolved table tr:nth-child(2n-1){background:#F4F5F9;}
.unresolved table tr:nth-child(2n){background:#FFFFFF;}
</style>
</html>
