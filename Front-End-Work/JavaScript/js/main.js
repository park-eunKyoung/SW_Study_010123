//export defulat import방법
//import getType from './getType.js' //default export
//export import방법 : 객체구조분해와 비슷한 방식
//import {random, user as kim} from './getRandom.js'
//모듈명: R
 //default가 먼저와야 되고 { }밖에 선언  
 import getType,{print, user} from './getType.js' //export default,{export} 
 //import * as GT from './getType.js'  //all(export default,export)
 import random from './getRandom.js' //export default
 
 console.log(getType(10));
 console.log(getType([1,2]));
 console.log(getType({}));
 print();
 console.log(user);
 
 console.log('main.js', random(), random());
 
 export default function(){
     console.log('defFct call');
     
 }

// import getType,{print,user} from "./getType.js";


// import Random from "./getRandom.js";

// console.log(getType(10));
// console.log(getType([1,2]));
// console.log(({}));
// print();
// console.log(user);

// console.log('main.js',random(),random());




