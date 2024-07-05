document.addEventListener('DOMContentLoaded', function() {
    let form = document.querySelector('#login-form');

    form.addEventListener('submit', function(event) {
        if (!checkId() || !checkPw() || !comparePw() || !checkEmail() || !checkName() || !checkTel()) {
            event.preventDefault();
        }
    });

    let id = document.querySelector("#id");
    let pw1 = document.querySelector("#pw1");
    let pw2 = document.querySelector("#pw2");
    let email = document.querySelector("#email");
    let u_name = document.querySelector("#name");
    let tel = document.querySelector("#tel");

    id.onchange = checkId;
    pw1.onchange = checkPw;
    pw2.onchange = comparePw;
    email.onchange = checkEmail;
    u_name.onchange = checkName;
    tel.onchange = checkTel;

    let idPattern = /^[A-Za-z]{1}[A-Za-z0-9]{3,14}$/;

    function checkId() {
        if (id.value.length < 4 || id.value.length > 15) {
            alert("아이디는 4~15자리를 입력하세요.");
            id.select();
            return false;
        }
        return true;
    }

    let pwPattern = /^(?=.*[a-zA-Z])(?=.*[!@#$%^&*+-=_])(?=.*[0-9]).{8,16}$/;

    function checkPw() {
        if (pw1.value.length < 8) {
            alert("비밀번호는 8자 이상 입력하세요.");
            pw1.value = "";
            pw1.focus();
            return false;
        }
        return true;
    }

    function comparePw() {
        if (pw1.value != pw2.value) {
            alert("비밀번호가 일치하지 않습니다.");
            pw2.value = "";
            pw2.focus();
            return false;
        }
        return true;
    }

    let namePattern = /^[가-힣]+$/;

    function checkName() {
        if (!namePattern.test(u_name.value)) {
            alert("이름은 한글만 입력가능합니다.");
            u_name.select();
            return false;
        }
        return true;
    }

    function checkTel() {
        if (telValue.length !== 13 || !/^\d{3}-\d{4}-\d{4}$/.test(telValue)) {
            alert("잘못된 전화번호 형식입니다. ex) 010-1234-5678");
            document.getElementById("tel").select();
            return false;
        }
        return true;
    }
});
