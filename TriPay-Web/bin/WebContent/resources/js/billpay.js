$(document).ready(function(){
	var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken = $("meta[name='_csrf']").attr("content");
	var hash_key="hash";
	var default_hash="123456";
	var headers = {};
	headers[hash_key] = default_hash;
	headers[csrfHeader] = csrfToken;
	console.log("FHGJ :: " + eStatus);
	console.log("inside bill payment");
	$("#cycle_number").hide();
	$("#ecity_provider").change(function(){
		var value = $("#ecity_provider").val();
		if(value == "REE"){
			$("#cycle_number").show();	
		}else {
			$("#cycle_number").hide();			
		}
		
	});

	//DTH bill payment
	$("#dth_submit").click(function() {
		console.log("DTH clicked");
		var valid = true;
		$("#dth_submit").html("Please Wait...");
		$("#dth_submit").addClass("disabled");
		$("#error_dth_provider").html("");
		$("#error_dth_number").html("");
		$("#error_dth_amount").html("");
		var provider = $("#dth_provider").val();
		var number = $("#dth_number").val();
		var amount = $("#dth_amount").val();
		if(provider == "" || provider == "#"){
			valid = false;
			$("#error_dth_provider").html("Select DTH Provider");
		}
		if(number.length <= 1){
			valid = false;
			$("#error_dth_number").html("Enter valid DTH No");
		}
		if(amount < 100){
			valid = false;
			$("#error_dth_amount").html("Amount must be greater than 100");
		}
		if(eStatus == "Inactive"){
			valid = false;
			$("#error_dth_amount").html("email is not Verified")
		}
		if(valid == false){
			$("#dth_submit").removeClass("disabled");
			$("#dth_submit").html("Pay");
		}
		if(valid == true){
			
			$.ajax({
				type : "POST",
				headers : headers,
				contentType : "application/x-www-form-urlencoded",
				url : "/User/BillPayment/ProcessDTH",
				data : {
					serviceProvider : provider,
					dthNo : number,
					amount : amount
				},
				success : function(response) {
					$("#dth_submit").removeClass("disabled");
					$("#dth_submit").html("Pay");
						console.log(response.response);
						var parsedResponse = JSON.parse(response.response);
						console.log(parsedResponse.details);
						if(parsedResponse.code == "F00"){
						    $("#error_dth_bill").html(parsedResponse.details);
						}
						if(parsedResponse.code == "S00"){
							$("#account_balance").html(parsedResponse.balance);
						    $("#success_dth_bill").html(parsedResponse.details);							
							$("#dth_provider").val("");
							$("#dth_number").val("");
							$("#dth_amount").val("");

						}
				}
			});

			
		}
			
	});
	
	//Landline Submission
	$("#landline_submit").click(function() {
		$("#landline_submit").html("Please Wait...");
		$("#landline_submit").addClass("disabled");
		console.log("Landline clicked");
		var valid = true;
		$("#error_landline_provider").html("");
		$("#error_landline_number").html("");
		$("#error_landline_amount").html("");
		$("#error_landline_account_number").html("");
		$("#error_landline_std").html("");
		
		var provider = $("#landline_provider").val();
		var std = $("#landline_std").val();
		var account_number = $("#landline_account_number").val();
		var number = $("#landline_number").val();
		var amountL = $("#landline_amount").val();
		
		if(provider == "" || provider == "#"){
			valid = false;
			$("#error_landline_provider").html("Select Landline Provider");
		}
		if(number.length <= 1){
			valid = false;
			$("#error_landline_number").html("Enter valid Landline No");
		}
		if(account_number.length <= 1){
			valid = false;
			$("#error_landline_account_number").html("Enter valid Account No.");
			
		}
		if(std.length <= 3){
			valid = false;
			$("#error_landline_std").html("enter valid STD code");
		}
		if(amountL < 100){
			valid = false;
			$("#error_landline_amount").html("Amount must be greater than 100");
		}
		if(eStatus == "Inactive"){
			valid = false;
			$("#error_landline_amount").html("email is not Verified")
		}
		if(valid == false){
			$("#landline_submit").removeClass("disabled");
			$("#landline_submit").html("Pay");
		}

		if(valid == true){
			
			$.ajax({
				type : "POST",
				headers : headers,
				contentType : "application/x-www-form-urlencoded",
				url : "/User/BillPayment/ProcessLandline",
				data : {
					serviceProvider : provider,
					stdCode:std,
					landlineNumber:number,
					accountNumber:account_number,
					amount : amountL
				},
				success : function(response) {
					$("#landline_submit").removeClass("disabled");
					$("#landline_submit").html("Pay");
						console.log(response.response);
						var parsedResponse = JSON.parse(response.response);
						console.log(parsedResponse.details);
						if(parsedResponse.code == "F00"){
						    $("#error_landline_bill").html(parsedResponse.details);
						}
						if(parsedResponse.code == "S00"){
							$("#account_balance").html(parsedResponse.balance);
							$("#success_landline_bill").html(parsedResponse.details)
							$("#landline_provider").val("");
							$("#landline_std").val("");
							$("#landline_account_number").val("");
							$("#landline_number").val("");
							$("#landline_amount").val("");

						}
				}
			});

			
		}
			
	});
	
	//Electricity Submission
	$("#ecity_submit").click(function() {
		$("#ecity_submit").addClass("disabled");
		$("#ecity_submit").html("Please Wait...");

		console.log("clicked");
		var valid = true;

		$("#error_ecity_provider").html("");
		$("#error_ecity_cycle_number").html("");
		$("#error_ecity_amount").html("");
		$("#error_ecity_account_number").html("");

		var provider = $("#ecity_provider").val();
		var account_number = $("#ecity_account_number").val();
		var number = $("#ecity_cycle_number").val();
		var amountL = $("#ecity_amount").val();
		
		if(provider == "" || provider == "#"){
			valid = false;
			$("#error_ecity_provider").html("Select Electricity Provider");
		}
		if(provider == "REE"){
			if(number.length <= 1){
				valid = false;
				$("#error_ecity_cycle_number").html("Enter valid Cycle No");
			}
		}
		if(account_number.length <= 1){
			valid = false;
			$("#error_ecity_account_number").html("Enter valid Account No.");
			
		}
		if(amountL < 10){
			valid = false;
			$("#error_ecity_amount").html("Amount must be greater than 10");
		}
		if(eStatus == "Inactive"){
			valid = false;
			$("#error_ecity_amount").html("email is not Verified")
		}
		if(valid == false){
			$("#ecity_submit").removeClass("disabled");
			$("#ecity_submit").html("Pay");			
		}
		if(valid == true){
			
			$.ajax({
				type : "POST",
				headers : headers,
				contentType : "application/x-www-form-urlencoded",
				url : "/User/BillPayment/ProcessElectricity",
				data : {
					serviceProvider : provider,
					cycleNumber:number,
					accountNumber:account_number,
					amount : amountL
				},
				success : function(response) {
					$("#ecity_submit").removeClass("disabled");
					$("#ecity_submit").html("Pay");			

					console.log(response.response);
						var parsedResponse = JSON.parse(response.response);
						console.log(parsedResponse.details);
						if(parsedResponse.code == "F00"){
						    $("#error_ecity_bill").html(parsedResponse.details);
						}
						if(parsedResponse.code == "S00"){
							$("#account_balance").html(parsedResponse.balance);
							$("#success_ecity_bill").html(parsedResponse.details)
							$("#ecity_provider").val("");
							$("#ecity_cycle_number").val("");
							$("#ecity_account_number").val("");
							$("#ecity_amount").val("");

						}
				}
			});

			
		}
			
	});
	
	
	//Gas Submission
	$("#gas_submit").click(function() {
		$("#gas_submit").addClass("disabled");
		$("#gas_submit").html("Please Wait...");
		console.log("clicked");
		var valid = true;

		$("#error_gas_provider").html("");
		$("#error_gas_number").html("");
		$("#error_gas_amount").html("");

		var provider = $("#gas_provider").val();
		var account_number = $("#gas_account_number").val();
		var amountL = $("#gas_amount").val();
		console.log(amountL);
		if(provider == "" || provider == "#"){
			valid = false;
			$("#error_gas_provider").html("Select Gas Provider");
		}
		
		if(account_number.length <= 1){
			valid = false;
			$("#error_gas_account_number").html("Enter valid Account No.");
			
		}
		if(amountL < 10 && amountL > 10000){
			valid = false;
			$("#error_gas_amount").html("Amount must be greater than 10 and less than 10000");
		}
		if(eStatus == "Inactive"){
			valid = false;
			$("#error_gas_amount").html("email is not Verified")
		}
		
		if(valid == false){
			$("#gas_submit").removeClass("disabled");
			$("#gas_submit").html("Pay");
			
		}

		if(valid == true){
			
			$.ajax({
				type : "POST",
				headers : headers,
				contentType : "application/x-www-form-urlencoded",
				url : "/User/BillPayment/ProcessGas",
				data : {
					serviceProvider : provider,
					accountNumber:account_number,
					amount : amountL
				},
				success : function(response) {
					$("#gas_submit").removeClass("disabled");
					$("#gas_submit").html("Pay");

					console.log(response.response);
						var parsedResponse = JSON.parse(response.response);
						console.log(parsedResponse.details);
						if(parsedResponse.code == "F00"){
						    $("#error_gas_bill").html(parsedResponse.details);
						}
						if(parsedResponse.code == "S00"){
							$("#account_balance").html(parsedResponse.balance);
							$("#success_gas_bill").html(parsedResponse.details)
							$("#gas_provider").val("");
							$("#gas_account_number").val("");
							$("#gas_amount").val("");

						}
				}
			});

			
		}
			
	});


	//Insurance Submission
	$("#ins_submit").click(function() {
		$("#ins_submit").addClass("disabled");
		$("#ins_submit").html("Please Wait...");

		console.log("clicked");
		var valid = true;

		$("#error_ins_provider").html("");
		$("#error_ins_policy_number").html("");
		$("#error_ins_policy_date")
		$("#error_ins_amount").html("");

		var provider = $("#ins_provider").val();
		var policy_number = $("#ins_policy_number").val();
		var policy_date = $("#ins_policy_date").val();
		var amountL = $("#ins_amount").val();
		console.log(amountL);
		if(provider == "" || provider == "#"){
			valid = false;
			$("#error_ins_provider").html("Select Insurance Provider");
		}
		
		if(policy_number.length <= 1){
			valid = false;
			$("#error_ins_policy_number").html("Enter valid Policy No.");
			
		}
		if(policy_date == "" || policy_date == null){
			valid = false;
			$("#error_ins_policy_date").html("enter policy date");
		}
		if(amountL < 10 && amountL > 10000){
			valid = false;
			$("#error_ins_amount").html("Amount must be greater than 10 and less than 10000");
		}
		if(eStatus == "Inactive"){
			valid = false;
			$("#error_ins_amount").html("email is not Verified")
		}
		if(valid == false){
			$("#ins_submit").removeClass("disabled");
			$("#ins_submit").html("Pay");			
		}

		if(valid == true){
			
			$.ajax({
				type : "POST",
				headers : headers,
				contentType : "application/x-www-form-urlencoded",
				url : "/User/BillPayment/ProcessInsurance",
				data : {
					serviceProvider : provider,
					policyNumber:policy_number,
					policyDate:policy_date,
					amount : amountL
				},
				success : function(response) {
					$("#ins_submit").removeClass("disabled");
					$("#ins_submit").html("Pay");			
					console.log(response.response);
						var parsedResponse = JSON.parse(response.response);
						console.log(parsedResponse.details);
						if(parsedResponse.code == "F00"){
						    $("#error_ins_bill").html(parsedResponse.details);
						}
						if(parsedResponse.code == "S00"){
							$("#account_balance").html(parsedResponse.balance);
							$("#success_ins_bill").html(parsedResponse.details)
							$("#ins_provider").val("");
							$("#ins_policy_number").val("");
							$("#ins_policy_date").val("");
							$("#ins_amount").val("");

						}
				}
			});

			
		}
			
	});



});