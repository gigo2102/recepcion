<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>FirstFewLines.com - SparkJava file upload example</title>
</head>
<body>
<h2>FirstFewLines.com - SparkJava file upload example</h2>
<hr>
<div style="display:block">
    <form action="/api/upload" method="post" enctype="multipart/form-data">
        <label for="myfile">Select a file</label>
        <input type="file" id="myfile" name="myfile"/>
        <input type="submit" id="buttonUpload" value="Upload"/>
        <br>
        <p>Result:&nbsp;:<span id="result"></span></p>
    </form>
</div>
</body>
</html>