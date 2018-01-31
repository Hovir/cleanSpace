<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<title>乐美优享</title>
    <link rel="stylesheet" type="text/css" href="css/header-footer.css"/>
	<script src="js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	<link rel="stylesheet" type="text/css" href="css/upload.css"/>
</head>
<body>
	<div class="header-footer">
		<div class="header">
			<div class="left">
				<div class="return">
					<a href="">
						<img src="img/back-white.png" />
					</a>
				</div>
			</div>
			<div class=" middle">
				<p>银行卡</p>
			</div>
			<div class=" right">
				<div class="go">
					<a href="">
						<p>完成</p>
					</a>
				</div>
			</div>
		</div>
	</div>
	<div class="content">
		<div class="leave">
			<textarea name="" rows="" cols="" placeholder="留言..."></textarea>
		</div>
		<div class="uploadimg">
			<ul class="upload-ul clearfix">
				<li class="upload-pick" style="background-image: url(img/upload-bj.png);">
					<div class="webuploader-container clearfix" id="goodsUpload"></div>
				</li>
			</ul>
		</div>
	</div>
<script src="js/webuploader.min.js"></script> 
<script>
    $(function(){
        //上传图片
        var $tgaUpload = $('#goodsUpload').diyUpload({
            url:'/uploadFilePath',
            success:function( data ) { },
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
            }
        });
    });
</script>
</body>
</html>