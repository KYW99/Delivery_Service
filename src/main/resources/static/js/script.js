// signup.js
document.getElementById('signupForm').addEventListener('submit', function(event) {
    event.preventDefault(); // 기본 폼 제출 동작을 막음

    // 폼 데이터 가져오기
    const formData = {
        username: document.getElementById('username').value,
        password: document.getElementById('password').value,
        confirmPassword: document.getElementById('confirmPassword').value,
        email: document.getElementById('email').value
    };

    // 비밀번호 확인
    if (formData.password !== formData.confirmPassword) {
        alert('비밀번호가 일치하지 않습니다.');
        return;
    }

    // 서버로 회원가입 데이터 전송
    fetch('/user/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    })
        .then(response => response.json())  // 응답을 JSON으로 파싱
        .then(data => {
            if (data.success) {
                alert(data.message);  // 서버에서 받은 메시지를 출력
                // 회원가입 후 리디렉션 또는 추가 작업
            } else {
                alert('회원가입 실패: ' + data.message);
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('서버와의 연결에 실패했습니다.');
        });
});
