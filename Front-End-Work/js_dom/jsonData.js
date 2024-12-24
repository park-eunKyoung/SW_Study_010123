let jsonText = document.getElementById('jText');
//입력버튼
let writeBtn = document.getElementById('jWrite');
//불러올 사원번호
let employeeNum = document.getElementById('eNum');
//읽기(추출) 버튼
let readBtn = document.getElementById('jRead');
//결과값 출력 요소
let nameResult = document.getElementById('result');

let jsObj; //전역변수
//텍스트 에어리어에 값을 제어슨 객체로 만들기
writeBtn.addEventListener('click', function () {
  //console.log(jsonText.value);
  //json 을 js 객체로 변환
  JSON.parse(jsonText.value);
  console.log(jsObj);
});
readBtn.addEventListener('click', function () {
  let idx = employeeNum.value - 1;
  console.log('idx', idx);

  (nameResult.textContent = '이름1 : '),
    jsObj.employee[idx].firstName +
      ', 이름2: ' +
      jsObj.employee[idx].lastName +
      '<br>';

  nameResult.innerHTML = str;
  
});
