$(document).ready(function() {
	
	$(".large").on("click", function(){
		$(".large").removeClass("selectLarge");
		$(this).addClass("selectLarge");
		
	});
	
	$("#btnSubmit").on("click", function(){
		let frm = $("#interestForm");
		let interestsList = $("#myInt").children();
		
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

let interestValues = []; // 중복 값 체크를 위한 배열
function addMyInterest(select) {
	let interestList = document.getElementById("myInt");
	let buttonCount = interestList.getElementsByTagName("button").length;
	let newInterest = select.cloneNode(true);
	$(newInterest).attr("onclick","");
	newInterest.classList.add("btnMyInterest");
	
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
	
	
	//console.log("select=",select);
	//console.log($(select).val());
	/*
  var interestList = document.getElementById("myInt");
  var buttonCount = interestList.getElementsByTagName("button").length;

  //console.log("interestList=", interestList);
  //console.log("interestValues=", interestValues);
  
  if (buttonCount >= 3) {
    return;
  }
  // 중복 값 체크
  if (interestValues.includes($(select).val())) {
    return;
  }
  
  var newInterest = document.createElement("button");
  newInterest.textContent = value;
  newInterest.classList.add("btnMyInterest");
  interestList.appendChild(newInterest);
  // 중복 값 배열에 추가
  interestValues.push(value);
  
  let newInterest = select.cloneNode(true);
   newInterest.classList.add("btnMyInterest");
  interestValues.push($(newInterest).val());
  interestList.appendChild(newInterest);
  
  $(newInterest).attr("onclick","");
  

  // 클릭 이벤트 핸들러 추가
  $(newInterest).on("click", function() {
	let target = "button[value='"+ $(newInterest).val() + "']";
	$(target).removeClass("selectSmall");
	
    // 중복 값 배열에서 제거
    var index = interestValues.indexOf($(newInterest).val());
    if (index > -1) {
      interestValues.splice(index, 1);
    }
    interestList.removeChild(newInterest);
  });

  var submitButton = document.querySelector("button[type='submit']");
  var myInt = document.getElementById("myInt");

  checkMyInt();

  myInt.addEventListener("click", function() {
    checkMyInt();
  });

  function checkMyInt() {
    if (myInt.childElementCount > 0) {
      submitButton.disabled = false;
    } else {
      submitButton.disabled = true;
    }
  }
  
  console.log("interestValues", interestValues);
  if (!interestValues.includes($(select).val())) {
    $(".small").on("click", function(){
			let target = "button[value='"+ $(select).val() + "']";
			$(target).addClass("selectSmall");
			console.log("target=", target);
			console.log("addClass(selectSmall)");
	});
  }
  */
   
} // end of addMyInterest

function clickSelect(){
	let myInterestList = document.getElementById("myInt");
}

function btnSubmitCheck(count){
	if(count == 0){
		$("#btnSubmit").prop("disabled", true);
	}else{
		$("#btnSubmit").prop("disabled", false);
	}
}