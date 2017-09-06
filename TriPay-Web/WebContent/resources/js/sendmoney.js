/**
 * 
 */
$(document).ready(function(){
	var spinnerUrl = "Please Wait <img src='/resources/images/spinner.gif'  height='20' width='20'>"
	var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken = $("meta[name='_csrf']").attr("content");
	var hash_key="hash";
	var default_hash="123456";
	var headers = {};
	headers[hash_key] = default_hash;
	headers[csrfHeader] = csrfToken;
	$("#smm_submit").click(function(){
		var valid = true;
		$("#error_smm_mobile").html("");
		$("#error_smm_amount").html("");
		var mobile = $("#smm_mobile").val();
		var amount = $("#smm_amount").val();
		var message = $("#smm_message").val();
		var commission = calculateSendMoneyCommission(amount);
		var numericAmount = parseFloat(amount);
        if(mobile.length != 10){
			valid = false;
			$("#error_smm_mobile").html("Please enter valid mobile number");
		}
		if(amount < 10){
			valid = false;
			$("#error_smm_amount").html("Amount must be greater than or equal to 10");
		}
		if(message.length <= 0){
			valid = false;
			$("#error_smm_message").html("Please enter message");
		}
		if(valid == true){
			$("#o_mobile_number").html(mobile);
			$("#o_amount").html("<i class='fa fa-rupee'></i> "+amount);
			$("#o_commission").html("<i class='fa fa-rupee'></i> "+commission);
			$("#o_net_amount").html("<i class='fa fa-rupee'></i> "+(numericAmount+commission));
			$("#order_confirmation").modal('show');
			console.log("inside smm ajax");
            $("#confirm_order").click(function(){
                $("#order_confirmation").modal('hide');
                $("#smm_submit").attr("disabled","disabled");
            $("#smm_submit").html(spinnerUrl);
            $.ajax({
				type : "POST",
				headers : headers,
				contentType : "application/x-www-form-urlencoded",
				url : "/TriPay-web/User/SendMoney/Mobile",
				data : {
					mobileNumber:mobile,
					amount:amount,
					message:message
				},
				success : function(response) {
					$("#smm_submit").removeAttr("disabled","disabled");
					$("#smm_submit").html("Send");
						console.log(response);
						var parsedResponse = JSON.parse(response.response);
						console.log(parsedResponse.details);
						if(parsedResponse.code == "F00"){
						    $("#error_mobile_sm").html(parsedResponse.details);
						}
						if(parsedResponse.code == "S00"){
							$("#account_balance").html(parsedResponse.balance);
							$("#success_mobile_sm").html(parsedResponse.details);
							$("#smm_mobile").val("");
							$("#smm_amount").val("");
							$("#smm_message").val("")
						}
				}
			});
            });
		}
	});

	$("#bt_submit").click(function(){
        $("#error_bank_account_no").html("");
        $("#error_bank_account_holder").html("");
        $("#error_bank_ifsc_code").html("");
        $("#error_bank_amount").html("");
        var buttonValue = $("#bt_submit").html();


        var valid = true;
        var bankAccountNo  = $("#bank_account_no").val();
        var bankAccountHolder = $("#bank_account_holder").val();
        var bankIFSC = $("#bank_ifsc_code").val();
        var bankAmount = $("#bank_amount").val();
        var bankAddress = $("#bank_address").val();
        var userAccountNumber = $("#bt_account_number").val();
        var userContactNo = $("#bt_mobile_number").val();
        var userMail = $("#bt_email").val();
        if(bankAccountNo.length <= 0){
            $("#error_bank_account_no").html("Please enter account number");
            valid= false;
        }
        if(bankAccountHolder.length <= 0){
            $("#error_bank_account_holder").html("Please enter Name of Account Holder");
            valid = false;
        }
        if(bankIFSC.length <= 0){
            $("#error_bank_ifsc_code").html("Please enter ifsc code");
            valid = false;
        }
        if(bankAmount <= 10){
            $("#error_bank_amount").html("Amount must be greater than 10");
            valid = false;
        }
        if(valid == true){
            console.log("inside smb ajax")
            $.ajax({
                type : "POST",
                headers : headers,
                contentType : "application/x-www-form-urlencoded",
                url : "/TriPay-web/User/SendMoney/Bank",
                data : {
                    bankAccountNumber:bankAccountNo,
                    bankAccountHolder:bankAccountHolder,
                    bankIFSC:bankIFSC,
                    address:bankAddress,
                    mobileNumber:userContactNo,
                    amount:bankAmount,
                    email:userMail,
                    accountNumber:userAccountNumber
                },
                success : function(response) {
                    $("#smm_submit").removeClass("disabled");
                    $("#smm_submit").html("Send");
                    console.log(response);
                    var parsedResponse = JSON.parse(response.response);
                    console.log(parsedResponse.details);
                    if(parsedResponse.code == "F00"){
                        $("#error_bt").html(parsedResponse.details);
                    }
                    if(parsedResponse.code == "S00"){
                        $("#account_balance").html(parsedResponse.balance);
                        $("#success_mobile_sm").html(parsedResponse.details);
                        $("#smm_mobile").val("");
                        $("#smm_amount").val("");
                        $("#smm_message").val("")
                    }
                }
            });

        }

	});
	
});
