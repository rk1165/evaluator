MOST_FREQUENT_ENDPOINT = "http://localhost:8080/api/v1/frequent"
CALCULATE_EXPRESSION_ENDPOINT = "http://localhost:8080/api/v1/expression"

const s1 = document.getElementById("calculate");
s1.addEventListener("click", e => {
    const userId = document.getElementById("userid").value;
    const expression = document.getElementById("expression").value;
    const body = JSON.stringify({ userId: userId, expression: expression });

    const xhr = new XMLHttpRequest();
    xhr.open("POST", CALCULATE_EXPRESSION_ENDPOINT);
    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            console.log(xhr.responseText);
            const response = JSON.parse(xhr.responseText);
            alert("The calculated value is " + response['response']);
            document.getElementById("form1").reset();
        } else if (xhr.status !== 200) {
            const response = JSON.parse(xhr.responseText);
            alert("Error : " + response['message']);
            document.getElementById("form1").reset();
        }
    }
    xhr.send(body);

});

const s2 = document.getElementById("frequent");
s2.addEventListener("click", e => {

    const userId = document.getElementById("freqid").value;
    var params = "userId=" + userId;

    const xhr = new XMLHttpRequest();
    xhr.open("GET", MOST_FREQUENT_ENDPOINT + "?" + params);
    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            console.log(xhr.responseText);
            const response = JSON.parse(xhr.responseText);
            alert("The most used operator is " + response['response']);
            document.getElementById("form2").reset();
        } else if (xhr.status !== 200) {
            console.log('Error : ' + xhr.responseText);
            const response = JSON.parse(xhr.responseText);
            alert("Error : " + response['message']);
            document.getElementById("form2").reset();
        }
    }
    xhr.send();
});