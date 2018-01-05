<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="/js/jquery-3.1.1.js"></script>
<script type="text/javascript" src="/js/jquery.form.js"></script>
<body>	

<span style="display: block;" id="mm">${msg! }</span>
	<form enctype="multipart/form-data" id="batchUpload"   method="post" class="form-horizontal">    
	    <input type="file" name="file" id="uploadEventFile" value="选择文件">                              
		<button type="button" id="submit_upload" >上传</button>  
	</form> 
	
	<input type="button" id="but_dc" value="一键导出数据">
	<span style="color:red" id="msg"></span>
	
</body>
<script type="text/javascript">
	$(function(){
	
		$("#submit_upload").click(function(){
			$("#mm").html("正在上传...");
			$("#batchUpload").ajaxSubmit({
				url:"/user/upload",
				type:"post",
				dataType:"Json",
				success:function(data){
					
					if(data.status==200){
						$("#mm").html("上传成功！");
					}else{
						alert(data.msg);
					}
				}
			});
		});
		
		$("#but_dc").click(function(){
			
			if($("#msg").html().trim()=="正在导出数据..."){
				return;
			}
			$("#msg").html("正在导出数据...");
			$.get("/user/daochu",function(data){
				if(data.status==200){
					$("#msg").html("导出数据成功!");
				}else{
					$("#msg").html("导出数据失败了!");
					
				}
			});
		});
	});
</script>
</html>