
<html>
<head>
	<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
</head>
<body>  
		<form src="${cPath}/imageStreaming.do">
			<select name='imgChoice' >  
			${options}
			</select> 
			<input type='submit' value='전송' />
		</form>
		<div>
			<img id="img_ck" src="">
		</div>
		
		<script type="text/javascript">
			$("[name=imgChoice]").on("change",function(event){
				
				//this.form.submit(); // submit이벤트가 발생하지 않음
				//$(this).parents("form").submit();
				console.log($(this).val());
				$('#img_ck').attr("src",${cPath}"/imageStreaming.do?imgChoice="+$(this).val());
						
			});
		</script>
</body>       
</html>           
		
