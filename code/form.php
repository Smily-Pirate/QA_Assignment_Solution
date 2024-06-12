
<?php

if(isset($_POST["submit"]))
{
	
	$target_dir = "uploads/";
	if(!file_exists($target_dir)){
		mkdir($target_dir, 0777, true);
	}
	
	$filename = basename($_FILES["document"]["name"]);
	$filename_sanitized = preg_replace("/[^a-zA-Z0-9._-]/", '', $filename);
	
	if($filename !== $filename_sanitized){
		echo "Invalid filename. Only alphanumeric characters, dots, underscores, and hyphens are allowed.";
		exit;
	}
	
	if(isset($_FILES["document"]) && $_FILES["document"]["error"] == 0){
		$file_type = mime_content_type($_FILES["document"]["tmp_name"]);
		$file_extension = pathinfo($_FILES["document"]["name"], PATHINFO_EXTENSION);
		
		
		if($file_type === 'application/pdf' && strtolower($file_extension) === 'pdf'){
			$target_file = $target_dir.basename($_FILES["document"]["name"]);
			
			if(move_uploaded_file($_FILES["document"]["tmp_name"], $target_file)){
				echo "Thank you. Your file is uploaded.";
			}else{
				echo "Sorry, there was an error uploading your file.";
			}
		}else{
			echo "Sorry, only PDF files are allowed.";
		}
	}else{
		echo "No file uploaded or there was an upload error.";
	}
	
}

?>

<form method="POST" enctype="multipart/form-data">
	Select document to upload (only pdf are allowed, max size 5MB): <input type="file" name="document" accept=".pdf,application/pdf" oninput="validateSize(this)">
	<input type="submit" value="Upload Document" name="submit">

</form>

<script>
function validateSize(fileInput){
	const maxAllowedSize = 5*1024*1024;
	const fileInfo = fileInput.files[0];
	const filename = fileInfo.name;
	const validCharacters = /^[a-zA-Z0-9._-]+$/;
	
	//Checks for file greater than 5MB.
	if(fileInfo.size > maxAllowedSize){
		alert('File size must not exceed 5MB.');
		fileInput.value='';
		return false;
	}
	
	//Checks for file name longer than 50 characters
	if (filename.length > 50){
		alert("Filename is too long. Only 50 character allowed.");
		fileInput.value = '';
		return false;
	}
	
	//Checks for invalid filename
	if(!validCharacters.test(filename)){
		alert("Invalid filename. Only alphanumeric characters, dots, underscores, and hyphens are allowed.");
		fileInput.value = '';
		return false;
	}
	
	return true;
	
	
}

</script>