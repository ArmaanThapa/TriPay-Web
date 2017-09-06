/**
 * 
 */

$(document).ready(function() {
	var spinnerUrl = "Please Wait <img src='/resources/images/spinner.gif' height='20' width='20'>"
	var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken = $("meta[name='_csrf']").attr("content");
	var hash_key="hash";
	var default_hash="123456";
	var headers = {};
	headers[hash_key] = default_hash;
	headers[csrfHeader] = csrfToken;
	
	$("#pre_mobile").keypress(function(event) {
						if (event.which >= 48 && event.which <= 57) {
							var mobilePart = $("#pre_mobile").val();
							if (mobilePart >= 700000000 && mobilePart <= 999999999) {
								$.ajax({
											type : "POST",
											headers : headers,
											url : "/TriPay-web/Api/v1/User/Windows/en/Topup/GetOperatorAndCircleForMobile",
											contentType : "application/json",
											datatype : 'json',
											data:JSON.stringify({
												"mobileNumber":""+mobilePart+"",
											}),
											success : function(response) {
												var u = response;
												var string =  JSON.parse(u.response);
												$("#plan_table").html("");
												$("#pre_operator").val(string.operator.serviceCode);
												$("#pre_circle").val(string.circle.code);
												var circle = string.circle.name;
												var operatorCode = string.operator.code;
												var operator = string.operator.serviceCode;
												var circleCode =  string.circle.code;
												if(circleCode != null) {
													$("#plan_link").html("<a href='#' id='planzz'> Browse Plans </a>");
												}
										
												/// for browse plans
												
												$("#planzz").click(function(){
													
													$.ajax({
														type : "POST",
														headers : headers,														url : "/TriPay-web/Api/v1/User/Windows/en/Topup/GetPlans",
														contentType : "application/json",
														datatype : 'json',
														data:JSON.stringify({
															"circleCode":""+circleCode+"",
															"operatorCode":""+operatorCode+""
														}),
														success : function(response) {
															var u1 = response;
															var plansResponse =  JSON.parse(u1.response);
															var complete_plans = plansResponse.plans;
															var g3plans=[];
															var g2plans=[];
															var special=[];
															var regular=[];
															console.log(plansResponse);
															for(var i in complete_plans){
																console.log("planName ::"+complete_plans[i].planName);
																var plan_name = complete_plans[i].planName;
																if(plan_name.includes("Data Plans") || plan_name.includes("2G")){
																	g2plans.push(complete_plans[i]);
																}
																else if(plan_name.includes("3G")){
																	g3plans.push(complete_plans[i]);
																}
																else if(plan_name.includes("Topup")) {
																	regular.push(complete_plans[i]);
																}
																else {
																	special.push(complete_plans[i]);
																}
															}
															
															generateTable(g3plans,"3g_plans");
															generateTable(g2plans,"2g_plans");
															generateTable(regular,"regular_plans");
															generateTable(special,"special_plans");
															$("#browse_plans_modal").modal('show');
															
														}
														});
													
												});
												
											},
												
								      });
							
							     }
					   
						     }
					});

	
	/// auto select datacard topup
	
	$("#dc_mobile").keypress(function(event) {
		if (event.which >= 48 && event.which <= 57) {
			
			console.log($("#dc_mobile").val());
			var mobilePart = $("#dc_mobile").val();
			if (mobilePart >= 7000 && mobilePart <= 99999) {
				$.ajax({
							type : "POST",
							headers : headers,
							url : "/TriPay-web/Api/v1/User/Windows/en/Topup/GetOperatorAndCircleForMobile",
							contentType : "application/json",
							datatype : 'json',
							data:JSON.stringify({
								"mobileNumber":""+mobilePart+""
							}),
							success : function(response) {
								var u = response;
								var string =  JSON.parse(u.response);
								$("#plan_table").html("");
								$("#dc_operator").val(string.operator.serviceCode);
								$("#dc_circle").val(string.circle.code);
								var circle = string.circle.name;
								var operatorCode = string.operator.code;
								var operator = string.operator.serviceCode;
								var circleCode =  string.circle.code;
								if(circleCode != null) {
									$("#plan_link_dc").html("<a href='#' id='dc_planzz'> Browse Plans </a>");
								}

								/// for browse plans
								
								$("#dc_planzz").click(function(){
									$.ajax({
										type : "POST",
										headers : headers,
										url : "/TriPay-web/Api/v1/User/Windows/en/Topup/GetPlans",
										contentType : "application/json",
										datatype : 'json',
										data:JSON.stringify({
											"circleCode":""+circleCode+"",
											"operatorCode":""+operatorCode+""
										}),
										success : function(response) {
											var u1 = response;
											var plansResponse =  JSON.parse(u1.response);
											var complete_plans = plansResponse.plans;
											var g3plans=[];
											var g2plans=[];
											console.log(plansResponse);
											for(var i in complete_plans){
												console.log("planName ::"+complete_plans[i].planName);
												var plan_name = complete_plans[i].planName;
												if(plan_name.includes("Data Plans")){
													g2plans.push(complete_plans[i]);
												}
												else if(plan_name.includes("3G Plans")){
													g3plans.push(complete_plans[i]);
												}
											}

											generateDCTable(g3plans,"3g_dc_plans");
											generateDCTable(g2plans,"2g_dc_plans");
											$("#browse_dc_plans_modal").modal('show');

										}
										});				
									
								});
								
							},

						});
			}
		}
	});

	
	//form submission for prepaid topup
	$("#pre_submit").click(function(){
		var valid = true;
	    $("#error_pre_topup").html("");
	    $("#success_pre_topup").html("");
		$("#error_pre_mobile").html("");
		$("#error_pre_operator").html("");
		$("#error_pre_circle").html("");
		$("#error_pre_amount").html("");
		var mobileNumber = $("#pre_mobile").val();
		var preOperator = $("#pre_operator").val();
		var preCircle = $("#pre_circle").val();
		var preAmount = $("#pre_amount").val();
		var circleName = $('#pre_circle').find(":selected").text();
		var operatorName = $('#pre_operator').find(":selected").text()
		if(mobileNumber.length < 10){
			valid = false;
			$("#error_pre_mobile").html("enter valid mobile number");
		}
		if(preOperator == "#" || preOperator == ""){
			valid = false;
			$("#error_pre_operator").html("select your operator");
		}
		if(preCircle == "#" || preCircle == ""){
			valid = false;
			$("#error_pre_circle").html("select your circle");
		}
		if(preAmount < 10) {
			valid = false;
			$("#error_pre_amount").html("enter valid amount");
		}
		if(eStatus == "Inactive"){
			valid = false;
			$("#error_pre_amount").html("email is not Verified")
		}


		if(valid == true){

            $("#o_topup").html("Prepaid");
			$("#o_mobile").html(mobileNumber);
			$("#o_operator").html(operatorName);
			$("#o_amount").html("<i class='fa fa-rupee' aria-hidden='true'></i> "+preAmount);
			$("#o_area").html(circleName);
			$("#order_confirmation").modal('show');

			$("#confirm_order").click(function(){
                $("#pre_submit").html(spinnerUrl);
                $("#pre_submit").attr("disabled","disabled");

                $("#order_confirmation").modal('hide');
				console.log("proceed to call ajax");
			$.ajax({
				type : "POST",
				headers: headers,
				contentType : "application/x-www-form-urlencoded",
				url : "/TriPay-web/User/MobileTopup/ProcessPrepaid",
				data : {
					topupType : "Prepaid",
					serviceProvider : preOperator,
					mobileNo : mobileNumber,
					area : preCircle,
					amount : preAmount
				},
				success : function(response) {
				    $("#pre_submit").html("Pay");
				    $("#pre_submit").removeAttr("disabled");
						console.log(response);
						console.log(response.response);
						var parsedResponse = JSON.parse(response.response);
						console.log(parsedResponse.details);
						if(parsedResponse.code == "F00"){
						    $("#error_pre_topup").html(parsedResponse.details);
						}
						if(parsedResponse.code == "S00"){
							$("#account_balance").html(parsedResponse.balance);
						    $("#success_pre_topup").html(parsedResponse.details);
							$("#pre_mobile").val("");
							 $("#pre_operator").val("");
							 $("#pre_circle").val("");
							 $("#pre_amount").val("");
						}
				}
				});
			});
		}
	});
	
	
	//form submission for postpaid topup
	$("#post_submit").click(function(){
		var valid = true;
		$("#error_post_topup").html("");
	    $("#success_post_topup").html("");
		$("#error_post_mobile").html("");
		$("#error_post_operator").html("");
		$("#error_post_circle").html("");
		$("#error_post_amount").html("");
		var mobileNumber = $("#post_mobile").val();
		var postOperator = $("#post_operator").val();
		var postCircle = $("#post_circle").val();
		var postAmount = $("#post_amount").val();
        var circleName = $('#post_circle').find(":selected").text();
        var operatorName = $('#post_operator').find(":selected").text();

        if(mobileNumber.length < 10){
			valid = false;
			$("#error_post_mobile").html("enter valid mobile number");
		}
		if(postOperator == "#" || postOperator == ""){
			valid = false;
			$("#error_post_operator").html("select your operator");
		}
		if(postCircle == "#" || postCircle == ""){
			valid = false;
			$("#error_post_circle").html("select your circle");
		}
		if(postAmount < 10) {
			valid = false;
			$("#error_post_amount").html("enter valid amount");
		}
		if(eStatus == "Inactive"){
			valid = false;
			$("#error_post_amount").html("email is not Verified")
		}

		if(valid == true){
            $("#o_topup").html("Postpaid");
            $("#o_mobile").html(mobileNumber);
            $("#o_operator").html(operatorName);
            $("#o_amount").html(postAmount);
            $("#o_area").html(circleName);
            $("#order_confirmation").modal('show');
            $('#confirm_order').click(function(){
                $("#post_submit").html(spinnerUrl);
                $("#post_submit").attr("disabled","disabled");
                $("#order_confirmation").modal('hide');
			$.ajax({
				type : "POST",
				headers : headers,
				contentType : "application/x-www-form-urlencoded",
				url : "/TriPay-web/User/MobileTopup/ProcessPostpaid",
				data : {
					topupType : "Postpaid",
					serviceProvider : postOperator,
					mobileNo : mobileNumber,
					area : postCircle,
					amount : postAmount
				},
				success : function(response) {
				    $("#post_submit").html("Pay");
				    $("#post_submit").removeAttr("disabled");
					console.log(response.response);
						var parsedResponse = JSON.parse(response.response);
						console.log(parsedResponse.details);
						if(parsedResponse.code == "F00"){
						    $("#error_post_topup").html(parsedResponse.details);
						}
						if(parsedResponse.code == "S00"){
							$("#account_balance").html(parsedResponse.balance);
						    $("#success_post_topup").html(parsedResponse.details);							
							$("#post_mobile").val("");
							 $("#post_operator").val("");
							 $("#post_circle").val("");
							 $("#post_amount").val("");

						}
						
				}
			});
            });
		}
	
	});

	//form submission datacard topup
	
	$("#dc_submit").click(function(){
		var valid = true;
	    $("#error_dc_topup").html("");
	    $("#success_dc_topup").html("");
		$("#error_dc_mobile").html("");
		$("#error_dc_operator").html("");
		$("#error_dc_circle").html("");
		$("#error_dc_amount").html("");
		var mobileNumber = $("#dc_mobile").val();
		var dcOperator = $("#dc_operator").val();
		var dcCircle = $("#dc_circle").val();
		var dcAmount = $("#dc_amount").val();
        var circleName = $('#dc_circle').find(":selected").text();
        var operatorName = $('#dc_operator').find(":selected").text();

		if(mobileNumber.length < 10){
			valid = false;
			$("#error_dc_mobile").html("enter valid mobile number");
		}
		if(dcOperator == "#" || dcOperator == ""){
			valid = false;
			$("#error_dc_operator").html("select your operator");
		}
		if(dcCircle == "#" || dcCircle == ""){
			valid = false;
			$("#error_dc_circle").html("select your circle");
		}
		if(dcAmount < 10) {
			valid = false;
			$("#error_dc_amount").html("enter valid amount");
		}
		if(eStatus == "Inactive"){
			valid = false;
			$("#error_dc_amount").html("email is not Verified")
		}

		if(valid == true){
            $("#o_topup").html("Data Card");
            $("#o_mobile").html(mobileNumber);
            $("#o_operator").html(operatorName);
            $("#o_amount").html(dcAmount);
            $("#o_area").html(circleName);
            $("#order_confirmation").modal('show');
            $('#confirm_order').click(function() {
                $("#dc_submit").html(spinnerUrl);
                $("#dc_submit").attr("disabled","disabled");
                $("#order_confirmation").modal('hide');
                $.ajax({
                    type: "POST",
                    headers: headers,
                    contentType: "application/x-www-form-urlencoded",
                    url: "/TriPay-web/User/MobileTopup/ProcessDataCard",
                    data: {
                        topupType: "DataCard",
                        serviceProvider: dcOperator,
                        mobileNo: mobileNumber,
                        area: dcCircle,
                        amount: dcAmount
                    },
                    success: function (response) {
                        $("#dc_submit").html("Pay");
                        $("#dc_submit").removeAttr("disabled");
                        console.log(response.response);
                        var parsedResponse = JSON.parse(response.response);
                        console.log(parsedResponse.details);
                        if (parsedResponse.code == "F00") {
                            $("#error_dc_topup").html(parsedResponse.details);
                        }
                        if (parsedResponse.code == "S00") {
                            $("#account_balance").html(parsedResponse.balance);
                            $("#success_dc_topup").html(parsedResponse.details);
                            $("#dc_mobile").val("");
                            $("#dc_operator").val("");
                            $("#dc_circle").val("");
                            $("#dc_amount").val("");
                        }
                    }
                });
            });
		}
	
	});
	
});