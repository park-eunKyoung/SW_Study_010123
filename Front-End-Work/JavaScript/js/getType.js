// let content = document.querySelector('h1').innerText
// console.log(content);

//모든 js파일은  모듈이다.
//export default 통로: 이름을 생략할 수 있는 외부통로(1개만 존재할수 있음)
//export 통로: 이름이 있는 외부통로(여러개 존재할 수 있음)

export default function(data){           //8인덱스부터 뒤부터 1글자 미만까지
    return Object.prototype.toString.call(data).slice(8,-1); 
 }
 export function print(){
    console.log('print',document.querySelector('h1').textContent)   
 }
 export const user={
    name:'kim',
    age: 10
 }