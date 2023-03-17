<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
</head>
<body>
	<select name="member">
		
	</select>
	<div id="btsdisp">
	
	</div>
<script type="text/javascript">

	
	
	let memberSelect = $("[name='member']").on('change',function(event){
		let code = $(this).val(); //선택한 option값
<%-- 		location.href = "<%=request.getContextPath()%>/bts/" + code; --%>
		let disp = $("#btsdisp");
		$.ajax({
			url : "<%=request.getContextPath()%>/bts/"+code,
			dataType : "html",
			success : function(resp) {
			 	disp.html(resp); // 영역안에서 한번만 출력
				//memberSelect.after(resp); // 중첩되서 출력됨
			},
			error : function(jqXHR, status, error) {
				console.log(jqXHR);
				console.log(status);
				console.log(error);
			}
		});
	});
	
	$.ajax({
		url : "<%=request.getContextPath()%>/bts",
		dataType : "json",
		success : function(resp) {
			let bts = resp.bts;
			console.log(bts);
			let options = [];
			$.each(bts,function(code,values){
				console.log(code);
				let option = $("<option>").val(code)
							 .text(values[0]);
				options.push(option);
				
				/* html += `
					<option>\${bts}</option>
				`*/
			});
			memberSelect.append(options);
		},
		error : function(jqXHR, status, error) {
			console.log(jqXHR);
			console.log(status);
			console.log(error);
		}
	});
	
</script>
<jsp:include page="/includee/postScript.jsp"></jsp:include>	
</body>
</html>