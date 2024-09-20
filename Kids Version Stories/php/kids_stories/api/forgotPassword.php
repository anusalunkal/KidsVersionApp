<?php 
include('connection.php');

	
	$email=$_POST['email'];

	$sel="SELECT * FROM user_tbl WHERE email='$_POST[email]'";
	$result = mysqli_query($con,$sel) or die(mysql_error());
	$row=mysqli_fetch_array($result);

	if($result > 0){

		$subject="Welcome To Kids Version Stories";
	    $title="Your Password";
	    $msg="Greetings from Kids Version Stories. \n Your Username is: $row[username] \n Your password is: $row[password]";
	    include('mail.php');

	    echo"";
	}else{
		echo "failed";
	}
	
	


?>