$(document).ready(function(){
    var spinnerUrl = "Please Wait <img src='/resources/images/spinner.gif' height='20' width='20'>"
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
	//landline
    $("#landline_account_number").hide();
    $("#landline_std").hide();
    $("#landline_ca").hide();
    //electricity
    $("#cycle_number").hide();
    $("#billing_unit").hide();
    $("#processing_cycle").hide();
    $("#city_name").hide();

    // gas
    $("#bill_group").hide();

    $("#dth_provider").change(function(){
		var value = $("#dth_provider").val();
		var label ;
        if(value == "VATV"){
            label = "Customer ID";
        }else if(value == "VDTV"){
            label = "Viewing Card Number";
        }else if(value == "VRTV"){
            label = "Smart Card Number";
        }else if(value == "VSTV"){
            label = "Smart Card Number";
        }else if(value == "VTTV"){
            label = "Subscriber ID";
        }else if(value == "VVTV"){
            label = "Customer ID";
        }
        $("#dth_number_label").html(label);
	});
    $("#landline_provider").change(function(){
        var value = $("#landline_provider").val();
        if(value == "VATL" || value == "VRGL" || value == "VTCL" ){
            $("#landline_std").show();
            $("#landline_account_number").hide();
            $("#landline_ca").hide();
        }else if(value == "VBGL"){
            $("#landline_account_number").show();
            $("#landline_std").show();
            $("#landline_ca").hide();

        }else if(value == "VMDL"){
            $("#landline_ca").show();
            $("#landline_account_number").hide();
            $("#landline_std").hide();
        }
    });
	$("#landline_get_amount").click(function(){
        var value = $("#landline_provider").val();
        var landlineSTD = $("#landline_std").val();
        var landlineCA = $("#landline_ca").val();
        var landlineNumber = $("#landline_number").val();
        var landlineAccountNumber = $("#landline_account_number").val();
            var valid = true;
        if(value == "VATL" || value == "VRGL" || value == "VTCL" ){
                if(landlineSTD.length <=0){
                    $("#error_landline_std").html("Please enter STD Code");
                    valid = false;
                }
                if(landlineNumber.length <= 0 ){
                    $("#error_landline_number").html("Please enter Landline Number");
                    valid = false;
                }
            if(valid == true){
                $.ajax({
                    type : "POST",
                    contentType : "application/x-www-form-urlencoded",
                    url : "/InstantPay/GetAmount",
                    data : {
                        serviceProvider : value,
                        stdCode : landlineSTD,
                        landlineNumber : landlineNumber
                    },
                    success : function(response) {
                        console.log(response);
                        if (!isNaN(response))
                        {
                            $("#landline_amount").val(response);
                        }
                    }
                });

            }

        }else if(value == "VBGL"){

            if(landlineNumber.length <= 0){
                $("#error_landline_number").html("Please enter Landline Number");
                valid = false;
            }
            if(landlineSTD.length <= 0){
                $("#error_landline_number").html("Please enter STD Code");
                valid = false;
            }
            if(landlineAccountNumber.length <= 0){
                $("#error_landline_account_number").html("Please enter Account Number");
                valid = false;
            }
            if(valid == true){
                $.ajax({
                    type : "POST",
                    contentType : "application/x-www-form-urlencoded",
                    url : "/InstantPay/GetAmount",
                    data : {
                        serviceProvider : value,
                        stdCode : landlineSTD,
                        landlineNumber : landlineNumber,
                        customerID : landlineAccountNumber
                    },
                    success : function(response) {
                        console.log(response);
                        if (!isNaN(response))
                        {
                            $("#landline_amount").val(response);
                        }

                    }
                });

            }

        }else if(value == "VMDL"){
            if(landlineNumber.length <= 0){
                $("#error_landline_number").html("Please enter Landline Number");
                valid = false;
            }
            if(landlineCA.length <= 0){
                $("#error_landline_ca").html("Enter CA Number");
                valid = false;
            }
            if(valid == true){
                $.ajax({
                    type : "POST",
                    contentType : "application/x-www-form-urlencoded",
                    url : "/InstantPay/GetAmount",
                    data : {
                        serviceProvider : value,
                        customerID : landlineCA,
                        landlineNumber : landlineNumber
                    },
                    success : function(response) {
                        console.log(response);
                        if (!isNaN(response))
                        {
                            $("#landline_amount").val(response);
                        }

                    }
                });

            }

        }


    });
	$("#ecity_provider").change(function(){
        $("#cycle_number").hide();
        $("#billing_unit").hide();
        $("#processing_cycle").hide();
        $("#city_name").hide();
        var value = $("#ecity_provider").val();
        if(value == "VARE" || value == "VJRE" || value =="VDRE"){
            $("#ecity_account_label").html("K Number");
        }else if(value == "VAAE" || value == "VBBE" || value == "VBME" || value == "VBRE" || value == "VBYE" || value == "VCWE" || value == "VIPE" || value == "VMME" || value == "VNUE" || value == "VDOE" || value == "VMPE" || value == "VSTE"){
            $("#ecity_account_label").html("Consumer Number");
        }else if(value == "VCCE"){
            $("#ecity_account_label").html("BP Number");
        }else if(value == "VDHE"){
            $("#ecity_account_label").html("Account Number");
        }else if(value == "VDNE" || value == "VSAE"){
            $("#ecity_account_label").html("Service Connection Number");
        }else if(value == "VJUE"){
            $("#ecity_account_label").html("Business Partner Number");
        }else if(value == "VMDE"){
            $("#ecity_account_label").html("Consumer Number");
            $("#billing_unit").show();
            $("#processing_cycle").show();
        }else if(value == "VREE"){
            $("#ecity_account_label").html("Account Number");
            $("#cycle_number").show();
        }else if(value == "VTPE") {
			$("#ecity_account_label").html("Service Connection Number");
			$("#city_name").show();

		}
	});
//    private String serviceProvider;
//     private String customerID;
//     private String additionalInfo;
//     private String amount;
//     private String landlineNumber;
//     private String stdCode;
//     private String billingUnit;
//     private String processingCycle;
//     private String cycleNumber;
//     private String cityName;
//     private String billGroupNumber;
//     private String dateOfBirth;
$("#ecity_get_amount").click(function(){
    var valid = true;
    var processingCycle = $("#ecity_processing_cycle").val();
    var accountNumber = $("#ecity_account_number").val();
    var billingUnit = $("#ecity_billing_unit").val();
    var cityName = $("#ecity_city_name").val();
    var cycleNumber = $("#ecity_cycle_number").val();
    var value = $("#ecity_provider").val();
    if(value == "VARE" || value == "VJRE" || value =="VDRE" || value == "VAAE" || value == "VBBE" || value == "VBME" || value == "VBRE" || value == "VBYE" || value == "VCWE" || value == "VIPE" || value == "VMME" || value == "VNUE" || value == "VDOE" || value == "VMPE" || value == "VSTE" || value == "VCCE" || value == "VDHE" || value == "VDNE" || value == "VSAE" || value == "VJUE"){
        if(accountNumber.length <= 0){
            $("#error_ecity_account_number").html("Please enter Consumer ID/ K Number / BP Number");
            valid = false;
        }
        if(valid == true){
            $.ajax({
                type : "POST",
                contentType : "application/x-www-form-urlencoded",
                url : "/InstantPay/GetAmount",
                data : {
                    serviceProvider : value,
                    customerID : accountNumber,
                },
                success : function(response) {
                    console.log(response);
                    if (!isNaN(response))
                    {
                            $("#ecity_amount").val(response);
                    }
                }
            });

        }

    }else if(value == "VMDE"){
     
        if(accountNumber.length <= 0){
            $("#error_ecity_account_number").html("Please enter Consumer Number");
            valid = false;
        }
        if(billingUnit.length <=  0){
            $("#error_ecity_billing_unit").html("Please enter Billing Unit");
            valid = false;
        }
        if(processingCycle.length <= 0){
            $("#error_ecity_processing_cycle").html("Please enter Processing Cycle");
            valid = false;
        }
        if(valid == true){
            $.ajax({
                type : "POST",
                contentType : "application/x-www-form-urlencoded",
                url : "/InstantPay/GetAmount",
                data : {
                    serviceProvider : value,
                    customerID : accountNumber,
                    billingUnit:billingUnit,
                    processingCycle:processingCycle
                },
                success : function(response) {
                    console.log(response);
                }
            });

        }
    }else if(value == "VREE"){

        if(accountNumber.length <= 0){
            $("#error_ecity_account_number").html("Please enter Consumer Number");
            valid = false;
        }
        if(cycleNumber.length <= 0){
            valid = false;
            $("#error_ecity_cycle_number").html("Please enter valid Cycle Number");
        }

        if(valid == true){
            $.ajax({
                type : "POST",
                contentType : "application/x-www-form-urlencoded",
                url : "/InstantPay/GetAmount",
                data : {
                    serviceProvider : value,
                    customerID : accountNumber,
                    cycleNumber:cycleNumber

                },
                success : function(response) {
                    console.log(response);
                    if (!isNaN(response))
                    {
                        $("#ecity_amount").val(response);
                    }
                }
            });

        }
    }else if(value == "VTPE") {
        $("#ecity_account_label").html("Service Connection Number");
        $("#city_name").show();
        if(accountNumber.length <= 0){
            $("#error_ecity_account_number").html("Please enter Consumer Number");
            valid = false;
        }
        if(cityName.length <= 0){
            valid = false;
            $("#error_ecity_city_name").html("Please enter City Name");
        }
        if(valid == true){
            $.ajax({
                type : "POST",
                contentType : "application/x-www-form-urlencoded",
                url : "/InstantPay/GetAmount",
                data : {
                    serviceProvider : value,
                    customerID : accountNumber,
                    billingUnit:billingUnit,
                    processingCycle:processingCycle
                },
                success : function(response) {
                    console.log(response);
                    if (!isNaN(response))
                    {
                        $("#ecity_amount").val(response);
                    }

                }
            });

        }

    }

});
    $("#gas_provider").change(function() {
        var value = $("#gas_provider").val();
        $("#bill_group").hide();
        var label;
        if(value == "VADG"){
            label = "Customer ID";
        }else if(value == "VGJG"){
            label = "Customer Number";
        }else if(value == "VIPG"){
            label = "BP Number";
        }else if(value == "VMMG"){
            label = "Customer Account Number";
            $("#bill_group").show();
        }
        $("#gas_account_label").html(label);

    })


	//DTH bill payment
	$("#dth_submit").click(function() {
		console.log("DTH clicked");
		var valid = true;
		$("#error_dth_provider").html("");
		$("#error_dth_number").html("");
		$("#error_dth_amount").html("");
		var provider = $("#dth_provider").val();
		var number = $("#dth_number").val();
		var amount = $("#dth_amount").val();
        var serviceName = $('#dth_provider').find(":selected").text();
        var serviceAmount = 0;
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
		if(valid == true){
            $("#o_service").html(serviceName);
            $("#o_bill_amount").html("<i class='fa fa-rupee'></i> "+amount);
            $("#o_account_name").html("DTH No.");
            $("#o_account_number").html(number);
            $("#o_service_amount").html("<i class='fa fa-rupee'></i> "+serviceAmount);
            $("#o_net_amount").html("<i class='fa fa-rupee'></i> "+(serviceAmount+amount).replace(/^0+/, ''));
            $("#order_confirmation").modal('show');

            $("#confirm_order").click(function(){
            $("#order_confirmation").modal('hide');
            $("#dth_submit").html(spinnerUrl);
            $("#dth_submit").attr("disabled","disabled");

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
					$("#dth_submit").removeAttr("disabled");
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

            });
		}
			
	});

    
	//Landline Submission
	$("#landline_submit").click(function() {
		console.log("Landline Clicked");
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
        var serviceName = $('#landline_provider').find(":selected").text();
        var serviceAmount = 0;

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

		if(valid == true){
            $("#o_service").html(serviceName);
            $("#o_bill_amount").html("<i class='fa fa-rupee'></i> "+amountL);
            $("#o_account_number").html(std+"-"+number);
            $("#o_service_amount").html("<i class='fa fa-rupee'></i> "+serviceAmount);
            $("#o_net_amount").html("<i class='fa fa-rupee'></i> "+(serviceAmount+amountL).replace(/^0+/, ''));
            $("#order_confirmation").modal('show');

            $("#confirm_order").click(function() {
                $("#order_confirmation").modal('hide');


                $("#landline_submit").html(spinnerUrl);
                $("#landline_submit").attr("disabled","disabled");

                $.ajax({
                    type: "POST",
                    headers: headers,
                    contentType: "application/x-www-form-urlencoded",
                    url: "/User/BillPayment/ProcessLandline",
                    data: {
                        serviceProvider: provider,
                        stdCode: std,
                        landlineNumber: number,
                        accountNumber: account_number,
                        amount: amountL
                    },
                    success: function (response) {
                        $("#landline_submit").removeAttr("disabled");
                        $("#landline_submit").html("Pay");
                        var parsedResponse = JSON.parse(response.response);
                        if (parsedResponse.code == "F00") {
                            $("#error_landline_bill").html(parsedResponse.details);
                        }
                        if (parsedResponse.code == "S00") {
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

            });
		}
			
	});
	
	//Electricity Submission
	$("#ecity_submit").click(function() {

		var valid = true;

		$("#error_ecity_provider").html("");
		$("#error_ecity_cycle_number").html("");
		$("#error_ecity_amount").html("");
		$("#error_ecity_account_number").html("");

		var provider = $("#ecity_provider").val();
		var account_number = $("#ecity_account_number").val();
		var number = $("#ecity_cycle_number").val();
		var amountL = $("#ecity_amount").val();
        var serviceName = $('#ecity_provider').find(":selected").text();
        var serviceAmount = calculateCommissionByService(provider);

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
		if(valid == true){
            $("#o_service").html(serviceName);
            $("#o_bill_amount").html("<i class='fa fa-rupee'></i> "+amountL);
            $("#o_account_name").html("Consumer Number");
            $("#o_account_number").html(account_number);
            $("#o_service_amount").html("<i class='fa fa-rupee'></i> "+serviceAmount);
            $("#o_net_amount").html("<i class='fa fa-rupee'></i> "+(serviceAmount+parseFloat(amountL)));
            $("#order_confirmation").modal('show');
            
            $("#confirm_order").click(function(){
            
            $("#order_confirmation").modal('hide');
            $("#ecity_submit").attr("disabled","disabled");
            $("#ecity_submit").html(spinnerUrl);
			
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
					$("#ecity_submit").removeAttr("disabled");
					$("#ecity_submit").html("Pay");			
                        console.log(response);
						var parsedResponse = JSON.parse(response.response);
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

            });
		}
			
	});
	
	
	//Gas Submission
	$("#gas_submit").click(function() {
		var valid = true;

		$("#error_gas_provider").html("");
		$("#error_gas_number").html("");
		$("#error_gas_amount").html("");

		var provider = $("#gas_provider").val();
		var account_number = $("#gas_account_number").val();
		var amountL = $("#gas_amount").val();
        var serviceName = $('#gas_provider').find(":selected").text();
        var serviceAmount = 0;
        if(provider == "VADG" || provider == "VGJG" || provider == "VGSG"){
            serviceAmount = 2;
        }
        console.log("provider is ::"+provider+" ,serviceAmount ::"+serviceAmount);
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
		

		if(valid == true){
            $("#o_service").html(serviceName);
            $("#o_bill_amount").html("<i class='fa fa-rupee'></i> "+amountL);
            $("#o_account_name").html("Consumer No.");
            $("#o_account_number").html(account_number);
            $("#o_service_amount").html("<i class='fa fa-rupee'></i> "+serviceAmount);
            $("#o_net_amount").html("<i class='fa fa-rupee'></i> "+(serviceAmount+parseFloat(amountL)));
            $("#order_confirmation").modal('show');

            $("#confirm_order").click(function() {

                $("#order_confirmation").modal('hide');
                $("#gas_submit").attr("disabled","disabled");
                $("#gas_submit").html(spinnerUrl);

                $.ajax({
                    type: "POST",
                    headers: headers,
                    contentType: "application/x-www-form-urlencoded",
                    url: "/User/BillPayment/ProcessGas",
                    data: {
                        serviceProvider: provider,
                        accountNumber: account_number,
                        amount: amountL
                    },
                    success: function (response) {
                        $("#gas_submit").removeAttr("disabled");
                        $("#gas_submit").html("Pay");

                        var parsedResponse = JSON.parse(response.response);
                        if (parsedResponse.code == "F00") {
                            $("#error_gas_bill").html(parsedResponse.details);
                        }
                        if (parsedResponse.code == "S00") {
                            $("#account_balance").html(parsedResponse.balance);
                            $("#success_gas_bill").html(parsedResponse.details)
                            $("#gas_provider").val("");
                            $("#gas_account_number").val("");
                            $("#gas_amount").val("");

                        }
                    }
                });

            });			
		}
			
	});


    function calculateCommissionByService(service){
        if(service == "VTTE"){
            return 0;
        }else if(service == "VTPE"){
            return 0;
        }else if(service == "VNDE"){
            return 0;
        }else if(service == "VSTE"){
            return 0;
        }else if(service == "VSAE"){
            return 0;
        }else if(service == "VREE"){
            return 0;
        }else if(service == "VMPE"){
            return 0;
        }else if(service == "VDOE"){
            return 0;
        }else if(service == "VNUE"){
            return 0;
        }else if(service == "VMDE"){
            return 0;
        }else if(service == "VMEE"){
            return 0;
        }else if(service == "VDRE"){
            return 0;
        }else if(service == "VJUE"){
            return 0;
        }else if(service == "VJRE"){
            return 0;
        }else if(service == "VIPE"){
            return 0;
        }else if(service == "VDNE"){
            return 0;
        }else if(service == "VDHE"){
            return 0;
        }else if(service == "VCCE"){
            return 0;
        }else if(service == "VCWE"){
            return 0;
        }else if(service == "VBYE"){
            return 0;
        }else if(service == "VBRE"){
            return 0;
        }else if(service == "VBME"){
            return 0;
        }else if(service == "VBBE"){
            return 0;
        }else if(service == "VAAE"){
            return 0;
        }else if(service == "VARE"){
            return 0;
        }else if(service == "VMMG"){
            return 0;
        }else if(service == "VIPG"){
            return 0;
        }else if(service == "VGJG"){
            return 2;
        }else if(service == "VGSG"){
            return 2;
        }else if(service == "VADG"){
            console.log("inside vadg condition");
            return 2;
        }else if(service == "VTAI"){
            return 0;
        }else if(service == "VILI"){
            return 2;
        }else if(service == "VIPI"){
            return 0;
        }else if(service == "VBAI"){
            return 2;
        }


    }


	//Insurance Submission
	$("#ins_submit").click(function() {

		var valid = true;

		$("#error_ins_provider").html("");
		$("#error_ins_policy_number").html("");
		$("#error_ins_policy_date")
		$("#error_ins_amount").html("");

		var provider = $("#ins_provider").val();
		var policy_number = $("#ins_policy_number").val();
		var policy_date = $("#ins_policy_date").val();
		var amountL = $("#ins_amount").val();
        var serviceName = $('#ins_provider').find(":selected").text();
        var serviceAmount = 0;
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

		if(valid == true){
            $("#o_service").html(serviceName);
            $("#o_bill_amount").html("<i class='fa fa-rupee'></i> "+amountL);
            $("#o_account_name").html("Policy No.");
            $("#o_account_number").html(policy_number);
            $("#o_service_amount").html("<i class='fa fa-rupee'></i> "+serviceAmount);
            $("#o_net_amount").html("<i class='fa fa-rupee'></i> "+(serviceAmount+amountL).replace(/^0+/, ''));
            $("#order_confirmation").modal('show');

            $("#confirm_order").click(function() {

                $("#order_confirmation").modal('hide');

                $("#ins_submit").attr("disabled", "disabled");
                $("#ins_submit").html(spinnerUrl);

                $.ajax({
                    type: "POST",
                    headers: headers,
                    contentType: "application/x-www-form-urlencoded",
                    url: "/User/BillPayment/ProcessInsurance",
                    data: {
                        serviceProvider: provider,
                        policyNumber: policy_number,
                        policyDate: policy_date,
                        amount: amountL
                    },
                    success: function (response) {
                        $("#ins_submit").removeAttr("disabled");
                        $("#ins_submit").html("Pay");
                        var parsedResponse = JSON.parse(response.response);
                        if (parsedResponse.code == "F00") {
                            $("#error_ins_bill").html(parsedResponse.details);
                        }
                        if (parsedResponse.code == "S00") {
                            $("#account_balance").html(parsedResponse.balance);
                            $("#success_ins_bill").html(parsedResponse.details)
                            $("#ins_provider").val("");
                            $("#ins_policy_number").val("");
                            $("#ins_policy_date").val("");
                            $("#ins_amount").val("");
                        }
                    }
                });

            });
		}
			
	});



});