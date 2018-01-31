$(document).ready(function(){
	$(".pull-child").hide();var num=0;
	$(".pull-btn").click(function() {
		$(".pull-child").fadeToggle();
		num+=1;
		if(num%2 ==0){
			$(".pull-btn").css("background-image","url(img/add.png)")
		} else{
			$(".pull-btn").css("background-image","url(img/minus.png)")
		}
	});
	
})
