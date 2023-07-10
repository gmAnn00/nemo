$(document).ready(function() {
	
	$(".large").on("click", function(){
		$(".large").removeClass("selectLarge");
		$(this).addClass("selectLarge");
		
	});
	
	$("#btnSubmit").on("click", function(){
		let frm = $("#interestForm");
		let interestsList = $("#myInt").children('button');
		
		let inputNum = document.createElement("input");
		inputNum.setAttribute("type", "hidden");
		inputNum.setAttribute("name", "inputNum");
		inputNum.setAttribute("value", interestsList.length);
		$(frm).append(inputNum);
		
		$(interestsList).each(function(index, key){
			let inputBig = document.createElement("input");
			inputBig.setAttribute("type", "hidden");
			inputBig.setAttribute("name", "main_name"+index);
			inputBig.setAttribute("value", key.getAttribute("data-class"));
			$(frm).append(inputBig);
			
			let inputSmall = document.createElement("input");
			inputSmall.setAttribute("type", "hidden");
			inputSmall.setAttribute("name", "sub_name"+index);
			inputSmall.setAttribute("value", key.value);
			$(frm).append(inputSmall);

		});
		
	
	frm.submit();
		
	});

});

//기존 선택 관심사 지우기
function removeInterest(select) {
	let interestList = document.getElementById("myInt");
	let interestValues = [];
	
	let target = "button[value='"+ $(select).val() + "']";
	$(target).removeClass("selectSmall");
    interestList.removeChild(select);
    
    //myInt 안에 있는 button(관심사)의 value 가져오기
	$($("#myInt").children()).each(function(index, key){
		console.log(key.value);
		interestValues.push(key.value);
	});
	
	btnSubmitCheck(interestValues.length);
}

function showSubcategories(category) {
  // 모든 소분류 숨기기
  var subCategories = document.getElementById("smallCate").children;
  for (var i = 0; i < subCategories.length; i++) {
    subCategories[i].style.display = "none";
  }
  // 선택한 대분류에 해당하는 소분류 보이기
  var selectedCategory = document.getElementById(category);
  if (selectedCategory) {
    selectedCategory.style.display = "block";
  }
}

//let interestValues = []; // 중복 값 체크를 위한 배열
function addMyInterest(select) {
	let interestList = document.getElementById("myInt");
	let interestValues = [];
	console.log("interestList", interestList);
	let buttonCount = interestList.getElementsByTagName("button").length;
	let newInterest = select.cloneNode(true);
	$(newInterest).attr("onclick","");
	newInterest.classList.add("btnMyInterest");
	
	//myInt 안에 있는 button(관심사)의 value 가져오기
	$($("#myInt").children()).each(function(index, key){
		console.log(key.value);
		interestValues.push(key.value);
	});
	
	
	if(!interestValues.includes($(newInterest).val()) && buttonCount < 3){
		interestValues.push($(newInterest).val());
		interestList.appendChild(newInterest);
		
		let target = "button[value='"+ $(newInterest).val() + "']";
		$(target).addClass("selectSmall");
		
		newInterest.addEventListener("click", function() {
			let target = "button[value='"+ $(newInterest).val() + "']";
			$(target).removeClass("selectSmall");
    		interestList.removeChild(newInterest);
    		
    		let findIndex = interestValues.indexOf($(newInterest).val());
    		if(findIndex > -1){
				interestValues.splice(findIndex, 1);
			}
			
			btnSubmitCheck(interestValues.length);
 		});

	}

	btnSubmitCheck(interestValues.length);

} // end of addMyInterest


function btnSubmitCheck(count){
	if(count == 0){
		$("#btnSubmit").prop("disabled", true);
	}else{
		$("#btnSubmit").prop("disabled", false);
	}
}