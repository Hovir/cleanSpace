<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
	<#assign base=springMacroRequestContext.contextPath/>
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<title>环保空间</title>
    <link rel="stylesheet" type="text/css" href="${base}/css/header-footer.css"/>
	<script src="${base}/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	<link rel="stylesheet" type="text/css" href="${base}/css/upload.css"/>
</head>
<body>
	<div class="header-footer">
		<div class="header">
			<div class="left">
				<div class="return">
					<a href="">
						<img src="${base}/img/back-white.png" />
					</a>
				</div>
			</div>
			<div class=" middle">
				<p>提交实验报告</p>
			</div>
			<div class=" right">
				<div class="go" id="complete">
					<a href="javascript:void(0)" onclick="doSubmit()">
						<p>完成</p>
					</a>
				</div>
			</div>
		</div>
	</div>
	<div class="content">
		<div class="leave">
			<textarea name="remarkArea" rows="" cols="" placeholder="留言..."></textarea>
		</div>
		<div class="uploadimg">
			<ul class="upload-ul clearfix">
				<li class="upload-pick" style="background-image: url(${base}/img/upload-bj.png);">
					<div class="webuploader-container clearfix" id="goodsUpload"></div>
				</li>
			</ul>
		</div>
		<form id="dataForm" action="${base}/order/updateOrderInfo" method="post">
			<input type="hidden" name="id"/>
			<input type="hidden" name="report"/>
            <input type="hidden" name="remark"/>
			<input type="hidden" name="companyId value="${session.companyId}/">
		</form>
	</div>
<script src="${base}/js/webuploader.min.js"></script>
<script>
     $('#goodsUpload').diyUpload({
            url:'/company/uploadReport/'+${orderId},
            success:function(backdata) {
                console.log(backdata);
                if(backdata["message"]=="noLogin"){
                    window.location.href="${base}/company/myCompany";
				}else if(backdata["message"]=="success"){
                    alert(backdata["orderId"]);
                    $("[name='id']").val(backdata["orderId"]);
                    $("[name='report']").val(backdata["reportUrl"])
				}
            },
            error:function( err ) { },
            buttonText : '',
            accept: {
                title: "Images",
                extensions: 'gif,jpg,jpeg,bmp,png'
            },
            thumb:{
                width:120,
                height:90,
                quality:100,
                allowMagnify:true,
                crop:true,
                type:"image/jpeg"
            },
            auto:true,

        });
     
     function doSubmit() {
         var report=$("[name='id']").val();
         var remark=$("[name='remarkArea']").val();
         alert($("[name='companyId']").val());
		 $("[name='remark']").val(remark);
		 if((typeof report)!="undefined"&&(typeof remark)!="undefined"&&report!=""&&remark!=""){
		 $("#dataForm").submit();
		 }
     }

</script>
</body>
</html>