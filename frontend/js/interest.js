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

var interestValues = []; // 중복 값 체크를 위한 배열
function addMyInterest(value) {
  var interestList = document.getElementById("myInt");
  var buttonCount = interestList.getElementsByTagName("button").length;
  if (buttonCount >= 3) {
    return;
  }
  // 중복 값 체크
  if (interestValues.includes(value)) {
    return;
  }
  var newInterest = document.createElement("button");
  newInterest.textContent = value;
  newInterest.classList.add("btnMyInterest");
  interestList.appendChild(newInterest);
  // 중복 값 배열에 추가
  interestValues.push(value);

  // 클릭 이벤트 핸들러 추가
  newInterest.addEventListener("click", function() {
    // 중복 값 배열에서 제거
    var index = interestValues.indexOf(value);
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
}
