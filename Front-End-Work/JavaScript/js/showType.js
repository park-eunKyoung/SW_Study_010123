let h1El = document.getElementById('title');
h1El.innerText='타이틀1'
console.log(h1El.innerText)

console.log(typeof 'Hello js');
console.log(typeof "Hello js");
console.log(typeof 123);
console.log(typeof 12.3);
console.log(typeof true);
console.log(typeof undefined);
console.log(typeof null);
console.log(typeof {}); //객체 obiect
console.log(typeof []); //배열 array
console.log(typeof function fct () {}); //함수객체

function getType(data){           //8인덱스부터 뒤부터 1글자 미만까지
    return Object.prototype.toString.call(data).slice(8,-1); 
 }
console.log(getType('Hello js')); //String, string
console.log(getType(123));       //Number, number
console.log(getType(12.3));      //Number, number
console.log(getType(true));     //Boolean, boolean
console.log(getType(undefined)); //Undefined, undefined
console.log(getType(null));      //Null,  null
console.log(getType({}));        //Object,  object
console.log(getType([]));        //Array,   object
console.log(getType(function fct(){}));//Function, object 