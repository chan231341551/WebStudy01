<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.6.1.min.js"></script>
<script type="text/javascript">

   $(function(){
      console.log("hi");
      let resultArea = $("#resultArea");
      $("form[name]").on("submit", function(event){
         event.preventDefault();
         let url = this.action;
         let method = this.method;
         let data = $(this).serialize();
         console.log(data);
         $.ajax({
            url : url,
            method : method,
            data : data,
            dataType : "html",
            success : function(resp) {
               console.log("hi");
               resultArea.html(resp);
            },
            error : function(jqXHR, status, error) {
               console.log("실패");
            }
         });
         return false;
      });
   });
</script>
</head>
<body>
<form name="facForm" action="<%=request.getContextPath() %>/02/factorial.do">
   <input type="number" name="number" />
   <input type="submit" value="전송" />
   <input type="reset" value="취소" />
   <input type="button" value="걍버튼" />
</form>
<div id="resultArea">

</div>
</body>
</html>