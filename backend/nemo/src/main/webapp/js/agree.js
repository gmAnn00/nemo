window.onload = function() {
    const form = document.querySelector(".terms");
    const allAgree = document.querySelector("#allAgree");
    const checkboxes = document.querySelectorAll("input[type='checkbox']");
    const button = document.querySelector("button[type='submit']");

/*
    form.addEventListener("submit", function(event) {
        event.preventDefault();
        let isAgreed = false;
        checkboxes.forEach(function(checkbox) {
        if (checkbox.checked) {
            isAgreed = true;
        }
        if (isAgreed) {
            button.removeAttribute("disabled");
            window.location.href = "";
        }
        });
    });
*/
    allAgree.addEventListener("change", function(event) {
    checkboxes.forEach(function(checkbox) {
        checkbox.checked = event.target.checked;
    });
    if (event.target.checked) {
        button.removeAttribute("disabled");
    } else {
        button.setAttribute("disabled", true);
    }
    });

    checkboxes.forEach(function(checkbox) {
        checkbox.addEventListener("change", function(event) {
            let isAllAgreed = true;
            checkboxes.forEach(function(checkbox) {
                if (!checkbox.checked) {
                isAllAgreed = false;
                }
            });
            if (isAllAgreed) {
                button.removeAttribute("disabled");
            }else if($('#term1').is(':checked') && $('#term2').is(':checked') && $('#term3').is(':checked') && $('#term4').is(':checked')) {
                allAgree.checked = true;
                button.removeAttribute("disabled");
            }else {
                allAgree.checked = false;
                button.setAttribute("disabled", true);
            }
        });
    });
}


